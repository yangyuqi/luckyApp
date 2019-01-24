package com.yunqilai.consumer.luckyapp.UserCenter.Model;

import java.util.List;

/**
 * Created by yangyuqi on 17-7-19.
 */

public class OrderInfoBean {
    private String orderId ;
    private String orderNo ;
    private String orderStatus ;
    private double totalPrice ;
    private int totalGoodsCount ;
    private List<OrderGoodsBean> goodsList ;
    private double goodsPrice ;
    private double shipPrice ;
    private double upFloorPrice ;
    private String shipType ;
    private String payType ;

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

    public OrderInfoBean(String orderId, String orderNo, String orderStatus, double totalPrice, int totalGoodsCount, List<OrderGoodsBean> goodsList, double goodsPrice, double shipPrice, double upFloorPrice, String shipType , String payType) {
        this.orderId = orderId;
        this.orderNo = orderNo;
        this.orderStatus = orderStatus;
        this.totalPrice = totalPrice;
        this.totalGoodsCount = totalGoodsCount;
        this.goodsList = goodsList;
        this.goodsPrice = goodsPrice;
        this.shipPrice = shipPrice;
        this.upFloorPrice = upFloorPrice;
        this.shipType = shipType;
        this.payType = payType ;

    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalGoodsCount() {
        return totalGoodsCount;
    }

    public void setTotalGoodsCount(int totalGoodsCount) {
        this.totalGoodsCount = totalGoodsCount;
    }

    public List<OrderGoodsBean> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<OrderGoodsBean> goodsList) {
        this.goodsList = goodsList;
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
}
