package com.yunqilai.consumer.luckyapp.UserCenter.Model;

/**
 * Created by yangyuqi on 17-7-29.
 */

public class PostReasionBean {
    private String accessToken ;
    private String orderId ;
    private String shopCartId ;
    private String reasonId ;
    private String message ;

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

    public String getReasonId() {
        return reasonId;
    }

    public void setReasonId(String reasonId) {
        this.reasonId = reasonId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PostReasionBean(String accessToken, String orderId, String shopCartId, String reasonId, String message) {

        this.accessToken = accessToken;
        this.orderId = orderId;
        this.shopCartId = shopCartId;
        this.reasonId = reasonId;
        this.message = message;
    }
}
