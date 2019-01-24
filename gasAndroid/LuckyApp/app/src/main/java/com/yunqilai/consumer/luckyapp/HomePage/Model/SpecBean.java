package com.yunqilai.consumer.luckyapp.HomePage.Model;

/**
 * Created by Administrator on 2017/8/16.
 */

public class SpecBean {
    private String specId ;
    private String specName ;

    public String getSpecId() {
        return specId;
    }

    public void setSpecId(String specId) {
        this.specId = specId;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public SpecBean(String specId, String specName) {

        this.specId = specId;
        this.specName = specName;
    }
}
