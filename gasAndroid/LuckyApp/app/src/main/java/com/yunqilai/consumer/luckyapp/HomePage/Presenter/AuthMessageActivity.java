package com.yunqilai.consumer.luckyapp.HomePage.Presenter;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.squareup.okhttp.Request;
import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.Base.BasePresenterActivity;
import com.yunqilai.consumer.luckyapp.Common.Model.ParseBaseEntity;
import com.yunqilai.consumer.luckyapp.Common.Presenter.ConfirmUserActivity;
import com.yunqilai.consumer.luckyapp.Common.Utils.OkHttpClientManager;
import com.yunqilai.consumer.luckyapp.Common.Utils.ResponseCodeUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.UrlUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.adapter.CommonAdapter;
import com.yunqilai.consumer.luckyapp.Common.Utils.adapter.ViewHolder;
import com.yunqilai.consumer.luckyapp.HomePage.Model.AuthMessageBean;
import com.yunqilai.consumer.luckyapp.HomePage.Model.AuthMessageData;
import com.yunqilai.consumer.luckyapp.HomePage.Model.SecondDataListBean;
import com.yunqilai.consumer.luckyapp.HomePage.Model.SecondMessageBean;
import com.yunqilai.consumer.luckyapp.HomePage.View.AuthMessageView;
import com.yunqilai.consumer.luckyapp.UserCenter.Presenter.RealNameActivity;
import com.yunqilai.consumer.luckyapp.UserCenter.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/5.
 */

public class AuthMessageActivity extends BasePresenterActivity<AuthMessageView> {

    List<AuthMessageBean> date = new ArrayList<>();
    private CommonAdapter<AuthMessageBean> adapter ;

    @Override
    protected Class<AuthMessageView> getViewClass() {
        return AuthMessageView.class;
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

        adapter = new CommonAdapter<AuthMessageBean>(context,date , R.layout.auth_message_ls_item) {
            @Override
            public void convert(ViewHolder helper, AuthMessageBean item) {
                helper.setText(R.id.tv_time,item.getDate());
//                if (item.getAttestation().getStatus().equals("failed"))
                View btn_view = helper.getView(R.id.btn_no_fail);
                helper.setText(R.id.tv_auth_q, StringUtils.attentionType(item.getAttestation().getStatus()));
                if (item.getAttestation().getStatus().equals("failed")){
                    helper.setText(R.id.tv_no_fail , item.getAttestation().getReason());
                    btn_view.setVisibility(View.VISIBLE);
                    btn_view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(context, RealNameActivity.class);
                            startActivity(intent);
                        }
                    });
                }else {
                    btn_view.setVisibility(View.GONE);
                }

            }
        };

        vu.getLs().setAdapter(adapter);
    }

    @Override
    protected void afterResume() {
        super.afterResume();
        initData();
    }

    private void initData() {
        String type = gson.toJson(new SecondMessageBean(getAccessToken(),1,20,"attestation"));
        OkHttpClientManager.postAsynJson(type, UrlUtils.SECOND_MESSAGE_URL, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                ParseBaseEntity entity = gson.fromJson(response,ParseBaseEntity.class);
                if (entity.getCode().equals(ResponseCodeUtils.OK)){
                    AuthMessageData bean_data = gson.fromJson(gson.toJson(entity.getData()),AuthMessageData.class);
                    if (bean_data.getList().size()>0){
                        date.clear();
                        date.addAll(bean_data.getList());
                        adapter.setData(date);
                        adapter.notifyDataSetChanged();
                    }else {
                        vu.getLs().setVisibility(View.GONE);
                        vu.getLl_show().setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }
}
