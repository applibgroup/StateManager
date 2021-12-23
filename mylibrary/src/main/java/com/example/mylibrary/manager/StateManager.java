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
 * StateManager  管理各个状态的切换
 */
public class StateManager implements StateViewManager, StateLoader, StateChanger {
    protected Context context;

    /**
     * 整体View模板
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
            //将按钮事件添加到监听中
            stateChanger.setStateEventListener(listener);
        }
    }


    /**
     * 根据viewState，Item自己去查找
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
            LogUtil.warn("StateManager", "没有注册对应的" + state + "State，需要调用addStater()进行注册");
            //HiLog.warn("StateManager", "没有注册对应的" + state + "State，需要调用addStater()进行注册");
            return false;
        }
        iState.setStateEventListener(listener);
        boolean isSuccess = StateViewHelper.showStater(context, overallView, iState);
        if (!isSuccess) {
            //如果state的View为null等则切换状态不成功
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
     * 根据viewState，Item自己去查找
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
        //注册核心view的State
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

        //注册核心view的State
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
     * 注册一个状态改变器，如果有重复的状态改变器，则不添加
     *
     * @param stater
     */
    @Override
    public boolean addState(IState stater) {
        if (stater != null) {
            stater.setStateEventListener(listener);
            //如果存在替换流程，需要将之前的StateView移除
            if(!TextTool.isNullOrEmpty(stater.getState())){
                removeState(stater.getState());
            }
            return stateRepository.addState(stater);
        }

        return false;
    }


    /**
     * 移除对应的状态加载器
     */
    @Override
    public boolean removeState(String state) {
        if (stateRepository == null) {
            return false;
        }
        Component stateView = getStateView(state);
        //移除对应状态的同时，也需要移除对应的View
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
     * destory或者destoryView的时候，调用
     */
    public void onDestoryView() {
        overallView = null;
        currentState = null;
        if (stateRepository != null) {
            stateRepository.clear();
        }
    }
}
