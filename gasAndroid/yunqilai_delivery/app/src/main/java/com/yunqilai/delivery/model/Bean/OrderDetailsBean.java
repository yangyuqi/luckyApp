package com.yunqilai.delivery.model.Bean;

/**
 * Created by yangyuqi on 17-7-26.
 */

public class OrderDetailsBean {
    private String orderId ;
    private String orderNumber ;
    private String buyerName ;
    private String phone ;
    private String address ;
    private String shipTime ;
    private int goodsCount ;
    private double totalPrice ;
    private double goodsPrice ;
    private double shipPrice ;
    private double upFloorPrice ;
    private String orderStatus ;
    private String shipType ;
    private String payType ;
    //拒单时间
    private String refuseTime;
    //拒单原因
    private String refuseReason;

    public OrderDetailsBean(String orderId, String orderNumber, String buyerName, String phone, String address, String shipTime, int goodsCount, double totalPrice, double goodsPrice, double shipPrice, double upFloorPrice, String orderStatus, String shipType, String payType) {
        this.orderId = orderId;
        this.orderNumber = orderNumber;
        this.buyerName = buyerName;
        this.phone = phone;
        this.address = address;
        this.shipTime = shipTime;
        this.goodsCount = goodsCount;
        this.totalPrice = totalPrice;
        this.goodsPrice = goodsPrice;
        this.shipPrice = shipPrice;
        this.upFloorPrice = upFloorPrice;
        this.orderStatus = orderStatus;
        this.shipType = shipType;
        this.payType = payType;
    }

    public String getRefuseTime() {
        return refuseTime;
    }

    public void setRefuseTime(String refuseTime) {
        this.refuseTime = refuseTime;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getShipTime() {
        return shipTime;
    }

    public void setShipTime(String shipTime) {
        this.shipTime = shipTime;
    }

    public int getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public double getShipPrice() {
        return shipPrice;
    }

    public void setShipPrice(double shipPrice) {
        this.shipPrice = shipPrice;
    }

    public double getUpFloorPrice() {
        return upFloorPrice;
    }

    public void setUpFloorPrice(double upFloorPrice) {
        this.upFloorPrice = upFloorPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getShipType() {
        return shipType;
    }

    public void setShipType(String shipType) {
        this.shipType = shipType;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }
}
