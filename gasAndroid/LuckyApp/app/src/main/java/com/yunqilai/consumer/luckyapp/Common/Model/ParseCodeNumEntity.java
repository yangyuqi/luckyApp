package com.yunqilai.consumer.luckyapp.Common.Model;

/**
 * Created by Administrator on 2017/5/31.
 */

public class ParseCodeNumEntity {
    private String smsCode;

    public ParseCodeNumEntity(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }
}
