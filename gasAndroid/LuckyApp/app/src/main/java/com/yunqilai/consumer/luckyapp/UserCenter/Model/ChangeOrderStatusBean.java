package com.yunqilai.consumer.luckyapp.UserCenter.Model;

/**
 * Created by yangyuqi on 17-7-20.
 */

public class ChangeOrderStatusBean {
    private String accessToken ;
    private String orderId ;
    private String operate ;
    private String reasonId ;

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

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public String getReasonId() {
        return reasonId;
    }

    public void setReasonId(String reasonId) {
        this.reasonId = reasonId;
    }

    public ChangeOrderStatusBean(String accessToken, String orderId, String operate, String reasonId) {

        this.accessToken = accessToken;
        this.orderId = orderId;
        this.operate = operate;
        this.reasonId = reasonId;
    }
}
