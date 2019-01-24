package com.yunqilai.delivery.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yunqilai.delivery.R;
import com.yunqilai.delivery.model.Bean.Info.InfoDetailsBean;
import com.yunqilai.delivery.model.Tank;
import com.yunqilai.delivery.ui.view.NoDoubleClickListener;

import java.util.List;

/**
 * 信息列表
 * Created by KK on 2017/6/8.
 */

public class InfoManageAdapter extends AbsBaseAdapter<InfoDetailsBean> {

    private InfoManageListCallback callback;

    public InfoManageAdapter(Context context, List<InfoDetailsBean> datas) {
        super(context, datas);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.listview_info_manage_list, null);
            holder.barCodeTv = (TextView) convertView.findViewById(R.id.tv_bar_code);
            holder.tankCodeTv = (TextView) convertView.findViewById(R.id.tv_tank_code);
            holder.tankModelTv = (TextView) convertView.findViewById(R.id.tv_tank_model);
            holder.produceTimeTv = (TextView) convertView.findViewById(R.id.tv_produce_time);
            holder.replaceCodeTv = (TextView) convertView.findViewById(R.id.iv_replace_code);
            holder.replaceCodeLayout = (RelativeLayout) convertView.findViewById(R.id.rl_to_replace_code);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        holder.barCodeTv.setText(mDatas.get(position).getBarCode());
        holder.tankCodeTv.setText(mDatas.get(position).getTankNumber());
        holder.tankModelTv.setText(mDatas.get(position).getModel());
        holder.produceTimeTv.setText(mDatas.get(position).getProduceTime());
        holder.replaceCodeLayout.setVisibility(View.VISIBLE);
        holder.replaceCodeTv.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                if(callback != null){
                    callback.replaceCode(mDatas.get(position));
                }
            }
        });
//        convertView.setOnClickListener(new NoDoubleClickListener() {
//            @Override
//            public void onNoDoubleClick(View v) {
//                if(callback != null){
//                    callback.enterTankDetail(mDatas.get(position).getId());
//                }
//            }
//        });

        return convertView;
    }

    private class ViewHolder{
        TextView barCodeTv;
        TextView tankCodeTv;
        TextView tankModelTv;
        TextView produceTimeTv;
        RelativeLayout replaceCodeLayout;
        TextView replaceCodeTv;
    }

    public interface InfoManageListCallback{
        void enterTankDetail(String tankId);
        void replaceCode(InfoDetailsBean tankId);
    }

    public void setCallback(InfoManageListCallback callback) {
        this.callback = callback;
    }
}
