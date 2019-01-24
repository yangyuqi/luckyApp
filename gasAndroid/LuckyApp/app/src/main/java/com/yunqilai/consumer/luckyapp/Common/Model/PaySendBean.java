package com.yunqilai.consumer.luckyapp.Common.Model;

/**
 * Created by Administrator on 2017/8/16.
 */

public class PaySendBean {
    private String str_pay ;
    private String str_send ;

    public String getStr_pay() {
        return str_pay;
    }

    public void setStr_pay(String str_pay) {
        this.str_pay = str_pay;
    }

    public String getStr_send() {
        return str_send;
    }

    public void setStr_send(String str_send) {
        this.str_send = str_send;
    }

    public PaySendBean(String str_pay, String str_send) {

        this.str_pay = str_pay;
        this.str_send = str_send;
    }
}
