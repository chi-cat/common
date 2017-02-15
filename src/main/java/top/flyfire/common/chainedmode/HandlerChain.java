package top.flyfire.common.chainedmode;

import top.flyfire.common.ObjectUtils;

/**
 * Created by shyy_work on 2016/11/29.
 */
public class HandlerChain<R,D>  {

    protected Handler<R,D> handler;

    protected HandlerChain<R,D> next;

    public HandlerChain(Handler<R, D>...handlers) {
        this(0,handlers);
    }

    public HandlerChain(int index, Handler<R, D>...handlers) {
        if(index<handlers.length){
            this.handler = handlers[index];
            this.next = new HandlerChain(++index,handlers);
        }
    }

    public R handling(D data) {
        if(ObjectUtils.isNotNull(handler)){
            return handler.handling(data,next);
        }
        return null;
    }

    public static <R,D> HandlerChain<R,D> buildChain(Handler<R,D>...handlers){
        return new HandlerChain<>(handlers);
    }

}
