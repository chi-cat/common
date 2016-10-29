package top.flyfire.common.type;

/**
 * Created by devll on 2016/10/28.
 */
public class ClassType extends AbstractType {

    protected Class<?>  cached;

    protected AbstractType[] typeParamters;

    @Override
    protected String buildTypeName() {
        return cached.toString();
    }

    @Override
    protected int buildTypeBehavior() {
        return TypeUtils.getTypeBehavior(cached);
    }
}
