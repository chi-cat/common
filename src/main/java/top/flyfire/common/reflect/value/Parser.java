package top.flyfire.common.reflect.value;

/**
 * Created by devll on 2016/11/6.
 */
public interface Parser<V,T> {

    V parse(T value);

}
