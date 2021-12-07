package com.example.mylibrary.manager;



import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;

/**
 * 视图状态管理
 */
public interface StateViewManager {


    ComponentContainer getOverallView();


    /**
     * 设置核心布局
     *
     * @param resId
     * @return
     */
    Component setContentView(int resId);

    Component setContentView(Component view);


    Component getContentView();


}
