package top.flyfire.common.reflect;

/**
 * Created by shyy_work on 2016/7/13.
 */
public class People<N extends Named,T> {

    private N name;

    private int age;

    private T extra;


    public N getName() {
        return name;
    }

    public void setName(N name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public T getExtra() {
        return extra;
    }

    public void setExtra(T extra) {
        this.extra = extra;
    }
}
