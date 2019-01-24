package com.yunqilai.delivery.ui.presenter.my;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.yunqilai.delivery.R;
import com.yunqilai.delivery.model.Address;
import com.yunqilai.delivery.model.Bean.ParseBaseEntity;
import com.yunqilai.delivery.model.Bean.my.ArticleBean;
import com.yunqilai.delivery.model.Bean.my.ArticleDtaa;
import com.yunqilai.delivery.model.Matter;
import com.yunqilai.delivery.model.Order;
import com.yunqilai.delivery.request.base.ErrorCode;
import com.yunqilai.delivery.ui.activity.my.DispatchMatterDetailActivity;
import com.yunqilai.delivery.ui.activity.my.DispatchStatisticsActivity;
import com.yunqilai.delivery.ui.interlayer.dispatch.DispatchListInterlayer;
import com.yunqilai.delivery.ui.interlayer.my.DispatchMatterInterlayer;
import com.yunqilai.delivery.ui.presenter.AbsPresenter;
import com.yunqilai.delivery.ui.view.ToastUtil;
import com.yunqilai.delivery.utils.Event;
import com.yunqilai.delivery.utils.MyOkHttpClientManager;
import com.yunqilai.delivery.utils.ResponseUtils;
import com.yunqilai.delivery.utils.UrlUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by KK on 2017/6/13.
 */

public class DispatchMatterPresenter extends AbsPresenter<DispatchMatterInterlayer> {

    private Gson gson ;

    public DispatchMatterPresenter(Context context, DispatchMatterInterlayer interlayer) {
        super(context, interlayer);
    }

    public void requestData(){

        gson = new Gson();

        MyOkHttpClientManager.postAsynJson("", UrlUtils.EXPRESS_THING_URL, new MyOkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Toast.makeText(mContext, R.string.service_error_500,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {
                try {
                    ArticleDtaa data = gson.fromJson(response, ArticleDtaa.class);
                    if (data.getCode().equals(ResponseUtils.OK)) {
                        if (data.getData().size() > 0) {
                            interlayer.refreshDatas(data.getData());
                        }
                    }else if(data.getCode().equals(ErrorCode.ERR_ACCESS_TOKEN_INVALID) || data.getCode().equals(ErrorCode.ERR_REFRESH_TOKEN_INVALID)){
                        EventBus.getDefault().post(Event.EXITANDLOGIN);
                    }
                }catch (Exception e){
                    Toast.makeText(mContext, R.string.service_error_500,Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

//        interlayer.refreshDatas(matters);
    }

    public void onItemClick(ArticleBean matter){
        Intent intent = new Intent(mContext, DispatchMatterDetailActivity.class);
        intent.putExtra("matter",matter);
        mContext.startActivity(intent);
    }

}
