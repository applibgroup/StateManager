package com.example.mylibrary.state;

import com.example.mylibrary.manager.StateEventListener;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.app.Context;

/**
 * State lifecycle management
 */
public interface IState<T extends StateProperty> {

    String EMPTY = "empty_state";
    String EXCEPTION = "exception_state";
    String LOADING = "loading_state";
    String NETERROR = "net_error_state";
    String ERROR = "error_state";

    /**
     * After the StateView is created, you can do some operations
     */
    void onStateCreate(Context context, ComponentContainer parent);


    /**
     * After StateView is displayed, you can do some operations
     */
    void onStateResume();

    /**
     * After the StateView is hidden, you can do some operations
     */
    void onStatePause();


    /**
     * Get current status
     *
     * @return
     */
    String getState();

    /**
     * Set some button operation callbacks in the current state
     *Get the View of the state machine
     * @param listener
     */
    void setStateEventListener(StateEventListener listener);


    /**
     * Get the View of the state machine
     *
     * @return
     */
    Component getView();


    /**
     * Customize the content of the controls in the View
     */
    void setViewProperty(T stateProperty);
}
