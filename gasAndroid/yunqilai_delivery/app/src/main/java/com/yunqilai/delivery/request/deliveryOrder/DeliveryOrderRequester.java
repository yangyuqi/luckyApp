package com.yunqilai.delivery.request.deliveryOrder;

import android.content.Context;

import com.yunqilai.delivery.request.base.BaseRequester;
import com.yunqilai.delivery.request.base.network.Server;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by KK on 2017/6/20.
 */

public class DeliveryOrderRequester extends BaseRequester {

    /**
     * 根据用户id获取所有订单信息
     * @param context
     * @param orderStatus “wait_order” 待接单 “wait_pickup” 待取货 “wait_delivery” 待配送 “complete” 已完成 “self_wait_extract” 自提订单待提取 “self_ complete” 自提订单已完成
     * @param pageNo
     * @param pageSize
     * @param selectOrderAllListener
     */
    public void queryGoodsClassAll(Context context,String orderStatus,int pageNo,int pageSize, SelectOrderAllListener selectOrderAllListener){
        Map<String, Object> params = new HashMap<>();
        params.put("orderStatus",orderStatus);
        params.put("pageNo",pageNo);
        params.put("pageSize",pageSize);
        post(context, Server.getUrl("deliveryOrder/selectOrderAll"),params,selectOrderAllListener,new SelectOrderAllParser(selectOrderAllListener));
    }

}
