package com.yunqilai.delivery.model.Bean.my;

/**
 * Created by yangyuqi on 17-8-9.
 */

public class NickNameBean {
    private String accessToken ;
    private String nickName ;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public NickNameBean(String accessToken, String nickName) {

        this.accessToken = accessToken;
        this.nickName = nickName;
    }
}
