package top.flyfire.common.type;

import java.lang.reflect.Type;

/**
 * Created by devll on 2016/10/28.
 */
public abstract class AbstractType implements Type {

    private static int BehaviorBase = 0;
    public final static int Primitive = 1 << BehaviorBase++,
            Map = 1 << BehaviorBase++,
            List = 1 << BehaviorBase++,
            Set = 1 << BehaviorBase++,
            Array = 1 << BehaviorBase++,
            Object = 1 << BehaviorBase++,
            Interface = 1 << BehaviorBase++,
            Annotation = 1 << BehaviorBase++,
            Enum = 1 << BehaviorBase++;

    protected String typeName;

    protected int typeBehavior;

    protected abstract int buildTypeBehavior();

    protected abstract String buildTypeName();

    public final int getBehaviorCode(){
        return this.typeBehavior == 0?this.typeBehavior = this.buildTypeBehavior():this.typeBehavior;
    }

    public final boolean isPrimitive(){
        return hasTypeBehavior(Primitive);
    }

    public final boolean isInterface(){
        return hasTypeBehavior(Interface);
    }

    public final boolean isMap(){
        return hasTypeBehavior(Map);
    }

    public final boolean isList(){
        return hasTypeBehavior(List);
    }

    public final boolean isSet(){
        return hasTypeBehavior(Set);
    }

    public final boolean isArray(){
        return hasTypeBehavior(Array);
    }

    public final boolean isAnnotation(){
        return hasTypeBehavior(Annotation);
    }

    public final boolean isEnum(){
        return hasTypeBehavior(Enum);
    }

    public final boolean isObject(){
        return hasTypeBehavior(Object);
    }

    private final boolean hasTypeBehavior(int behaviorCode){
        return behaviorCode==(behaviorCode&typeBehavior);
    }

    public final String getTypeName() {
        return this.toString();
    }

    @Override
    public final String toString() {
        return this.typeName == null?this.typeName=this.buildTypeName():this.typeName;
    }


}
