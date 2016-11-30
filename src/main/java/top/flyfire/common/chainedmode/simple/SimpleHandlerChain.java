package top.flyfire.common.chainedmode.simple;

import top.flyfire.common.ObjectUtils;
import top.flyfire.common.chainedmode.Handler;
import top.flyfire.common.chainedmode.HandlerChain;

/**
 * Created by shyy_work on 2016/11/29.
 */
public class SimpleHandlerChain<R,D> implements HandlerChain<R,D> {

    protected Handler<R,D> handler;

    protected SimpleHandlerChain<R,D> next;

    public SimpleHandlerChain(Handler<R, D>...handlers) {
        this(0,handlers);
    }

    public SimpleHandlerChain(int index, Handler<R, D>...handlers) {
        if(index<handlers.length){
            this.handler = handlers[index];
            this.next = new SimpleHandlerChain(index++,handlers);
        }
    }

    @Override
    public R handling(D data) {
        if(ObjectUtils.isNotNull(handler)){
            handler.handling(data,next);
        }
        return null;
    }

    public static <R,D> SimpleHandlerChain<R,D> buildChain(Handler<R,D>...handlers){
        return new SimpleHandlerChain<>(handlers);
    }

}
