package com.yunqilai.delivery.request.deliveryOrder;


import android.support.annotation.Nullable;

import com.yunqilai.delivery.model.Address;
import com.yunqilai.delivery.model.Order;
import com.yunqilai.delivery.model.response.OrderData;
import com.yunqilai.delivery.model.response.SelectOrderAllData;
import com.yunqilai.delivery.request.base.AbsBaseParser;
import com.yunqilai.delivery.request.base.OnBaseRequestListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 根据用户id获取所有订单信息 接口 解析器
 * Created by KK on 2017/6/20.
 */

public class SelectOrderAllParser extends AbsBaseParser {
    public SelectOrderAllParser(OnBaseRequestListener onBaseBizParseListener) {
        super(onBaseBizParseListener);
    }

    @Override
    protected void parseData(@Nullable String data) {
        SelectOrderAllData selectOrderAllData = mDataParser.parseObject(data,SelectOrderAllData.class);
        List<OrderData> orderDatas = selectOrderAllData.getOrders();

        List<Order> orderList = mapped(orderDatas);

        SelectOrderAllListener listener = (SelectOrderAllListener)mOnBaseRequestListener.get();
        if(listener!=null){
            listener.onSelectOrderAllSuccess(selectOrderAllData.getCount(),orderList);
        }
    }

    //映射到app模型
    private List<Order> mapped(List<OrderData> orderDatas){
        List<Order> orders = new ArrayList<>();
        if(orderDatas !=null){
            for(OrderData orderData : orderDatas){
                Order order = new Order();
                order.setId(orderData.getOrderId());
                order.setOrderNO(orderData.getOrderNumber());
                Address address = new Address();
                address.setUsername(orderData.getBuyerName());
                address.setUserphone(orderData.getPhone());
                address.setDetail(orderData.getAddress());
                order.setAddress(address);
                order.setDeliveryTime(orderData.getShipTime());
                order.setProductCount(orderData.getGoodsCount());
                order.setOrderPrice(orderData.getTotalPrice());
                switch (orderData.getOrderStatus()){
                    case "wait_order":
                        order.setStatu(Order.STATU.WAIT_ORDER);
                        break;
                    case "wait_pickup":
                        order.setStatu(Order.STATU.WAIT_PICKUP);
                        break;
                    case "wait_delivery":
                        order.setStatu(Order.STATU.WAIT_DELIVERY);
                        break;
                    case "complete":
                        order.setStatu(Order.STATU.COMPLETE);
                        break;
                    case "self_wait_extract":
                        order.setStatu(Order.STATU.SELF_WAIT_EXTRACT);
                        break;
                    case "self_complete":
                        order.setStatu(Order.STATU.SELF_COMPLETE);
                        break;
                }
                switch (orderData.getShipType()){
                    case "0":
                        order.setDeliveryType(Order.DELIVERY_TYPE.DELIVERY);
                        break;
                    case "1":
                        order.setDeliveryType(Order.DELIVERY_TYPE.SINCE);
                        break;
                }
                switch (orderData.getPayType()){
                    case "0":
                        order.setPayType(Order.PAY_TYPE.ONLINE);
                        break;
                    case "1":
                        order.setPayType(Order.PAY_TYPE.ON_DELIVERY);
                        break;
                }
                orders.add(order);
            }
        }

        return orders;
    }
}
