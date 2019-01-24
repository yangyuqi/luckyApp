package com.yunqilai.delivery.ui.activity.common;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.yunqilai.delivery.R;
import com.yunqilai.delivery.manager.SharedPreferencesUtil;
import com.yunqilai.delivery.model.Bean.LoginComBean;
import com.yunqilai.delivery.model.Bean.ParseLoginBean;
import com.yunqilai.delivery.model.LoginBean;
import com.yunqilai.delivery.request.base.ErrorCode;
import com.yunqilai.delivery.ui.activity.BaseActivity;
import com.yunqilai.delivery.ui.activity.MainActivity;
import com.yunqilai.delivery.ui.activity.my.ModifyPasswordActivity;
import com.yunqilai.delivery.ui.interlayer.LoginInterlayer;
import com.yunqilai.delivery.ui.presenter.LoginPresenter;
import com.yunqilai.delivery.utils.Event;
import com.yunqilai.delivery.utils.MD5Utils;
import com.yunqilai.delivery.utils.MyOkHttpClientManager;
import com.yunqilai.delivery.utils.ResponseUtils;
import com.yunqilai.delivery.utils.StringUtils;
import com.yunqilai.delivery.utils.UrlUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import rx.functions.Action1;

/**
 * Created by KK on 2017/5/23.
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginInterlayer,View.OnClickListener{

    private EditText usernameEt;
    private EditText passwordEt;
    private Button loginBtn;
    private TextView forgetPasswordTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter = new LoginPresenter(this,this);

        initView();
        initEvent();
    }

    private void initView(){
        usernameEt = (EditText) findViewById(R.id.edt_login_phone);
        passwordEt = (EditText) findViewById(R.id.edt_login_pwd);
        loginBtn = (Button) findViewById(R.id.btn_login);
        forgetPasswordTv = (TextView) findViewById(R.id.tv_forget_pwd);
    }

    private void initEvent(){
        loginBtn.setOnClickListener(this);
        forgetPasswordTv.setOnClickListener(this);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setUsernameError() {

    }

    @Override
    public void setPasswordError() {

    }

    @Override
    public void goToMain() {
//        startActivity(new Intent(LoginActivity.this, MainActivity.class));
//        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                RxPermissions permissions = new RxPermissions(LoginActivity.this);
                permissions.request(Manifest.permission.READ_PHONE_STATE).subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                  if (aBoolean) {
                      String type = gson.toJson(new LoginBean(usernameEt.getText().toString(), MD5Utils.md5Password(passwordEt.getText().toString()), "manager", StringUtils.getPhotoImEi(context)));
                      MyOkHttpClientManager.postAsynJson(type, UrlUtils.LOGIN_URL, new MyOkHttpClientManager.StringCallback() {
                          @Override
                          public void onFailure(Request request, IOException e) {
                              Toast.makeText(LoginActivity.this, R.string.service_error_500, Toast.LENGTH_SHORT).show();
                          }

                          @Override
                          public void onResponse(String response) {
                              try {
                                  ParseLoginBean bean = gson.fromJson(response, ParseLoginBean.class);
                                  if (bean.getCode().equals(ResponseUtils.OK)) {
                                      final LoginComBean loginComBean = bean.getData();
                                      SharedPreferencesUtil.put(context, SharedPreferencesUtil.role, loginComBean.getRole());
                                      SharedPreferencesUtil.put(context, SharedPreferencesUtil.accessToken, loginComBean.getAccessToken());
                                      SharedPreferencesUtil.put(context, SharedPreferencesUtil.mobile, loginComBean.getMobile());
                                      try {
                                          SharedPreferencesUtil.put(context, SharedPreferencesUtil.userName, loginComBean.getUserName());
                                          SharedPreferencesUtil.put(context, SharedPreferencesUtil.icon, loginComBean.getIcon());
                                      } catch (Exception e) {
                                      }

                                      String name = (String) SharedPreferencesUtil.get(context, SharedPreferencesUtil.mobile, "");
                                      String aline = name + "_2_" + StringUtils.getPhotoImEi(context);
                                      Log.e("ssssssss", aline);
                                      JPushInterface.setAlias(context, aline, new TagAliasCallback() {
                                          @Override
                                          public void gotResult(int i, String s, Set<String> set) {

                                              if (loginComBean.isNeedModifyPwd()) {
                                                  startActivity(new Intent(context, ModifyPasswordActivity.class));
                                              } else {
                                                  startActivity(new Intent(context, MainActivity.class));
                                                  finish();
                                              }
                                          }
                                      });


                                  } else {
                                      Toast.makeText(context, bean.getMsg(), Toast.LENGTH_SHORT).show();
                                  }
                              } catch (Exception e) {
                                  Toast.makeText(LoginActivity.this, R.string.service_error_500, Toast.LENGTH_SHORT).show();
                                  e.printStackTrace();
                              }
                          }
                      });

                  }else {
                      Toast.makeText(context,"缺少权限",Toast.LENGTH_SHORT).show();
                  }
        }
    });

//                presenter.toLogin(usernameEt.getText().toString(), passwordEt.getText().toString());
                break;
            case R.id.tv_forget_pwd:
                startActivity(new Intent(LoginActivity.this, FindPwdActivity.class));
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        String name = (String) SharedPreferencesUtil.get(context,SharedPreferencesUtil.mobile,"");
        usernameEt.setText(name);
    }
}
