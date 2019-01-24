package com.yunqilai.delivery.model.Bean.Info;

import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by yangyuqi on 17-8-2.
 */

public class InfoDetailsBean implements Serializable {
    private String tankId ;
    private String tankNumber ;
    private String barCode ;
    private String model ;
    private String produceTime ;

    public String getTankId() {
        return tankId;
    }

    public void setTankId(String tankId) {
        this.tankId = tankId;
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

    public InfoDetailsBean(String tankId, String tankNumber, String barCode, String model, String produceTime) {

        this.tankId = tankId;
        this.tankNumber = tankNumber;
        this.barCode = barCode;
        this.model = model;
        this.produceTime = produceTime;
    }
}
