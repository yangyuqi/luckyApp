package com.yunqilai.delivery.model;

import java.io.Serializable;

/**
 * 收货地址
 * Created by KK on 2017/6/7.
 */

public class Address implements Serializable {
    private String id;
    private String username;
    private String userphone;
    private String detail;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
