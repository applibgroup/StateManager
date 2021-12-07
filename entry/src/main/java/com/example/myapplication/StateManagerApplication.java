package com.example.myapplication;

//import android.app.Application;

import com.example.myapplication.state.ExceptionState;
import com.example.myapplication.state.LoadingState;
import com.example.mylibrary.loader.StateRepository;
import ohos.aafwk.ability.AbilityPackage;

public class StateManagerApplication extends AbilityPackage {


    @Override
    public void onInitialize() {
        super.onInitialize();
        StateRepository.registerState(LoadingState.STATE,LoadingState.class);
        StateRepository.registerState(ExceptionState.STATE,ExceptionState.class);

    }
}
