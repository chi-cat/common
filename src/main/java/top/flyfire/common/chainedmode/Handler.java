package top.flyfire.common.chainedmode;

/**
 * Created by shyy_work on 2016/11/29.
 */
public interface Handler<R,D> {

    R handling(D data,HandlerChain<R,D> handlerChain);

}
