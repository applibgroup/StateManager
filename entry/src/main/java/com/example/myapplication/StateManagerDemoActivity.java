package com.example.myapplication;

import com.example.myapplication.state.ExceptionState;
import com.example.myapplication.state.LoadingState;
import com.example.mylibrary.manager.StateEventListener;
import com.example.mylibrary.state.CoreState;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.utils.TextTool;

public class StateManagerDemoActivity extends StateManagerActivity implements StateEventListener {


    @Override
    protected void onStart(Intent savedInstanceState) {
        super.onStart(savedInstanceState);
        setContentView(ResourceTable.Layout_ability_main);

        findComponentById(ResourceTable.Id_show_loading).setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component view) {
                showState(LoadingState.STATE);
            }
        });

        findComponentById(ResourceTable.Id_show_exception).setClickedListener(new Component.ClickedListener(){
            @Override
            public void onClick(Component view) {
                showState(ExceptionState.STATE);
            }
        });

        setStateEventListener(new StateEventListener() {
            @Override
            public void onEventListener(String event, Component view) {
                if (TextTool.isEqual(event, LoadingState.EVENT_CLICK)) {
                    showState(ExceptionState.STATE);
                } else {
                    showState(CoreState.STATE);
                }
            }
        });


    }

    @Override
    public void onEventListener(String event, Component view) {
        //        if(TextTools.isEquals(event,LoadingState.STATE)){
        //           stateLayout.showState(ExceptionState.STATE);
        //        }else {
        showState(CoreState.STATE);
        //        }
    }
}
