package com.yunqilai.consumer.luckyapp.ShoppingCart.Bean;

/**
 * Created by yangyuqi on 17-7-27.
 */

public class ParseAddressDetailsBean {

    private String id ;
    private String trueName ;
    private String mobile ;
    private String shengId ;
    private String shengName ;
    private String shiId ;
    private String shiName ;
    private String quId ;
    private String quName ;
    private String areaInfo ;
    private FloorBean floor ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getShengId() {
        return shengId;
    }

    public void setShengId(String shengId) {
        this.shengId = shengId;
    }

    public String getShengName() {
        return shengName;
    }

    public void setShengName(String shengName) {
        this.shengName = shengName;
    }

    public String getShiId() {
        return shiId;
    }

    public void setShiId(String shiId) {
        this.shiId = shiId;
    }

    public String getShiName() {
        return shiName;
    }

    public void setShiName(String shiName) {
        this.shiName = shiName;
    }

    public String getQuId() {
        return quId;
    }

    public void setQuId(String quId) {
        this.quId = quId;
    }

    public String getQuName() {
        return quName;
    }

    public void setQuName(String quName) {
        this.quName = quName;
    }

    public String getAreaInfo() {
        return areaInfo;
    }

    public void setAreaInfo(String areaInfo) {
        this.areaInfo = areaInfo;
    }

    public FloorBean getFloor() {
        return floor;
    }

    public void setFloor(FloorBean floor) {
        this.floor = floor;
    }

    public ParseAddressDetailsBean(String id, String trueName, String mobile, String shengId, String shengName, String shiId, String shiName, String quId, String quName, String areaInfo, FloorBean floor) {

        this.id = id;
        this.trueName = trueName;
        this.mobile = mobile;
        this.shengId = shengId;
        this.shengName = shengName;
        this.shiId = shiId;
        this.shiName = shiName;
        this.quId = quId;
        this.quName = quName;
        this.areaInfo = areaInfo;
        this.floor = floor;
    }
}
