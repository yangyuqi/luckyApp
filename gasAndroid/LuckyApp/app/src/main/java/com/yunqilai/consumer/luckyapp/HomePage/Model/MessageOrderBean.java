package com.yunqilai.consumer.luckyapp.HomePage.Model;

import com.yunqilai.consumer.luckyapp.UserCenter.Model.OrderGoodsBean;

import java.util.List;
import java.util.Map;

/**
 * Created by yangyuqi on 17-8-9.
 */

public class MessageOrderBean {

    private List<OrderGoodsBean> goodsList ;
    private String orderStatus ;
    private String orderNo ;

    public List<OrderGoodsBean> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<OrderGoodsBean> goodsList) {
        this.goodsList = goodsList;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public MessageOrderBean(List<OrderGoodsBean> goodsList, String orderStatus, String orderNo) {

        this.goodsList = goodsList;
        this.orderStatus = orderStatus;
        this.orderNo = orderNo;
    }
}
