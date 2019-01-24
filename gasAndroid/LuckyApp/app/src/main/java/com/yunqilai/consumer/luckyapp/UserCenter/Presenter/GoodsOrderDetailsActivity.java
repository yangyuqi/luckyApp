package com.yunqilai.consumer.luckyapp.UserCenter.Presenter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.okhttp.Request;
import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.ChoicePage.Bean.SelfAddressBean;
import com.yunqilai.consumer.luckyapp.Common.Base.BasePresenterActivity;
import com.yunqilai.consumer.luckyapp.Common.Model.ParseBaseEntity;
import com.yunqilai.consumer.luckyapp.Common.Ui.BottomDialogUI;
import com.yunqilai.consumer.luckyapp.Common.Ui.DeleteDialog;
import com.yunqilai.consumer.luckyapp.Common.Utils.OkHttpClientManager;
import com.yunqilai.consumer.luckyapp.Common.Utils.ResponseCodeUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.UrlUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.adapter.CommonAdapter;
import com.yunqilai.consumer.luckyapp.Common.Utils.adapter.ViewHolder;
import com.yunqilai.consumer.luckyapp.HomePage.Presenter.PayWaysActivity;
import com.yunqilai.consumer.luckyapp.HomePage.Presenter.SelectPayWayActivity;
import com.yunqilai.consumer.luckyapp.UserCenter.Interface.DeleteDialogInterface;
import com.yunqilai.consumer.luckyapp.UserCenter.Interface.SendData;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.ChangeOrderStatusBean;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.ChangePayWayBean;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.OrderDetailsBean;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.OrderGoodsBean;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.OrderGoodsCartBean;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.ParseOrderBean;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.RefundPriceBean;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.RefuseOrderBean;
import com.yunqilai.consumer.luckyapp.UserCenter.StringUtils;
import com.yunqilai.consumer.luckyapp.UserCenter.View.GoodsOrderDetailsView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/8.
 */

public class GoodsOrderDetailsActivity extends BasePresenterActivity<GoodsOrderDetailsView> {

    ProgressDialog dialog2 ;
    DecimalFormat df   = new DecimalFormat("######0.00");
    private List<OrderGoodsCartBean> data = new ArrayList<>();
    private CommonAdapter<OrderGoodsCartBean> adapter ;
    private boolean showBtn = false ;

    private String deliverType ;
    private String pickPlaceId = "";
    private String skipTime ;
    private String payType ;
    private String addressId ;

    private String orderId ,money;
    private boolean isEdit = false ;
    private String refund ;


    @Override
    protected Class<GoodsOrderDetailsView> getViewClass() {
        return GoodsOrderDetailsView.class;
    }

