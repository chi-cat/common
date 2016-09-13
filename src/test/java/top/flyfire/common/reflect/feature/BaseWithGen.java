package top.flyfire.common.reflect.feature;

/**
 * Created by shyy_work on 2016/9/13.
 */
public class BaseWithGen<T extends Number> {

    private T gen;

    public T getGen() {
        return gen;
    }

    public void setGen(T gen) {

        this.gen = gen;
    }
}
