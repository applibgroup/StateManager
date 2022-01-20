package com.example.myapplication;


import com.example.myapplication.state.BizState;
import com.example.myapplication.state.LoadingState;
import com.example.mylibrary.StateLayout;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.eventhandler.EventHandler;
import ohos.eventhandler.EventRunner;
import org.jetbrains.annotations.Nullable;

/**
 * 自定义State及动态注册
 */

public class CustomStateActivity extends Ability {

    @Override
    protected void onStart(@Nullable Intent savedInstanceState) {
        super.onStart(savedInstanceState);
        setUIContent(ResourceTable.Layout_activity_customstate);
        final StateLayout stateLayout = (StateLayout) findComponentById(ResourceTable.Id_statelayout);
        stateLayout.addState(new BizState());
        Button requestData = (Button) findComponentById(ResourceTable.Id_request_data);

        requestData.setClickedListener(v -> {
            stateLayout.showState(LoadingState.STATE);
            //mock request data
            EventHandler eventhandler = new EventHandler(EventRunner.getMainEventRunner());
            eventhandler.postTask(() -> {
                BizState.BizMo bizMo = new BizState.BizMo();
                bizMo.title = "I am a Custom State";
                bizMo.desc = "Dynamically register a State, only when the State needs to be displayed, will the View be dynamically created";
                bizMo.content = "Custom State is suitable for multiple pages to reuse common logic, similar to lightweight Fragment, try to only do the rendering level";
                stateLayout.showState(bizMo);
            },3000);

        });

    }



}
