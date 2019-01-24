package com.yunqilai.consumer.luckyapp.UserCenter.Model;

import java.util.List;

/**
 * Created by Administrator on 2017/8/17.
 */

public class ParseRefundBean {
    private String status ;
    private double refundPrice ;
    private String reason ;
    private String message;
    private String date ;
    private String refundNo ;
    private List<RefundGoodsBean> goodsList ;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getRefundPrice() {
        return refundPrice;
    }

    public void setRefundPrice(double refundPrice) {
        this.refundPrice = refundPrice;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRefundNo() {
        return refundNo;
    }

    public void setRefundNo(String refundNo) {
        this.refundNo = refundNo;
    }

    public List<RefundGoodsBean> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<RefundGoodsBean> goodsList) {
        this.goodsList = goodsList;
    }

    public ParseRefundBean(String status, double refundPrice, String reason, String message, String date, String refundNo, List<RefundGoodsBean> goodsList) {

        this.status = status;
        this.refundPrice = refundPrice;
        this.reason = reason;
        this.message = message;
        this.date = date;
        this.refundNo = refundNo;
        this.goodsList = goodsList;
    }
}
