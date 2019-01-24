package com.yunqilai.consumer.luckyapp.HomePage.Model;

/**
 * Created by Administrator on 2017/6/27.
 */

public class DetailsGoodsBean {
    private String code ;
    private String msg ;
    private DetailsGoodsDataBean data ;

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

    public DetailsGoodsDataBean getData() {
        return data;
    }

    public void setData(DetailsGoodsDataBean data) {
        this.data = data;
    }

    public DetailsGoodsBean(String code, String msg, DetailsGoodsDataBean data) {

        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
