package com.yunqilai.consumer.luckyapp.ShoppingCart.Bean;

import java.util.List;

/**
 * Created by yangyuqi on 17-7-27.
 */

public class ParseFlatDataBean {
    private List<FlatBean> noLift ;
    private List<FlatBean> hasLift ;

    public List<FlatBean> getNoLift() {
        return noLift;
    }

    public void setNoLift(List<FlatBean> noLift) {
        this.noLift = noLift;
    }

    public List<FlatBean> getHasLift() {
        return hasLift;
    }

    public void setHasLift(List<FlatBean> hasLift) {
        this.hasLift = hasLift;
    }

    public ParseFlatDataBean(List<FlatBean> noLift, List<FlatBean> hasLift) {

        this.noLift = noLift;
        this.hasLift = hasLift;
    }
}
