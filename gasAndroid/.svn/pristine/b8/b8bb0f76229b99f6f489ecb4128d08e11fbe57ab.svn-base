package com.yunqilai.delivery.ui.activity.attestation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.okhttp.Request;
import com.yunqilai.delivery.R;
import com.yunqilai.delivery.manager.SharedPreferencesUtil;
import com.yunqilai.delivery.model.Bean.ParseBaseEntity;
import com.yunqilai.delivery.model.Bean.attention.GetAttentionBean;
import com.yunqilai.delivery.model.Bean.attention.ParseAttestation;
import com.yunqilai.delivery.request.base.ErrorCode;
import com.yunqilai.delivery.ui.activity.BaseFragmentActivity;
import com.yunqilai.delivery.ui.activity.common.LoginActivity;
import com.yunqilai.delivery.ui.view.CommonTitle;
import com.yunqilai.delivery.utils.Event;
import com.yunqilai.delivery.utils.MyOkHttpClientManager;
import com.yunqilai.delivery.utils.ResponseUtils;
import com.yunqilai.delivery.utils.StringUtils;
import com.yunqilai.delivery.utils.UrlUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;

public class AttestationDetailActivity extends BaseFragmentActivity {

    private TextView tv_name , tv_phone ,tv_id_num ,tv_progress ,tv_reason;
    private ImageView iv_front ,iv_back ,iv_attention;
    private String attentionId ;
    private View show_view ;
    private Button btn ;
    private ParseAttestation station ;

    private CommonTitle commonTitle ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_attestation_detail);
        initView();
        initData();

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
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (station!=null){
                    Intent intent = new Intent(context,AddAttestationActivity.class);
                    intent.putExtra("type","2");
                    intent.putExtra("attention",station);
                    startActivity(intent);
                    finish();
                }
            }
        });

        commonTitle.setOnTitleClickListener(new CommonTitle.OnTitleClickListener() {
            @Override
            public void onLeftClick() {
                finish();
            }

            @Override
            public void onRightClick() {

            }
        });
    }

    private void initData() {
        MyOkHttpClientManager.postAsynJson(gson.toJson(new GetAttentionBean(getAccessToken(), attentionId)), UrlUtils.ATTENTION_DETAILS_URL, new MyOkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Toast.makeText(AttestationDetailActivity.this,R.string.service_error_500,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {
                try {
                    ParseBaseEntity entity = gson.fromJson(response, ParseBaseEntity.class);
                    if (entity.getCode().equals(ResponseUtils.OK)) {
                        ParseAttestation attestation = gson.fromJson(gson.toJson(entity.getData()), ParseAttestation.class);
                        tv_name.setText(attestation.getName());
                        tv_phone.setText(attestation.getPhone());
                        tv_id_num.setText(attestation.getIdCardNumber());
                        Glide.with(context).load(UrlUtils.PHOTO_URL_BASE + attestation.getIdCardFront()).into(iv_front);
                        Glide.with(context).load(UrlUtils.PHOTO_URL_BASE + attestation.getIdCardBack()).into(iv_back);
                        tv_progress.setText(StringUtils.attentionType(attestation.getStatus()));
                        if (attestation.getStatus().equals("failed")) {
                            show_view.setVisibility(View.VISIBLE);
                            tv_reason.setText(attestation.getReason());
                            station = attestation;
                        } else if (attestation.getStatus().equals("success")) {
                            iv_attention.setImageResource(R.mipmap.icon_authentication_success);
                        } else {
                            iv_attention.setImageResource(R.mipmap.icon_audit);
                        }
                    }else if(entity.getCode().equals(ErrorCode.ERR_ACCESS_TOKEN_INVALID) || entity.getCode().equals(ErrorCode.ERR_REFRESH_TOKEN_INVALID)){
                        EventBus.getDefault().post(Event.EXITANDLOGIN);
                    }
                }catch (Exception e){
                    Toast.makeText(AttestationDetailActivity.this,R.string.service_error_500,Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }

    private void initView() {
        attentionId = getIntent().getStringExtra("attestationId");
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        tv_id_num = (TextView) findViewById(R.id.tv_id_num);
        iv_front = (ImageView) findViewById(R.id.iv_id_front);
        iv_back = (ImageView) findViewById(R.id.iv_id_back);
        show_view = findViewById(R.id.rl_reason);
        tv_progress = (TextView) findViewById(R.id.tv_result_tip);
        iv_attention = (ImageView) findViewById(R.id.iv_result_icon);
        tv_reason = (TextView) findViewById(R.id.tv_result_reason);
        btn = (Button) findViewById(R.id.btn_to_modify);
        commonTitle = (CommonTitle) findViewById(R.id.com_title);
    }
}
