package com.example.myapplication;

import com.example.mylibrary.loader.StateRepository;
import com.example.mylibrary.manager.StateChanger;
import com.example.mylibrary.manager.StateEventListener;
import com.example.mylibrary.manager.StateManager;
import com.example.mylibrary.state.StateProperty;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.window.service.WindowManager;


public abstract class StateManagerActivity extends Ability implements StateChanger {

    private StateManager stateManager;

    @Override
    protected void onStart(Intent savedInstanceState) {
        super.onStart(savedInstanceState);
        stateManager = StateManager.newInstance(this, new StateRepository(this));
    }


  //@Override
    public void setContentView(int layoutResID) {

        super.setUIContent((ComponentContainer) stateManager.setContentView(layoutResID));
    }

//    @Override
    public void setContentView(ComponentContainer view, WindowManager.LayoutConfig params) {
        super.setLayoutParams(params);
  }

   // @Override
    public void setContentView(ComponentContainer view) {

        super.setUIContent((ComponentContainer) stateManager.setContentView(view));
    }

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

    @Override
    protected void onStop() {
        super.onStop();
        stateManager.onDestoryView();
    }
}
