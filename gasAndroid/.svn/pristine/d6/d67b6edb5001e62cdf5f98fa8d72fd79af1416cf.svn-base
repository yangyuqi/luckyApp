package com.yunqilai.delivery.ui.presenter.attestation;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.yunqilai.delivery.R;
import com.yunqilai.delivery.manager.SharedPreferencesUtil;
import com.yunqilai.delivery.model.Address;
import com.yunqilai.delivery.model.Attestation;
import com.yunqilai.delivery.model.Bean.ParseBaseEntity;
import com.yunqilai.delivery.model.Bean.attention.AttentionBean;
import com.yunqilai.delivery.model.Bean.attention.AttentionListBean;
import com.yunqilai.delivery.model.Bean.attention.ParseAttestation;
import com.yunqilai.delivery.model.Order;
import com.yunqilai.delivery.request.base.ErrorCode;
import com.yunqilai.delivery.ui.activity.attestation.AddAttestationActivity;
import com.yunqilai.delivery.ui.activity.attestation.AttestationDetailActivity;
import com.yunqilai.delivery.ui.interlayer.attestation.AttestationInterlayer;
import com.yunqilai.delivery.ui.interlayer.dispatch.DispatchListInterlayer;
import com.yunqilai.delivery.ui.presenter.AbsPresenter;
import com.yunqilai.delivery.utils.Event;
import com.yunqilai.delivery.utils.MyOkHttpClientManager;
import com.yunqilai.delivery.utils.ResponseUtils;
import com.yunqilai.delivery.utils.UrlUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by KK on 2017/6/7.
 */

public class AttestationPresenter extends AbsPresenter<AttestationInterlayer> {
    private String accessToken ;
    private Gson gson ;
    private List<ParseAttestation> data = new ArrayList<>();

    public AttestationPresenter(Context context, AttestationInterlayer interlayer) {
        super(context, interlayer);
        accessToken = (String) SharedPreferencesUtil.get(context,SharedPreferencesUtil.accessToken,"");
        gson = new Gson();
    }

    public void requestData(final int currentPage){
        String type = gson.toJson(new AttentionBean(accessToken,currentPage,10));
        MyOkHttpClientManager.postAsynJson(type, UrlUtils.ATTENTION_LIST_URL, new MyOkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Toast.makeText(mContext, R.string.service_error_500,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {
                try {
                    ParseBaseEntity entity = gson.fromJson(response, ParseBaseEntity.class);
                    if (entity.getCode().equals(ResponseUtils.OK)) {
                        AttentionListBean bean = gson.fromJson(gson.toJson(entity.getData()), AttentionListBean.class);
                        data.clear();
                        data.addAll(bean.getAttestationList());
                        interlayer.refreshDatas(data, bean.getCount(),currentPage);
                    }else if(entity.getCode().equals(ErrorCode.ERR_ACCESS_TOKEN_INVALID) || entity.getCode().equals(ErrorCode.ERR_REFRESH_TOKEN_INVALID)){
                        EventBus.getDefault().post(Event.EXITANDLOGIN);
                    }
                }catch (Exception e){
                    Toast.makeText(mContext, R.string.service_error_500,Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }

    public void onItemClick(String attestationId ){
        Intent intent = new Intent(mContext, AttestationDetailActivity.class);
        intent.putExtra("attestationId",attestationId);
        mContext.startActivity(intent);
    }

    public void addAttestation(){
        Intent intent = new Intent(mContext, AddAttestationActivity.class);
        intent.putExtra("type","1");
        mContext.startActivity(intent);
    }

}
