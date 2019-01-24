package com.yunqilai.delivery.ui.presenter.dispatch;

import android.content.Context;
import android.util.Log;

import com.yunqilai.delivery.manager.SharedPreferencesUtil;
import com.yunqilai.delivery.model.Order;
import com.yunqilai.delivery.ui.interlayer.dispatch.DispatchInterlayer;
import com.yunqilai.delivery.ui.presenter.AbsPresenter;

import java.util.ArrayList;

/**
 * Created by KK on 2017/6/6.
 */

public class DispatchPresenter extends AbsPresenter<DispatchInterlayer> {

    private String type ;

    public DispatchPresenter(Context context, DispatchInterlayer interlayer) {
        super(context, interlayer);
        type = (String) SharedPreferencesUtil.get(context,SharedPreferencesUtil.role,"");
    }

    public void onCreateView(){

        ArrayList<Order.STATU> status = new ArrayList<>();
        if (!type.equals("manager")) {
            status.add(Order.STATU.WAIT_ORDER);
            status.add(Order.STATU.WAIT_PICKUP);
            status.add(Order.STATU.WAIT_DELIVERY);
            status.add(Order.STATU.COMPLETE);
        }else {
            status.add(Order.STATU.SELF_WAIT_EXTRACT);
            status.add(Order.STATU.SELF_COMPLETE);
        }

        interlayer.setDatas(status);
    }
}
