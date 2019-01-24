package com.yunqilai.consumer.luckyapp.Common.Model;

/**
 * Created by Administrator on 2017/8/17.
 */

public class RefreshTokenBean {
    private String refreshToken ;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public RefreshTokenBean(String refreshToken) {

        this.refreshToken = refreshToken;
    }
}
