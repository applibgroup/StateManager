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
                bizMo.title = "我是自定义State";
                bizMo.desc = "动态注册一个State，只有当需要展示State的时候，才会动态创建View";
                bizMo.content = "自定义State适合多个页面复用共同的逻辑，类似于轻量级的Fragment，尽量只做渲染层面的事";
                stateLayout.showState(bizMo);
            },3000);

        });

    }



}
