package com.yunqilai.consumer.luckyapp.HomePage.Model;

/**
 * Created by Administrator on 2017/8/18.
 */

public class AuthMessageBean {
    private String date ;
    private AttentionBean attestation ;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public AttentionBean getAttestation() {
        return attestation;
    }

    public void setAttestation(AttentionBean attestation) {
        this.attestation = attestation;
    }

    public AuthMessageBean(String date, AttentionBean attestation) {

        this.date = date;
        this.attestation = attestation;
    }
}
