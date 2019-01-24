package com.yunqilai.consumer.luckyapp.ShoppingCart.Bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/29.
 */

public class EditCartNumBean {
    private String accessToken ;
    private List<CartUserBean> carts ;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public List<CartUserBean> getCarts() {
        return carts;
    }

    public void setCarts(List<CartUserBean> carts) {
        this.carts = carts;
    }

    public EditCartNumBean(String accessToken, List<CartUserBean> carts) {

        this.accessToken = accessToken;
        this.carts = carts;
    }
}
