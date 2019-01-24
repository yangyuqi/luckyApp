package com.yunqilai.consumer.luckyapp.UserCenter.Presenter;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.squareup.okhttp.Request;
import com.yunqilai.consumer.luckyapp.Common.Base.BasePresenterActivity;
import com.yunqilai.consumer.luckyapp.Common.Model.ParseBaseEntity;
import com.yunqilai.consumer.luckyapp.Common.Utils.OkHttpClientManager;
import com.yunqilai.consumer.luckyapp.Common.Utils.ResponseCodeUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.SharedPreferencesUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.UrlUtils;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.ReNameBean;
import com.yunqilai.consumer.luckyapp.UserCenter.View.RenameView;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

/**
 * Created by Administrator on 2017/6/12.
 */

public class RenameActivity extends BasePresenterActivity<RenameView> {
    @Override
    protected Class<RenameView> getViewClass() {
        return RenameView.class;
    }

    private String name ;

    @Override
    protected void onBindView() {
        super.onBindView();
        vu.getIv_back().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        name = (String) SharedPreferencesUtils.getParam(context,SharedPreferencesUtils.userName,"");
        vu.getEdt().setText(name);

        vu.getTv_save().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!vu.getEdt().getText().toString().equals("")&&vu.getEdt().getText().toString().length()>=4&&vu.getEdt().getText().toString().length()<25){
                    String type = gson.toJson(new ReNameBean(getAccessToken(),vu.getEdt().getText().toString()));
                    OkHttpClientManager.postAsynJson(type, UrlUtils.EDIT_NICKNAME_URL, new OkHttpClientManager.StringCallback() {
                        @Override
                        public void onFailure(Request request, IOException e) {

                        }

                        @Override
                        public void onResponse(String response) {
                            ParseBaseEntity entity = gson.fromJson(response,ParseBaseEntity.class);
                            if (entity.getCode().equals(ResponseCodeUtils.OK)){
                                SharedPreferencesUtils.setParam(context,SharedPreferencesUtils.userName,vu.getEdt().getText().toString());
                                finish();
                            }else {
                                Toast.makeText(context,entity.getMsg(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(context,"请输入4到 25位昵称",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
