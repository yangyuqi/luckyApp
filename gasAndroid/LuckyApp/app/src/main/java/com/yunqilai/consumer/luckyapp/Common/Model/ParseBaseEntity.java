package com.yunqilai.consumer.luckyapp.Common.Model;

/**
 * Created by Administrator on 2017/5/31.
 */

public class ParseBaseEntity<T> {
    private String code ;
    private String msg ;
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ParseBaseEntity(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private T data ;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
