package com.example.mylibrary.manager;



import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;

/**
 * View state management
 */
public interface StateViewManager {


    ComponentContainer getOverallView();


    /**
     * Set up the core layout
     *
     * @param resId
     * @return
     */
    Component setContentView(int resId);

    Component setContentView(Component view);


    Component getContentView();


}
