package com.example.mylibrary.manager;

//import android.view.View;
import ohos.agp.components.Component;

/**
 * 异常界面和数据为空界面按钮点击事件监听
 */
public interface StateEventListener {
    void onEventListener(String state, Component view);

}