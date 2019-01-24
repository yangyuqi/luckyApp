package com.yunqilai.consumer.luckyapp.UserCenter.Presenter;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.squareup.okhttp.Request;
import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.Base.BasePresenterActivity;
import com.yunqilai.consumer.luckyapp.Common.Model.ParseBaseEntity;
import com.yunqilai.consumer.luckyapp.Common.Utils.OkHttpClientManager;
import com.yunqilai.consumer.luckyapp.Common.Utils.ResponseCodeUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.UrlUtils;
import com.yunqilai.consumer.luckyapp.ShoppingCart.Bean.AccessTokenBean;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.UserAttentionBean;
import com.yunqilai.consumer.luckyapp.UserCenter.StringUtils;
import com.yunqilai.consumer.luckyapp.UserCenter.View.RealNameView;

import java.io.IOException;

/**
 * Created by Administrator on 2017/6/12.
 */

public class RealNameActivity extends BasePresenterActivity<RealNameView> {
    @Override
    protected Class<RealNameView> getViewClass() {
        return RealNameView.class;
    }

    @Override
    protected void onBindView() {
        super.onBindView();
        vu.getIv_back().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getAttention();
    }

    private void getAttention() {
        String type = gson.toJson(new AccessTokenBean(getAccessToken()));
        OkHttpClientManager.postAsynJson(type, UrlUtils.MY_ATTENTION_URL, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                ParseBaseEntity entity = gson.fromJson(response,ParseBaseEntity.class);
                if (entity.getCode().equals(ResponseCodeUtils.OK)) {
                    final UserAttentionBean bean = gson.fromJson(gson.toJson(entity.getData()), UserAttentionBean.class);
                    if (bean.getStatus().equals("ing")){
                        vu.getTv_content().setText("审核中,请耐心等待");
                    }else {
                        vu.getTv_content().setText(StringUtils.attentionType(bean.getStatus()));
                    }
                    vu.getEdt_name().setText(bean.getName());
                    vu.getEdt_num().setText(bean.getIdCardNumber());
                    Glide.with(context).load(UrlUtils.URL_BASE+bean.getIdCardFront()).into(vu.getIv_front());
                    Glide.with(context).load(UrlUtils.URL_BASE+bean.getIdCardBack()).into(vu.getIv_iv_back());
                    if (bean.getStatus().equals("failed")){
                        vu.getIv_icon().setImageResource(R.drawable.icon_authentication_failureddd);
                        vu.getView_rl_show().setVisibility(View.VISIBLE);
                        vu.getRl_tv_show().setText(bean.getReason());
                        vu.getRl_btn_show().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(context,EditUserAuthenActivity.class);
                                intent.putExtra("attestationId",bean);
                                startActivity(intent);
                                finish();
                            }
                        });
                    }
                    if (bean.getStatus().equals("success")){
                        vu.getIv_icon().setImageResource(R.drawable.icon_authentication_success);
                    }
                }else {

                }
            }
        });
    }

}
