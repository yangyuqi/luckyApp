package com.yunqilai.delivery.ui.presenter;

import android.content.Context;



/**
 * Created by KK on 2017/6/5.
 */

public abstract class AbsPresenter<T> {

    protected Context mContext;
    protected T interlayer;

    public AbsPresenter(Context context, T interlayer) {
        this.mContext = context;
        this.interlayer = interlayer;
    }

    public void onDestory(){
        interlayer = null;
    }
}
