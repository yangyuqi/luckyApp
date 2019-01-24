package com.yunqilai.consumer.luckyapp.HomePage.Model;

import java.util.Map;

/**
 * Created by Administrator on 2017/6/28.
 */

public class MessageFristBean {
    private String code ;
    private String msg ;
    private Map<String,String> data ;

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

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public MessageFristBean(String code, String msg, Map<String, String> data) {

        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
