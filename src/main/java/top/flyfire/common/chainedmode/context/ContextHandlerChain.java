package top.flyfire.common.chainedmode.context;

import top.flyfire.common.ObjectUtils;
import top.flyfire.common.chainedmode.simple.SimpleHandlerChain;

import java.util.Map;

/**
 * Created by shyy_work on 2016/11/29.
 */
public class ContextHandlerChain<R,D extends ContextHandler> extends SimpleHandlerChain<R,D> implements Validator<String,D> {

    protected Map<String,Validator<D,Validator>> validators;

    @Override
    public boolean validate(String key,D data){
        Boolean value;
        if(ObjectUtils.isNull(value = data.getState(key))){
            value = validators.get(key).validate(data,this);
            data.setState(key,value);
        }
        return value;
    }

}
