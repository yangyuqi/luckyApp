package com.yunqilai.delivery;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.yunqilai.delivery.manager.MyCrashHandler;
import com.yunqilai.delivery.manager.SharedPreferencesUtil;
import com.yunqilai.delivery.utils.StringUtils;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * Created by KK on 2017/6/12.
 */

public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = this;

//        if(!Constants.isDebug){
//            //捕获程序崩溃
            Thread.setDefaultUncaughtExceptionHandler(new MyCrashHandler(this));
//        }

        //初始化sdk
        JPushInterface.setDebugMode(true);//正式版的时候设置false，关闭调试
        JPushInterface.init(this);
        //建议添加tag标签，发送消息的之后就可以指定tag标签来发送了
//        String name = (String) SharedPreferencesUtil.get(context,SharedPreferencesUtil.mobile,"");
//        String aline = name + "2"+StringUtils.getPhotoImEi(this);
//        JPushInterface.setAlias(this, aline, new TagAliasCallback() {
//            @Override
//            public void gotResult(int i, String s, Set<String> set) {
//
//
//            }
//        });

    }

    public static Context getContext() {
        return context;
    }

}
