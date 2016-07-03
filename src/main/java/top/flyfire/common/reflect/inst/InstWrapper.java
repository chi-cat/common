package top.flyfire.common.reflect.inst;

/**
 * Created by flyfire[dev.lluo@outlook.com] on 2016/5/22.
 * instance wrapper
 */
public interface InstWrapper<T> {

    void set(T t);

    T get();

}
