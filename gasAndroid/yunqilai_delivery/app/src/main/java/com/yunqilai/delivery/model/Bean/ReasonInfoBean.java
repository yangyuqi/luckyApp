package com.yunqilai.delivery.model.Bean;

/**
 * Created by yangyuqi on 17-8-1.
 */

public class ReasonInfoBean {
    private String reasonId ;
    private String reason ;

    public String getReasonId() {
        return reasonId;
    }

    public void setReasonId(String reasonId) {
        this.reasonId = reasonId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ReasonInfoBean(String reasonId, String reason) {

        this.reasonId = reasonId;
        this.reason = reason;
    }
}
