package com.yunqilai.delivery.model.Bean.Info;

/**
 * Created by yangyuqi on 17-8-3.
 */

public class ReplaceBarBean {
    private String accessToken ;
    private String tankId ;
    private String barCode ;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTankId() {
        return tankId;
    }

    public void setTankId(String tankId) {
        this.tankId = tankId;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public ReplaceBarBean(String accessToken, String tankId, String barCode) {

        this.accessToken = accessToken;
        this.tankId = tankId;
        this.barCode = barCode;
    }
}
