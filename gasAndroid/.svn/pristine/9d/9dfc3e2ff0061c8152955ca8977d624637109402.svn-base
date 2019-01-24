package com.yunqilai.consumer.luckyapp.Common.Base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

import com.google.gson.Gson;
import com.yunqilai.consumer.luckyapp.Common.Utils.SharedPreferencesUtils;
import com.yunqilai.consumer.luckyapp.Common.View.Vu;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;

/**
 * Created by Administrator on 2017/5/23.
 */

public abstract class BasePresenterActivity <V extends Vu> extends AppCompatActivity{
    protected V vu ;
    protected FragmentManager fm;
    protected EventBus bus;
    protected Gson gson ;
    public int windowWidth = 0;
    public int windowHeight = 0;
    protected Context context ;
    protected List<Subscription> rxBusList = new ArrayList<>();

    public String accessToken ;

    public String getAccessToken() {
        accessToken = (String) SharedPreferencesUtils.getParam(context,SharedPreferencesUtils.access_token,"");
        if (accessToken!=null){
            return accessToken ;
        }
        return "";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fm = getSupportFragmentManager();
        bus = EventBus.getDefault();
        gson = new Gson();
        context = this ;
        getAccessToken();
        DisplayMetrics dm = getResources().getDisplayMetrics();
        windowWidth = dm.widthPixels;
        windowHeight = dm.heightPixels;
        try {
            vu = getViewClass().newInstance();
            vu.init(getLayoutInflater(),null);
            setContentView(vu.getView());
            onBindData(savedInstanceState);
            onBindView();//model绑定 可以进行网络数据请求
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected final void onPause() {
        beforePause();
        super.onPause();
    }

    protected void beforePause(){}

    @Override
    protected final void onResume() {
        super.onResume();
        afterResume();
    }

    protected void afterResume(){}

    @Override
    protected final void onDestroy() {
        onDestroyVu();
        vu = null;
        super.onDestroy();
    }

    @Override
    public final void onBackPressed() {
        if(!handleBackPressed()) {
            super.onBackPressed();
        }
    }

    public boolean handleBackPressed(){
        return false;
    }

    protected abstract Class<V> getViewClass();

    protected void onBindView(){};

    protected void onDestroyVu() {};

    protected void onBindData(Bundle savedInstanceState){

    }
}
