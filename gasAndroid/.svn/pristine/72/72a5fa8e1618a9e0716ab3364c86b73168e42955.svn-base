package com.yunqilai.consumer.luckyapp.Common.Utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * 屏幕帮助类
 *
 * @author zhaokaiqiang
 *
 */
public class ScreenUtils {

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    @SuppressWarnings("deprecation")
    public static int getScreenWidth(Context context) {
        return ((WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
                .getWidth();
    }

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    @SuppressWarnings("deprecation")
    public static int getScreenHeight(Context context) {
        return ((WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
                .getHeight();
    }


    /**
     * 为rootView添加蒙层
     * @return
     */
    public static void addLayer(Activity mContext, View layerView) {
        if (mContext == null || layerView == null)
            return;
        ViewGroup contentView = (ViewGroup) mContext.getWindow().findViewById(Window.ID_ANDROID_CONTENT);
        contentView.addView(layerView);
    }


    public static void removeLayer(Activity mContext, View layerView){
        if (mContext == null || layerView == null)
            return;
        ViewGroup contentView = (ViewGroup) mContext.getWindow().findViewById(Window.ID_ANDROID_CONTENT);
        contentView.removeView(layerView);
    }
}