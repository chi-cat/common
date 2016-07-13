package top.flyfire.common.reflect.inst;

import top.flyfire.common.reflect.ReflectUtils;
import top.flyfire.common.reflect.ReflectiveException;
import top.flyfire.common.reflect.metainfo.ClassMetaInfo;

import java.util.*;

/**
 * Created by shyy_work on 2016/7/13.
 */
public class ClassInstWrapper extends InstWrapper<ClassMetaInfo> {

    public ClassInstWrapper(ClassMetaInfo metaInfo) {
        super(metaInfo);
    }

    @Override
    public Object newInstance() {
        try {
            Class clzz = metaInfo.getRawType();
            if(ReflectUtils.isInterface(clzz)){
                if(Map.class.equals(clzz)){
                    return new HashMap();
                }else if(List.class.equals(clzz)){
                    return new ArrayList();
                }else if(Set.class.equals(clzz)){
                    return new HashSet();
                } else if(Collection.class.equals(clzz)){
                    return new ArrayList();
                }else{
                    throw new ReflectiveException(metaInfo+"is a interface and isn't registered");
                }
            }else {
                return metaInfo.getRawType().newInstance();
            }
        }catch (ReflectiveOperationException e){
            throw new ReflectiveException(e);
        }
    }

    @Override
    public Object newContainerInstance(int size) {
        return null;
    }
}
