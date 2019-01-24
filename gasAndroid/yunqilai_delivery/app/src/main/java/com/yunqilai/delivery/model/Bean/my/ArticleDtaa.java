package com.yunqilai.delivery.model.Bean.my;

import java.util.List;

/**
 * Created by Administrator on 2017/8/11.
 */

public class ArticleDtaa {
    private String msg ;
    private String code ;
    private List<ArticleBean> data ;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<ArticleBean> getData() {
        return data;
    }

    public void setData(List<ArticleBean> data) {
        this.data = data;
    }

    public ArticleDtaa(String msg, String code, List<ArticleBean> data) {

        this.msg = msg;
        this.code = code;
        this.data = data;
    }
}
