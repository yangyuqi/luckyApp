package com.yunqilai.consumer.luckyapp.UserCenter.Model;

/**
 * Created by yangyuqi on 17-7-21.
 */

public class PayDataBean {
    private String orderString ;

    public String getOrderString() {
        return orderString;
    }

    public void setOrderString(String orderString) {
        this.orderString = orderString;
    }

    public PayDataBean(String orderString) {

        this.orderString = orderString;
    }
}
