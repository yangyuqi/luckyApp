package com.yunqilai.consumer.luckyapp.UserCenter.Model;

/**
 * Created by Administrator on 2017/8/17.
 */

public class RefundDetailsBean {
    private String accessToken ;
    private String orderId ;

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

    public RefundDetailsBean(String accessToken, String orderId) {

        this.accessToken = accessToken;
        this.orderId = orderId;
    }
}
