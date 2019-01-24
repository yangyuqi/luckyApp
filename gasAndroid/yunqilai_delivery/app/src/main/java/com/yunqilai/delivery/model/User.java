package com.yunqilai.delivery.model;

import java.io.Serializable;

/**
 * 用户
 * Created by KK on 2017/6/7.
 */

public class User implements Serializable {

    private String id;
    private String name;
    private String icon;
    private String phone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
