package top.flyfire.common.reflect;

/**
 * Created by shyy_work on 2016/7/13.
 */
public class Named<C> {

    private C called;


    public C getCalled() {
        return called;
    }

    public void setCalled(C called) {
        this.called = called;
    }
}
