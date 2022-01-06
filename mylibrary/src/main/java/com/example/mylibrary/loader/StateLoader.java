package com.example.mylibrary.loader;


import com.example.mylibrary.state.IState;
import ohos.agp.components.Component;

/**
 * State loader, load various states
 */
public interface StateLoader {


    /**
     * Register a state device, if there are duplicate state changers, do not add
     *
     * @param changger
     */
    boolean addState(IState changger);

    /**
     * If the corresponding state loader
     *
     * @param state state
     */
    boolean removeState(String state);
    Component getStateView(String state);
}
