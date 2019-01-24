package com.yunqilai.consumer.luckyapp.UserCenter.Model;

/**
 * Created by yangyuqi on 17-7-28.
 */

public class ReasionBean {
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

    public ReasionBean(String reasonId, String reason) {

        this.reasonId = reasonId;
        this.reason = reason;
    }
}
