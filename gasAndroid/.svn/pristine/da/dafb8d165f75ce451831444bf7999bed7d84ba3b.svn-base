package com.yunqilai.consumer.luckyapp.UserCenter.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.Utils.UrlUtils;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.OrderGoodsBean;
import com.yunqilai.consumer.luckyapp.UserCenter.Presenter.GoodsOrderDetailsActivity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/18.
 */

public class OrderImageAdapter extends RecyclerView.Adapter<OrderImageAdapter.ImageviewHolder> {

    List<OrderGoodsBean> m_data ;
    Context context ;
    String orderId ;

    public OrderImageAdapter(Context mcontext, List<OrderGoodsBean> data, String orderId) {
        m_data = data ;
        context = mcontext ;
        this.orderId = orderId ;
    }

    @Override
    public ImageviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.more_goods_gv_item,null);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, GoodsOrderDetailsActivity.class);
                intent.putExtra("orderId",orderId);
                context.startActivity(intent);
            }
        });
        return new ImageviewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageviewHolder holder, int position) {
        Glide.with(context).load(UrlUtils.URL_BASE+m_data.get(position).getGoodsPhoto()).placeholder(R.drawable.banner).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return m_data.size();
    }

    public class ImageviewHolder extends RecyclerView.ViewHolder{

        ImageView imageView ;

        public ImageviewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.more_item_gv_iv);
        }
    }
}
