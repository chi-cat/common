package top.flyfire.common.value;

/**
 * Created by shyy_work on 2016/4/9.
 */
public class PrimitiveUtil {

    public static boolean isNumeric(String s){
        int len = s.length();
        if(len==1){
            char c = s.charAt(0);
            return c>='0'&&c<='9';
        }else{
            int cursor =0;char c;boolean signed = false;
            if((c=s.charAt(cursor))=='+'||c=='-'){
                cursor++;
                signed = true;
            }
            if((!signed&&c=='.')||(signed&&(c=s.charAt(cursor))=='.')){
                return false;
            }else if(c>='0'&&c<='9'){
                len = len -1;
                while(++cursor<len){
                    if(((c=s.charAt(cursor))<'0'||c>'9')&&c!='.'){
                        return false;
                    }
                }
                return ((c=s.charAt(len))>='0'&&c<='9') ;
            }else{
                return false;
            }

        }
    }


    public static boolean isTimeStamp(String s){
        int cursor = 0,len = s.length(),_5 = 5,_8 = 8,_11 = 11,_14 = 14,_17 = 17,_20 = 20   ;char c;
        if((c=s.charAt(0))=='\"'&&c==s.charAt(len=len-1)) {
            if (len == 24) {
                if(((c=s.charAt(_5))=='/'||c=='-')&&c==s.charAt(_8)
                        &&((c=s.charAt(_11))==' '||c=='T')
                        &&((c=s.charAt(_14))==':'&&c==s.charAt(_17))
                        &&(c=s.charAt(_20))=='.'){
                    while(++cursor<len){
                        if(cursor==_5
                                ||cursor==_8
                                ||cursor==_11
                                ||cursor==_14
                                ||cursor==_17
                                ||cursor==_20)continue;
                        if((c=s.charAt(cursor))<'0'||c>'9'){
                            return false;
                        }
                    }
                    return true;
                }
                return false;
            }
            return false;
        }else{
            return false;
        }
    }

    public static boolean isDate(String s){
        int cursor = 0,len = s.length(),_5 = 5,_8 = 8,_11 = 11,_14 = 14,_17 = 17   ;char c;
        if((c=s.charAt(0))=='\"'&&c==s.charAt(len=len-1)) {
            if (len == 20) {
                if(((c=s.charAt(_5))=='/'||c=='-')&&c==s.charAt(_8)
                        &&((c=s.charAt(_11))==' '||c=='T')
                        &&((c=s.charAt(_14))==':'&&c==s.charAt(_17))){
                    while(++cursor<len){
                        if(cursor==_5
                                ||cursor==_8
                                ||cursor==_11
                                ||cursor==_14
                                ||cursor==_17)continue;
                        if((c=s.charAt(cursor))<'0'||c>'9'){
                            return false;
                        }
                    }
                    return true;
                }
                return false;
            }
            return false;
        }else{
            return false;
        }
    }

    public static boolean isBoolean(String s){
        int len = s.length();
        if((len==5&&s.charAt(0)=='f'&&s.charAt(1)=='a'&&s.charAt(2)=='l'&&s.charAt(3)=='s'&&s.charAt(4)=='e')
                ||(len==4&&s.charAt(0)=='t'&&s.charAt(1)=='r'&&s.charAt(2)=='u'&&s.charAt(3)=='e')){
            return true;
        }else{
            return false;
        }
    }

    public static boolean isString(String s){
        int len = s.length();char c;
        return len>=2&&((c = s.charAt(0))=='\"'||c =='\'')&&c==s.charAt(len-1);
    }

    public static boolean isNull(String s){
        int len = s.length();char c;
        return len==4&&s.charAt(0)=='n'&&s.charAt(1)=='u'&&(c = s.charAt(2))=='l'&&c==s.charAt(3);
    }

}
