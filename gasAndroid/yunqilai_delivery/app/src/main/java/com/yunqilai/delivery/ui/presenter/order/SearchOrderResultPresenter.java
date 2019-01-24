package com.yunqilai.delivery.ui.presenter.order;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.yunqilai.delivery.R;
import com.yunqilai.delivery.manager.SharedPreferencesUtil;
import com.yunqilai.delivery.model.Address;
import com.yunqilai.delivery.model.Bean.OrderAllBean;
import com.yunqilai.delivery.model.Bean.ParseOrderBean;
import com.yunqilai.delivery.model.Order;
import com.yunqilai.delivery.ui.interlayer.order.SearchOrderResultInterlayer;
import com.yunqilai.delivery.ui.presenter.AbsPresenter;
import com.yunqilai.delivery.ui.view.ToastUtil;
import com.yunqilai.delivery.utils.MyOkHttpClientManager;
import com.yunqilai.delivery.utils.ResponseUtils;
import com.yunqilai.delivery.utils.UrlUtils;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by KK on 2017/6/12.
 */

public class SearchOrderResultPresenter extends AbsPresenter<SearchOrderResultInterlayer> {

    private String keyWord ;
    private Context Mcontext ;
    private String accessToken ;
    private Gson gson ;

    public SearchOrderResultPresenter(Context context, SearchOrderResultInterlayer interlayer, String word) {
        super(context, interlayer);
        keyWord = word ;
        Mcontext = context ;
        gson = new Gson();
        accessToken = (String) SharedPreferencesUtil.get(Mcontext,SharedPreferencesUtil.accessToken,"");
    }

    public void requestData(){

        String type = gson.toJson(new OrderAllBean(accessToken,null,keyWord,1,40));
        MyOkHttpClientManager.postAsynJson(type, UrlUtils.GET_ALL_ORDER_URL, new MyOkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Toast.makeText(mContext, R.string.service_error_500,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {
                Log.e("sssssssv",response);
                try {
                    ParseOrderBean bean = gson.fromJson(response, ParseOrderBean.class);
                    if (bean.getCode().equals(ResponseUtils.OK)) {
                        if (bean.getData().getOrders().size() > 0) {
                            interlayer.refreshDatas(bean.getData().getOrders());
                        } else {
                            Toast.makeText(mContext, "未搜索到相关商品", Toast.LENGTH_SHORT).show();
                        }
                    }
                }catch (Exception e){
                    Toast.makeText(mContext, R.string.service_error_500,Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

    }

    public void callPhone(String phone){
        ToastUtil.show(mContext,"call phone");
    }

    public void pickup(String orderId){
        ToastUtil.show(mContext,"surePickup");
    }

}
