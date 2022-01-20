package com.example.mylibrary;


import com.example.mylibrary.loader.StateLoader;
import com.example.mylibrary.loader.StateRepository;
import com.example.mylibrary.manager.StateChanger;
import com.example.mylibrary.manager.StateEventListener;
import com.example.mylibrary.manager.StateManager;
import com.example.mylibrary.state.IState;
import com.example.mylibrary.state.StateProperty;
import ohos.agp.components.AttrSet;
import ohos.agp.components.Component;
import ohos.agp.components.StackLayout;
import ohos.app.Context;

/**
 * TODO If you add child views dynamically, there is no good way to prohibit it
 * It is recommended not to use the addView() method，Add child view，Try to use addState to register
 */
public class StateLayout extends StackLayout implements StateChanger, StateLoader {

    private StateManager stateManager;


    public StateLayout(Context context, StateRepository repository) {
        super(context);
        init(context, repository);

    }

    public StateLayout(Context context, AttrSet attrs) {
        super(context, attrs);
        init(context, new StateRepository(context));
    }

    public StateLayout(Context context, AttrSet attrs, int defStyleAttr) {
        super(context, attrs);
        init(context, new StateRepository(context));
    }

    public StateLayout(Context context, AttrSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs);
        init(context, new StateRepository(context));
    }//only 3 constructors in ohos(statelayout)--4 constructors in android(framelayout)

    private void init(Context context, StateRepository repository) {
        stateManager = StateManager.newInstance(context, repository, this);
    }

    @Override
    public void postLayout() {
        super.postLayout();
        if (getChildCount() > 1) {
            try {
                throw new IllegalStateException("StateLayout can have only one direct child");

            } catch (IllegalStateException e) {

            }
        } else if (getChildCount() == 1) {
            loadCoreView(getComponentAt(0));
        }
    }

    private void loadCoreView(Component child) {
        if (child != null) {
            stateManager.setContentView(child);
        }
    }

    public void removeAllState() {
        stateManager.removeAllState();
    }

    @Override
    public boolean showState(String state) {
        return stateManager.showState(state);
    }

    @Override
    public boolean showState(StateProperty state) {
        return stateManager.showState(state);
    }

    @Override
    public void setStateEventListener(StateEventListener listener) {
        stateManager.setStateEventListener(listener);
    }

    @Override
    public String getStates() {
        return stateManager.getStates();
    }

    @Override
    public boolean addState(IState changger) {
        return stateManager.addState(changger);
    }

    @Override
    public boolean removeState(String state) {
        return stateManager.removeState(state);
    }

    @Override
    public Component getStateView(String state) {
        return stateManager.getStateView(state);
    }

    public StateManager getStateManager() {
        return stateManager;
    }

}