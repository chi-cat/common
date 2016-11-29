package top.flyfire.common.chainedmode.context;

import top.flyfire.common.chainedmode.Handler;

import java.util.Map;

/**
 * Created by shyy_work on 2016/11/29.
 */
public abstract class ContextHandler<R,D extends ContextHandler> implements Handler<R,D> {

    private Map<String,Boolean> state;

    public boolean getState(String key){
        return state.get(key);
    }

    public boolean setState(String key,boolean value){
        state.put(key,value);
        return value;
    }

}
