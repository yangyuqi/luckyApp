package com.yunqilai.consumer.luckyapp.UserCenter.Presenter;

import android.Manifest;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.okhttp.Request;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.Base.BasePresenterActivity;
import com.yunqilai.consumer.luckyapp.Common.Model.AttentionUserBean;
import com.yunqilai.consumer.luckyapp.Common.Model.FileDataBean;
import com.yunqilai.consumer.luckyapp.Common.Model.ParseBaseEntity;
import com.yunqilai.consumer.luckyapp.Common.Presenter.ConfirmSuccessActivity;
import com.yunqilai.consumer.luckyapp.Common.Presenter.ConfirmUserActivity;
import com.yunqilai.consumer.luckyapp.Common.Utils.OkHttpClientManager;
import com.yunqilai.consumer.luckyapp.Common.Utils.ResponseCodeUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.SharedPreferencesUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.StringUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.UrlUtils;
import com.yunqilai.consumer.luckyapp.Common.View.ConfirmUserView;
import com.yunqilai.consumer.luckyapp.Common.View.VuCallback;
import com.yunqilai.consumer.luckyapp.MainActivity;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.UserAttentionBean;
import com.yunqilai.consumer.luckyapp.UserCenter.View.EditUserAuthenView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import me.iwf.photopicker.PhotoPicker;
import rx.functions.Action1;

/**
 * Created by yangyuqi on 17-7-31.
 */

public class EditUserAuthenActivity extends BasePresenterActivity<EditUserAuthenView> {
    private String pro_str_one ,pro_str_two ;
    private String type ;
    private String phone ;
    private UserAttentionBean attentionUserBean ;

    @Override
    protected Class<EditUserAuthenView> getViewClass() {
        return EditUserAuthenView.class;
    }

    @Override
    protected void onBindView() {
        super.onBindView();

         attentionUserBean = (UserAttentionBean) getIntent().getSerializableExtra("attestationId");

        pro_str_one = attentionUserBean.getIdCardFront();
        pro_str_two = attentionUserBean.getIdCardBack();
        vu.getEdt_name().setText(attentionUserBean.getName());
        vu.getEdt_body().setText(attentionUserBean.getIdCardNumber());

        Glide.with(context).load(UrlUtils.URL_BASE+attentionUserBean.getIdCardFront()).into(vu.getIv_photo());
        Glide.with(context).load(UrlUtils.URL_BASE+attentionUserBean.getIdCardBack()).into(vu.getIv_iv_photo());

        vu.getIv_back().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        vu.getTv_next().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, MainActivity.class));
                finish();
            }
        });

        vu.getTv_again().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "1";
                initPhoto();
            }
        });

        vu.getTv_again_tv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "2";
                initPhoto();
            }
        });

        vu.getImg_name().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vu.getEdt_name().setText("");
            }
        });

        vu.getImg_body().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vu.getEdt_body().setText("");
            }
        });

        vu.getEdt_name().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() > 0){
                    vu.getImg_name().setVisibility(View.VISIBLE);
                }else{
                    vu.getImg_name().setVisibility(View.GONE);
                }
            }
        });

        vu.getEdt_body().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() > 0){
                    vu.getImg_body().setVisibility(View.VISIBLE);
                }else{
                    vu.getImg_body().setVisibility(View.GONE);
                }
            }
        });

        vu.setOnClickListener(new VuCallback<View>() {
            @Override
            public void execute(View result) {
                switch (result.getId()){
                    case  R.id.btn_confrim_commit :

                        if(!StringUtils.isChinese(vu.getEdt_name().getText().toString())){
                            Toast.makeText(context,"请填写正确姓名",Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (vu.getEdt_body().getText().toString().length()==0) {
                            Toast.makeText(context,"身份证不能为空",Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if(!StringUtils.isUserID(vu.getEdt_body().getText().toString())){
                            Toast.makeText(context,"身份证格式错误",Toast.LENGTH_SHORT).show();
                            return;
                        }

                            String type = gson.toJson(new AttentionUserBean(getAccessToken(),"self",attentionUserBean.getAttestationId(),vu.getEdt_name().getText().toString(),phone,vu.getEdt_body().getText().toString(),pro_str_one,pro_str_two));
                            OkHttpClientManager.postAsynJson(type, UrlUtils.ATTENTION_NEW_URL, new OkHttpClientManager.StringCallback() {
                                @Override
                                public void onFailure(Request request, IOException e) {

                                }

                                @Override
                                public void onResponse(String response) {
                                    ParseBaseEntity entity = gson.fromJson(response,ParseBaseEntity.class);
                                    Toast.makeText(context,entity.getMsg(),Toast.LENGTH_SHORT).show();
                                    if (entity.getCode().equals(ResponseCodeUtils.OK)){
                                        finish();
                                    }else {
                                        Toast.makeText(context,entity.getMsg(),Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

//                        startActivity(new Intent(ConfirmUserActivity.this,ConfirmSuccessActivity.class));
                        break;
                }
            }
        });
    }

    public void initPhoto(){
        RxPermissions permissions = new RxPermissions(EditUserAuthenActivity.this);
        permissions.request(Manifest.permission.CAMERA,Manifest.permission.VIBRATE).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                if (aBoolean){
                    PhotoPicker.builder()
                            .setPhotoCount(1)
                            .setShowCamera(true)
                            .setShowGif(true)
                            .setPreviewEnabled(false)
                            .start(EditUserAuthenActivity.this, PhotoPicker.REQUEST_CODE);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
            if (data != null) {
                ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                final File file = new File(photos.get(0));

                if (type.equals("1")){
                    Glide.with(this).load(photos.get(0)).into(vu.getIv_photo());
                }
                if (type.equals("2")){
                    Glide.with(this).load(photos.get(0)).into(vu.getIv_iv_photo());
                }
                try {
                    OkHttpClientManager.postAsyn(UrlUtils.UPLOAD_PHOTO_URL, new OkHttpClientManager.StringCallback() {
                        @Override
                        public void onFailure(Request request, IOException e) {

                        }

                        @Override
                        public void onResponse(String response) {
                            ParseBaseEntity entity = gson.fromJson(response,ParseBaseEntity.class);
                            if (entity.getCode().equals(ResponseCodeUtils.OK)){
                                FileDataBean dataBean = gson.fromJson(gson.toJson(entity.getData()),FileDataBean.class);
                                if (type.equals("1")){
                                    pro_str_one = dataBean.getPath();
                                }else {
                                    pro_str_two = dataBean.getPath();
                                }
                            }
                        }
                    },file,"file");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
