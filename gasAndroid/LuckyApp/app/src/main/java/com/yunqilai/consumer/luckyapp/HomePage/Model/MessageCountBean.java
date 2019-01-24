package com.yunqilai.consumer.luckyapp.HomePage.Model;

/**
 * Created by Administrator on 2017/6/28.
 */

public class MessageCountBean {
    private int count ;
    private String content ;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MessageCountBean(int count, String content) {

        this.count = count;
        this.content = content;
    }
}
