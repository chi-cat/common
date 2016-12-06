package top.flyfire.common.reflect;

import top.flyfire.common.reflect.wrapper.ValueWrapper;
import top.flyfire.common.reflect.wrapper.Wrapper;
import top.flyfire.common.reflect.wrapper.WrapperFactory;

import java.lang.reflect.Type;

/**
 * Created by shyy_work on 2016/4/24.
 */
public abstract class MetaInfo implements Type {

    public static final MetaInfo[] NONE ;

    public static final MetaInfo NULL;

    static {
        NONE = new MetaInfo[0];
        NULL = new NullMetaInfo();
        NULL.initialize();
    }

    public final void initialize(){
        typeName = this.buildTypeName();
        wrapper = WrapperFactory.wrap(this);
        hash = typeName.hashCode();
    }

    protected String typeName;

    protected abstract String buildTypeName();

    public final String getTypeName() {
        return typeName;
    }

    protected Wrapper wrapper;

    public final Wrapper getWrapper(){
        return wrapper;
    }

    protected int hash;

    @Override
    public final int hashCode() {
        return hash;
    }

    @Override
    public final String toString() {
        return this.typeName;
    }

}
