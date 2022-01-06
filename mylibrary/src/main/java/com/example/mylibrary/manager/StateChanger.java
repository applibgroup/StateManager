package com.example.mylibrary.manager;

import com.example.mylibrary.state.StateProperty;

/**
 * State observer
 */
public interface StateChanger {

    /**
     * The StateView that currently needs to be displayed
     * Not thread safe
     *
     * @param state The current status corresponding to the view that needs to be displayed
     * @return
     */
    boolean showState(String state);

    /**
     * The StateView that currently needs to be displayed
     * Not thread safe
     *
     * @param state The current status corresponding to the view that needs to be displayed
     * @return
     */
    boolean showState(StateProperty state);

    /**
     * Get current status
     *
     * @return
     */
    public String getStates();


    /**
     * Set some button operation callbacks in the current state
     *
     * @param listener
     */
    void setStateEventListener(StateEventListener listener);

}
