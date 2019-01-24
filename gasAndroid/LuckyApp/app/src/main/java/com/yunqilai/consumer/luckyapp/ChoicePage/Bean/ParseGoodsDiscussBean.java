package com.yunqilai.consumer.luckyapp.ChoicePage.Bean;

import java.util.List;

/**
 * Created by yangyuqi on 17-7-28.
 */

public class ParseGoodsDiscussBean {
    private String id ;
    private String icon ;
    private String userName ;
    private String date ;
    private int stars ;
    private String content ;
    private List<String> imgs ;

    public ParseGoodsDiscussBean(String id, String icon, String userName, String date, int stars, String content, List<String> imgs) {
        this.id = id;
        this.icon = icon;
        this.userName = userName;
        this.date = date;
        this.stars = stars;
        this.content = content;
        this.imgs = imgs;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }
}
