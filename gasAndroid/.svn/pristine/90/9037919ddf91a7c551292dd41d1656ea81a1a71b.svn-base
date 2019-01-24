package com.yunqilai.consumer.luckyapp.UserCenter.Presenter;

import android.content.Intent;
import android.view.View;

import com.yunqilai.consumer.luckyapp.Common.Base.BasePresenterActivity;
import com.yunqilai.consumer.luckyapp.Common.Utils.SharedPreferencesUtils;
import com.yunqilai.consumer.luckyapp.UserCenter.View.AccountSafeView;
import com.yunqilai.consumer.luckyapp.UserCenter.View.RePwdView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Administrator on 2017/6/12.
 */

public class AccountSafeActivity extends BasePresenterActivity<AccountSafeView> {

    private String name ;

    @Override
    protected Class<AccountSafeView> getViewClass() {
        return AccountSafeView.class;
    }

    @Override
    protected void onBindView() {
        super.onBindView();

        EventBus.getDefault().register(this);
        vu.getIv_back().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        vu.getView_ni().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,RenameActivity.class));
            }
        });

        vu.getView_pwd().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, RePwdActivity.class));
            }
        });
    }

    @Override
    protected void afterResume() {
        super.afterResume();
        name = (String) SharedPreferencesUtils.getParam(context,SharedPreferencesUtils.userName,"");
        if (!name.equals("")){
            vu.getTv_name().setText(name);
        }
    }

    @Override
    protected void onDestroyVu() {
        super.onDestroyVu();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(Integer s){
        if (s==6){
            finish();
        }
    }
}
