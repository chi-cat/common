package top.flyfire.common.chainedmode.context;

/**
 * Created by shyy_work on 2016/11/29.
 */
public interface Validator<T,V> {

    boolean validate(T t,V v);

}
