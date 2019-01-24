package com.yunqilai.consumer.luckyapp.HomePage.Model;

/**
 * Created by Administrator on 2017/6/28.
 */

public class AdverInfoBean {
    private String id ;
    private String title ;
    private String photo ;
    private String forwardType ;
    private String forwardId ;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getForwardType() {
        return forwardType;
    }

    public void setForwardType(String forwardType) {
        this.forwardType = forwardType;
    }

    public String getForwardId() {
        return forwardId;
    }

    public void setForwardId(String forwardId) {
        this.forwardId = forwardId;
    }

    public AdverInfoBean(String id, String title, String photo, String forwardType, String forwardId) {

        this.id = id;
        this.title = title;
        this.photo = photo;
        this.forwardType = forwardType;
        this.forwardId = forwardId;
    }
}
