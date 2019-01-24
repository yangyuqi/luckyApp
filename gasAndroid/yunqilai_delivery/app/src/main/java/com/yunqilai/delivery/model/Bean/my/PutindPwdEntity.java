package com.yunqilai.delivery.model.Bean.my;

/**
 * Created by Administrator on 2017/8/11.
 */

public class PutindPwdEntity {
    private String mobile ;
    private String newpassword ;
    private String smsCode ;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public PutindPwdEntity(String mobile, String newpassword, String smsCode) {

        this.mobile = mobile;
        this.newpassword = newpassword;
        this.smsCode = smsCode;
    }
}
