package com.yunqilai.consumer.luckyapp.UserCenter.Presenter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.okhttp.Request;
import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.Base.BasePresenterFragment;
import com.yunqilai.consumer.luckyapp.Common.Ui.SpaceItemDecoration;
import com.yunqilai.consumer.luckyapp.Common.Utils.OkHttpClientManager;
import com.yunqilai.consumer.luckyapp.Common.Utils.ResponseCodeUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.UrlUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.adapter.CommonAdapter;
import com.yunqilai.consumer.luckyapp.Common.Utils.adapter.ViewHolder;
import com.yunqilai.consumer.luckyapp.Common.View.RegisterView;
import com.yunqilai.consumer.luckyapp.UserCenter.Interface.OnItemClickListener;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.GetOrderBean;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.OrderGoodsBean;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.OrderInfoBean;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.ParseOrderInfoBean;
import com.yunqilai.consumer.luckyapp.UserCenter.View.EveryOrderView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/7.
 */

public class EveryOrderFragment extends BasePresenterFragment<EveryOrderView> {

    private OrderTypeAdapter adapter ;
    private List<OrderInfoBean> data = new ArrayList<>();

    private int currentPage = 1 ;
    private int showCount = 8 ;

    @Override
    public Class<EveryOrderView> getViewClass() {
        return EveryOrderView.class;
    }

    @Override
    public void bindView() {
        super.bindView();

        EventBus.getDefault().register(this);
        data.clear();
        adapter = new OrderTypeAdapter(data);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context );
        layoutManager.setOrientation(OrientationHelper. VERTICAL);
        vu.getRv().setAdapter(adapter);
        vu.getRv().addItemDecoration(new SpaceItemDecoration(15));
        vu.getRv().setLayoutManager(layoutManager);

        adapter.OnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(context,GoodsOrderDetailsActivity.class));
            }
        });

    }



    public class OrderTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        private final int ONE_VIEW = 1;
        private final int MORE_VIEW = 2;

        private List<OrderInfoBean> list;
        private OnItemClickListener listener;

        public OrderTypeAdapter( List<OrderInfoBean> list) {
            this.list = list;
        }

        public void refreshData(List<OrderInfoBean> list){
            this.list = list ;
            notifyDataSetChanged();
        }

        public void OnItemClickListener(OnItemClickListener clickListener){
            listener = clickListener ;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view ;
           if (viewType == ONE_VIEW){
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_goods_layout_item,null);
                return new OneViewHolder(view);
            }else  {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.more_goods_layout_item,null);
                return new MoreViewHolder(view);
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(v,position);
                }
            });

            if (holder instanceof OneViewHolder){
                ((OneViewHolder) holder).tv_state.setText(list.get(position).getOrderStatus());
                ((OneViewHolder) holder).tv_order_num.setText(list.get(position).getOrderId());
                ((OneViewHolder) holder).tv_name.setText(list.get(position).getGoodsList().get(0).getGoodsName());
                ((OneViewHolder) holder).tv_money.setText("￥"+String.valueOf(list.get(position).getGoodsList().get(0).getGoodsPrice()));
                ((OneViewHolder) holder).tv_num.setText("×"+String.valueOf(list.get(position).getGoodsList().get(0).getGoodsCount()));
                ((OneViewHolder) holder).tv_style.setText(list.get(position).getGoodsList().get(0).getGoodsModel());
                ((OneViewHolder) holder).btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(context,AppraiseActivity.class));
                    }
                });

            }
            if (holder instanceof MoreViewHolder){
                ((MoreViewHolder) holder).tv_my_order.setText(list.get(position).getOrderStatus());
                CommonAdapter<OrderGoodsBean> adapter = new CommonAdapter<OrderGoodsBean>(context,list.get(position).getGoodsList(),R.layout.more_goods_gv_item) {
                    @Override
                    public void convert(ViewHolder helper, OrderGoodsBean item) {
                        ImageView imageView = helper.getView(R.id.more_item_gv_iv);
                        Glide.with(context).load(UrlUtils.BASE_URL+item.getGoodsPhoto()).placeholder(R.mipmap.ic_launcher).into(imageView);
                    }
                };
                ((MoreViewHolder) holder).gv.setAdapter(adapter);
            }
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        public int getItemViewType(int position) {
            if(list.get(position).getGoodsList().size()==1){
                return ONE_VIEW;
            } else {
                return MORE_VIEW;
            }
        }


        class OneViewHolder extends RecyclerView.ViewHolder{
            TextView tv_state ,tv_order_num ,tv_name ,tv_money , tv_num ,tv_style;
            Button btn ;
            public OneViewHolder(View itemView) {
                super(itemView);
                tv_state = (TextView) itemView.findViewById(R.id.tv_pay_state);
                btn = (Button) itemView.findViewById(R.id.btn_pay);
                tv_order_num = (TextView) itemView.findViewById(R.id.tv_tv_ordeer_num);
                tv_name = (TextView) itemView.findViewById(R.id.tv_content_cart);
                tv_money = (TextView) itemView.findViewById(R.id.tv_order_ls_item_money);
                tv_num = (TextView) itemView.findViewById(R.id.tv_num);
                tv_style = (TextView) itemView.findViewById(R.id.tv_style_cart);
            }
        }

        class MoreViewHolder extends RecyclerView.ViewHolder{
            TextView tv_my_order ;
            GridView gv ;
            public MoreViewHolder(View itemView) {
                super(itemView);
                gv = (GridView) itemView.findViewById(R.id.more_goods_ngv);
                tv_my_order = (TextView) itemView.findViewById(R.id.tv_my_order);
            }
        }
    }


    @Subscribe
    public void onEvent(String type){
        initData(type);
    }

    private void initData(String type) {
        GetOrderBean bean = new GetOrderBean(getAccessToken(),type,currentPage,showCount);
        final String info = gson.toJson(bean);
        OkHttpClientManager.postAsynJson(info, UrlUtils.GET_ORDER_INFO_URL, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                Log.e("cccccccc",response);
                ParseOrderInfoBean infoBean = gson.fromJson(response,ParseOrderInfoBean.class);
                if (infoBean.getCode().equals(ResponseCodeUtils.OK)){
                    if (infoBean.getData().get("list").size()>0){
                        data.addAll(infoBean.getData().get("list"));
                        adapter.refreshData(data);
                    }
                }else {
                    Toast.makeText(context,infoBean.getMsg(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
