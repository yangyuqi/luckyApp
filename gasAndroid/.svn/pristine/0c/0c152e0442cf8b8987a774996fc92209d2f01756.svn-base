package com.yunqilai.delivery.model;

import java.io.Serializable;
import java.util.List;

/**
 * 订单
 * Created by KK on 2017/6/7.
 */

public class Order implements Serializable {

    private String id;
    private STATU statu;
    private PAY_TYPE payType;
    private PAY_STATU payStatu;
    private ORDER_TYPE orderType;
    private DELIVERY_TYPE deliveryType;
    //订单编号
    private String orderNO;
    //收货地址
    private Address address;
    //配送时间
    private String deliveryTime;
    //商品数量
    private int productCount;
    //订单价格
    private double orderPrice;
    //商品列表
    private List<Product> productList;
    //商品总价格
    private double productTotalPrice;
    //上楼费
    private double upFloorPrice;
    //运费
    private double deliveryPrice;
    //下单时间
    private String orderTime;
    //支付时间
    private String payTime;
    //取货时间
    private String pickupTime;
    //送达时间
    private String arrivalTime;
    //拒单时间
    private String refuseTime;
    //拒单原因
    private RefuseOrderReason refuseOrderReason;

    public enum STATU{
        //
        NULL,
        //待接单
        WAIT_ORDER,
        //待取货
        WAIT_PICKUP,
        //待配送
        WAIT_DELIVERY,
        //已完成
        COMPLETE,
        //自提订单待提取
        SELF_WAIT_EXTRACT,
        //自提订单已完成
        SELF_COMPLETE
    }

    public enum PAY_TYPE{
        //
        NULL,
        //在线支付
        ONLINE,
        //货到付款
        ON_DELIVERY
    }
    public enum PAY_STATU{
        //
        NULL,
        //待付款
        WAIT_PAY,
        //已付款
        HAS_PAY
    }
    public enum ORDER_TYPE{
        //
        NULL,
        //线上订单
        ONLINE_ORDER,
        //代客下单
        AGENT_ORDER
    }

    public enum DELIVERY_TYPE{
        //配送
        DELIVERY,
        //自提
        SINCE
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNO() {
        return orderNO;
    }

    public void setOrderNO(String orderNO) {
        this.orderNO = orderNO;
    }

    public STATU getStatu() {
        return statu;
    }

    public void setStatu(STATU statu) {
        this.statu = statu;
    }

    public PAY_TYPE getPayType() {
        return payType;
    }

    public void setPayType(PAY_TYPE payType) {
        this.payType = payType;
    }

    public PAY_STATU getPayStatu() {
        return payStatu;
    }

    public double getUpFloorPrice() {
        return upFloorPrice;
    }

    public void setUpFloorPrice(double upFloorPrice) {
        this.upFloorPrice = upFloorPrice;
    }

    public void setPayStatu(PAY_STATU payStatu) {
        this.payStatu = payStatu;
    }

    public ORDER_TYPE getOrderType() {
        return orderType;
    }

    public void setOrderType(ORDER_TYPE orderType) {
        this.orderType = orderType;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public double getProductTotalPrice() {
        return productTotalPrice;
    }

    public void setProductTotalPrice(double productTotalPrice) {
        this.productTotalPrice = productTotalPrice;
    }

    public double getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(double deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
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

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getRefuseTime() {
        return refuseTime;
    }

    public void setRefuseTime(String refuseTime) {
        this.refuseTime = refuseTime;
    }

    public RefuseOrderReason getRefuseOrderReason() {
        return refuseOrderReason;
    }

    public void setRefuseOrderReason(RefuseOrderReason refuseOrderReason) {
        this.refuseOrderReason = refuseOrderReason;
    }

    public DELIVERY_TYPE getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DELIVERY_TYPE deliveryType) {
        this.deliveryType = deliveryType;
    }
}
