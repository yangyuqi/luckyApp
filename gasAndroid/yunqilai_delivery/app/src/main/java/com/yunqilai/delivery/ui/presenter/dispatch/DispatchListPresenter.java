package com.yunqilai.delivery.ui.presenter.dispatch;

import android.content.Context;

import com.yunqilai.delivery.model.Order;
import com.yunqilai.delivery.request.base.DefaultRequestListener;
import com.yunqilai.delivery.request.deliveryOrder.DeliveryOrderRequester;
import com.yunqilai.delivery.request.deliveryOrder.SelectOrderAllListener;
import com.yunqilai.delivery.ui.interlayer.dispatch.DispatchListInterlayer;
import com.yunqilai.delivery.ui.presenter.AbsPresenter;
import com.yunqilai.delivery.ui.view.ToastUtil;

import java.util.List;

/**
 * Created by KK on 2017/6/7.
 */

public class DispatchListPresenter extends AbsPresenter<DispatchListInterlayer> {

    private DeliveryOrderRequester deliveryOrderRequester;
    private SelectOrderAllListenerImpl selectOrderAllListener;
    private class SelectOrderAllListenerImpl extends DefaultRequestListener implements SelectOrderAllListener{

        @Override
        protected void onRequestFail() {

        }

        @Override
        public void onSelectOrderAllSuccess(int count, List<Order> orderList) {
            interlayer.refreshDatas(orderList);
        }
    }


    public DispatchListPresenter(Context context, DispatchListInterlayer interlayer) {
        super(context, interlayer);
        deliveryOrderRequester = new DeliveryOrderRequester();
        selectOrderAllListener = new SelectOrderAllListenerImpl();
    }

    public void requestData(){
        deliveryOrderRequester.queryGoodsClassAll(mContext,"",1,20,selectOrderAllListener);
    }

    public void callPhone(String phone){
        ToastUtil.show(mContext,"call phone");
    }

    public void pickup(String orderId){
        ToastUtil.show(mContext,"surePickup");
    }

}
