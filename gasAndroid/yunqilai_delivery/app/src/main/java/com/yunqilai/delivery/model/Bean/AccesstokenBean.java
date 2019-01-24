package com.yunqilai.delivery.model.Bean;

/**
 * Created by yangyuqi on 17-8-1.
 */

public class AccesstokenBean {
    private String accessToken ;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public AccesstokenBean(String accessToken) {

        this.accessToken = accessToken;
    }
}
