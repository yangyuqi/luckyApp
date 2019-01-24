package com.yunqilai.delivery.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunqilai.delivery.R;
import com.yunqilai.delivery.model.Bean.OrderDetailsBean;
import com.yunqilai.delivery.model.Order;
import com.yunqilai.delivery.ui.view.NoDoubleClickListener;

import java.util.List;

/**
 * Created by KK on 2017/6/7.
 */

public class OrderListAdapter extends AbsBaseAdapter<OrderDetailsBean>{

    private OrderListCallBack callBack;

    public interface OrderListCallBack{
        void enterOrderDetail(String orderId);
        void call(String phone);
        void surePickup(String orderId);
    }

    public OrderListAdapter(Context context, List<OrderDetailsBean> datas) {
        super(context, datas);
    }

    @Override
    public int getCount() {
        return null !=mDatas? mDatas.size():0;
    }

    @Override
    public OrderDetailsBean getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.listview_order_list, null);
            holder.orderNoTv = (TextView) convertView.findViewById(R.id.tv_order_id);
            holder.payTypeTv = (TextView) convertView.findViewById(R.id.tv_order_pay_type);
            holder.callIv = (ImageView) convertView.findViewById(R.id.iv_phone);
            holder.usernameTv = (TextView) convertView.findViewById(R.id.tv_username);
            holder.phoneTv = (TextView) convertView.findViewById(R.id.tv_phone);
            holder.addressTv = (TextView) convertView.findViewById(R.id.tv_address);
            holder.deliveryTimeTv = (TextView) convertView.findViewById(R.id.tv_delivery_time);
            holder.countTv = (TextView) convertView.findViewById(R.id.tv_count);
            holder.priceTv = (TextView) convertView.findViewById(R.id.tv_price);
            holder.pickBtn = (TextView) convertView.findViewById(R.id.iv_pick);
            holder.label_wait_pay_ = (TextView) convertView.findViewById(R.id.label_wait_pay_);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        holder.orderNoTv.setText(mDatas.get(position).getOrderNumber());
        try {
            switch (mDatas.get(position).getPayType()) {
                case "0":
                    holder.payTypeTv.setText(R.string.label_pay_online);
                    holder.label_wait_pay_.setText(R.string.lable_payed_);
                    break;
                case "1":
                    holder.payTypeTv.setText(R.string.label_pay_on_delivery);
                    holder.label_wait_pay_.setText(R.string.label_wait_pay_);
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        holder.usernameTv.setText(mDatas.get(position).getBuyerName());
        holder.phoneTv.setText(mDatas.get(position).getPhone());
        holder.addressTv.setText(mDatas.get(position).getAddress());
        holder.deliveryTimeTv.setText(mDatas.get(position).getShipTime());
        holder.countTv.setText(mDatas.get(position).getGoodsCount()+"");
        holder.priceTv.setText(mDatas.get(position).getGoodsPrice()+"");

        holder.callIv.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                //
                if(callBack != null){
                    callBack.call(mDatas.get(position).getPhone());
                }

            }
        });
//        holder.pickBtn.setOnClickListener(new NoDoubleClickListener() {
//            @Override
//            public void onNoDoubleClick(View v) {
//                //
//                if(callBack != null){
//                    callBack.surePickup(mDatas.get(position).getId());
//                }
//            }
//        });
        convertView.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                //
                if(callBack != null){
                    callBack.enterOrderDetail(mDatas.get(position).getOrderId());
                }
            }
        });

        return convertView;
    }

    public void setCallBack(OrderListCallBack callBack) {
        this.callBack = callBack;
    }

    private class ViewHolder{
        TextView orderNoTv;
        TextView payTypeTv;
        ImageView callIv;
        TextView usernameTv;
        TextView phoneTv;
        TextView addressTv;
        TextView deliveryTimeTv;
        TextView countTv;
        TextView priceTv;
        TextView pickBtn;
        TextView label_wait_pay_;
    }
}
