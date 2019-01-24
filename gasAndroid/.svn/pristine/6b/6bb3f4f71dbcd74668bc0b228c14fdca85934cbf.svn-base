package com.yunqilai.consumer.luckyapp.UserCenter.Interface;

import com.yunqilai.consumer.luckyapp.UserCenter.Model.OrderGoodsBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yangyuqi on 17-8-5.
 */

public class SendData implements Serializable {
    private List<OrderGoodsBean> list ;
    private String orderId ;

    public List<OrderGoodsBean> getList() {
        return list;
    }

    public void setList(List<OrderGoodsBean> list) {
        this.list = list;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public SendData(List<OrderGoodsBean> list, String orderId) {

        this.list = list;
        this.orderId = orderId;
    }
}
