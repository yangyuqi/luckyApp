package com.yunqilai.consumer.luckyapp.ShoppingCart.Bean;

/**
 * Created by Administrator on 2017/6/26.
 */

public class ShoppingCartUserBean {
    private String code ;
    private String msg ;
    private ShoppingDataBean data ;

    public ShoppingCartUserBean(String code, String msg, ShoppingDataBean data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ShoppingDataBean getData() {
        return data;
    }

    public void setData(ShoppingDataBean data) {
        this.data = data;
    }
}
