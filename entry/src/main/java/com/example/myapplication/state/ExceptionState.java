package com.example.myapplication.state;


import com.example.myapplication.ResourceTable;
import com.example.mylibrary.state.BaseState;
import ohos.agp.components.Component;

public class ExceptionState extends BaseState {
    public static final String STATE = "ExceptionState";

    @Override
    protected int getLayoutId() {

       // return R.layout.exception_state;
        return ResourceTable.Layout_exception_state;
    }

    @Override
    protected void onViewCreated(Component stateView) {
        stateView.findComponentById(ResourceTable.Id_exit).setClickedListener(view -> {
            if (stateEventListener != null) {
                stateEventListener.onEventListener(STATE, view);
            }
        });
    }

    @Override
    public String getState() {
        return STATE;
    }
}
