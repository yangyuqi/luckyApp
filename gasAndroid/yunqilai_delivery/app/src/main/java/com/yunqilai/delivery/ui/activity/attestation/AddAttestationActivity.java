package com.yunqilai.delivery.ui.activity.attestation;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.okhttp.Request;
import com.yunqilai.delivery.R;
import com.yunqilai.delivery.model.Bean.ParseBaseEntity;
import com.yunqilai.delivery.model.Bean.attention.AttentionUserBean;
import com.yunqilai.delivery.model.Bean.attention.FileDataBean;
import com.yunqilai.delivery.model.Bean.attention.ParseAttestation;
import com.yunqilai.delivery.model.Bean.attention.UseAttentionBean;
import com.yunqilai.delivery.request.base.ErrorCode;
import com.yunqilai.delivery.ui.activity.BaseActivity;
import com.yunqilai.delivery.ui.inputFilter.IDCardNumInputFilter;
import com.yunqilai.delivery.ui.inputFilter.OnlyChineseInputFilter;
import com.yunqilai.delivery.ui.view.CommonTitle;
import com.yunqilai.delivery.utils.ArrayUtil;
import com.yunqilai.delivery.utils.Event;
import com.yunqilai.delivery.utils.MyOkHttpClientManager;
import com.yunqilai.delivery.utils.ResponseUtils;
import com.yunqilai.delivery.utils.StringUtils;
import com.yunqilai.delivery.utils.UrlUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import me.iwf.photopicker.PhotoPicker;
import rx.functions.Action1;

/**
 * Created by  on 2017/6/13.
 */

public class AddAttestationActivity extends BaseActivity {

    private String type ;
    private ParseAttestation attestation ;
    private CommonTitle title ;
    private String photo_front ,photo_back ,flag ,flag2;

    private EditText edt_name ,edt_phone ,edt_card ;
    private TextView tv_left ,tv_right ;
    private ImageView iv_iv_left ,iv_iv_right ;
    UseAttentionBean attentionBean ;
    private Button btn_confirm ;

