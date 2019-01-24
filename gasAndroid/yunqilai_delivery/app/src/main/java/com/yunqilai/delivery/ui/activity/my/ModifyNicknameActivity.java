package com.yunqilai.delivery.ui.activity.my;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Request;
import com.yunqilai.delivery.R;
import com.yunqilai.delivery.manager.SharedPreferencesUtil;
import com.yunqilai.delivery.model.Bean.ParseBaseEntity;
import com.yunqilai.delivery.model.Bean.my.NickNameBean;
import com.yunqilai.delivery.request.base.ErrorCode;
import com.yunqilai.delivery.ui.activity.BaseActivity;
import com.yunqilai.delivery.ui.interlayer.my.ModifyNicknameInterlayer;
import com.yunqilai.delivery.ui.presenter.my.ModifyNicknamePresenter;
import com.yunqilai.delivery.ui.view.CommonTitle;
import com.yunqilai.delivery.utils.Event;
import com.yunqilai.delivery.utils.MyOkHttpClientManager;
import com.yunqilai.delivery.utils.ResponseUtils;
import com.yunqilai.delivery.utils.UrlUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;

public class ModifyNicknameActivity extends BaseActivity<ModifyNicknamePresenter> implements ModifyNicknameInterlayer {

    private CommonTitle commonTitle;
    private EditText nicknameTv;
    private ImageView deleteIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_modify_nickname);

        presenter = new ModifyNicknamePresenter(this,this);

        initView();
        initEvent();

        presenter.getData();
        String name = (String) SharedPreferencesUtil.get(context,SharedPreferencesUtil.userName,"");
        nicknameTv.setText(name);
        nicknameTv.setSelection(name.length());
    }

    private void initView(){
        commonTitle = (CommonTitle)findViewById(R.id.common_title);
        nicknameTv = (EditText)findViewById(R.id.et_nickname);
        deleteIv = (ImageView)findViewById(R.id.iv_delete);
    }

    private void initEvent(){
        commonTitle.setOnTitleClickListener(new CommonTitle.OnTitleClickListener() {
            @Override
            public void onLeftClick() {
                finish();
            }

            @Override
            public void onRightClick() {
                if (!nicknameTv.getText().toString().equals("")){
                    String type = gson.toJson(new NickNameBean(getAccessToken(),nicknameTv.getText().toString()));
                    MyOkHttpClientManager.postAsynJson(type, UrlUtils.EDIT_NICKNAME_URL, new MyOkHttpClientManager.StringCallback() {
                        @Override
                        public void onFailure(Request request, IOException e) {
                            Toast.makeText(ModifyNicknameActivity.this,R.string.service_error_500,Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(String response) {
                            try {
                                ParseBaseEntity entity = gson.fromJson(response, ParseBaseEntity.class);
                                if (entity.getCode().equals(ResponseUtils.OK)) {
                                    SharedPreferencesUtil.put(context, SharedPreferencesUtil.userName, nicknameTv.getText().toString());
                                    finish();
                                }else if(entity.getCode().equals(ErrorCode.ERR_ACCESS_TOKEN_INVALID) || entity.getCode().equals(ErrorCode.ERR_REFRESH_TOKEN_INVALID)){
                                    EventBus.getDefault().post(Event.EXITANDLOGIN);
                                }
                            }catch (Exception e){
                                Toast.makeText(ModifyNicknameActivity.this,R.string.service_error_500,Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
        deleteIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nicknameTv.setText("");
            }
        });
    }


    @Override
    public void setNickname(String nickname) {

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
