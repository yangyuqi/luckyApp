package com.yunqilai.delivery.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.google.gson.Gson;
import com.yunqilai.delivery.manager.SharedPreferencesUtil;
import com.yunqilai.delivery.ui.presenter.AbsPresenter;

/**
 * Created by KK on 2017/6/5.
 */

public class BaseFragment<T extends AbsPresenter> extends Fragment {
    protected T presenter;
    protected Context context ;
    protected Gson gson ;

    protected String accessToken ;

    public String getAccessToken() {
        accessToken = (String) SharedPreferencesUtil.get(context,SharedPreferencesUtil.accessToken,"");
        if (accessToken!=null){
            return accessToken ;
        }
        return "";
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        gson = new Gson();
        getAccessToken();
    }

    @Override
    public void onDestroy() {
        if(presenter != null){
            presenter.onDestory();
        }
        super.onDestroy();
    }
}
