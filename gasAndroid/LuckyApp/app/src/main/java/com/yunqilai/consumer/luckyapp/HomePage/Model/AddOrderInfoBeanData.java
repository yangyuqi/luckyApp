package com.yunqilai.consumer.luckyapp.HomePage.Model;

import java.util.List;

/**
 * Created by yangyuqi on 17-7-17.
 */

public class AddOrderInfoBeanData {
    private String accessToken ;
    private String addressId ;
    private List<AddOrderInfoBean> goodsList ;
    private String deliverType ;
    private String skipTime ;
    private String payType ;
    private String message ;

    private String pickPlaceId ;


    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public List<AddOrderInfoBean> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<AddOrderInfoBean> goodsList) {
        this.goodsList = goodsList;
    }

    public String getDeliverType() {
        return deliverType;
    }

    public void setDeliverType(String deliverType) {
        this.deliverType = deliverType;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPickPlaceId() {
        return pickPlaceId;
    }

    public void setPickPlaceId(String pickPlaceId) {
        this.pickPlaceId = pickPlaceId;
    }

    public AddOrderInfoBeanData(String accessToken, String addressId, List<AddOrderInfoBean> goodsList, String deliverType, String skipTime, String payType, String message , String pickPlaceId) {

        this.accessToken = accessToken;
        this.addressId = addressId;
        this.goodsList = goodsList;
        this.deliverType = deliverType;
        this.skipTime = skipTime;
        this.payType = payType;
        this.message = message;
        this.pickPlaceId = pickPlaceId ;

    }
}
