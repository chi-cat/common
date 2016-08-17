package top.flyfire.common.reflect;

import java.lang.reflect.Type;

/**
 * Created by shyy_work on 2016/4/24.
 */
public abstract class MetaInfo implements Type {

    public static final MetaInfo[] NONE = new MetaInfo[0];

    public static final MetaInfo NULL = new MetaInfo() {
        @Override
        protected String buildTypeName() {
            return "null";
        }

        @Override
        public boolean compatible(Type type) {
            return false;
        }

        @Override
        public boolean equals(Object obj) {
            return obj == this;
        }
    };

    protected String typeName;

    protected abstract String buildTypeName();

    public abstract boolean compatible(Type type);

    public final String getTypeName() {
        return this.toString();
    }

    @Override
    public final String toString() {
        return this.typeName == null?this.typeName=this.buildTypeName():this.typeName;
    }

}
