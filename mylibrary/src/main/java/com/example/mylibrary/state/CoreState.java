package com.example.mylibrary.state;


import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;

/**
 * 核心UI界面
 */
public class CoreState extends BaseState {

    public static final String STATE = "CoreState";

    private String state = STATE;
    public CoreState(Component coreView) {
        stateView = coreView;
    }

    /**
     * 支持创建多个CoreState时，需要指定不同的State，达到分离业务逻辑
     *
     * @param coreView
     * @param state
     */
    public CoreState(Component coreView, String state) {
        stateView = coreView;
        this.state = state;
    }


    /**
     * 如果使用这个构造，需要重写{@link BaseState#getLayoutId()}方法
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
