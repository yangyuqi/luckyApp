package com.yunqilai.delivery.model.Bean.Info;

/**
 * Created by yangyuqi on 17-8-2.
 */

public class ActionOperateBean {
    private String accessToken ;
    private String tankId ;
    private String operate ;
    private String orderId ;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTankId() {
        return tankId;
    }

    public void setTankId(String tankId) {
        this.tankId = tankId;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public ActionOperateBean(String accessToken, String tankId, String operate, String orderId) {

        this.accessToken = accessToken;
        this.tankId = tankId;
        this.operate = operate;
        this.orderId = orderId;
    }
}
