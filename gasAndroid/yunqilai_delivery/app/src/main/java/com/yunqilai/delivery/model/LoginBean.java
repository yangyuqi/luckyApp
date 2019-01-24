package com.yunqilai.delivery.model;

/**
 * Created by yangyuqi on 17-7-25.
 */

public class LoginBean {
    private String username ;
    private String password ;
    private String type ;
    private String device ;

    public LoginBean(String username, String password, String type, String device) {
        this.username = username;
        this.password = password;
        this.type = type;
        this.device = device;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
}
