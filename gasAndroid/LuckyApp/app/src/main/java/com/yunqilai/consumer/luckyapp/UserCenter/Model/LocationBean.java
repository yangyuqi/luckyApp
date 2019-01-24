package com.yunqilai.consumer.luckyapp.UserCenter.Model;

import com.yunqilai.consumer.luckyapp.ShoppingCart.Bean.FloorBean;

import java.io.Serializable;

/**
 * Created by yangyuqi on 17-7-25.
 */

public class LocationBean implements Serializable{
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
    private String defaultVal ;
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

    public String getDefaultVal() {
        return defaultVal;
    }

    public void setDefaultVal(String defaultVal) {
        this.defaultVal = defaultVal;
    }

    public FloorBean getFloor() {
        return floor;
    }

    public void setFloor(FloorBean floor) {
        this.floor = floor;
    }

    public LocationBean(String id, String trueName, String mobile, String shengId, String shengName, String shiId, String shiName, String quId, String quName, String areaInfo, String defaultVal , FloorBean floor) {

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
        this.defaultVal = defaultVal;
        this.floor = floor ;

    }
}
