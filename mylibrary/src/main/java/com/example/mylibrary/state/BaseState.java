package com.example.mylibrary.state;


import com.example.mylibrary.manager.StateEventListener;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.LayoutScatter;
import ohos.app.Context;

/**
 * Basic state machine, load some interfaces
 * All state machines must implement this class
 */
public abstract class BaseState<T extends StateProperty> implements IState<T> {

    public static final String STATE = "NONE";

    protected Context context;

    /**
     * Status View
     */
    protected Component stateView;

    /**
     * Status attribute: dynamic copywriting
     */
    protected T stateProperty;

    /**
     * Button monitoring in view state
     */
    protected StateEventListener stateEventListener;
    protected Component mOverallView;

    public BaseState() {
    }

    @Override
    public void onStateCreate(Context context, ComponentContainer parent) {
        this.context = context;
        this.mOverallView = parent;
        stateView = LayoutScatter.getInstance(context).parse(getLayoutId(), parent, false);
        onViewCreated(stateView);

    }

    @Override
    public Component getView() {
        return stateView;
    }

    protected abstract int getLayoutId();


    @Override
    public void setViewProperty(T stateProperty) {
        this.stateProperty = stateProperty;
    }

    /**
     * Get the current state view
     *
     * @param stateView
     * @return
     */
    protected abstract void onViewCreated(Component stateView);

    @Override
    public void onStateResume() {
    }

    @Override
    public void onStatePause() {

    }

    public void setStateEventListener(StateEventListener listener) {
        this.stateEventListener = listener;
    }


    public Context getContext() {
        return context;
    }
}
