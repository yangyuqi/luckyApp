package com.yunqilai.delivery.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunqilai.delivery.R;
import com.yunqilai.delivery.model.Attestation;
import com.yunqilai.delivery.model.TankOperate;

import java.util.List;

/**
 * 操作
 * Created by KK on 2017/6/14.
 */

public class TankOperateAdapter extends AbsBaseAdapter<TankOperate> {
    public TankOperateAdapter(Context context, List<TankOperate> datas) {
        super(context, datas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.listview_attestation_list, null);
            holder.iconIv = (ImageView) convertView.findViewById(R.id.iv_icon);
            holder.titleTv = (TextView) convertView.findViewById(R.id.tv_title);
            holder.chooseIv = (ImageView) convertView.findViewById(R.id.iv_choose);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        if(mDatas.get(position).getIconId()>0){
            holder.iconIv.setImageResource(mDatas.get(position).getIconId());
        }
        holder.titleTv.setText(mDatas.get(position).getTitle());
        if(mDatas.get(position).isChoose()){
            holder.chooseIv.setVisibility(View.VISIBLE);
        }else{
            holder.chooseIv.setVisibility(View.GONE);
        }

        return convertView;
    }

    private class ViewHolder{
        ImageView iconIv;
        TextView titleTv;
        ImageView chooseIv;
    }
}
