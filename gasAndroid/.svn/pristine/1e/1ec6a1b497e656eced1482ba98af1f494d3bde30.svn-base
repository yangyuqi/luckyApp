package com.yunqilai.consumer.luckyapp.UserCenter.Presenter;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.squareup.okhttp.Request;
import com.yunqilai.consumer.luckyapp.Common.Base.BasePresenterActivity;
import com.yunqilai.consumer.luckyapp.Common.Model.ParseBaseEntity;
import com.yunqilai.consumer.luckyapp.Common.Presenter.LoginActivity;
import com.yunqilai.consumer.luckyapp.Common.Utils.MD5Utils;
import com.yunqilai.consumer.luckyapp.Common.Utils.OkHttpClientManager;
import com.yunqilai.consumer.luckyapp.Common.Utils.ResponseCodeUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.UrlUtils;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.RePwdBean;
import com.yunqilai.consumer.luckyapp.UserCenter.View.RePwdView;
import com.yunqilai.consumer.luckyapp.UserCenter.View.RenameView;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

/**
 * Created by Administrator on 2017/6/12.
 */

public class RePwdActivity extends BasePresenterActivity<RePwdView> {
    @Override
    protected Class<RePwdView> getViewClass() {
        return RePwdView.class;
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

        vu.getBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!vu.getEdt_old().getText().toString().equals("")&&!vu.getEdt_one().getText().toString().equals("")&&!vu.getEdt_two().getText().toString().equals("")){
                      String type = gson.toJson(new RePwdBean(getAccessToken(),MD5Utils.md5Password(vu.getEdt_old().getText().toString()), MD5Utils.md5Password(vu.getEdt_one().getText().toString())));
                    OkHttpClientManager.postAsynJson(type, UrlUtils.EDIT_PWD_URL, new OkHttpClientManager.StringCallback() {
                        @Override
                        public void onFailure(Request request, IOException e) {

                        }

                        @Override
                        public void onResponse(String response) {
                                ParseBaseEntity entity = gson.fromJson(response, ParseBaseEntity.class);
                                if (entity.getCode().equals(ResponseCodeUtils.OK)) {
                                    Intent i = new Intent(context, LoginActivity.class);
                                    startActivity(i);
                                    EventBus.getDefault().post(6);
                                    finish();
                                }else {
                                    Toast.makeText(context,entity.getMsg(),Toast.LENGTH_SHORT).show();
                                }
                        }
                    });
                }else {
                    Toast.makeText(context,"密码不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
