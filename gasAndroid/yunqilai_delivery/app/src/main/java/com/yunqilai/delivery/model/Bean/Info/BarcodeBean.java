package com.yunqilai.delivery.model.Bean.Info;

/**
 * Created by Administrator on 2017/8/17 0017.
 */

public class BarcodeBean {

    public BarcodeBean(){}

    public BarcodeBean(String accessToken,String barCode){
        this.accessToken = accessToken;
        this.barCode = barCode;
    }
    private String accessToken;
    private String barCode;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }
}
