package com.yunqilai.consumer.luckyapp.ShoppingCart.Bean;

/**
 * Created by yangyuqi on 17-7-27.
 */

public class PostPositionBean {
    private String accessToken ;
    private String trueName ;
    private String mobile ;
    private String areaId ;
    private String areaInfo ;
    private String liftId ;
    private String defaultVal ;

    public PostPositionBean(String accessToken, String trueName, String mobile, String areaId, String areaInfo, String liftId, String defaultVal) {
        this.accessToken = accessToken;
        this.trueName = trueName;
        this.mobile = mobile;
        this.areaId = areaId;
        this.areaInfo = areaInfo;
        this.liftId = liftId;
        this.defaultVal = defaultVal;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaInfo() {
        return areaInfo;
    }

    public void setAreaInfo(String areaInfo) {
        this.areaInfo = areaInfo;
    }

    public String getLiftId() {
        return liftId;
    }

    public void setLiftId(String liftId) {
        this.liftId = liftId;
    }

    public String getDefaultVal() {
        return defaultVal;
    }

    public void setDefaultVal(String defaultVal) {
        this.defaultVal = defaultVal;
    }
}
