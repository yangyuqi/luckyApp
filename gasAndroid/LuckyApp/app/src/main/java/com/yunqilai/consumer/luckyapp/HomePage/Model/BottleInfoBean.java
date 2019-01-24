package com.yunqilai.consumer.luckyapp.HomePage.Model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/28.
 */

public class BottleInfoBean implements Serializable{
    private String tankId ;
    private String buyerName ;
    private String  phone ;
    private String nextCheckTime ;
    private String tankExpiredTime ;
    private String checkTimes ;
    private String tankNumber ;
    private String barCode ;
    private String model ;
    private String produceTime ;
    private String useTimes ;
    private String operater ;
    private String operateTime ;
    private String tankStatus ;

    private String idCardNumber ;
    private String address ;

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTankId() {
        return tankId;
    }

    public void setTankId(String tankId) {
        this.tankId = tankId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNextCheckTime() {
        return nextCheckTime;
    }

    public void setNextCheckTime(String nextCheckTime) {
        this.nextCheckTime = nextCheckTime;
    }

    public String getTankExpiredTime() {
        return tankExpiredTime;
    }

    public void setTankExpiredTime(String tankExpiredTime) {
        this.tankExpiredTime = tankExpiredTime;
    }

    public String getCheckTimes() {
        return checkTimes;
    }

    public void setCheckTimes(String checkTimes) {
        this.checkTimes = checkTimes;
    }

    public String getTankNumber() {
        return tankNumber;
    }

    public void setTankNumber(String tankNumber) {
        this.tankNumber = tankNumber;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProduceTime() {
        return produceTime;
    }

    public void setProduceTime(String produceTime) {
        this.produceTime = produceTime;
    }

    public String getUseTimes() {
        return useTimes;
    }

    public void setUseTimes(String useTimes) {
        this.useTimes = useTimes;
    }

    public String getOperater() {
        return operater;
    }

    public void setOperater(String operater) {
        this.operater = operater;
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    public String getTankStatus() {
        return tankStatus;
    }

    public void setTankStatus(String tankStatus) {
        this.tankStatus = tankStatus;
    }

    public BottleInfoBean(String tankId, String buyerName, String phone, String nextCheckTime, String tankExpiredTime, String checkTimes, String tankNumber, String barCode, String model, String produceTime, String useTimes, String operater, String operateTime, String tankStatus) {

        this.tankId = tankId;
        this.buyerName = buyerName;
        this.phone = phone;
        this.nextCheckTime = nextCheckTime;
        this.tankExpiredTime = tankExpiredTime;
        this.checkTimes = checkTimes;
        this.tankNumber = tankNumber;
        this.barCode = barCode;
        this.model = model;
        this.produceTime = produceTime;
        this.useTimes = useTimes;
        this.operater = operater;
        this.operateTime = operateTime;
        this.tankStatus = tankStatus;
    }
}
