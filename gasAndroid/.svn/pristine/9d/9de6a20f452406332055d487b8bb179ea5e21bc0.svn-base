package com.yunqilai.consumer.luckyapp.Common.Presenter;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.squareup.okhttp.Request;
import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.Base.BasePresenterActivity;
import com.yunqilai.consumer.luckyapp.Common.Model.ParseBaseEntity;
import com.yunqilai.consumer.luckyapp.Common.Model.ParseCodeNumEntity;
import com.yunqilai.consumer.luckyapp.Common.Model.ParseLoginEntity;
import com.yunqilai.consumer.luckyapp.Common.Model.PostCodeEntity;
import com.yunqilai.consumer.luckyapp.Common.Model.PostLoginEntity;
import com.yunqilai.consumer.luckyapp.Common.Ui.MyCountDownTimer;
import com.yunqilai.consumer.luckyapp.Common.Utils.MD5Utils;
import com.yunqilai.consumer.luckyapp.Common.Utils.OkHttpClientManager;
import com.yunqilai.consumer.luckyapp.Common.Utils.ResponseCodeUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.SharedPreferencesUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.UrlUtils;
import com.yunqilai.consumer.luckyapp.Common.View.RegisterView;
import com.yunqilai.consumer.luckyapp.Common.View.VuCallback;
import com.yunqilai.consumer.luckyapp.UserCenter.StringUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;


/**
 * Created by Administrator on 2017/5/24.
 */

public class RegisterActivity extends BasePresenterActivity<RegisterView> {

    private String smsCode = new String();

    private Subscription subscription ;

    private MyCountDownTimer countDownTimer ;

    @Override
    protected Class<RegisterView> getViewClass() {
        return RegisterView.class;
    }

    @Override
    protected void onBindView() {
        super.onBindView();

        countDownTimer = new MyCountDownTimer(vu.getBtn_code(),60000,1000);

        vu.getTv_h5().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,UserProtocalActivity.class));
            }
        });

        vu.getEdt_code().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() > 0)
                    vu.getBtn_register().setBackgroundResource(R.drawable.btn_login_login_bg);
            }
        });

        vu.onClickListener(new VuCallback<View>() {
            @Override
            public void execute(View result) {
                switch (result.getId()){
                    case R.id.btn_register:
                        if(vu.getPhoneNum().length() ==0){
                            Toast.makeText(context,"手机号不能为空",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (!com.yunqilai.consumer.luckyapp.Common.Utils.StringUtils.checkMobileNumber(vu.getPhoneNum())){
                            Toast.makeText(context,"请输入正确手机号",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (StringUtils.isCorrectPwd(vu.getEdt_pwd().getText().toString())) {

                            if (!vu.pwdIsCorrect()){
                                Toast.makeText(context,"两次输入密码不一致",Toast.LENGTH_SHORT).show();
                                return;
                            }

                            if (vu.getCodeNum().equals(smsCode) && !vu.getCodeNum().equals("")) {
                                   //vu.getBtn_register().setBackgroundResource(R.drawable.btn_login_login_bg);
                                    final String type = gson.toJson(new PostLoginEntity(vu.getPhoneNum(), MD5Utils.md5Password(vu.getPwdNum()), vu.getCodeNum(), StringUtils.getPhotoImEi(context)));
                                    OkHttpClientManager.postAsynJson(type, UrlUtils.REGISTER_AUTHON_URL, new OkHttpClientManager.StringCallback() {
                                        @Override
                                        public void onFailure(Request request, IOException e) {

                                        }

                                        @Override
                                        public void onResponse(String response) {
                                            Log.e("sssssss", response);
                                            ParseBaseEntity baseEntity = gson.fromJson(response, ParseBaseEntity.class);
                                            if (baseEntity.getCode().equals(ResponseCodeUtils.OK)) {
                                                ParseLoginEntity entity = gson.fromJson(gson.toJson(baseEntity.getData()), ParseLoginEntity.class);
                                                SharedPreferencesUtils.setParam(RegisterActivity.this,SharedPreferencesUtils.refresh_token,entity.getRefreshToken());
                                                SharedPreferencesUtils.setParam(RegisterActivity.this, SharedPreferencesUtils.access_token, entity.getAccessToken());
                                                SharedPreferencesUtils.setParam(RegisterActivity.this, SharedPreferencesUtils.userName, entity.getUserName());
                                                SharedPreferencesUtils.setParam(RegisterActivity.this, SharedPreferencesUtils.mobile, entity.getMobile());

                                                String name = (String) SharedPreferencesUtils.getParam(context,SharedPreferencesUtils.mobile,"");
                                                String aline = name+"_1_"+StringUtils.getPhotoImEi(context);

                                                JPushInterface.setAlias(context, aline, new TagAliasCallback() {
                                                    @Override
                                                    public void gotResult(int i, String s, Set<String> set) {


                                                    }
                                                });

                                                Intent intent = new Intent(RegisterActivity.this, ConfirmUserActivity.class);
                                                intent.putExtra("flag", "1");
                                                startActivity(intent);
                                                EventBus.getDefault().post(6);
                                                finish();
                                            } else {
                                                Toast.makeText(context, baseEntity.getMsg(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                            } else {
                                Toast.makeText(context, "验证码不正确", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(context, "密码为6-16数字与字母组合", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.btn_code:
                        if(vu.getPhoneNum().length() ==0){
                            Toast.makeText(context,"手机号不能为空",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (com.yunqilai.consumer.luckyapp.Common.Utils.StringUtils.checkMobileNumber(vu.getPhoneNum())){ //发送验证码
                                String type = gson.toJson(new PostCodeEntity(vu.getPhoneNum(),"regist","buyer"));
                                OkHttpClientManager.postAsynJson(type, UrlUtils.CODE_SEND_URL, new OkHttpClientManager.StringCallback() {
                                @Override
                                public void onFailure(Request request, IOException e) {

                                }

                                @Override
                                public void onResponse(String response) {
                                    Log.e("sssssss",response);
                                    ParseBaseEntity baseEntity = gson.fromJson(response,ParseBaseEntity.class);
                                    if (baseEntity.getCode().equals(ResponseCodeUtils.OK)){
                                        countDownTimer.start();
                                        ParseCodeNumEntity codeNumEntity = gson.fromJson(gson.toJson(baseEntity.getData()),ParseCodeNumEntity.class);
                                        smsCode = codeNumEntity.getSmsCode();
                                    }else {
                                        Toast.makeText(context,baseEntity.getMsg(),Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }else {
                            Toast.makeText(context,"请输入正确手机号",Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case R.id.iv_back_register:
                        finish();
                        break;
                }
            }
        });
    }

    @Override
    protected void onDestroyVu() {
        super.onDestroyVu();
        if (subscription!=null){
            subscription.unsubscribe();
        }
    }

}
