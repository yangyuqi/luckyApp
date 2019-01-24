package com.yunqilai.consumer.luckyapp.HomePage.Model;

/**
 * Created by Administrator on 2017/8/18.
 */

public class AttentionBean {
    private String attestationId ;
    private String status ;
    private String reason ;

    public String getAttestationId() {
        return attestationId;
    }

    public void setAttestationId(String attestationId) {
        this.attestationId = attestationId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public AttentionBean(String attestationId, String status, String reason) {

        this.attestationId = attestationId;
        this.status = status;
        this.reason = reason;
    }
}
