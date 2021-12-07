package com.example.mylibrary.state;

import com.example.mylibrary.manager.StateEventListener;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.app.Context;

/**
 * State生命周期管理
 */
public interface IState<T extends StateProperty> {

    String EMPTY = "empty_state";
    String EXCEPTION = "exception_state";
    String LOADING = "loading_state";
    String NETERROR = "net_error_state";
    String ERROR = "error_state";

    /**
     * StateView创建后，可以做一些操作
     */
    void onStateCreate(Context context, ComponentContainer parent);


    /**
     * StateView显示后，可以做一些操作
     */
    void onStateResume();

    /**
     * StateView隐藏后，可以做一些操作
     */
    void onStatePause();


    /**
     * 获取当前状态
     *
     * @return
     */
    String getState();

    /**
     * 设置当前状态下的一些按钮操作回调
     *
     * @param listener
     */
    void setStateEventListener(StateEventListener listener);


    /**
     * 获取状态机的View
     *
     * @return
     */
    Component getView();


    /**
     * 定制View里面控件的内容
     */
    void setViewProperty(T stateProperty);
}
