package com.yunqilai.delivery.ui.presenter.order;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.yunqilai.delivery.R;
import com.yunqilai.delivery.manager.SharedPreferencesUtil;
import com.yunqilai.delivery.model.Address;
import com.yunqilai.delivery.model.Bean.ParseBaseEntity;
import com.yunqilai.delivery.model.Bean.ParseOrderDetailsBean;
import com.yunqilai.delivery.model.Bean.PutOrderDetailsBean;
import com.yunqilai.delivery.model.Bean.home.GetOrderBean;
import com.yunqilai.delivery.model.Order;
import com.yunqilai.delivery.model.Product;
import com.yunqilai.delivery.ui.Dialog.DeleteDialog;
import com.yunqilai.delivery.ui.Dialog.DialogReject;
import com.yunqilai.delivery.ui.activity.order.OrderDetailActivity;
import com.yunqilai.delivery.ui.interf.DeleteDialogInterface;
import com.yunqilai.delivery.ui.interlayer.order.OrderDetailInterlayer;
import com.yunqilai.delivery.ui.presenter.AbsPresenter;
import com.yunqilai.delivery.ui.view.ToastUtil;
import com.yunqilai.delivery.utils.MyOkHttpClientManager;
import com.yunqilai.delivery.utils.ResponseUtils;
import com.yunqilai.delivery.utils.UrlUtils;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by KK on 2017/6/7.
 */

public class OrderDetailPresenter extends AbsPresenter<OrderDetailInterlayer> {

    String accesstoken ,orderId;
    Gson gson ;
    OrderDetailActivity mCont ;


    public OrderDetailPresenter(OrderDetailActivity context, OrderDetailInterlayer interlayer, String morderId) {
        super(context, interlayer);
        accesstoken = (String) SharedPreferencesUtil.get(context,SharedPreferencesUtil.accessToken,"");
        orderId = morderId ;
        gson = new Gson() ;
        mCont = context ;
    }

    public void requestData(){

        String type  = gson.toJson(new PutOrderDetailsBean(accesstoken,orderId));
        MyOkHttpClientManager.postAsynJson(type, UrlUtils.ORDER_DETAILS_URL, new MyOkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Toast.makeText(mContext, R.string.service_error_500,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {
                try {
                    ParseBaseEntity entity = gson.fromJson(response, ParseBaseEntity.class);
                    if (entity.getCode().equals(ResponseUtils.OK)) {
                        ParseOrderDetailsBean bean = gson.fromJson(gson.toJson(entity.getData()), ParseOrderDetailsBean.class);
                        interlayer.refreshDatas(bean);
                    } else {
                        Toast.makeText(mContext, entity.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(mContext, R.string.service_error_500,Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

    }

    public void refuse(){
        DialogReject reject = new DialogReject(mCont,accesstoken ,orderId);
        reject.show();

    }

}
