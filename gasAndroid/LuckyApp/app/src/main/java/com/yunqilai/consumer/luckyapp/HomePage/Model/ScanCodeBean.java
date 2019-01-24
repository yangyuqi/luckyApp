package com.yunqilai.consumer.luckyapp.HomePage.Model;

/**
 * Created by Administrator on 2017/6/28.
 */

public class ScanCodeBean {
    private String accessToken ;
    private String barCode ;

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

    public ScanCodeBean(String accessToken, String barCode) {

        this.accessToken = accessToken;
        this.barCode = barCode;
    }
}
