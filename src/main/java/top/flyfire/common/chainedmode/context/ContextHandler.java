package top.flyfire.common.chainedmode.context;

import top.flyfire.common.chainedmode.Handler;

import java.util.Map;

/**
 * Created by shyy_work on 2016/11/29.
 */
public abstract class ContextHandler<R,D extends ContextHandler> implements Handler<R,D> {

    private Map<String,Boolean> state;

    public boolean validate(String key){
        return state.get(key);
    }

}
