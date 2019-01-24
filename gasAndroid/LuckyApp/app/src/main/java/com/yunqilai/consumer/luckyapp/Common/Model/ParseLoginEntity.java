package com.yunqilai.consumer.luckyapp.Common.Model;

/**
 * Created by Administrator on 2017/5/31.
 */

public class ParseLoginEntity {
    private boolean needModifyPwd ;
    private String accessToken ;
    private String refreshToken ;
    private String userName ;
    private String attestation ;
    private String mobile ;
    private String icon ;
    private String role ;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public ParseLoginEntity(boolean needModifyPwd, String accessToken, String refreshToken, String userName, String attestation, String mobile , String icon , String role) {
        this.needModifyPwd = needModifyPwd;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.userName = userName;
        this.attestation = attestation;
        this.mobile = mobile;
        this.icon = icon ;
        this.role = role ;


    }

    public boolean isNeedModifyPwd() {
        return needModifyPwd;
    }

    public void setNeedModifyPwd(boolean needModifyPwd) {
        this.needModifyPwd = needModifyPwd;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAttestation() {
        return attestation;
    }

    public void setAttestation(String attestation) {
        this.attestation = attestation;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
