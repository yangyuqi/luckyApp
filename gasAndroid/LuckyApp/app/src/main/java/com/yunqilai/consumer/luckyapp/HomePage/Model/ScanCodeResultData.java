package com.yunqilai.consumer.luckyapp.HomePage.Model;

import java.util.Map;

/**
 * Created by Administrator on 2017/6/28.
 */

public class ScanCodeResultData {
    private String code ;
    private String msg ;
    private BottleInfoBean data ;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public BottleInfoBean getData() {
        return data;
    }

    public void setData(BottleInfoBean data) {
        this.data = data;
    }

    public ScanCodeResultData(String code, String msg, BottleInfoBean data) {

        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
