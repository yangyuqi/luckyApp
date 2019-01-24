package com.yunqilai.consumer.luckyapp.ShoppingCart.Bean;

/**
 * Created by Administrator on 2017/6/29.
 */

public class CartUserBean {
    private String shopCartId ;
    private int count ;

    public String getShopCartId() {
        return shopCartId;
    }

    public void setShopCartId(String shopCartId) {
        this.shopCartId = shopCartId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public CartUserBean(String shopCartId, int count) {

        this.shopCartId = shopCartId;
        this.count = count;
    }
}
