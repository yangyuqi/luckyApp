package com.yunqilai.consumer.luckyapp.Common.Base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.yunqilai.consumer.luckyapp.Common.Utils.SharedPreferencesUtils;
import com.yunqilai.consumer.luckyapp.Common.View.Vu;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2017/5/23.
 */

public abstract class BasePresenterFragment <V extends Vu> extends Fragment {

    protected V vu ;
    protected Context context ;
    public int windowWidth = 0;
    public int windowHeight = 0;
    public EventBus bus = EventBus.getDefault();
    public Gson gson ;

    public String accessToken ;

    public String getAccessToken() {
        accessToken = (String) SharedPreferencesUtils.getParam(context,SharedPreferencesUtils.access_token,"");
        if (accessToken!=null){
            return accessToken ;
        }
        return "";
    }

    @Override
    public void onAttach(Context mcontext) {
        super.onAttach(mcontext);
        context = mcontext ;
        DisplayMetrics dm = getResources().getDisplayMetrics();
        windowWidth = dm.widthPixels;
        windowHeight = dm.heightPixels;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        gson = new Gson();
        getAccessToken();
        View view = null;
        try {
            vu =  getViewClass().newInstance();
            vu.init(inflater, container);
            bindView();
            view = vu.getView();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bindData();
    }

    @Override
    public final void onDestroyView() {
        destroyView();
        vu = null;
        super.onDestroyView();
    }

    public abstract Class<V> getViewClass();
    public void bindView(){};
    protected void destroyView() {};

    public void bindData(){};
}
