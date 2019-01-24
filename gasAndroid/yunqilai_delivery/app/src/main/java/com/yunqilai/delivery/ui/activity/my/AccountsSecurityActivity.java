package com.yunqilai.delivery.ui.activity.my;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yunqilai.delivery.R;
import com.yunqilai.delivery.manager.SharedPreferencesUtil;
import com.yunqilai.delivery.ui.activity.BaseActivity;
import com.yunqilai.delivery.ui.interlayer.my.AccountsSecurityInterlayer;
import com.yunqilai.delivery.ui.presenter.my.AccountsSecurityPresenter;
import com.yunqilai.delivery.ui.view.CommonTitle;
import com.yunqilai.delivery.utils.Event;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class AccountsSecurityActivity extends BaseActivity<AccountsSecurityPresenter> implements AccountsSecurityInterlayer,View.OnClickListener {

    private CommonTitle commonTitle;
    private RelativeLayout nicknameLayout;
    private RelativeLayout modifyLayout;
    private TextView nicknameTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_accounts_security);

        presenter = new AccountsSecurityPresenter(this,this);

        initView();
        initEvent();

        presenter.setData();
    }

    private void initView(){
        commonTitle = (CommonTitle)findViewById(R.id.common_title);
        nicknameLayout = (RelativeLayout)findViewById(R.id.rl_nickname);
        modifyLayout = (RelativeLayout)findViewById(R.id.rl_modify_pwd);
        nicknameTv = (TextView)findViewById(R.id.tv_nickname);
    }

    private void initEvent(){
        commonTitle.setOnTitleClickListener(new CommonTitle.OnTitleClickListener() {
            @Override
            public void onLeftClick() {
                finish();
            }

            @Override
            public void onRightClick() {

            }
        });
        nicknameLayout.setOnClickListener(this);
        modifyLayout.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String name = (String) SharedPreferencesUtil.get(context,SharedPreferencesUtil.userName,"");
        nicknameTv.setText(name);
    }

    @Override
    public void setNickname(String nickname) {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rl_nickname:
                presenter.modifyNickname();
                break;
            case R.id.rl_modify_pwd:
                presenter.modifyPassword();
                break;
        }

    }

    @Subscribe
    public void onEvent(Event event){
        if (event == Event.EXITANDLOGIN) {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