    private boolean nameResult =false,phoneResult = false,idResult = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_add_attestation);
        initView();
        type = getIntent().getStringExtra("type");
        if (type.equals("2")){
            attestation = (ParseAttestation) getIntent().getSerializableExtra("attention");
            photo_front = attestation.getIdCardFront();
            photo_back = attestation.getIdCardBack() ;
            Glide.with(context).load(UrlUtils.PHOTO_URL_BASE+photo_front).into(iv_iv_left);
            Glide.with(context).load(UrlUtils.PHOTO_URL_BASE+photo_back).into(iv_iv_right);
            tv_left.setVisibility(View.VISIBLE);
            tv_right.setVisibility(View.VISIBLE);
            iv_iv_left.setClickable(false);
            iv_iv_right.setClickable(false);
        }else if (type.equals("1")){
            title.setTitle("实名认证");
            try {
                attentionBean = (UseAttentionBean) getIntent().getSerializableExtra("data");
                edt_name.setText(attentionBean.getName());
                edt_phone.setText(attentionBean.getPhone());
            }catch (Exception e){

            }
        }

        initEvent();
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

    private void initEvent() {
        title.setOnTitleClickListener(new CommonTitle.OnTitleClickListener() {
            @Override
            public void onLeftClick() {
                finish();
            }

            @Override
            public void onRightClick() {

            }
        });

        iv_iv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = "1";
                initPhoto();
            }
        });

        iv_iv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag2 = "2";
                initPhoto();
            }
        });

        tv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = "1";
                initPhoto();
            }
        });

        tv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag2 = "2";
                initPhoto();
            }
        });

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mtype = "" ;

                if(edt_name.getText().toString().length() == 0){
                    Toast.makeText(context,"姓名不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!StringUtils.isChinese(edt_name.getText().toString())){
                    Toast.makeText(context,"姓名需为中文",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(edt_name.getText().toString().length() > 30){
                    Toast.makeText(context,"姓名过长",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(edt_phone.getText().toString().length() == 0){
                    Toast.makeText(context,"手机号不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!StringUtils.checkMobileNumber(edt_phone.getText().toString())){
                    Toast.makeText(context,"手机号格式错误",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(edt_card.getText().toString().length() == 0){
                    Toast.makeText(context,"身份证不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!StringUtils.isUserID(edt_card.getText().toString())){
                    Toast.makeText(context,"身份证格式错误",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(photo_front == null || "".equals(photo_front)){
                    Toast.makeText(context,"请上传身份证正面照",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(photo_back == null || "".equals(photo_back)){
                    Toast.makeText(context,"请上传身份证反面照",Toast.LENGTH_SHORT).show();
                    return;
                }

                    if (photo_front!=null&&photo_back!=null){
                        if (type.equals("1")) {
                            if (!edt_card.getText().toString().equals("")&&!edt_phone.getText().equals("")&&!edt_name.getText().toString().equals("")) {
                                if (edt_card.getText().toString().length()==18&&edt_phone.getText().toString().length()==11) {
                                    mtype = gson.toJson(new AttentionUserBean(getAccessToken(), "other", null, edt_name.getText().toString(), edt_phone.getText().toString(), edt_card.getText().toString(), photo_front, photo_back));
                                }else {
                                    Toast.makeText(context,"手机号11位，身份证18位填写完整",Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(context,"填写完整",Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            mtype = gson.toJson(new AttentionUserBean(getAccessToken(), "other", attestation.getAttestationId(), edt_name.getText().toString(), edt_phone.getText().toString(), edt_card.getText().toString(), photo_front, photo_back));
                        }
                        if (!mtype.equals("")) {
                            btn_confirm.setClickable(false);
                            MyOkHttpClientManager.postAsynJson(mtype, UrlUtils.ATTENTION_NEW_URL, new MyOkHttpClientManager.StringCallback() {
                                @Override
                                public void onFailure(Request request, IOException e) {
                                    Toast.makeText(AddAttestationActivity.this,R.string.service_error_500,Toast.LENGTH_SHORT).show();
                                    btn_confirm.setClickable(true);
                                }

                                @Override
                                public void onResponse(String response) {
                                    Log.e("sssssss", response);
                                    try {
                                        btn_confirm.setClickable(false);
                                        ParseBaseEntity entity = gson.fromJson(response, ParseBaseEntity.class);
                                        Toast.makeText(context, entity.getMsg(), Toast.LENGTH_SHORT).show();
                                        if (entity.getCode().equals(ResponseUtils.OK)) {
                                            Intent intent = new Intent();
                                            setResult(1,intent);
                                            finish();
                                        }else if(entity.getCode().equals(ErrorCode.ERR_ACCESS_TOKEN_INVALID) || entity.getCode().equals(ErrorCode.ERR_REFRESH_TOKEN_INVALID)){
                                            EventBus.getDefault().post(Event.EXITANDLOGIN);
                                        } else {
                                            Toast.makeText(context, entity.getMsg(), Toast.LENGTH_SHORT).show();
                                        }
                                    }catch (Exception e){
                                        Toast.makeText(AddAttestationActivity.this,R.string.service_error_500,Toast.LENGTH_SHORT).show();
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    }else {
                        Toast.makeText(context,"选择身份证",Toast.LENGTH_SHORT).show();
                    }
                }

        });

        edt_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() > 0){
                    nameResult = true;
                }else {
                    nameResult = false;
                }
                if(nameResult && phoneResult && idResult){
                    btn_confirm.setBackgroundResource(R.drawable.bg_blue_gradient_round_side);
                }else {
                    btn_confirm.setBackgroundResource(R.drawable.bg_gray_gradient_round_side);
                }
            }
        });
        edt_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() > 0){
                    phoneResult = true;
                }else {
                    phoneResult = false;
                }
                if(nameResult && phoneResult && idResult){
                    btn_confirm.setBackgroundResource(R.drawable.bg_blue_gradient_round_side);
                }else {
                    btn_confirm.setBackgroundResource(R.drawable.bg_gray_gradient_round_side);
                }
            }
        });
        edt_card.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() > 0){
                    idResult = true;
                }else {
                    idResult = false;
                }
                if(nameResult && phoneResult && idResult){
                    btn_confirm.setBackgroundResource(R.drawable.bg_blue_gradient_round_side);
                }else {
                    btn_confirm.setBackgroundResource(R.drawable.bg_gray_gradient_round_side);
                }
            }
        });

    }

    private void initView() {
        title = (CommonTitle) findViewById(R.id.com_title);
        edt_name = (EditText) findViewById(R.id.edt_name_);
        edt_phone = (EditText) findViewById(R.id.edt_phone_);
        edt_card = (EditText) findViewById(R.id.edt_card_);
        tv_left = (TextView) findViewById(R.id.tv_click_left);
        tv_right = (TextView) findViewById(R.id.tv_click_right);
        iv_iv_left = (ImageView) findViewById(R.id.iv_iv_left);
        iv_iv_right = (ImageView) findViewById(R.id.iv_iv_right);
        btn_confirm = (Button) findViewById(R.id.btn_confrim_commit);

        //增加输入过滤器
        edt_name.setFilters(ArrayUtil.appendFilter(edt_name.getFilters(), new OnlyChineseInputFilter()));
        edt_card.setFilters(ArrayUtil.appendFilter(edt_card.getFilters(), new IDCardNumInputFilter()));

    }

    public void initPhoto(){
                    PhotoPicker.builder()
                            .setPhotoCount(1)
                            .setShowCamera(true)
                            .setShowGif(true)
                            .setPreviewEnabled(false)
                            .start(AddAttestationActivity.this, PhotoPicker.REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
            if (data != null) {
                final ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                final File file = new File(photos.get(0));

                try{
                    if ("1".equals(flag)){
                        Glide.with(context).load(photos.get(0)).into(iv_iv_left);
                    }else if ("2".equals(flag2)){
                        Glide.with(context).load(photos.get(0)).into(iv_iv_right);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                try {
                    MyOkHttpClientManager.postAsyn(UrlUtils.UPLOAD_PHOTO_URL, new MyOkHttpClientManager.StringCallback() {
                        @Override
                        public void onFailure(Request request, IOException e) {

                        }

                        @Override
                        public void onResponse(String response) {
                            ParseBaseEntity entity = gson.fromJson(response,ParseBaseEntity.class);
                            if (entity.getCode().equals(ResponseUtils.OK)){
                                FileDataBean dataBean = gson.fromJson(gson.toJson(entity.getData()),FileDataBean.class);
                                if ("1".equals(flag)){
                                    photo_front = dataBean.getPath();
                                    //Glide.with(context).load(photos.get(0)).into(iv_iv_left);
                                    flag = "";
                                }else if ("2".equals(flag2)){
                                    photo_back = dataBean.getPath();
                                    //Glide.with(context).load(photos.get(0)).into(iv_iv_right);
                                    flag2 = "";
                                }
                            }else if(entity.getCode().equals(ErrorCode.ERR_ACCESS_TOKEN_INVALID) || entity.getCode().equals(ErrorCode.ERR_REFRESH_TOKEN_INVALID)){
                                EventBus.getDefault().post(Event.EXITANDLOGIN);
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
