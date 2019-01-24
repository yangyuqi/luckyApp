package com.yunqilai.consumer.luckyapp.Common;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.yunqilai.consumer.luckyapp.Common.Utils.SharedPreferencesUtils;
import com.yunqilai.consumer.luckyapp.UserCenter.StringUtils;

import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * Created by Administrator on 2017/6/5.
 */

public class LuckyApp extends Application {

    public static Application application ;
    public static Context context ;


    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this ;
        context = this ;

        //初始化sdk
        JPushInterface.setDebugMode(true);//正式版的时候设置false，关闭调试
        JPushInterface.init(this);
        //建议添加tag标签，发送消息的之后就可以指定tag标签来发送了
//        String name = (String) SharedPreferencesUtils.getParam(context,SharedPreferencesUtils.mobile,"");
//        String aline = name+"1"+StringUtils.getPhotoImEi(this);
//
//        JPushInterface.setAlias(this, aline, new TagAliasCallback() {
//            @Override
//            public void gotResult(int i, String s, Set<String> set) {
//
//
//            }
//        });

    }
}
