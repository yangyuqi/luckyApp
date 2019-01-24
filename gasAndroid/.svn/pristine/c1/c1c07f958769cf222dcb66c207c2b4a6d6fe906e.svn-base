package com.yunqilai.delivery.ui.presenter.order;

import android.content.Context;
import android.content.Intent;

import com.yunqilai.delivery.model.Order;
import com.yunqilai.delivery.ui.activity.order.SearchOrderActivity;
import com.yunqilai.delivery.ui.interlayer.order.OrderInterlayer;
import com.yunqilai.delivery.ui.presenter.AbsPresenter;

import java.util.ArrayList;

/**
 * Created by KK on 2017/6/6.
 */

public class OrderPresenter extends AbsPresenter<OrderInterlayer> {
    public OrderPresenter(Context context, OrderInterlayer interlayer) {
        super(context, interlayer);
    }

    public void onCreateView(){

        ArrayList<Order.STATU> status = new ArrayList<>();
        status.add(Order.STATU.SELF_WAIT_EXTRACT);
        status.add(Order.STATU.SELF_COMPLETE);
        interlayer.setDatas(status);
    }

    public void search(){
        Intent intent = new Intent(mContext, SearchOrderActivity.class);
        mContext.startActivity(intent);
    }
}
