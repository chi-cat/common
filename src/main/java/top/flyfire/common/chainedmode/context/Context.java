package top.flyfire.common.chainedmode.context;

import java.util.Map;

/**
 * Created by devll on 2016/12/1.
 */
public abstract class Context {

    private Map<String,Boolean> state;

    public boolean getState(String key){
        return state.get(key);
    }

    public boolean setState(String key,boolean value){
        state.put(key,value);
        return value;
    }

}
