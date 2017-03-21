package top.flyfire.common.reflect.metainfo;

import top.flyfire.common.reflect.*;

/**
 * Created by flyfire[dev.lluo@outlook.com] on 2016/5/31.
 */
public class ParameterizeMetaInfo extends MetaInfo implements GenericTypeAdapted {

    private final MetaInfo[] actualTypeArguments;

    private final MetaInfo rawType;

    private final MetaInfo ownerType;

    public ParameterizeMetaInfo(MetaInfo[] actualTypeArguments, MetaInfo rawType, MetaInfo ownerType) {
        this.actualTypeArguments = actualTypeArguments;
        this.rawType = rawType;
        this.ownerType = ownerType;
    }

    @Override
    protected String buildTypeName() {
        if (this.actualTypeArguments == null || this.actualTypeArguments.length == 0) {
            return this.rawType.getTypeName();
        } else {
            StringBuilder toString = new StringBuilder(this.rawType.getTypeName());
            toString.append('<').append(this.actualTypeArguments[0].getTypeName());
            for (int i = 1; i < this.actualTypeArguments.length; i++) {
                toString.append(',').append(this.actualTypeArguments[i].getTypeName());
            }
            toString.append('>');
            return toString.toString();
        }
    }


    public MetaInfo[] getActualTypeArguments() {
        return actualTypeArguments;
    }

    public MetaInfo getRawType() {
        return rawType;
    }

    public MetaInfo getOwnerType() {
        return ownerType;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof ParameterizeMetaInfo) {
            ParameterizeMetaInfo other = ((ParameterizeMetaInfo) obj);
            return rawType.equals(other.rawType) && ownerType.equals(other.ownerType) && ReflectUtils.metaInfoArrEquals(actualTypeArguments, other.actualTypeArguments);
        } else {
            return false;
        }
    }

    public MetaInfo adapt(MetaInfo[] variableMetaInfos, MetaInfo[] typeStore) {
        MetaInfo[] actualTypeArguments = new MetaInfo[this.actualTypeArguments.length];
        ParameterizeMetaInfo parameterizeMetaInfo = new ParameterizeMetaInfo(actualTypeArguments, rawType, ownerType);
        for (int i = 0; i < actualTypeArguments.length; i++) {
            if (this.actualTypeArguments[i] instanceof GenericTypeAdapted)
                actualTypeArguments[i] = ((GenericTypeAdapted) this.actualTypeArguments[i]).adapt(variableMetaInfos, typeStore);
            else {
                actualTypeArguments[i] = this.actualTypeArguments[i];
            }
        }
        return parameterizeMetaInfo;
    }

    public ClassMetaInfo asClassMetaInfo() {
        MetaInfo metaInfo;
        if (!((metaInfo = this.rawType) instanceof ClassMetaInfo)) {
            throw new ReflectiveSyntaxException("[A ClassMetaInfo is expected in the buidlSuper , but superType is of type " + this.rawType + " .]");
        }
        ClassMetaInfo classMetaInfo = ((ClassMetaInfo) metaInfo).ssPrototype();
        classMetaInfo.extendSuper(this);
        return classMetaInfo;
    }

}
