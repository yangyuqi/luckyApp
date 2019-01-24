package com.yunqilai.consumer.luckyapp.UserCenter.Model;

/**
 * Created by yangyuqi on 17-8-8.
 */

public class DownLoadBean {
    private String address ;
    private String comment ;
    private String version ;
    private String apptybe ;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getApptybe() {
        return apptybe;
    }

    public void setApptybe(String apptybe) {
        this.apptybe = apptybe;
    }

    public DownLoadBean(String address, String comment, String version, String apptybe) {

        this.address = address;
        this.comment = comment;
        this.version = version;
        this.apptybe = apptybe;
    }
}
