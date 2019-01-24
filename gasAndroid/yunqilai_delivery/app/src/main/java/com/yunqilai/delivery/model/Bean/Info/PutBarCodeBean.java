package com.yunqilai.delivery.model.Bean.Info;

/**
 * Created by yangyuqi on 17-8-2.
 */

public class PutBarCodeBean {
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

    public PutBarCodeBean(String accessToken, String barCode) {

        this.accessToken = accessToken;
        this.barCode = barCode;
    }
}
