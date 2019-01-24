package com.yunqilai.consumer.luckyapp.SafeKnowledge.Bean;

import java.util.List;

/**
 * Created by yangyuqi on 17-7-27.
 */

public class SafeBeanData {
    private String code ;
    private String msg ;
    private List<SafeBean> data ;

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

    public List<SafeBean> getData() {
        return data;
    }

    public void setData(List<SafeBean> data) {
        this.data = data;
    }

    public SafeBeanData(String code, String msg, List<SafeBean> data) {

        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
