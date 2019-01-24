package com.yunqilai.delivery.ui.view;

import android.view.View;

import java.util.Calendar;

/**
 * 防止多次点击的点击事件监听
 * Created by KK on 2016/10/31.
 */
public abstract class NoDoubleClickListener implements View.OnClickListener {

    public final int MIN_CLICK_DELAY_TIME = 1000;
    private long lastClickTime = 0;

    @Override
    public void onClick(View v) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            onNoDoubleClick(v);
        }
    }

    public abstract void onNoDoubleClick(View v);
}