    @Override
    protected void onBindView() {
        super.onBindView();
        EventBus.getDefault().register(this);
        vu.getIv_back().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        orderId = getIntent().getStringExtra("orderId");
        dialog2 = ProgressDialog.show(this, "提示", "请稍后....");
        adapter = new CommonAdapter<OrderGoodsCartBean>(context,data, R.layout.order_info_ls_item_new) {
            @Override
            public void convert(ViewHolder helper, final OrderGoodsCartBean item) {
                helper.setText(R.id.tv_order_ls_item_money,"￥"+df.format(item.getGoodsPrice()));
                helper.setText(R.id.tv_content_cart,item.getGoodsName());
                helper.setText(R.id.tv_num,"×"+item.getGoodsCount());
                helper.setText(R.id.tv_style_cart,item.getGoodsModel());
                ImageView imageView = helper.getView(R.id.iv_icon);
                Glide.with(context).load(UrlUtils.URL_BASE+item.getGoodsPhoto()).into(imageView);
                if (isEdit) {
                    Button btn = (Button) helper.getConvertView().findViewById(R.id.btn_pay);
                    btn.setVisibility(View.VISIBLE);
                    if (refund.equals("refund")) {
                        btn.setText("退款");
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (deliverType.equals("0")) {
                                    RefundOrder(item.getShopCartId());
                                }else {//自提退订单
                                    RefundOrder(null);
                                }
                            }
                        });
                    }else if (refund.equals("refunding")){
                        btn.setText("退款中");
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(context,RefundDetailsActivity.class);
                                intent.putExtra("orderId",orderId);
                                startActivity(intent);
                            }
                        });
                    }
                }
            }
        };
        vu.getLs().setAdapter(adapter);
        initData();

        vu.getView_change_pay().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SelectPayWayActivity.class);
                intent.putExtra("deliverType",deliverType);
                intent.putExtra("payType",payType);
                intent.putExtra("pickPlaceId",pickPlaceId);
                startActivityForResult(intent, 1);
            }
        });
    }

    private void initData() {
        dialog2.show();
        data.clear();
        String type = gson.toJson(new OrderDetailsBean(getAccessToken(),orderId));
        OkHttpClientManager.postAsynJson(type, UrlUtils.ORDER_DETAILS_URL, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                dialog2.dismiss();
                ParseOrderBean bean = gson.fromJson(response,ParseOrderBean.class);
                if (bean.getCode().equals(ResponseCodeUtils.OK)){
                    data.addAll(bean.getData().getGoodsList());
                    adapter.setData(data);
                    adapter.notifyDataSetChanged();
                    payType = bean.getData().getPayType();
                    deliverType = bean.getData().getShipType();
                    vu.getTv_floor_money().setText("(含上楼配送费"+String.valueOf(df.format(bean.getData().getUpFloorPrice())+"元)"));

                    if (deliverType.equals("1")){ //自提
                        vu.getTv_name().setText("自提地址 :");
                        SelfAddressBean selfAddressBean = bean.getData().getPickPlace();
                        pickPlaceId =selfAddressBean.getId();
                        vu.getTv_address().setText(selfAddressBean.getName()+selfAddressBean.getAddress());
                    }else {
                        vu.getTv_name().setText(bean.getData().getBuyerName());
                        vu.getTv_address().setText(bean.getData().getAddress());
                    }
                    try {
                        addressId = bean.getData().getAddressId();
                    }catch (Exception e){}
                    vu.getTv_phone().setText(bean.getData().getPhone());
                    vu.getTv_message().setText(bean.getData().getMessage());
                    vu.getTv_money().setText(String.valueOf("￥"+df.format(bean.getData().getTotalPrice())));
                    vu.getTv_order_money().setText(String.valueOf("￥"+df.format(bean.getData().getGoodsPrice())));
                    vu.getTv_send_money().setText(String.valueOf("￥"+df.format(bean.getData().getShipPrice())));
                    vu.getTv_pay().setText(StringUtils.payType(bean.getData().getPayType()));
                    if (bean.getData().getShipType().equals("1")){
                        SelfAddressBean addressBean = bean.getData().getPickPlace();
                        String s = "点" + addressBean.getName()+addressBean.getAddress();
                        vu.getTv_send().setText(StringUtils.sendType(bean.getData().getShipType())+s);
                    }else {
                        vu.getTv_send().setText(StringUtils.sendType(bean.getData().getShipType()));
                    }
                    vu.getTv_send_type().setText(StringUtils.sendType(bean.getData().getShipType()));
                    vu.getTv_pay_type().setText(StringUtils.payType(bean.getData().getPayType()));
                    getOrderStatus(bean.getData().getOrderStatus(),bean);
                    vu.getTv_order_num().setText(bean.getData().getOrderNo());
                    vu.getTv_order_time().setText(bean.getData().getAddTime());
                }
            }

        });
    }


    private void changOrderState(String cancel, String orderId,String reason) {
        String type = gson.toJson(new ChangeOrderStatusBean(getAccessToken(),orderId,cancel,reason));
        OkHttpClientManager.postAsynJson(type, UrlUtils.CHANGE_ORDER_INFO_URL, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                ParseBaseEntity entity = gson.fromJson(response,ParseBaseEntity.class);
                if (entity.getCode().equals(ResponseCodeUtils.OK)){
                    initData();
                }
            }
        });
    }

    //退款金额
    private void RefundOrder(final String cartId) {
        String type = null ;
        if (cartId!=null) {
            type = gson.toJson(new RefuseOrderBean(getAccessToken(), orderId, cartId));
        }else {
            type = gson.toJson(new RefuseOrderBean(getAccessToken(), orderId, null));
        }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1&&resultCode==1){
            deliverType = data.getStringExtra("send");
            pickPlaceId = data.getStringExtra("pickPlaceId");
            skipTime = data.getStringExtra("time");
            payType = data.getStringExtra("pay");
            changePayWay();
        }
    }

    private void changePayWay() {
        String type = null ;
        if (pickPlaceId.equals("")) {
            type = gson.toJson(new ChangePayWayBean(getAccessToken(), orderId, deliverType, null, skipTime, payType,addressId));
        }else {
            type = gson.toJson(new ChangePayWayBean(getAccessToken(), orderId, deliverType, pickPlaceId, skipTime, payType,null));
        }
        OkHttpClientManager.postAsynJson(type, UrlUtils.CHANGE_SEND_PAY_URL, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                ParseBaseEntity entity = gson.fromJson(response,ParseBaseEntity.class);
                if (entity.getCode().equals(ResponseCodeUtils.OK)){
                    initData();
                }else {
                    Toast.makeText(context,entity.getMsg(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    private void getOrderStatus(String status, final ParseOrderBean bean) {
        if (status.equals(StringUtils.ORDER_WAIT_PAY)){
            if ((bean.getData().getPayType().equals("0"))){
                vu.getView_bottom_show().setVisibility(View.VISIBLE);
                vu.getBtn_refuse().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BottomDialogUI dailog = new BottomDialogUI(context, R.layout.refund_reason_layout,getAccessToken(),orderId,GoodsOrderDetailsActivity.this,"1");
                        dailog.show();
                    }
                });

                vu.getBtn_pay().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, PayWaysActivity.class);
                        intent.putExtra("orderId",orderId);
                        intent.putExtra("money",vu.getTv_money().getText().toString());
                        startActivity(intent);
                    }
                });

            }else {
                vu.getView_bottom_show().setVisibility(View.GONE);
            }
            vu.getView_change_pay().setVisibility(View.VISIBLE);
            vu.getView_pay_send().setVisibility(View.GONE);
            vu.getView_pay_time().setVisibility(View.GONE);
            vu.getView_pay_type().setVisibility(View.GONE);
            vu.getTv_mmm().setVisibility(View.GONE);
        }else if (status.equals(StringUtils.ORDER_WAIT_SKIP)){//待发货
            vu.getChange_txt().setText("等待发货");
            if (bean.getData().getPayType().equals("0")){
                vu.getTv_heji().setText("实付款");
            }
            vu.getChange_icon().setImageResource(R.drawable.icon_order_send);
            if (bean.getData().getShipType().equals("1")&&bean.getData().getPayType().equals("1")){
                vu.getView_pay_time().setVisibility(View.GONE);
                vu.getBtn_pay().setText("取消订单");
                vu.getBtn_pay().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BottomDialogUI dailog = new BottomDialogUI(context, R.layout.refund_reason_layout,getAccessToken(),orderId,GoodsOrderDetailsActivity.this,"1");
                        dailog.show();
                    }
                });
                vu.getBtn_refuse().setVisibility(View.GONE);
            }else if(bean.getData().getShipType().equals("0")&&bean.getData().getPayType().equals("0")){
                vu.getTv_heji().setText("实付款");
                isEdit = true ;
                refund = "refund";
                adapter.notifyDataSetChanged();
                vu.getView_bottom_show().setVisibility(View.GONE);
                vu.getTv_pay_time().setText(bean.getData().getPayTime());
            }else if (bean.getData().getPayType().equals("1")&&bean.getData().getShipType().equals("0")){
                vu.getBtn_pay().setText("取消订单");
                vu.getView_pay_time().setVisibility(View.GONE);
                vu.getBtn_refuse().setVisibility(View.GONE);
                vu.getBtn_pay().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BottomDialogUI dailog = new BottomDialogUI(context, R.layout.refund_reason_layout,getAccessToken(),orderId,GoodsOrderDetailsActivity.this,"1");
                        dailog.show();
                    }
                });
            }else if (bean.getData().getPayType().equals("0")&&bean.getData().getShipType().equals("1")){
                vu.getView_bottom_show().setVisibility(View.GONE);
            }
        }else if (status.equals(StringUtils.ORDER_WAIT_RECEIVE)){ //待收货
            vu.getChange_icon().setImageResource(R.drawable.icon_order_take);
            vu.getChange_txt().setText("等待收货");
            if (bean.getData().getPayType().equals("0")) {
                vu.getTv_heji().setText("实付款");
                vu.getTv_pay_time().setText(bean.getData().getPayTime());
                if (bean.getData().getShipType().equals("0")) {
                    vu.getBtn_pay().setText("确认收货");
                    vu.getBtn_pay().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            DeleteDialog dialog = new DeleteDialog(context,"提示","是否确认收货","确定");
                            dialog.show();
                            dialog.OnDeleteBtn(new DeleteDialogInterface() {
                                @Override
                                public void isDelete(boolean isdelete) {
                                    if (isdelete){
                                        changOrderState("sure", bean.getData().getOrderId(), null);
                                    }
                                }
                            });
                        }
                    });
                    vu.getBtn_refuse().setVisibility(View.VISIBLE);
                    vu.getBtn_refuse().setText("退款");
                    vu.getBtn_refuse().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            DeleteDialog dialog = new DeleteDialog(context, "提示", "是否申请退款", "确定");
                            dialog.show();
                            dialog.OnDeleteBtn(new DeleteDialogInterface() {
                                @Override
                                public void isDelete(boolean isdelete) {
                                    if (isdelete){
                                        //自提退订单
                                        RefundOrder(bean.getData().getShopCartId());
                                    }
                                }
                            });

                        }
                    });
                } else if (bean.getData().getShipType().equals("1")) {
                    isEdit = true ;
                    refund = "refund";
                    adapter.notifyDataSetChanged();
                    vu.getView_bottom_show().setVisibility(View.GONE);
                }
            }else if (bean.getData().getPayType().equals("1")){
                if (bean.getData().getShipType().equals("0")){
                    vu.getBtn_pay().setText("确认收货");
                    vu.getBtn_pay().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            changOrderState("sure", bean.getData().getOrderId(), null);
                        }
                    });
                } else {
                    vu.getBtn_pay().setText("取消订单");
                    vu.getBtn_pay().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            BottomDialogUI dailog = new BottomDialogUI(context, R.layout.refund_reason_layout,getAccessToken(),orderId,GoodsOrderDetailsActivity.this,"1");
                            dailog.show();
                        }
                    });
                }
                vu.getBtn_refuse().setVisibility(View.GONE);
            }
        }else if (status.equals(StringUtils.ORDER_WAIT_EVALUTE)){
            vu.getBtn_refuse().setVisibility(View.VISIBLE);
            vu.getChange_icon().setImageResource(R.drawable.icon_order_success);
            if (bean.getData().getPayType().equals("0")){
                vu.getTv_heji().setText("实付款");
                vu.getTv_pay_time().setText(bean.getData().getPayTime());
            }
            vu.getChange_txt().setText("交易成功");
            vu.getBtn_pay().setText("评价");
            vu.getBtn_refuse().setText("删除订单");
            vu.getBtn_refuse().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DeleteDialog dialog = new DeleteDialog(context,"提示","删除此商品吗","确认");
                    dialog.show();
                    dialog.OnDeleteBtn(new DeleteDialogInterface() {
                        @Override
                        public void isDelete(boolean isdelete) {
                            if (isdelete){
                                changOrderState("delete", bean.getData().getOrderId(), null);
                                finish();
                            }
                        }
                    });
                }
            });
            vu.getBtn_pay().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,AppraiseActivity.class) ;
                    List<OrderGoodsBean> mlist =  new ArrayList<>();
                    mlist.clear();
                    for (int i =0 ;i<bean.getData().getGoodsList().size();i++){
                        OrderGoodsCartBean cartBean = bean.getData().getGoodsList().get(i);
                        OrderGoodsBean goodsBean = new OrderGoodsBean(cartBean.getGoodsId(),cartBean.getGoodsName(),cartBean.getGoodsModel(),cartBean.getGoodsPrice(),cartBean.getGoodsPhoto(),cartBean.getGoodsCount(),cartBean.getShopCartId());
                        mlist.add(goodsBean);
                    }
                    SendData send_data = new SendData(mlist,orderId);
                    intent.putExtra("send_data",send_data);
                    startActivity(intent);
                }
            });
        }else if (status.equals(StringUtils.HAS_EVALUTE)){
            if (bean.getData().getPayType().equals("0")){
                vu.getTv_heji().setText("实付款");
                vu.getTv_pay_time().setText(bean.getData().getPayTime());
            }
            vu.getChange_icon().setImageResource(R.drawable.icon_order_success);
            vu.getChange_txt().setText("交易成功");
            vu.getBtn_pay().setVisibility(View.GONE);
            vu.getBtn_refuse().setText("删除订单");
            vu.getBtn_refuse().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DeleteDialog dialog = new DeleteDialog(context,"提示","删除此商品吗","确认");
                    dialog.show();
                    dialog.OnDeleteBtn(new DeleteDialogInterface() {
                        @Override
                        public void isDelete(boolean isdelete) {
                            if (isdelete){
                                changOrderState("delete", bean.getData().getOrderId(), null);
                                finish();
                            }
                        }
                    });
                }
            });
        }else if (status.equals(StringUtils.ORDER_CANCEL)){
            if (bean.getData().getPayType().equals("0")){
                vu.getTv_heji().setText("实付款");
                vu.getTv_pay_time().setText(bean.getData().getPayTime());
            }
            vu.getChange_icon().setImageResource(R.drawable.icon_order_close);
            vu.getChange_txt().setText("交易关闭");
            vu.getBtn_pay().setVisibility(View.GONE);
            vu.getBtn_refuse().setVisibility(View.VISIBLE);
            vu.getBtn_refuse().setText("删除订单");
            vu.getBtn_refuse().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DeleteDialog dialog = new DeleteDialog(context,"提示","删除此商品吗","确认");
                    dialog.show();
                    dialog.OnDeleteBtn(new DeleteDialogInterface() {
                        @Override
                        public void isDelete(boolean isdelete) {
                            if (isdelete){
                                changOrderState("delete", bean.getData().getOrderId(), null);
                                finish();
                            }
                        }
                    });
                }
            });
        }else if (status.equals(StringUtils.ORDER_REFUNDING)||status.equals(StringUtils.REFUND_FAILED_URL)){
            if (bean.getData().getPayType().equals("0")){
                vu.getTv_heji().setText("实付款");
                vu.getTv_pay_time().setText(bean.getData().getPayTime());
            }
            vu.getTv_mmm().setVisibility(View.VISIBLE);
            vu.getBtn_pay().setVisibility(View.GONE);
            vu.getBtn_refuse().setVisibility(View.GONE);
            vu.getChange_icon().setImageResource(R.drawable.icon_order_connection);
            vu.getChange_txt().setText("买家已付款");
            vu.getTv_mmm().setText("退款中");
            isEdit = true ;
            refund = "refunding";
            adapter.notifyDataSetChanged();
        }else if (status.equals(StringUtils.ORDEEER_REFUNDED)){
            if (bean.getData().getPayType().equals("0")){
                vu.getTv_heji().setText("实付款");
                vu.getTv_pay_time().setText(bean.getData().getPayTime());
            }
            vu.getTv_mmm().setVisibility(View.VISIBLE);
            vu.getChange_icon().setImageResource(R.drawable.icon_order_close);
            vu.getChange_txt().setText("交易关闭");
            vu.getBtn_pay().setVisibility(View.GONE);
            vu.getBtn_refuse().setVisibility(View.GONE);
            vu.getTv_mmm().setText("退款已完成");
        }
    }

    @Override
    protected void onDestroyVu() {
        super.onDestroyVu();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(String s){
        if (s.equals("refresh")){
            initData();
        }
    }

}
