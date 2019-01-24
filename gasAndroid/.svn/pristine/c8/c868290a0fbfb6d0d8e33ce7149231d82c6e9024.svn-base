package com.yunqilai.delivery.ui.adapter;

import android.content.Context;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by KK on 2017/2/17.
 */
public abstract class AbsBaseAdapter<T> extends BaseAdapter {

    protected Context mContext;
    protected List<T> mDatas;

    public AbsBaseAdapter(Context context, List<T> datas) {
        this.mContext = context;
        this.mDatas = datas;
    }

    @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
