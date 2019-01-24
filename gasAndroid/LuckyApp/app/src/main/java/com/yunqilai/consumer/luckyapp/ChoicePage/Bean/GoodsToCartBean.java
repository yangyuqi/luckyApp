package com.yunqilai.consumer.luckyapp.ChoicePage.Bean;

/**
 * Created by Administrator on 2017/6/27.
 */

public class GoodsToCartBean {

    private String accessToken ;
    private GoodsData goods ;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public GoodsData getGoods() {
        return goods;
    }

    public void setGoods(GoodsData goods) {
        this.goods = goods;
    }

    public GoodsToCartBean(String accessToken, GoodsData goods) {

        this.accessToken = accessToken;
        this.goods = goods;
    }
}
