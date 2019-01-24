package com.yunqilai.consumer.luckyapp.UserCenter.Presenter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
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

import com.bumptech.glide.Glide;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;
import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.Base.BasePresenterActivity;
import com.yunqilai.consumer.luckyapp.Common.Model.ParseBaseEntity;
import com.yunqilai.consumer.luckyapp.Common.Ui.BottomDialogUI;
import com.yunqilai.consumer.luckyapp.Common.Ui.DeleteDialog;
import com.yunqilai.consumer.luckyapp.Common.Ui.SpaceItemDecoration;
import com.yunqilai.consumer.luckyapp.Common.Ui.WrapContentLinearLayoutManager;
import com.yunqilai.consumer.luckyapp.Common.Utils.OkHttpClientManager;
import com.yunqilai.consumer.luckyapp.Common.Utils.ResponseCodeUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.UrlUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.adapter.CommonAdapter;
import com.yunqilai.consumer.luckyapp.Common.Utils.adapter.ViewHolder;
import com.yunqilai.consumer.luckyapp.HomePage.Presenter.PayWaysActivity;
import com.yunqilai.consumer.luckyapp.UserCenter.Adapter.OrderImageAdapter;
import com.yunqilai.consumer.luckyapp.UserCenter.Interface.DeleteDialogInterface;
import com.yunqilai.consumer.luckyapp.UserCenter.Interface.OnItemClickListener;
import com.yunqilai.consumer.luckyapp.UserCenter.Interface.SendData;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.ChangeOrderStatusBean;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.GetOrderBean;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.OrderGoodsBean;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.OrderInfoBean;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.ParseOrderInfoBean;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.RefundPriceBean;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.RefuseOrderBean;
import com.yunqilai.consumer.luckyapp.UserCenter.StringUtils;
import com.yunqilai.consumer.luckyapp.UserCenter.View.OrderAllInfoView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/7.
 */

public class OrderAllInfoActivity extends BasePresenterActivity<OrderAllInfoView> {

    DecimalFormat df   = new DecimalFormat("######0.00");
    private List<Fragment> list = new ArrayList<>();
    private String[] titles = {"全部", "待支付", "待发货","待收货","待评价"};

    private OrderTypeAdapter adapter ;
    private List<OrderInfoBean> data = new ArrayList<>();
    private List<OrderGoodsBean> goodsInfo = new ArrayList<>();

    private int currentPage = 1 ;
    private int showCount = 100 ;

    private boolean mIsRefreshing=false;
    private String current_place = null ;

    ProgressDialog dialog2 ;

    @Override
    protected Class<OrderAllInfoView> getViewClass() {
        return OrderAllInfoView.class;
    }

