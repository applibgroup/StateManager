package com.example.mylibrary.manager;



import com.example.mylibrary.loader.StateLoader;
import com.example.mylibrary.loader.StateRepository;
import com.example.mylibrary.state.BaseState;
import com.example.mylibrary.state.CoreState;
import com.example.mylibrary.state.IState;
import com.example.mylibrary.state.StateProperty;
import com.example.mylibrary.wrapper.LogUtil;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.LayoutScatter;
import ohos.agp.components.StackLayout;
import ohos.agp.utils.TextTool;
import ohos.app.Context;

import java.util.Iterator;

/**
 * StateManager  Manage the switching of each state
 */
public class StateManager implements StateViewManager, StateLoader, StateChanger {
    protected Context context;

    /**
     * Overall View template
     */
    private ComponentContainer overallView;

    private StateRepository stateRepository;

    private IState currentState;

    private StateEventListener listener;

    protected StateManager(Context context) {
        this.context = context;

    }

    public static StateManager newInstance(Context context, StateRepository repository) {
        StateManager stateManager = new StateManager(context);
        stateManager.stateRepository = repository;
        return stateManager;
    }

    public static StateManager newInstance(Context context, StateRepository repository,ComponentContainer overallView) {
        StateManager stateManager = new StateManager(context);
        stateManager.stateRepository = repository;
        stateManager.overallView = overallView;
        return stateManager;
    }

    public void setStateEventListener(StateEventListener listener) {
        this.listener = listener;

        Iterator<IState> iterator = stateRepository.getStateMap().values().iterator();
        IState stateChanger = null;
        while (iterator.hasNext()) {
            stateChanger = iterator.next();
            //Add the button event to the listener
            stateChanger.setStateEventListener(listener);
        }
    }


    /**
     * According to the viewState, the Item will find it by itself
     *
     * @param state
     */
    public boolean showState(String state) {
        if (overallView == null || TextTool.isNullOrEmpty(state)) {
            return false;
        }

        IState iState = stateRepository.get(state);
        if (iState == null) {
           // HiLogLabel hiLogLabel=null;
            LogUtil.warn("StateManager", "No corresponding registration" + state + "State, need to call addStater() to register");
             return false;
        }
        iState.setStateEventListener(listener);
        boolean isSuccess = StateViewHelper.showStater(context, overallView, iState);
        if (!isSuccess) {
            //If the state's View is null, etc., the switch state is unsuccessful
            return false;
        }
        if (currentState != null) {
            if (currentState.getState().equals(state)) {
                return true;
            }
            StateViewHelper.hideStater(currentState);
        }

        currentState = iState;
        return true;
    }


    /**
     * According to the viewState, the Item will find it by itself
     *
     * @param state
     */
    public boolean showState(StateProperty state) {
        boolean result = showState(state.getState());
        if (result) {
            IState baseStater = stateRepository.get(state.getState());
            baseStater.setViewProperty(state);
        }
        return result;
    }

    @Override
    public ComponentContainer getOverallView() {
        return overallView;
    }

    public void setOverallView(ComponentContainer parent) {
        overallView = parent;
    }


    @Override
    public Component setContentView(int layoutId) {

        if (overallView == null) {
            overallView = new StackLayout(context);
            overallView.setLayoutConfig(
                    new StackLayout.LayoutConfig(ComponentContainer.LayoutConfig.MATCH_PARENT,ComponentContainer.LayoutConfig.MATCH_PARENT));
        }

        Component view = LayoutScatter.getInstance(context).parse(layoutId, overallView, false);
        //Register the State of the core view
        addState(new CoreState(view));
        showState(CoreState.STATE);

        return overallView;
    }

    @Override
    public Component setContentView(Component view) {
        if (overallView == null) {

            overallView = new StackLayout(context);
            overallView.setLayoutConfig(
                    new StackLayout.LayoutConfig(ComponentContainer.LayoutConfig.MATCH_PARENT, ComponentContainer.LayoutConfig.MATCH_PARENT));
        }

        //Register the State of the core view
        addState(new CoreState(view));
        showState(CoreState.STATE);
        return overallView;
    }

    @Override
    public Component getContentView() {
        return getStateView(CoreState.STATE);
    }


    public String getStates() {
        return currentState == null ? BaseState.STATE : currentState.getState();
    }


    /**
     * Register a state changer, if there are duplicate state changers, do not add
     *
     * @param stater
     */
    @Override
    public boolean addState(IState stater) {
        if (stater != null) {
            stater.setStateEventListener(listener);
            //If there is a replacement process, the previous StateView needs to be removed
            if(!TextTool.isNullOrEmpty(stater.getState())){
                removeState(stater.getState());
            }
            return stateRepository.addState(stater);
        }

        return false;
    }


    /**
     * Remove the corresponding state loader
     */
    @Override
    public boolean removeState(String state) {
        if (stateRepository == null) {
            return false;
        }
        Component stateView = getStateView(state);
        //While removing the corresponding state, you also need to remove the corresponding View
        if (stateView != null) {
            overallView.removeComponent(stateView);
        }
        return stateRepository.removeState(state);
    }


    public void removeAllState() {
        if (stateRepository != null) {
            stateRepository.clear();
        }
        overallView.removeAllComponents();
    }

    public Component getStateView(String state) {

        IState stateChanger = stateRepository.get(state);
        if (null != stateChanger) {
            return stateChanger.getView();
        }

        return null;
    }

    public IState getState(String state) {
        return stateRepository.get(state);
    }


    public IState getCurrentState() {
        return currentState;
    }

    public StateRepository getStateRepository() {

        return stateRepository;
    }

    public void setStateRepository(StateRepository stateRepository) {

        if (stateRepository == null) {
            return;
        }

        stateRepository.addState(this.stateRepository.get(CoreState.STATE));

        this.stateRepository = stateRepository;
    }

    /**
     * When destroy or destroyView, call
     */
    public void onDestoryView() {
        overallView = null;
        currentState = null;
        if (stateRepository != null) {
            stateRepository.clear();
        }
    }
}
