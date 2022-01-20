package com.example.myapplication;

import com.example.mylibrary.loader.StateRepository;
import com.example.mylibrary.manager.StateChanger;
import com.example.mylibrary.manager.StateEventListener;
import com.example.mylibrary.manager.StateManager;
import com.example.mylibrary.state.CoreState;
import com.example.mylibrary.state.StateProperty;
import ohos.aafwk.ability.fraction.Fraction;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.LayoutScatter;


public abstract class StateManagerFragment extends Fraction implements StateChanger {

    private StateManager stateManager;

    protected Component layoutView;

    public abstract int getLayoutId();

    @Override
    public void onStart(Intent savedInstanceState) {
        super.onStart(savedInstanceState);
        stateManager = StateManager.newInstance(getFractionAbility(), new StateRepository(getFractionAbility()));

    }

    /**
     * 这个方法不允许重写，如果有初始化工作，可以在{@link StateManagerFragment#initViewContent(Component, Intent)}方法中实现
     *
     */
    @Override
    public final Component onComponentAttached(LayoutScatter scatter, ComponentContainer container, Intent savedInstanceState) {

        layoutView = stateManager.setContentView(getLayoutId());
        initViewContent(stateManager.getStateView(CoreState.STATE), savedInstanceState);
        return layoutView;
    }

    /**
     * 初始化界面
     *  @param layoutView
     * @param savedInstanceState
     */
    public abstract void initViewContent(Component layoutView, Intent savedInstanceState);


    @Override
    public void setStateEventListener(StateEventListener listener) {
        stateManager.setStateEventListener(listener);
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
    public String getStates() {
        return stateManager.getStates();
    }

    /**
     * 获取全局View
     */
    public Component getOverallView() {
        return stateManager.getOverallView();
    }

    public StateManager getStateManager() {
        return stateManager;
    }

    public void onStop() {
        super.onStop();
        stateManager.onDestoryView();
    }
}
