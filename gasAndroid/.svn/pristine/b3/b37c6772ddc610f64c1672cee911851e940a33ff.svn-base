package com.yunqilai.delivery.ui.fragment.order;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.google.gson.Gson;
import com.yunqilai.delivery.manager.SharedPreferencesUtil;

/**
 * Created by yangyuqi on 17-7-26.
 */

public class BaseBaseFragment extends Fragment {

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
        gson = new Gson() ;
        getAccessToken();
    }
}
