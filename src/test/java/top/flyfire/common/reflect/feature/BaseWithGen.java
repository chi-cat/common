package top.flyfire.common.reflect.feature;

/**
 * Created by shyy_work on 2016/9/13.
 */
public class BaseWithGen<T extends Base> {

    private T gen;

    public T getGen() {
        return gen;
    }

    public void setGen(T gen) {

        this.gen = gen;
    }

    private Base[] bases;

    public Base[] getBases() {
        return bases;
    }

    public void setBases(Base[] bases) {
        this.bases = bases;
    }
}
