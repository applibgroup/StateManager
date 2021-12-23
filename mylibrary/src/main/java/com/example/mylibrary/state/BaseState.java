package com.example.mylibrary.state;


import com.example.mylibrary.manager.StateEventListener;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.LayoutScatter;
import ohos.app.Context;

/**
 * 基础状态器，加载一些界面
 * 所有的状态机都要实现这个类
 */
public abstract class BaseState<T extends StateProperty> implements IState<T> {

    public static final String STATE = "NONE";

    protected Context context;

    /**
     * 状态View
     */
    protected Component stateView;

    /**
     * 状态属性：动态文案
     */
    protected T stateProperty;

    /**
     * 视图状态里面的按钮监听
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
     * 获取当前状态view
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
