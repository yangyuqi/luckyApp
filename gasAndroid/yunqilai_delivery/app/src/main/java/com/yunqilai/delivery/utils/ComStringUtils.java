package com.yunqilai.delivery.utils;

import com.yunqilai.delivery.model.Order;

/**
 * Created by yangyuqi on 17-7-26.
 */

public class ComStringUtils {

    public static String getStatus(Order.STATU statu){
        if (String.valueOf(statu).equals("WAIT_ORDER")){
            return "wait_order";
        }else if (String.valueOf(statu).equals("WAIT_PICKUP")){
            return "wait_pickup";
        }else if (String.valueOf(statu).equals("WAIT_DELIVERY")){
            return "wait_delivery";
        }else if (String.valueOf(statu).equals("COMPLETE")){
            return "complete";
        }else if (String.valueOf(statu).equals("SELF_WAIT_EXTRACT")){
            return "self_wait_extract";
        }else {
            return "self_ complete";
        }
    }

    public static String getPayType(String type) {
        try {
            if (type.equals("0")) {
                return "在线支付";
            } else {
                return "货到付款";
            }
        } catch (Exception e) {

        }

        return "";
    }


}
