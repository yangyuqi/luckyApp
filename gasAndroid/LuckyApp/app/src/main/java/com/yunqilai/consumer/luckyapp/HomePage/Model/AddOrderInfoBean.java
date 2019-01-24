package com.yunqilai.consumer.luckyapp.HomePage.Model;

/**
 * Created by yangyuqi on 17-7-17.
 */

public class AddOrderInfoBean {
    private String cartId ;
    private String goodsId ;
    private String specId ;
    private int count ;

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getSpecId() {
        return specId;
    }

    public void setSpecId(String specId) {
        this.specId = specId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public AddOrderInfoBean(String cartId, String goodsId, String specId, int count) {

        this.cartId = cartId;
        this.goodsId = goodsId;
        this.specId = specId;
        this.count = count;
    }
}
