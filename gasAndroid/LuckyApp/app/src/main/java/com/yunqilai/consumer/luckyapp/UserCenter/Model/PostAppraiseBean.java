package com.yunqilai.consumer.luckyapp.UserCenter.Model;

import java.util.List;

/**
 * Created by yangyuqi on 17-8-5.
 */

public class PostAppraiseBean {
    private String accessToken ;
    private String orderId ;
    private int  logistics ;
    private int service ;
    private List<AppraBean> goodsEvaluates ;

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

    public int getLogistics() {
        return logistics;
    }

    public void setLogistics(int logistics) {
        this.logistics = logistics;
    }

    public int getService() {
        return service;
    }

    public void setService(int service) {
        this.service = service;
    }

    public List<AppraBean> getGoodsEvaluates() {
        return goodsEvaluates;
    }

    public void setGoodsEvaluates(List<AppraBean> goodsEvaluates) {
        this.goodsEvaluates = goodsEvaluates;
    }

    public PostAppraiseBean(String accessToken, String orderId, int logistics, int service, List<AppraBean> goodsEvaluates) {

        this.accessToken = accessToken;
        this.orderId = orderId;
        this.logistics = logistics;
        this.service = service;
        this.goodsEvaluates = goodsEvaluates;
    }
}
