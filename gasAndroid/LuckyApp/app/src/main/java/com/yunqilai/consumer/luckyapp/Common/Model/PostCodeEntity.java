package com.yunqilai.consumer.luckyapp.Common.Model;

/**
 * Created by Administrator on 2017/5/31.
 */

public class PostCodeEntity {
    private String mobile ;
    private String type ;
    private String  client ;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public PostCodeEntity(String mobile, String type, String client) {

        this.mobile = mobile;
        this.type = type;
        this.client = client;
    }
}
