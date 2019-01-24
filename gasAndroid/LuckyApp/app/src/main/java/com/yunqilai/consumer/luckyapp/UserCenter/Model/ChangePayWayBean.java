package com.yunqilai.consumer.luckyapp.UserCenter.Model;

/**
 * Created by yangyuqi on 17-7-28.
 */

public class ChangePayWayBean {
    private String accessToken ;
    private String orderId ;
    private String deliverType ;
    private String pickPlaceId ;
    private String skipTime ;
    private String payType ;
    private String addressId ;

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

    public String getDeliverType() {
        return deliverType;
    }

    public void setDeliverType(String deliverType) {
        this.deliverType = deliverType;
    }

    public String getPickPlaceId() {
        return pickPlaceId;
    }

    public void setPickPlaceId(String pickPlaceId) {
        this.pickPlaceId = pickPlaceId;
    }

    public String getSkipTime() {
        return skipTime;
    }

    public void setSkipTime(String skipTime) {
        this.skipTime = skipTime;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public ChangePayWayBean(String accessToken, String orderId, String deliverType, String pickPlaceId, String skipTime, String payType , String addressId) {

        this.accessToken = accessToken;
        this.orderId = orderId;
        this.deliverType = deliverType;
        this.pickPlaceId = pickPlaceId;
        this.skipTime = skipTime;
        this.payType = payType;
        this.addressId = addressId ;

    }
}
