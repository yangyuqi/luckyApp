package com.yunqilai.consumer.luckyapp.ChoicePage.Bean;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/26.
 */

public class ChoicePageBean {
    private String code ;
    private String msg ;
    private Map<String , List<ChoiceBean>> data ;

    public ChoicePageBean(String code, String msg, Map<String, List<ChoiceBean>> data) {
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

    public Map<String, List<ChoiceBean>> getData() {
        return data;
    }

    public void setData(Map<String, List<ChoiceBean>> data) {
        this.data = data;
    }
}
