package com.yunqilai.consumer.luckyapp.Common.Presenter;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.Base.BasePresenterActivity;
import com.yunqilai.consumer.luckyapp.Common.Model.AttentionUserBean;
import com.yunqilai.consumer.luckyapp.Common.Model.FileDataBean;
import com.yunqilai.consumer.luckyapp.Common.Model.ParseBaseEntity;
import com.yunqilai.consumer.luckyapp.Common.Utils.OkHttpClientManager;
import com.yunqilai.consumer.luckyapp.Common.Utils.ResponseCodeUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.SharedPreferencesUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.StringUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.UrlUtils;
import com.yunqilai.consumer.luckyapp.Common.View.ConfirmUserView;
import com.yunqilai.consumer.luckyapp.Common.View.VuCallback;
import com.yunqilai.consumer.luckyapp.MainActivity;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import me.iwf.photopicker.PhotoPicker;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/5/24.
 */

public class ConfirmUserActivity extends BasePresenterActivity<ConfirmUserView> {

    private String pro_str_one ,pro_str_two ;
    private String type = "",flag ,type2 = "";
    private String phone ;

    @Override
    protected Class<ConfirmUserView> getViewClass() {
        return ConfirmUserView.class;
    }

    @Override
    protected void onBindView() {
        super.onBindView();

        phone = (String) SharedPreferencesUtils.getParam(context,SharedPreferencesUtils.mobile,"");
        flag = getIntent().getStringExtra("flag");//为１来自注册界面

        if (flag.equals("2")){
            vu.getTv_next().setVisibility(View.GONE);
        }

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
                EventBus.getDefault().post("closeLogin");
                finish();
            }
        });

        vu.getImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "1";
                initPhoto();
            }
        });

        vu.getImageView_two().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type2 = "2";
                initPhoto();
            }
        });

        vu.getTv_photo_one().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "1";
                initPhoto();
            }
        });

        vu.getTv_photo_two().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type2 = "2";
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
                        if (pro_str_one!=null&&pro_str_two!=null){
                            if (vu.getEdt_name().getText().toString().length()>=2&&vu.getEdt_name().getText().toString().length()<20) {

                                if(!StringUtils.isChinese(vu.getEdt_name().getText().toString())){
                                    Toast.makeText(context,"请填写正确姓名",Toast.LENGTH_SHORT).show();
                                    return;
                                }


                                if (vu.getEdt_body().getText().toString().length()!=0) {

                                    if(!StringUtils.isUserID(vu.getEdt_body().getText().toString())){
                                        Toast.makeText(context,"身份证格式错误",Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                    String type = gson.toJson(new AttentionUserBean(getAccessToken(), "self", null, vu.getEdt_name().getText().toString(), phone, vu.getEdt_body().getText().toString(), pro_str_one, pro_str_two));
                                    OkHttpClientManager.postAsynJson(type, UrlUtils.ATTENTION_NEW_URL, new OkHttpClientManager.StringCallback() {
                                        @Override
                                        public void onFailure(Request request, IOException e) {

                                        }

                                        @Override
                                        public void onResponse(String response) {
                                            ParseBaseEntity entity = gson.fromJson(response, ParseBaseEntity.class);
                                            Toast.makeText(context, entity.getMsg(), Toast.LENGTH_SHORT).show();
                                            if (entity.getCode().equals(ResponseCodeUtils.OK)) {
                                                if (flag.equals("1")) {
                                                    startActivity(new Intent(context, ConfirmSuccessActivity.class));
                                                    finish();
                                                } else {
                                                    finish();
                                                    EventBus.getDefault().post("refresh");
                                                }
                                            } else {
                                                Toast.makeText(context, entity.getMsg(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }else {
                                    Toast.makeText(context,"身份证号码不能为空",Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(context,"请填写正确姓名",Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(context,"请上传身份证",Toast.LENGTH_SHORT).show();
                        }

//                        startActivity(new Intent(ConfirmUserActivity.this,ConfirmSuccessActivity.class));
                        break;
                }
            }
        });
    }

    public void initPhoto(){
        RxPermissions permissions = new RxPermissions(ConfirmUserActivity.this);
        permissions.request(Manifest.permission.CAMERA,Manifest.permission.VIBRATE).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                if (aBoolean){
                    PhotoPicker.builder()
                            .setPhotoCount(1)
                            .setShowCamera(true)
                            .setShowGif(true)
                            .setPreviewEnabled(false)
                            .start(ConfirmUserActivity.this, PhotoPicker.REQUEST_CODE);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
            if (data != null) {
                final ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                final File file = new File(photos.get(0));

                final ProgressDialog dialog2 = ProgressDialog.show(context, "提示", "正在上传,请稍后~");
                dialog2.show();

                try {
                    OkHttpClientManager.postAsyn(UrlUtils.UPLOAD_PHOTO_URL, new OkHttpClientManager.StringCallback() {
                        @Override
                        public void onFailure(Request request, IOException e) {

                        }

                        @Override
                        public void onResponse(String response) {
                            dialog2.dismiss();
                            ParseBaseEntity entity = gson.fromJson(response,ParseBaseEntity.class);
                            if (entity.getCode().equals(ResponseCodeUtils.OK)){
                                FileDataBean dataBean = gson.fromJson(gson.toJson(entity.getData()),FileDataBean.class);
                                if (type.equals("1")){
                                    pro_str_one = dataBean.getPath();
                                    Glide.with(context).load(photos.get(0)).into(vu.getIv_photo());
                                    vu.getImageView().setVisibility(View.GONE);
                                    vu.getView_photo().setVisibility(View.VISIBLE);
                                    type = "";
                                    if (type.equals("")&&pro_str_two==null){
                                        Toast.makeText(context, "请上传身份证反面照", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                if (type2.equals("2")){
                                    pro_str_two = dataBean.getPath();
                                    Glide.with(context).load(photos.get(0)).into(vu.getIv_iv_photo());
                                    vu.getImageView_two().setVisibility(View.GONE);
                                    vu.getView_photo().setVisibility(View.VISIBLE);
                                    type2 = "";
                                    if (type2.equals("")&&pro_str_one==null) {
                                        Toast.makeText(context, "请上传身份证正面照", Toast.LENGTH_SHORT).show();
                                    }
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
