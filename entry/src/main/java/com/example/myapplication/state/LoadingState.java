package com.example.myapplication.state;


import com.example.myapplication.ResourceTable;
import com.example.mylibrary.state.BaseState;
import ohos.agp.components.Component;

public class    LoadingState extends BaseState {
    public static final String STATE = "LoadingState";

    public static final String EVENT_CLICK = "LoadingState_CLICK";

    @Override
    protected int getLayoutId() {


        return ResourceTable.Layout_loading_state;
    }

    @Override
    protected void onViewCreated(Component stateView) {
        stateView.findComponentById(ResourceTable.Id_click).setClickedListener(view -> {
            if (stateEventListener != null) {
                stateEventListener.onEventListener(EVENT_CLICK, view);
            }
        });
    }

    @Override
    public String getState() {
        return STATE;
    }
}
