package com.yunqilai.delivery.model;

import java.io.Serializable;

/**
 * 瓶罐
 * Created by KK on 2017/6/7.
 */

public class Tank implements Serializable {

    private String id;

    private STATU statu;
    //用户
    private User user;
    //条形编码
    private String barCode;
    //瓶罐编码
    private String tankCode;
    //瓶罐型号
    private String tankModel;
    //生产日期
    private String produceTime;
    //已使用次数
    private int useTimes;
    //下次例检时间
    private String nextCheckTime;
    //瓶罐到期时间
    private String expirationTime;
    //已例检次数
    private int checkTimes;
    //操作员
    private String operator;
    //操作时间
    private String operateTime;

    //能否去补码
    private boolean canReplaceCode;

    public enum STATU{
        //
        NULL,
        //库中
        IN_DEPOT,
        //入库
        ENTER_DEPOT,
        //库外
        OUT_DEPOT,
        //例检中
        IN_CHECK,
        //配送中
        IN_DELIVERY,
        //使用中
        IN_USE,
        //已补码
        IN_CODE

    }

    public boolean isCanReplaceCode() {
        return canReplaceCode;
    }

    public void setCanReplaceCode(boolean canReplaceCode) {
        this.canReplaceCode = canReplaceCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public STATU getStatu() {
        return statu;
    }

    public void setStatu(STATU statu) {
        this.statu = statu;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getTankCode() {
        return tankCode;
    }

    public void setTankCode(String tankCode) {
        this.tankCode = tankCode;
    }

    public String getTankModel() {
        return tankModel;
    }

    public void setTankModel(String tankModel) {
        this.tankModel = tankModel;
    }

    public String getProduceTime() {
        return produceTime;
    }

    public void setProduceTime(String produceTime) {
        this.produceTime = produceTime;
    }

    public int getUseTimes() {
        return useTimes;
    }

    public void setUseTimes(int useTimes) {
        this.useTimes = useTimes;
    }

    public String getNextCheckTime() {
        return nextCheckTime;
    }

    public void setNextCheckTime(String nextCheckTime) {
        this.nextCheckTime = nextCheckTime;
    }

    public String getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(String expirationTime) {
        this.expirationTime = expirationTime;
    }

    public int getCheckTimes() {
        return checkTimes;
    }

    public void setCheckTimes(int checkTimes) {
        this.checkTimes = checkTimes;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }
}
