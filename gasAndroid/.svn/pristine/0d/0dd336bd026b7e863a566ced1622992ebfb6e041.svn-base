package com.yunqilai.delivery.ui.activity.common;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.yunqilai.delivery.R;
import com.yunqilai.delivery.model.Bean.ParseBaseEntity;
import com.yunqilai.delivery.model.Bean.my.GetCodeBean;
import com.yunqilai.delivery.model.Bean.my.ParseCodeEntity;
import com.yunqilai.delivery.model.Bean.my.PutindPwdEntity;
import com.yunqilai.delivery.model.Bean.my.SendCodeBean;
import com.yunqilai.delivery.request.base.ErrorCode;
import com.yunqilai.delivery.ui.Dialog.MyCountDownTimer;
import com.yunqilai.delivery.ui.activity.BaseActivity;
import com.yunqilai.delivery.ui.activity.attestation.AddAttestationActivity;
import com.yunqilai.delivery.ui.interlayer.FindPwdInterlayer;
import com.yunqilai.delivery.ui.presenter.FindPwdPresenter;
import com.yunqilai.delivery.utils.Event;
import com.yunqilai.delivery.utils.MD5Utils;
import com.yunqilai.delivery.utils.MyOkHttpClientManager;
import com.yunqilai.delivery.utils.ResponseUtils;
import com.yunqilai.delivery.utils.UrlUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;

/**
 * 忘记密码
 * Created by KK on 2017/6/6.
 */

public class FindPwdActivity extends BaseActivity<FindPwdPresenter> implements FindPwdInterlayer,View.OnClickListener{

    private EditText edt_phone ,edt_login_pwd ,edt_reg_pwd ,edt_reg_code ;
    private Button btn_send ,btn_send_code;
    private MyCountDownTimer timer ;
    private String code ;
    private LinearLayout iv_back ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_find_pwd);
        initView();
    }

    private void initView() {
        iv_back = (LinearLayout) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        edt_phone = (EditText) findViewById(R.id.edt_login_phone);
        edt_login_pwd = (EditText) findViewById(R.id.edt_login_pwd);
        edt_reg_pwd = (EditText) findViewById(R.id.edt_reg_pwd);
        edt_reg_code = (EditText) findViewById(R.id.edt_reg_code);
        btn_send_code = (Button) findViewById(R.id.btn_send);
        btn_send = (Button) findViewById(R.id.btn_findpwd);
        timer= new MyCountDownTimer(btn_send_code,60000,1000);

        btn_send_code.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (edt_phone.getText().toString().length()==11){
                    String type = gson.toJson(new SendCodeBean(edt_phone.getText().toString(),"forgetPwd","manager"));
                    MyOkHttpClientManager.postAsynJson(type, UrlUtils.SENF_PWD_CODE_URL, new MyOkHttpClientManager.StringCallback() {
                        @Override
                        public void onFailure(Request request, IOException e) {
                            Toast.makeText(FindPwdActivity.this,R.string.service_error_500,Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(String response) {
                            Log.e("sssssssssss",response);
                            Gson gsonn = new Gson();
                            try {
                                ParseCodeEntity entity = gsonn.fromJson(response, ParseCodeEntity.class);
                                if (entity.getCode().equals(ResponseUtils.OK)) {
                                    GetCodeBean codeNumEntity = entity.getData();
                                    code = codeNumEntity.getSmsCode();
                                    timer.start();
                                }else if(entity.getCode().equals(ErrorCode.ERR_ACCESS_TOKEN_INVALID) || entity.getCode().equals(ErrorCode.ERR_REFRESH_TOKEN_INVALID)){
                                    EventBus.getDefault().post(Event.EXITANDLOGIN);
                                } else {
                                    Toast.makeText(context, entity.getMsg(), Toast.LENGTH_SHORT).show();
                                }
                            }catch (Exception e){
                                Toast.makeText(FindPwdActivity.this,R.string.service_error_500,Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        }
                    });
                }else {
                    Toast.makeText(context,"请输入正确手机号",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (edt_phone.getText().length()==11){
                        if (edt_reg_pwd.getText().toString().equals(edt_login_pwd.getText().toString())){
                            if (edt_reg_pwd.getText().toString().length()>=6&&edt_reg_pwd.getText().toString().length()<=16){
                            if (code.equals(edt_reg_code.getText().toString())) {
                                String type = gson.toJson(new PutindPwdEntity(edt_phone.getText().toString(), MD5Utils.md5Password(edt_login_pwd.getText().toString()), code));
                                MyOkHttpClientManager.postAsynJson(type, UrlUtils.FIND_PWD_NEW_URL, new MyOkHttpClientManager.StringCallback() {
                                    @Override
                                    public void onFailure(Request request, IOException e) {
                                        Toast.makeText(FindPwdActivity.this,R.string.service_error_500,Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            ParseBaseEntity entity = gson.fromJson(response, ParseBaseEntity.class);
                                            if (entity.getCode().equals(ResponseUtils.OK)) {
                                                finish();
                                            }else if(entity.getCode().equals(ErrorCode.ERR_ACCESS_TOKEN_INVALID) || entity.getCode().equals(ErrorCode.ERR_REFRESH_TOKEN_INVALID)){
                                                EventBus.getDefault().post(Event.EXITANDLOGIN);
                                            } else {
                                                Toast.makeText(context, entity.getMsg(), Toast.LENGTH_SHORT).show();
                                            }
                                        }catch (Exception e){
                                            Toast.makeText(FindPwdActivity.this,R.string.service_error_500,Toast.LENGTH_SHORT).show();
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            }else {
                                Toast.makeText(context,"请输入不小于6位密码",Toast.LENGTH_SHORT).show();
                            }
                            }else {
                                Toast.makeText(context,"验证码错误",Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(context,"两次密码不一样",Toast.LENGTH_SHORT).show();
                        }
                    }
            }
        });

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void toLogin() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(Event event){
        if (event == Event.EXITANDLOGIN) {
            finish();
        }
    }
}
