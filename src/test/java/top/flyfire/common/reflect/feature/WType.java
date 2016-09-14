package top.flyfire.common.reflect.feature;

import java.util.List;

/**
 * Created by shyy_work on 2016/9/13.
 */
public class WType {

    private List<? extends Base> wtype;

    public List<? extends Base> getWtype() {
        return wtype;
    }

    public void setWtype(List<? extends Base> wtype) {
        this.wtype = wtype;
    }

    private BaseWithGen<? extends Base> wtype2;

    public BaseWithGen<? extends Base> getWtype2() {
        return wtype2;
    }

    public void setWtype2(BaseWithGen<? extends Base> wtype2) {
        this.wtype2 = wtype2;
    }
}
