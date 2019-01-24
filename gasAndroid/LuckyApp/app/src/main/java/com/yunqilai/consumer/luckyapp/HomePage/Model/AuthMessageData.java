package com.yunqilai.consumer.luckyapp.HomePage.Model;

import java.util.List;

/**
 * Created by Administrator on 2017/8/18.
 */

public class AuthMessageData {
    private List<AuthMessageBean> list ;

    public List<AuthMessageBean> getList() {
        return list;
    }

    public void setList(List<AuthMessageBean> list) {
        this.list = list;
    }

    public AuthMessageData(List<AuthMessageBean> list) {

        this.list = list;
    }
}