    @Override
    protected void onBindView() {
        super.onBindView();

        EventBus.getDefault().register(this);
        dialog2 = ProgressDialog.show(this, "提示", "请稍后....");
        vu.getIv_back().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        vu.getTb().addTab(vu.getTb().newTab().setText(titles[0]));
        vu.getTb().addTab(vu.getTb().newTab().setText(titles[1]));
        vu.getTb().addTab(vu.getTb().newTab().setText(titles[2]));
        vu.getTb().addTab(vu.getTb().newTab().setText(titles[3]));
        vu.getTb().addTab(vu.getTb().newTab().setText(titles[4]));

        WrapContentLinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(context );
        layoutManager.setOrientation(OrientationHelper. VERTICAL);
        vu.getRv().addItemDecoration(new SpaceItemDecoration(15));
        vu.getRv().setLayoutManager(layoutManager);

        vu.getTb().addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0 : initData("all");break;
                    case 1 : initData("wait_pay");break;
                    case 2 : initData("wait_skip");break;
                    case 3 : initData("wait_receive");break;
                    case 4 : initData("wait_evaluate");break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        vu.getSpringView().setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                initData(current_place);
                vu.getSpringView().onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                vu.getSpringView().onFinishFreshAndLoad();
            }
        });


    }

    @Override
    protected void afterResume() {
        super.afterResume();
        if (current_place!=null) {
            initData(current_place);
        }
    }

    @Override
    protected void onDestroyVu() {
        super.onDestroyVu();
        EventBus.getDefault().unregister(this);
    }


    public class OrderTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        private final int ONE_VIEW = 1;
        private final int MORE_VIEW = 2;

        private List<OrderInfoBean> list = new ArrayList<>();
        private OnItemClickListener listener;

        public OrderTypeAdapter( List<OrderInfoBean> list) {
            this.list = list;
        }

        public void refreshData(List<OrderInfoBean> list){


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
//                    listener.onItemClick(v,position);
                    Intent intent = new Intent(context,GoodsOrderDetailsActivity.class);
                    if (!list.get(position).getOrderId().equals("")) {
                        intent.putExtra("orderId", list.get(position).getOrderId());
                        startActivity(intent);
                    }
                }
            });

            if (holder instanceof OneViewHolder){
                ((OneViewHolder) holder).tv_state.setText(StringUtils.getName(list.get(position).getOrderStatus()));
                ((OneViewHolder) holder).tv_order_num.setText(list.get(position).getOrderNo());
                ((OneViewHolder) holder).tv_name.setText(list.get(position).getGoodsList().get(0).getGoodsName());
                ((OneViewHolder) holder).tv_money.setText("￥"+String.valueOf(df.format(list.get(position).getGoodsList().get(0).getGoodsPrice())));
                ((OneViewHolder) holder).tv_num.setText("×"+String.valueOf(list.get(position).getGoodsList().get(0).getGoodsCount()));
                ((OneViewHolder) holder).tv_style.setText(list.get(position).getGoodsList().get(0).getGoodsModel());
                ((OneViewHolder) holder).tv_all_money.setText("￥"+df.format(list.get(position).getTotalPrice()));
                ((OneViewHolder) holder).tv_get_num.setText(String.valueOf(list.get(position).getTotalGoodsCount()));
                Glide.with(context).load(UrlUtils.URL_BASE+list.get(position).getGoodsList().get(0).getGoodsPhoto()).placeholder(R.drawable.banner).into(((OneViewHolder) holder).iv);
                ((OneViewHolder) holder).view_status.setVisibility(View.VISIBLE);
                ((OneViewHolder) holder).tv_get_num.setText(String.valueOf(list.get(position).getGoodsList().size()));
                changeStatusOne((OneViewHolder) holder,position,list);

            }
            if (holder instanceof MoreViewHolder){
                ((MoreViewHolder) holder).tv_status.setText(StringUtils.getName(list.get(position).getOrderStatus()));
                ((MoreViewHolder) holder).tv_order.setText(list.get(position).getOrderNo());
                ((MoreViewHolder) holder).tv_money.setText("￥"+df.format(list.get(position).getTotalPrice()));
                ((MoreViewHolder) holder).tv_num_num.setText(String.valueOf(list.get(position).getGoodsList().size()));
                OrderImageAdapter adapter = new OrderImageAdapter(context,list.get(position).getGoodsList(),list.get(position).getOrderId());
                WrapContentLinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(context );
                layoutManager.setOrientation(OrientationHelper. HORIZONTAL);
                ((MoreViewHolder) holder).gv.addItemDecoration(new SpaceItemDecoration(15));
                ((MoreViewHolder) holder).gv.setLayoutManager(layoutManager);
                ((MoreViewHolder) holder).gv.setAdapter(adapter);


                changeStatusTwo((MoreViewHolder)holder,position,list);
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
            TextView tv_state ,tv_order_num ,tv_name ,tv_money , tv_num ,tv_style ,tv_all_money ,tv_get_num ,tv_tv_status ,tv_show_price_type;
            Button btn_pay ,btn_cancel ;
            private ImageView iv ;
            private View view_status ;
            public OneViewHolder(View itemView) {
                super(itemView);
                tv_state = (TextView) itemView.findViewById(R.id.tv_pay_state);
                btn_pay = (Button) itemView.findViewById(R.id.btn_pay);
                tv_order_num = (TextView) itemView.findViewById(R.id.tv_tv_ordeer_num);
                tv_name = (TextView) itemView.findViewById(R.id.tv_content_cart);
                tv_money = (TextView) itemView.findViewById(R.id.tv_order_ls_item_money);
                tv_num = (TextView) itemView.findViewById(R.id.tv_num);
                tv_style = (TextView) itemView.findViewById(R.id.tv_style_cart);
                tv_all_money = (TextView) itemView.findViewById(R.id.tv_tv_order_money);
                tv_get_num = (TextView) itemView.findViewById(R.id.tv_get_num);
                btn_cancel = (Button) itemView.findViewById(R.id.btn_btn_cancel);
                tv_tv_status = (TextView) itemView.findViewById(R.id.tv_tv_status);
                view_status = itemView.findViewById(R.id.rl_rl_status);
                iv = (ImageView) itemView.findViewById(R.id.iv_icon);
                tv_show_price_type = (TextView) itemView.findViewById(R.id.tv_show_price_type);
            }
        }

        class MoreViewHolder extends RecyclerView.ViewHolder{
            TextView tv_order ,tv_money ,tv_status ,tv_status_bottom ,tv_num_num ,tv_bottom_type;
            Button btn_pay ,btn_cancel;
            View botton_view ;
            RecyclerView gv ;
            public MoreViewHolder(View itemView) {
                super(itemView);
                gv = (RecyclerView) itemView.findViewById(R.id.more_goods_ngv);
                tv_order = (TextView) itemView.findViewById(R.id.tv_my_order);
                tv_money = (TextView) itemView.findViewById(R.id.tv_monet);
                tv_status = (TextView) itemView.findViewById(R.id.tv_status);
                btn_pay = (Button) itemView.findViewById(R.id.btn_pay);
                tv_status_bottom = (TextView) itemView.findViewById(R.id.tv_status_bottom);
                btn_cancel = (Button) itemView.findViewById(R.id.btn_cancel);
                botton_view = itemView.findViewById(R.id.bottom_view);
                tv_num_num = (TextView) itemView.findViewById(R.id.tv_num_num);
                tv_bottom_type = (TextView) itemView.findViewById(R.id.tv_bottom_type);
            }
        }
    }

    private void initData(String type) {
        dialog2.show();
           current_place = type;
        GetOrderBean bean = new GetOrderBean(getAccessToken(),type,currentPage,showCount);
        final String info = gson.toJson(bean);
        OkHttpClientManager.postAsynJson(info, UrlUtils.GET_ORDER_INFO_URL, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                dialog2.dismiss();
                ParseOrderInfoBean infoBean = gson.fromJson(response,ParseOrderInfoBean.class);
                if (infoBean.getCode().equals(ResponseCodeUtils.OK)){
                    if (infoBean.getData().get("list").size()>0){
                        vu.getRv().setVisibility(View.VISIBLE);
                        vu.getView_show().setVisibility(View.GONE);
                        adapter = new OrderTypeAdapter(infoBean.getData().get("list"));
                        vu.getRv().setAdapter(adapter);
                    }else {
                        vu.getRv().setVisibility(View.GONE);
//                        adapter.refreshData(new ArrayList<OrderInfoBean>());
                        vu.getView_show().setVisibility(View.VISIBLE);
                    }
                }else {
//                    Toast.makeText(context,infoBean.getMsg(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Subscribe
    public void onEvent(String s){
        if (s.equals("wait_pay")){
            vu.getTb().getTabAt(1).select();
        }else if (s.equals("wait_skip")){
            vu.getTb().getTabAt(2).select();
        }else if (s.equals("wait_receive")){
            vu.getTb().getTabAt(3).select();
        }else if (s.equals("wait_evaluate")){
            vu.getTb().getTabAt(4).select();
        }else if (s.equals("all")){
            vu.getTb().getTabAt(0).select();
            initData("all");
        }else if (s.equals("refresh")){
            initData(current_place);
        }
    }

    private void changOrderState(String cancel, String orderId, final int position, final List<OrderInfoBean> list) {
        String type = gson.toJson(new ChangeOrderStatusBean(getAccessToken(),orderId,cancel,"9"));
        OkHttpClientManager.postAsynJson(type, UrlUtils.CHANGE_ORDER_INFO_URL, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                ParseBaseEntity entity = gson.fromJson(response,ParseBaseEntity.class);
                if (entity.getCode().equals(ResponseCodeUtils.OK)){
                    list.remove(position);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void changeStatusTwo(final OrderTypeAdapter.MoreViewHolder holder, final int position, final List<OrderInfoBean> list) {
        if (list.get(position).getOrderStatus().equals("wait_pay")){
            holder.tv_bottom_type.setText("需付款 :");
            holder.btn_pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PayWaysActivity.class);
                    intent.putExtra("orderId",list.get(position).getOrderId());
                    intent.putExtra("money",list.get(position).getTotalPrice());
                    startActivity(intent);

                }
            });
            holder.btn_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BottomDialogUI dailog = new BottomDialogUI(context, R.layout.refund_reason_layout,getAccessToken(),list.get(position).getOrderId(),OrderAllInfoActivity.this,"2");
                    dailog.show();
//                            changOrderState("cancel", list.get(position).getOrderI),position);
                }
            });
        }else if (list.get(position).getOrderStatus().equals("wait_receive")){
            if (list.get(position).getPayType().equals("1")&&list.get(position).getShipType().equals("1")){
                holder.btn_cancel.setText("取消订单");
                holder.btn_cancel.setVisibility(View.VISIBLE);
                holder.btn_pay.setVisibility(View.GONE);
                holder.btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DeleteDialog dialog = new DeleteDialog(context, "提示", "是否取消订单", "确定");
                        dialog.show();
                        dialog.OnDeleteBtn(new DeleteDialogInterface() {
                            @Override
                            public void isDelete(boolean isdelete) {
                                if (isdelete){
                                    changOrderState("cancel", list.get(position).getOrderId(), position, list);
                                }
                            }
                        });
                    }
                });
            } else if (list.get(position).getPayType().equals("0")&&list.get(position).getShipType().equals("1")){
                holder.btn_pay.setText("退款");
                holder.btn_pay.setVisibility(View.VISIBLE);
                holder.btn_cancel.setVisibility(View.GONE);
                holder.btn_pay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DeleteDialog dialog = new DeleteDialog(context, "提示", "是否申请退款", "确定");
                        dialog.show();
                        dialog.OnDeleteBtn(new DeleteDialogInterface() {
                            @Override
                            public void isDelete(boolean isdelete) {
                                if (isdelete){
                                    //自提退订单
                                    RefundOrder(null,list.get(position).getOrderId());
                                }
                            }
                        });
                    }
                });
            } else {
                holder.btn_cancel.setVisibility(View.GONE);
                holder.btn_pay.setText("确认收货");
                holder.btn_pay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DeleteDialog dialog = new DeleteDialog(context, "提示", "是否确认收货", "确定");
                        dialog.show();
                        dialog.OnDeleteBtn(new DeleteDialogInterface() {
                            @Override
                            public void isDelete(boolean isdelete) {
                                if (isdelete) {
                                    changOrderState("sure", list.get(position).getOrderId(), position, list);
                                }
                            }
                        });
                    }
                });
            }
        }else if (list.get(position).getOrderStatus().equals("wait_evaluate")){
            holder.btn_cancel.setText("删除订单");
            holder.btn_cancel.setVisibility(View.VISIBLE);
            holder.btn_pay.setText("评价");
            holder.btn_pay.setVisibility(View.VISIBLE);
            holder.btn_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DeleteDialog dialog = new DeleteDialog(context,"提示","删除订单","确定");
                    dialog.show();
                    dialog.OnDeleteBtn(new DeleteDialogInterface() {
                        @Override
                        public void isDelete(boolean isdelete) {
                            if (isdelete){
                                changOrderState("delete", list.get(position).getOrderId(),position, list);
                            }
                        }
                    });
                }
            });
            holder.btn_pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,AppraiseActivity.class) ;
                    SendData send_data = new SendData(list.get(position).getGoodsList(),list.get(position).getOrderId());
                    intent.putExtra("send_data",send_data);
                    startActivity(intent);
                }
            });
        }else if (list.get(position).getOrderStatus().equals("wait_skip")) {
            holder.botton_view.setVisibility(View.GONE);
        }else if (list.get(position).getOrderStatus().equals("refunding")||list.get(position).getOrderStatus().equals("refuseRefund")){
            holder.btn_pay.setVisibility(View.GONE);
            holder.btn_cancel.setVisibility(View.GONE);
            holder.tv_status_bottom.setVisibility(View.VISIBLE);
            holder.tv_status_bottom.setText("退款中");
        }else if (list.get(position).getOrderStatus().equals("cancel")){
            holder.tv_status_bottom.setVisibility(View.GONE);
            holder.btn_pay.setVisibility(View.GONE);
            holder.btn_cancel.setText("删除订单");
            holder.btn_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DeleteDialog dialog = new DeleteDialog(context,"提示","删除订单","确定");
                    dialog.show();
                    dialog.OnDeleteBtn(new DeleteDialogInterface() {
                        @Override
                        public void isDelete(boolean isdelete) {
                            if (isdelete){
                                changOrderState("delete", list.get(position).getOrderId(),position, list);
                            }
                        }
                    });
                }
            });
        }else if (list.get(position).getOrderStatus().equals("has_evaluate")){
            holder.tv_status_bottom.setVisibility(View.GONE);
            holder.btn_pay.setVisibility(View.GONE);
            holder.btn_cancel.setText("删除订单");
            holder.btn_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DeleteDialog dialog = new DeleteDialog(context,"提示","删除订单","确定");
                    dialog.show();
                    dialog.OnDeleteBtn(new DeleteDialogInterface() {
                        @Override
                        public void isDelete(boolean isdelete) {
                            if (isdelete){
                                changOrderState("delete", list.get(position).getOrderId(),position, list);
                            }
                        }
                    });
                }
            });
        }else if (list.get(position).getOrderStatus().equals("refunded")){
            holder.btn_cancel.setVisibility(View.GONE);
            holder.btn_pay.setVisibility(View.GONE);
            holder.tv_status_bottom.setVisibility(View.VISIBLE);
            holder.tv_status_bottom.setText("退款成功");
        }
    }

    public void changeStatusOne(OrderTypeAdapter.OneViewHolder holder, final int position, final List<OrderInfoBean> list){
        if (list.get(position).getOrderStatus().equals("wait_pay")){
            holder.tv_show_price_type.setText("需付款 :");
            holder.btn_pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PayWaysActivity.class);
                    intent.putExtra("orderId",list.get(position).getOrderId());
                    intent.putExtra("money","￥"+list.get(position).getTotalPrice());
                    startActivity(intent);

                }
            });
            holder.btn_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BottomDialogUI dailog = new BottomDialogUI(context, R.layout.refund_reason_layout,getAccessToken(),list.get(position).getOrderId(),OrderAllInfoActivity.this,"2");
                    dailog.show();
                }

            });
        }else if (list.get(position).getOrderStatus().equals("wait_receive")){
            if (list.get(position).getPayType().equals("1")&&list.get(position).getShipType().equals("1")){
                holder.btn_cancel.setText("取消订单");
                holder.btn_cancel.setVisibility(View.VISIBLE);
                holder.btn_pay.setVisibility(View.GONE);
                holder.btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DeleteDialog dialog = new DeleteDialog(context, "提示", "是否取消订单", "确定");
                        dialog.show();
                        dialog.OnDeleteBtn(new DeleteDialogInterface() {
                            @Override
                            public void isDelete(boolean isdelete) {
                                if (isdelete){
                                    changOrderState("cancel", list.get(position).getOrderId(), position, list);
                                }
                            }
                        });
                    }
                });
            }else if (list.get(position).getPayType().equals("0")&&list.get(position).getShipType().equals("1")){
                holder.btn_pay.setText("退款");
                holder.btn_pay.setVisibility(View.VISIBLE);
                holder.btn_cancel.setVisibility(View.GONE);
                holder.btn_pay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DeleteDialog dialog = new DeleteDialog(context, "提示", "是否申请退款", "确定");
                        dialog.show();
                        dialog.OnDeleteBtn(new DeleteDialogInterface() {
                            @Override
                            public void isDelete(boolean isdelete) {
                                if (isdelete){
                                    //自提退订单
                                    RefundOrder(null,list.get(position).getOrderId());
                                }
                            }
                        });
                    }
                });
            } else {
                holder.btn_cancel.setVisibility(View.VISIBLE);
                holder.btn_cancel.setText("退款");
                holder.btn_pay.setText("确认收货");
                holder.btn_pay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DeleteDialog dialog = new DeleteDialog(context, "提示", "是否确认收货", "确定");
                        dialog.show();
                        dialog.OnDeleteBtn(new DeleteDialogInterface() {
                            @Override
                            public void isDelete(boolean isdelete) {
                                changOrderState("sure", list.get(position).getOrderId(), position, list);
                            }
                        });
                    }
                });

                holder.btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DeleteDialog dialog = new DeleteDialog(context, "提示", "是否申请退款", "确定");
                        dialog.show();
                        dialog.OnDeleteBtn(new DeleteDialogInterface() {
                            @Override
                            public void isDelete(boolean isdelete) {
                                if (isdelete){
                                    //自提退订单
                                    RefundOrder(null,list.get(position).getOrderId());
                                }
                            }
                        });
                    }
                });
            }
        }else if (list.get(position).getOrderStatus().equals("wait_evaluate")){
            holder.btn_cancel.setText("删除订单");
            holder.btn_cancel.setVisibility(View.VISIBLE);
            holder.btn_pay.setText("评价");
            holder.btn_pay.setVisibility(View.VISIBLE);
            holder.btn_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DeleteDialog dialog = new DeleteDialog(context,"提示","删除订单","确定");
                    dialog.show();
                    dialog.OnDeleteBtn(new DeleteDialogInterface() {
                        @Override
                        public void isDelete(boolean isdelete) {
                            if (isdelete){
                                changOrderState("delete", list.get(position).getOrderId(),position, list);
                            }
                        }
                    });
                }
            });
            holder.btn_pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,AppraiseActivity.class) ;
                    SendData send_data = new SendData(list.get(position).getGoodsList(),list.get(position).getOrderId());
                    intent.putExtra("send_data",send_data);
                    startActivity(intent);
                }
            });
        }else if (list.get(position).getOrderStatus().equals("wait_skip")){
            holder.view_status.setVisibility(View.GONE);
        }else if (list.get(position).getOrderStatus().equals("refunding")||list.get(position).getOrderStatus().equals("refuseRefund")){
            holder.btn_pay.setVisibility(View.GONE);
            holder.btn_cancel.setVisibility(View.GONE);
            holder.tv_tv_status.setVisibility(View.VISIBLE);
            holder.tv_tv_status.setText("退款中");
        }else if (list.get(position).getOrderStatus().equals("refunding")){
            holder.tv_tv_status.setVisibility(View.VISIBLE);
            holder.tv_tv_status.setText("退款成功");
            holder.btn_cancel.setVisibility(View.VISIBLE);
            holder.btn_cancel.setText("删除订单");
            holder.btn_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DeleteDialog dialog = new DeleteDialog(context,"提示","删除订单","确定");
                    dialog.show();
                    dialog.OnDeleteBtn(new DeleteDialogInterface() {
                        @Override
                        public void isDelete(boolean isdelete) {
                            if (isdelete){
                                changOrderState("delete", list.get(position).getOrderId(),position, list);
                            }
                        }
                    });
                }
            });
        }else if (list.get(position).getOrderStatus().equals("cancel")){
            holder.tv_tv_status.setVisibility(View.GONE);
            holder.btn_pay.setVisibility(View.GONE);
            holder.btn_cancel.setText("删除订单");
            holder.btn_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DeleteDialog dialog = new DeleteDialog(context,"提示","删除订单","确定");
                    dialog.show();
                    dialog.OnDeleteBtn(new DeleteDialogInterface() {
                        @Override
                        public void isDelete(boolean isdelete) {
                            if (isdelete){
                                changOrderState("delete", list.get(position).getOrderId(),position, list);
                            }
                        }
                    });
                }
            });
        }else if (list.get(position).getOrderStatus().equals("has_evaluate")){
            holder.tv_tv_status.setVisibility(View.GONE);
            holder.btn_pay.setVisibility(View.GONE);
            holder.btn_cancel.setText("删除订单");
            holder.btn_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DeleteDialog dialog = new DeleteDialog(context,"提示","删除订单","确定");
                    dialog.show();
                    dialog.OnDeleteBtn(new DeleteDialogInterface() {
                        @Override
                        public void isDelete(boolean isdelete) {
                            if (isdelete){
                                changOrderState("delete", list.get(position).getOrderId(),position, list);
                            }
                        }
                    });
                }
            });
        }else if (list.get(position).getOrderStatus().equals("refunded")){
            holder.btn_pay.setVisibility(View.GONE);
            holder.btn_cancel.setVisibility(View.GONE);
            holder.tv_tv_status.setText("退款成功");
            holder.tv_tv_status.setVisibility(View.VISIBLE);
        }
    }


    //退款金额
    private void RefundOrder(final String cartId ,final String orderId) {
        String type = null ;
        if (cartId!=null) {
            type = gson.toJson(new RefuseOrderBean(getAccessToken(), orderId, cartId));
        }else {
            type = gson.toJson(new RefuseOrderBean(getAccessToken(), orderId, null));
        }
        Log.e("ssssss",gson.toJson(type));
        OkHttpClientManager.postAsynJson(type, UrlUtils.REFUND_ORDER_URL, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                ParseBaseEntity entity = gson.fromJson(response,ParseBaseEntity.class);
                if (entity.getCode().equals(ResponseCodeUtils.OK)){
                    Intent intent = new Intent(context,RefundActivity.class);
                    RefundPriceBean bean = gson.fromJson(gson.toJson(entity.getData()),RefundPriceBean.class);
                    intent.putExtra("price",String.valueOf(bean.getPrice()));
                    intent.putExtra("orderId",orderId);
                    intent.putExtra("shopCartId",cartId);
                    startActivity(intent);
                }
            }
        });
    }


}
