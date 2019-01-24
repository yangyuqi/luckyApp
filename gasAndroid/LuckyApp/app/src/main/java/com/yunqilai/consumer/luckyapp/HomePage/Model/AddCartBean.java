package com.yunqilai.consumer.luckyapp.HomePage.Model;

import java.util.List;

/**
 * Created by Administrator on 2017/6/29.
 */

public class AddCartBean {
    private List<SpecGoodsBean> spec_bean ;
    private String goodsId ;
    private String content ;
    private String goodsPhoto ;

    public List<SpecGoodsBean> getSpec_bean() {
        return spec_bean;
    }

    public void setSpec_bean(List<SpecGoodsBean> spec_bean) {
        this.spec_bean = spec_bean;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getGoodsPhoto() {
        return goodsPhoto;
    }

    public void setGoodsPhoto(String goodsPhoto) {
        this.goodsPhoto = goodsPhoto;
    }

    public AddCartBean(List<SpecGoodsBean> spec_bean, String goodsId, String content , String goodsPhoto) {

        this.spec_bean = spec_bean;
        this.goodsId = goodsId;
        this.content = content;
        this.goodsPhoto = goodsPhoto ;

    }
}
