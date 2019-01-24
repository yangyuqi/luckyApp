package com.yunqilai.consumer.luckyapp.Common.Presenter;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.squareup.okhttp.Request;
import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.Base.BasePresenterActivity;
import com.yunqilai.consumer.luckyapp.Common.Model.FindPwdBean;
import com.yunqilai.consumer.luckyapp.Common.Model.ParseBaseEntity;
import com.yunqilai.consumer.luckyapp.Common.Model.ParseCodeNumEntity;
import com.yunqilai.consumer.luckyapp.Common.Model.PostCodeEntity;
import com.yunqilai.consumer.luckyapp.Common.Ui.MyCountDownTimer;
import com.yunqilai.consumer.luckyapp.Common.Utils.MD5Utils;
import com.yunqilai.consumer.luckyapp.Common.Utils.OkHttpClientManager;
import com.yunqilai.consumer.luckyapp.Common.Utils.ResponseCodeUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.UrlUtils;
import com.yunqilai.consumer.luckyapp.Common.View.FindPwdView;
import com.yunqilai.consumer.luckyapp.UserCenter.StringUtils;

import org.jsoup.helper.StringUtil;

import java.io.IOException;

/**
 * Created by Administrator on 2017/5/24.
 */

public class FindPwdActivity extends BasePresenterActivity<FindPwdView> implements View.OnClickListener{

    private MyCountDownTimer countDownTimer ;
    private String smsCode = new String();

    @Override
    protected Class<FindPwdView> getViewClass() {
        return FindPwdView.class;
    }

    @Override
    protected void onBindView() {
        super.onBindView();

        countDownTimer = new MyCountDownTimer(vu.getBtn_code(),60000,1000);

        vu.getBtn_code().setOnClickListener(this);
        vu.getIv_back().setOnClickListener(this);
        vu.getBtn_confirm().setOnClickListener(this);

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
                    vu.getBtn_confirm().setBackgroundResource(R.drawable.btn_login_login_bg);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back_register:
                finish();
            case R.id.btn_send_code:
                if(vu.getEdt_phone().getText().toString().length() == 0){
                    Toast.makeText(context,"手机号不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (com.yunqilai.consumer.luckyapp.Common.Utils.StringUtils.checkMobileNumber(vu.getEdt_phone().getText().toString())){
                    String type = gson.toJson(new PostCodeEntity(vu.getEdt_phone().getText().toString(),"forgetPwd","buyer"));
                    OkHttpClientManager.postAsynJson(type, UrlUtils.CODE_SEND_URL, new OkHttpClientManager.StringCallback() {
                        @Override
                        public void onFailure(Request request, IOException e) {

                        }

                        @Override
                        public void onResponse(String response) {
                            Log.e("ssssssss",response);
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
                    Toast.makeText(context,"请输入正确手机号码",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_findpwd:

                if(vu.getEdt_phone().getText().toString().length() == 0){
                    Toast.makeText(context,"手机号不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (com.yunqilai.consumer.luckyapp.Common.Utils.StringUtils.checkMobileNumber(vu.getEdt_phone().getText().toString())){

                    if (!StringUtils.isCorrectPwd(vu.getEdt_pwd().getText().toString())){
                        Toast.makeText(context, "密码为6-16数字与字母组合", Toast.LENGTH_SHORT).show();
                        return;
                    }




                    if (vu.getEdt_pwd().getText().toString().equals(vu.getEdt_again_pwd().getText().toString())){
                        if (StringUtils.isCorrectPwd(vu.getEdt_pwd().getText().toString())) {
                            String type = gson.toJson(new FindPwdBean(vu.getEdt_phone().getText().toString(), MD5Utils.md5Password(vu.getEdt_again_pwd().getText().toString()), vu.getEdt_code().getText().toString()));
                            OkHttpClientManager.postAsynJson(type, UrlUtils.FIND_PWD_URL, new OkHttpClientManager.StringCallback() {
                                @Override
                                public void onFailure(Request request, IOException e) {

                                }

                                @Override
                                public void onResponse(String response) {
                                    ParseBaseEntity entity = gson.fromJson(response, ParseBaseEntity.class);
                                    if (entity.getCode().equals(ResponseCodeUtils.OK)) {
                                        finish();
                                    } else {
                                        Toast.makeText(context, entity.getMsg(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }else {
                            Toast.makeText(context,"密码为6-16位数字与字母组合",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(context,"两次密码不一致",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(context,"请输入正确手机号",Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }
}
