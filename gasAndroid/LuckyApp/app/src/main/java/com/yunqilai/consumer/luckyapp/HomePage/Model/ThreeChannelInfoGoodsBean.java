package com.yunqilai.consumer.luckyapp.HomePage.Model;

import java.util.List;

/**
 * Created by Administrator on 2017/6/29.
 */

public class ThreeChannelInfoGoodsBean {
    private String goodsName ;
    private Double goodsPrice ;
    private String goodsPhoto ;
    private String goodsId ;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsPhoto() {
        return goodsPhoto;
    }

    public void setGoodsPhoto(String goodsPhoto) {
        this.goodsPhoto = goodsPhoto;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public ThreeChannelInfoGoodsBean(String goodsName, Double goodsPrice, String goodsPhoto, String goodsId) {

        this.goodsName = goodsName;
        this.goodsPrice = goodsPrice;
        this.goodsPhoto = goodsPhoto;
        this.goodsId = goodsId;
    }
}
