package com.yunqilai.consumer.luckyapp.UserCenter.Model;

/**
 * Created by yangyuqi on 17-7-28.
 */

public class RefuseOrderBean {
    private String accessToken ;
    private String orderId ;
    private String shopCartId ;

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

    public String getShopCartId() {
        return shopCartId;
    }

    public void setShopCartId(String shopCartId) {
        this.shopCartId = shopCartId;
    }

    public RefuseOrderBean(String accessToken, String orderId, String shopCartId) {

        this.accessToken = accessToken;
        this.orderId = orderId;
        this.shopCartId = shopCartId;
    }
}
