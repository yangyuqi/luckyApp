package com.yunqilai.delivery.model;

import java.io.Serializable;

/**
 * Created by KK on 2017/6/14.
 */

public class TankOperate implements Serializable {
    private String id;
    private int iconId;
    private String title;
    private boolean isChoose;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isChoose() {
        return isChoose;
    }

    public void setChoose(boolean choose) {
        isChoose = choose;
    }
}
