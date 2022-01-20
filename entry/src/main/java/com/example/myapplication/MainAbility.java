package com.example.myapplication;


import com.example.myapplication.state.ExceptionState;
import com.example.myapplication.state.LoadingState;
import com.example.mylibrary.state.CoreState;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.utils.TextTool;


public class MainAbility extends StateManagerActivity {

    public static final String TAG = "MainAbility";

    @Override
    protected void onStart(Intent savedInstanceState) {
        super.onStart(savedInstanceState);
        super.setContentView(ResourceTable.Layout_ability_main);

        findComponentById(ResourceTable.Id_show_loading).setClickedListener(view -> showState(LoadingState.STATE));

        findComponentById(ResourceTable.Id_show_exception).setClickedListener(view -> showState(ExceptionState.STATE));

        findComponentById(ResourceTable.Id_statemanager_demo).setClickedListener(view -> {
            Intent intent = new Intent();
            Operation operation=new Intent.OperationBuilder().withAction("action.first").build();
            intent.setOperation(operation);
            startAbility(intent);
        });

        findComponentById(ResourceTable.Id_recyclerview_demo).setClickedListener(v -> {
            Intent intent = new Intent();
            Operation operation=new Intent.OperationBuilder().withAction("action.second").build();
            intent.setOperation(operation);
            startAbility(intent);
        });

        findComponentById(ResourceTable.Id_statelayout_demo).setClickedListener(v -> {
            Intent intent = new Intent();
            Operation operation=new Intent.OperationBuilder().withAction("action.third").build();
            intent.setOperation(operation);
            startAbility(intent);
        });

        findComponentById(ResourceTable.Id_customstate_demo).setClickedListener(v -> {
            Intent intent = new Intent();
            Operation operation=new Intent.OperationBuilder().withAction("action.fourth").build();
            intent.setOperation(operation);
            startAbility(intent);
        });


        setStateEventListener((event, view) -> {
            if (TextTool.isEqual(event, LoadingState.EVENT_CLICK)) {
                showState(ExceptionState.STATE);
            } else {
                showState(CoreState.STATE);
            }
        });
    }
}
