package com.yunqilai.consumer.luckyapp.UserCenter.Model;

/**
 * Created by yangyuqi on 17-7-20.
 */

public class OrderDetailsBean {
    private String accessToken ;
    private String orderId ;

    public OrderDetailsBean(String accessToken, String orderId) {
        this.accessToken = accessToken;
        this.orderId = orderId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
