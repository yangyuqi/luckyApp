package com.yunqilai.delivery.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yunqilai.delivery.R;
import com.yunqilai.delivery.model.Bean.GoodsBean;
import com.yunqilai.delivery.model.Product;
import com.yunqilai.delivery.utils.ImageLoaderUtil;
import com.yunqilai.delivery.utils.UrlUtils;

import java.util.List;

/**
 * Created by KK on 2017/6/12.
 */

public class ProductListAdapter extends AbsBaseAdapter<GoodsBean> {
    public ProductListAdapter(Context context, List<GoodsBean> datas) {
        super(context, datas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.listview_product_list, null);
            holder.productIconIv = (ImageView) convertView.findViewById(R.id.iv_product_icon);
            holder.nameTv = (TextView) convertView.findViewById(R.id.tv_product_name);
            holder.typeTv = (TextView) convertView.findViewById(R.id.tv_product_type);
            holder.priceTv = (TextView) convertView.findViewById(R.id.tv_product_price);
            holder.numTv = (TextView) convertView.findViewById(R.id.tv_product_num);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        Glide.with(mContext).load(UrlUtils.PHOTO_URL_BASE+mDatas.get(position).getGoodsPhoto()).placeholder(R.mipmap.icon_logo4).into(holder.productIconIv);
        holder.nameTv.setText(mDatas.get(position).getGoodsName());
        holder.typeTv.setText(mDatas.get(position).getGoodsModel());
        holder.priceTv.setText(String.format(mContext.getString(R.string.label_price),mDatas.get(position).getGoodsPrice()+""));
        try {
            holder.numTv.setText(((int) Double.parseDouble(mDatas.get(position).getGoodsCount())) + "");
        }catch (Exception e){
            holder.numTv.setText(mDatas.get(position).getGoodsCount() + "");
            e.printStackTrace();
        }
        return convertView;
    }

    private class ViewHolder{
        ImageView productIconIv;
        TextView nameTv;
        TextView typeTv;
        TextView priceTv;
        TextView numTv;
    }
}
