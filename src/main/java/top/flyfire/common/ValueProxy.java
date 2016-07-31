package top.flyfire.common;

/**
 * Created by shyy_work on 2016/6/21.
 */
public class ValueProxy <T> {

    private T value;

    public T get(){
        return value;
    }

    public void set(T t){
        value = t;
    }

    public boolean isNull(){
        return value == null;
    }

}
