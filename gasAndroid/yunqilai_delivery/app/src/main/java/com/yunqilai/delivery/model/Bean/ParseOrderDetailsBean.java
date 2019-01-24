package com.yunqilai.delivery.model.Bean;

import java.util.List;

/**
 * Created by yangyuqi on 17-8-1.
 */

public class ParseOrderDetailsBean {
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
    private List<GoodsBean> goodsList ;
    private String addTime ;
    private String orderType ;
    private String payTime ;
    private String pickupTime ;
    private String arriveTime ;
    private String refuseTime ;
    private String refuseReason ;
    private String status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message ;




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

    public List<GoodsBean> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<GoodsBean> goodsList) {
        this.goodsList = goodsList;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ParseOrderDetailsBean(String orderId, String orderNumber, String buyerName, String phone, String address, String shipTime, int goodsCount, double totalPrice, double goodsPrice, double shipPrice, double upFloorPrice, String orderStatus, String shipType, String payType, List<GoodsBean> goodsList, String addTime, String orderType, String payTime, String pickupTime, String arriveTime, String refuseTime , String refuseReason) {

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
        this.goodsList = goodsList;
        this.addTime = addTime;
        this.orderType = orderType;
        this.payTime = payTime;
        this.pickupTime = pickupTime;
        this.arriveTime = arriveTime;
        this.refuseTime = refuseTime;
        this.refuseReason = refuseReason ;

    }
}
