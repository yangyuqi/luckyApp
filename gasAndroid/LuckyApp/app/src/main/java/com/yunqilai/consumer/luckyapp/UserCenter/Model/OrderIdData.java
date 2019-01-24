package com.yunqilai.consumer.luckyapp.UserCenter.Model;

/**
 * Created by yangyuqi on 17-7-21.
 */

public class OrderIdData {
    private String orderId ;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public OrderIdData(String orderId) {

        this.orderId = orderId;
    }
}
