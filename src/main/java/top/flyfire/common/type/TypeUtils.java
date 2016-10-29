package top.flyfire.common.type;

import java.util.*;

/**
 * Created by devll on 2016/10/28.
 */
public final class TypeUtils {

    public static int getTypeBehavior(Class<?> clzz) {
        int value = 0;
        if (clzz.isPrimitive()
                || Number.class.isAssignableFrom(clzz)
                || Date.class.isAssignableFrom(clzz)) {
            value |= AbstractType.Primitive;
        } else if (Map.class.isAssignableFrom(clzz)) {
            value |= AbstractType.Map;
        } else if (List.class.isAssignableFrom(clzz)) {
            value |= AbstractType.List;
        } else if (Set.class.isAssignableFrom(clzz)) {
            value |= AbstractType.Set;
        } else if (clzz.isArray()) {
            value |= AbstractType.Array;
        } else {
            value |= AbstractType.Object;
        }

        if (clzz.isInterface()) {
            value |= AbstractType.Interface;
        } else if (clzz.isAnnotation()) {
            value |= AbstractType.Annotation;
        } else if (clzz.isEnum()) {
            value |= AbstractType.Enum;
        }

        return value;
    }

}
