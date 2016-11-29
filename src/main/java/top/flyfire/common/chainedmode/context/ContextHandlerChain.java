package top.flyfire.common.chainedmode.context;

import top.flyfire.common.ObjectUtils;
import top.flyfire.common.chainedmode.simple.SimpleHandlerChain;

import java.util.Map;

/**
 * Created by shyy_work on 2016/11/29.
 */
public abstract class ContextHandlerChain<R,D extends ContextHandler> extends SimpleHandlerChain<R,D> implements Validator<String,D> {

    protected Map<String,Validator<D,Validator>> validators;

    @Override
    public boolean validate(String key,D data){
        Boolean result;
        if(ObjectUtils.isNull(result = data.validate(key))){
            result = validators.get(key).validate(data,this);
        }
        return result;
    }

}
