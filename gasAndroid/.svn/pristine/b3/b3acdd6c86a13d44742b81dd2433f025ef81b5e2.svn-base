package com.yunqilai.delivery.ui.activity.my;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.Request;
import com.yunqilai.delivery.R;
import com.yunqilai.delivery.model.Bean.ParseBaseEntity;
import com.yunqilai.delivery.model.Bean.my.AgainPwdBean;
import com.yunqilai.delivery.request.base.ErrorCode;
import com.yunqilai.delivery.ui.activity.BaseFragmentActivity;
import com.yunqilai.delivery.ui.view.CommonTitle;
import com.yunqilai.delivery.utils.Event;
import com.yunqilai.delivery.utils.MD5Utils;
import com.yunqilai.delivery.utils.MyOkHttpClientManager;
import com.yunqilai.delivery.utils.ResponseUtils;
import com.yunqilai.delivery.utils.UrlUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;

public class ModifyPasswordActivity extends BaseFragmentActivity {

    private EditText edt_old ,edt_new ,edt_again ;
    private CommonTitle title ;
    private Button btn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_modify_password);
        title = (CommonTitle) findViewById(R.id.com_title);
        edt_old = (EditText) findViewById(R.id.edt_pwd_old);
        edt_new = (EditText) findViewById(R.id.edt_pwd_new);
        edt_again = (EditText) findViewById(R.id.edt_pwd_again);
        btn = (Button) findViewById(R.id.btn_sure);
        title.setOnTitleClickListener(new CommonTitle.OnTitleClickListener() {
            @Override
            public void onLeftClick() {
                finish();
            }

            @Override
            public void onRightClick() {

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edt_old.getText().toString().equals("")){
                    if (edt_new.getText().toString().equals(edt_again.getText().toString())){
                        String type = gson.toJson(new AgainPwdBean(getAccessToken(), MD5Utils.md5Password(edt_old.getText().toString()),MD5Utils.md5Password(edt_again.getText().toString())));
                        MyOkHttpClientManager.postAsynJson(type, UrlUtils.EDIT_NEW_PASSWORD, new MyOkHttpClientManager.StringCallback() {
                            @Override
                            public void onFailure(Request request, IOException e) {
                                Toast.makeText(ModifyPasswordActivity.this,R.string.service_error_500,Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onResponse(String response) {
                                try {
                                    ParseBaseEntity entity = gson.fromJson(response, ParseBaseEntity.class);
                                    if (entity.getCode().equals(ResponseUtils.OK)) {
                                        EventBus.getDefault().post(Event.EXITANDLOGIN);
                                        finish();
                                    }else if(entity.getCode().equals(ErrorCode.ERR_ACCESS_TOKEN_INVALID) || entity.getCode().equals(ErrorCode.ERR_REFRESH_TOKEN_INVALID)){
                                        EventBus.getDefault().post(Event.EXITANDLOGIN);
                                    }
                                }catch (Exception e){
                                    Toast.makeText(ModifyPasswordActivity.this,R.string.service_error_500,Toast.LENGTH_SHORT).show();
                                    e.printStackTrace();
                                }
                            }
                        });
                    }else {
                        Toast.makeText(context, "两次密码不一致", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(context,"密码不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });

        edt_old.addTextChangedListener(emptyWatcher);
        edt_new.addTextChangedListener(emptyWatcher);
        edt_again.addTextChangedListener(emptyWatcher);
    }


    private TextWatcher emptyWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            refreshSubmitBtn();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
    private void refreshSubmitBtn(){
        if("".equals(edt_old.getText().toString().trim())||
                "".equals(edt_new.getText().toString().trim())||
                "".equals(edt_again.getText().toString().trim())
                ){
            btn.setEnabled(false);
        }else{
            btn.setEnabled(true);
        }
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
