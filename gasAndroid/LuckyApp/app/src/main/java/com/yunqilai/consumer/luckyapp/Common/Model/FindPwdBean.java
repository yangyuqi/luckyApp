package com.yunqilai.consumer.luckyapp.Common.Model;

/**
 * Created by yangyuqi on 17-7-25.
 */

public class FindPwdBean {
    private String mobile ;
    private String newpassword ;
    private String smsCode ;

    public FindPwdBean(String mobile, String newpassword, String smsCode) {
        this.mobile = mobile;
        this.newpassword = newpassword;
        this.smsCode = smsCode;
    }

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
}
