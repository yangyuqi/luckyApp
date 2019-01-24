package com.yunqilai.delivery.model.Bean.my;

/**
 * Created by yangyuqi on 17-7-27.
 */

public class AboutUsBean {
    private String content ;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public AboutUsBean(String content) {

        this.content = content;
    }
}
