package com.yunqilai.delivery.model.Bean.my;

/**
 * Created by yangyuqi on 17-8-9.
 */

public class AgainPwdBean {
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

    public AgainPwdBean(String accessToken, String password, String newpassword) {

        this.accessToken = accessToken;
        this.password = password;
        this.newpassword = newpassword;
    }
}
