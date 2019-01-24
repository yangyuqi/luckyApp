package com.yunqilai.consumer.luckyapp.UserCenter.Model;

import java.util.List;
import java.util.Map;

/**
 * Created by yangyuqi on 17-7-19.
 */

public class ParseOrderInfoBean {
    private String code ;
    private String msg ;
    private Map<String , List<OrderInfoBean>> data ;

    public ParseOrderInfoBean(String code, String msg, Map<String, List<OrderInfoBean>> data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

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

    public Map<String, List<OrderInfoBean>> getData() {
        return data;
    }

    public void setData(Map<String, List<OrderInfoBean>> data) {
        this.data = data;
    }
}
