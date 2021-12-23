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
        setUIContent(ResourceTable.Layout_activity_statemanager_demo);
        super.setContentView(ResourceTable.Layout_activity_statemanager_demo);

        findComponentById(ResourceTable.Id_show_loading).setClickedListener(view -> showState(LoadingState.STATE));

        findComponentById(ResourceTable.Id_show_exception).setClickedListener(view -> showState(ExceptionState.STATE));

        setStateEventListener((event, view) -> {
            if (TextTool.isEqual(event, LoadingState.EVENT_CLICK)) {
                showState(ExceptionState.STATE);
            } else {
                showState(CoreState.STATE);
            }
        });


    }
    @Override
    public void onEventListener(String event, Component view) {
//                if(TextTool.isEqual(event,LoadingState.STATE)){
//                   showState(ExceptionState.STATE);
//                }else {
        showState(CoreState.STATE);
         //     }
    }
}
