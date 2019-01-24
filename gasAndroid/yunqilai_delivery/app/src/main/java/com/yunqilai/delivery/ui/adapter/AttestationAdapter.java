package com.yunqilai.delivery.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yunqilai.delivery.R;
import com.yunqilai.delivery.model.Attestation;
import com.yunqilai.delivery.model.Bean.attention.ParseAttestation;

import java.util.List;

/**
 * 认证列表
 * Created by KK on 2017/6/8.
 */

public class AttestationAdapter extends AbsBaseAdapter<ParseAttestation> {
    public AttestationAdapter(Context context, List<ParseAttestation> datas) {
        super(context, datas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.listview_attestation_list, null);
            holder.nameTv = (TextView) convertView.findViewById(R.id.tv_name);
            holder.phoneTv = (TextView) convertView.findViewById(R.id.tv_phone);
            holder.idCardTv = (TextView) convertView.findViewById(R.id.tv_id_card);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        holder.nameTv.setText(mDatas.get(position).getName());
        holder.phoneTv.setText(mDatas.get(position).getPhone());
        holder.idCardTv.setText(mDatas.get(position).getIdCardNumber());

        return convertView;
    }

    private class ViewHolder{
        TextView nameTv;
        TextView phoneTv;
        TextView idCardTv;
    }
}
