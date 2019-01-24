package com.yunqilai.consumer.luckyapp.HomePage.Model;

import java.util.List;

/**
 * Created by yangyuqi on 17-7-17.
 */

public class ComputerOrderMoneyBean {
    private String accessToken ;
    private String addressId ;
    private List<AddOrderInfoBean> goodsList ;
    private String deliverType ;

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

    public ComputerOrderMoneyBean(String accessToken, String addressId, List<AddOrderInfoBean> goodsList, String deliverType) {

        this.accessToken = accessToken;
        this.addressId = addressId;
        this.goodsList = goodsList;
        this.deliverType = deliverType;
    }
}
