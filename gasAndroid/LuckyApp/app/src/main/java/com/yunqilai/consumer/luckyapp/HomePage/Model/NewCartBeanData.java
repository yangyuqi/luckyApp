package com.yunqilai.consumer.luckyapp.HomePage.Model;

/**
 * Created by yangyuqi on 17-7-13.
 */

public class NewCartBeanData {
    private String cartId ;
    private String goodsId ;
    private String specId ;
    private int count ;

    private String img_url ;
    private String title ;
    private String price ;
    private String specIdName ;

    public String getSpecIdName() {
        return specIdName;
    }

    public void setSpecIdName(String specIdName) {
        this.specIdName = specIdName;
    }

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

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public NewCartBeanData(String cartId, String goodsId, String specId, int count, String img_url, String title, String price ,String specIdName) {

        this.cartId = cartId;
        this.goodsId = goodsId;
        this.specId = specId;
        this.count = count;
        this.img_url = img_url;
        this.title = title;
        this.price = price;
        this.specIdName = specIdName ;
    }
}
