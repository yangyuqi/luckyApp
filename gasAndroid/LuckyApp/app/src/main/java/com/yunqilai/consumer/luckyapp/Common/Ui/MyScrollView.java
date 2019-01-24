package com.yunqilai.consumer.luckyapp.Common.Ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ScrollView;

import rx.Observable;

/**
 * Created by yangyuqi on 17-7-31.
 */

public class MyScrollView extends ScrollView {

    public onScrollListener listener ;

    public interface onScrollListener{
        void onDown(boolean down);
        void onUp(boolean up);
    }

    public void onListener(onScrollListener m){
        listener = m ;
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (oldt < t && ((t - oldt) > 15)) {// 向上

            listener.onUp(true);
            Log.e("wangly","向上");

        }  else if (oldt > t && (oldt - t) > 15) {// 向下
            listener.onDown(true);
            Log.e("wangly","向下");
        }
    }
}
