package top.flyfire.common;

/**
 * Created by flyfire[dev.lluo@outlook.com] on 2016/5/19.
 */
public abstract class Validator<T> {

    boolean complie;

    abstract boolean validate(T t);

    public void complie(){
        //// TODO: 2016/5/19 complie
        complie = true;
    }

}
