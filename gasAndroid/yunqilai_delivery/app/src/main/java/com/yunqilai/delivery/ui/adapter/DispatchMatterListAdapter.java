package com.yunqilai.delivery.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yunqilai.delivery.R;
import com.yunqilai.delivery.model.Bean.my.ArticleBean;
import com.yunqilai.delivery.model.Matter;
import com.yunqilai.delivery.utils.ImageLoaderUtil;
import com.yunqilai.delivery.utils.UrlUtils;

import java.util.List;

/**
 * Created by KK on 2017/6/13.
 */

public class DispatchMatterListAdapter extends AbsBaseAdapter<ArticleBean>{

    public DispatchMatterListAdapter(Context context, List<ArticleBean> datas) {
        super(context, datas);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.listview_dispatch_matter_list, null);
            holder.backgroundIv = (ImageView) convertView.findViewById(R.id.iv_background);
            holder.titleTv = (TextView) convertView.findViewById(R.id.tv_title);
            holder.dateTv = (TextView) convertView.findViewById(R.id.tv_date);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        Glide.with(parent.getContext()).load(UrlUtils.PHOTO_URL_BASE+mDatas.get(position).getUrl()).into(holder.backgroundIv);
        holder.titleTv.setText(mDatas.get(position).getTitle());
        holder.dateTv.setText(mDatas.get(position).getAddTime());

        return convertView;
    }

    private class ViewHolder{
        ImageView backgroundIv;
        TextView titleTv;
        TextView dateTv;
    }
}
