package com.yunqilai.consumer.luckyapp.UserCenter.Model;

/**
 * Created by yangyuqi on 17-8-8.
 */

public class AndroidTypeBean {
    private String type ;
    private String client ;

    public AndroidTypeBean(String type, String client) {
        this.type = type;
        this.client = client;
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
}
