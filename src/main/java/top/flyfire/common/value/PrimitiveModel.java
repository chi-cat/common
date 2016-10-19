package top.flyfire.common.value;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by shyy_work on 2016/4/9.
 */
public class PrimitiveModel implements Value {

    private Value value;

    private BigDecimal numberValue;

    private Date timeValue;

    private String strValue;

    private Boolean boolValue;

    public PrimitiveModel(String value) {
        int len = value.length();
        if (PrimitiveUtil.isNumeric(value)){
            this.numberValue = new BigDecimal(value);
            this.value = new NumVal();
        }else if(PrimitiveUtil.isTimeStamp(value)||PrimitiveUtil.isDate(value)){
            this.timeValue = Timestamp.valueOf(value.substring(1,len-1));
            this.value = new TimeVal();
        }else if(PrimitiveUtil.isBoolean(value)){
            this.boolValue = Boolean.parseBoolean(value);
            this.value = new BoolVal();
        }else if(PrimitiveUtil.isString(value)){
            this.strValue = value.substring(1,len-1);
            this.value = new StrVal();
        }else if(PrimitiveUtil.isNull(value)){
            this.value = new NullVal();
        }else{
            throw new RuntimeException("unexpectd primitive value["+value+"]");
        }
    }

    @Override
    public Object val() {
        return this.value.val();
    }

    private class NumVal implements Value {
        @Override
        public Object val() {
            return PrimitiveModel.this.numberValue;
        }
    }

    private class StrVal implements Value {
        @Override
        public Object val() {
            return PrimitiveModel.this.strValue;
        }
    }

    private class TimeVal implements Value {
        @Override
        public Object val() {
            return PrimitiveModel.this.timeValue;
        }
    }

    private class BoolVal implements Value {
        @Override
        public Object val() {
            return PrimitiveModel.this.boolValue;
        }
    }

    private class NullVal implements Value {
        @Override
        public Object val() {
            return null;
        }
    }

}
