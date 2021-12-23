package com.example.mylibrary.loader;



import com.example.mylibrary.state.IState;
import ohos.agp.components.Component;
import ohos.agp.utils.TextTool;
import ohos.app.Context;

import java.util.HashMap;

/**
 * 状态仓库
 */
public class StateRepository implements StateLoader {

    public static void registerState(String state, Class clazz) {
        stateClazzMap.put(state, clazz);
    }

    public static void unregisterState(String state) {
        stateClazzMap.remove(state);
    }

    /**
     * 用于映射State和具体State对象
     */
    protected HashMap<String, IState> stateMap = new HashMap<String, IState>(5);
    protected static HashMap<String, Class> stateClazzMap = new HashMap<String, Class>(5);


    protected Context mContext;

    public StateRepository(Context context) {
        mContext = context;
    }

    @Override
    public boolean addState(IState changger) {
        if (changger != null && !TextTool.isNullOrEmpty(changger.getState())) {
            stateMap.put(changger.getState(), changger);
            return true;
        }

        return false;
    }

    @Override
    public boolean removeState(String state) {
        if (!TextTool.isNullOrEmpty(state)) {
            stateMap.remove(state);
            return true;
        }
        return false;
    }


    public IState get(String state) {

        IState istate = stateMap.get(state);
        if (istate != null) {
            return istate;
        }

        Class clazz = stateClazzMap.get(state);
        if (clazz != null) {
            try {
                istate = (IState) clazz.newInstance();
                addState(istate);
            } catch (Exception e) {

            }
        }

        return istate;
    }

    @Override
    public Component getStateView(String state) {
        IState stateChanger = get(state);
        if (stateChanger != null) {
            return stateChanger.getView();
        }
        return null;
    }


    public HashMap<String, IState> getStateMap() {
        return stateMap;
    }


    public void clear() {
        stateMap.clear();
    }

}
