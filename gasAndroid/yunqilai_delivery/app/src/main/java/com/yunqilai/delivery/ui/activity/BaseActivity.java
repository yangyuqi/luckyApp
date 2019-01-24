package com.yunqilai.delivery.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.yunqilai.delivery.manager.SharedPreferencesUtil;
import com.yunqilai.delivery.ui.presenter.AbsPresenter;

public class BaseActivity<T extends AbsPresenter> extends Activity {
    protected T presenter;
    protected Gson gson ;
    protected Context context ;
    protected String accessToken ;

    public String getAccessToken() {
        accessToken = (String) SharedPreferencesUtil.get(context,SharedPreferencesUtil.accessToken,"");
        if (accessToken!=null){
            return accessToken ;
        }
        return "";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gson = new Gson();
        context = this ;
        getAccessToken();
    }

    @Override
    protected void onDestroy() {
        if(presenter != null){
            presenter.onDestory();
        }
        super.onDestroy();
    }
}
