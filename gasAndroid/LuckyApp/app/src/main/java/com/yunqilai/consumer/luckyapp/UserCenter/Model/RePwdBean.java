package com.yunqilai.consumer.luckyapp.UserCenter.Model;

/**
 * Created by yangyuqi on 17-7-25.
 */

public class RePwdBean {
    private String accessToken ;
    private String password ;
    private String newpassword ;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public RePwdBean(String accessToken, String password, String newpassword) {

        this.accessToken = accessToken;
        this.password = password;
        this.newpassword = newpassword;
    }
}
