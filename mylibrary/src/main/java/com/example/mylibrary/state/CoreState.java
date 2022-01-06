package com.example.mylibrary.state;


import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;

/**
 * Core UI interface
 */
public class CoreState extends BaseState {

    public static final String STATE = "CoreState";

    private String state = STATE;
    public CoreState(Component coreView) {
        stateView = coreView;
    }

    /**
     * When supporting the creation of multiple CoreStates, different States need to be specified to achieve separation of business logic
     *
     * @param coreView
     * @param state
     */
    public CoreState(Component coreView, String state) {
        stateView = coreView;
        this.state = state;
    }


    /**
     * If you use this structure, you need to rewrite{@link BaseState#getLayoutId()}method
     * @param view
     */
    protected CoreState(ComponentContainer view) { }


    @Override
    protected int getLayoutId() {

        try {
            throw new IllegalStateException(this + "没有返回布局文件Id");
        } catch (IllegalStateException e) {
            return -1;
        }

    }

    @Override
    protected void onViewCreated(Component stateView) {
        // it is used to create view in listcontainer .
    }


    @Override
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
