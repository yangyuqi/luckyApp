package com.yunqilai.consumer.luckyapp.UserCenter.Model;

/**
 * Created by yangyuqi on 17-7-28.
 */

public class RefundPriceBean {
    private double price ;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public RefundPriceBean(double price) {

        this.price = price;
    }
}
