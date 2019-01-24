package com.yunqilai.consumer.luckyapp.HomePage.Presenter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.okhttp.Request;
import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.Base.BasePresenterActivity;
import com.yunqilai.consumer.luckyapp.Common.Model.ParseBaseEntity;
import com.yunqilai.consumer.luckyapp.Common.Model.PaySendBean;
import com.yunqilai.consumer.luckyapp.Common.Presenter.ConfirmUserActivity;
import com.yunqilai.consumer.luckyapp.Common.Ui.DeleteDialog;
import com.yunqilai.consumer.luckyapp.Common.Utils.OkHttpClientManager;
import com.yunqilai.consumer.luckyapp.Common.Utils.ResponseCodeUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.SharedPreferencesUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.UrlUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.adapter.CommonAdapter;
import com.yunqilai.consumer.luckyapp.Common.Utils.adapter.ViewHolder;
import com.yunqilai.consumer.luckyapp.HomePage.Model.AddOrderInfoBean;
import com.yunqilai.consumer.luckyapp.HomePage.Model.AddOrderInfoBeanData;
import com.yunqilai.consumer.luckyapp.HomePage.Model.ComputerOrderMoneyBean;
import com.yunqilai.consumer.luckyapp.HomePage.Model.NewCartBeanData;
import com.yunqilai.consumer.luckyapp.HomePage.Model.ParseComputerOrderBean;
import com.yunqilai.consumer.luckyapp.HomePage.View.OrderInfoView;
import com.yunqilai.consumer.luckyapp.ShoppingCart.Bean.AccessTokenBean;
import com.yunqilai.consumer.luckyapp.UserCenter.Interface.DeleteDialogInterface;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.LocationBean;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.OrderIdData;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.UserAttentionBean;
import com.yunqilai.consumer.luckyapp.UserCenter.Presenter.RealNameActivity;
import com.yunqilai.consumer.luckyapp.UserCenter.StringUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/6.
 */

public class OrderInfoActivity extends BasePresenterActivity<OrderInfoView> {
    DecimalFormat df   = new DecimalFormat("######0.00");
    private List<NewCartBeanData> my_data = new ArrayList<>();
    private CommonAdapter<NewCartBeanData> adapter ;
    private String addressId = "";
    private String deliverType = "0" ;
    private String skipTime = "" ;
    private String payType = "0";
    private String allMoney = null ;
    private String pickPlaceId = "" ;
    private int position = 0 ;

    private String attestation = "" ;
     ProgressDialog dialog2 ;


    @Override
    protected Class<OrderInfoView> getViewClass() {
        return OrderInfoView.class;
    }

    @Override
    protected void onBindView() {
        super.onBindView();

        EventBus.getDefault().register(this);

        dialog2 = ProgressDialog.show(this, "提示", "请稍后....");

        adapter = new CommonAdapter<NewCartBeanData>(context,my_data, R.layout.order_info_ls_item) {
            @Override
            public void convert(ViewHolder helper, NewCartBeanData item) {
                helper.setText(R.id.tv_order_ls_item_money,"￥"+df.format(Double.parseDouble(item.getPrice().substring(1))));
                helper.setText(R.id.tv_content_cart,item.getTitle());
                helper.setText(R.id.tv_style_cart,item.getSpecIdName());
                helper.setText(R.id.tv_num,"×"+String.valueOf(item.getCount()));
                ImageView imageView = helper.getView(R.id.iv_icon);
                Glide.with(context).load(UrlUtils.URL_BASE+item.getImg_url()).into(imageView);
            }
        };

        vu.getLs().setAdapter(adapter);

        vu.getIv_back().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        vu.getSelect_addr().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,LocationManagerActivity.class);
                intent.putExtra("flag","1");
                startActivityForResult(intent,1);
            }
        });

        vu.getSelect_style().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,SelectPayWayActivity.class);
                intent.putExtra("deliverType",deliverType);
                intent.putExtra("payType",payType);
                intent.putExtra("pickPlaceId",pickPlaceId);
                startActivityForResult(intent,1);
            }
        });

        vu.getTv_pay().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (!vu.getTv_money_all().getText().toString().equals("")) {
                    String role = (String) SharedPreferencesUtils.getParam(context,SharedPreferencesUtils.role,"");
                    if (role.equals("manager")||role.equals("delivery")){
                            if (deliverType.equals("0")){
                                if (!addressId.equals("")) {
                                    if (!skipTime.equals("")) {
                                        newOrder();
                                    } else {
                                        Toast.makeText(context, "选择时间段", Toast.LENGTH_SHORT).show();
                                    }
                                }else {
                                    Toast.makeText(context,"请选择地址",Toast.LENGTH_SHORT).show();
                                }
                            }
                            if (deliverType.equals("1")){
                                if (!addressId.equals("")) {
                                    newOrder();
                                }else {
                                    Toast.makeText(context,"请选择地址",Toast.LENGTH_SHORT).show();
                                }
                            }
                    }else {
                        getAttention();
                    }
//                }else {
//                    Toast.makeText(context,"选择时间",Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }

    private void initAddress() {
        dialog2.show();
        OkHttpClientManager.postAsynJson(gson.toJson(new AccessTokenBean(getAccessToken())), UrlUtils.GET_DEFAULT_ADDRESS_URL, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                dialog2.dismiss();
                ParseBaseEntity entity = gson.fromJson(response,ParseBaseEntity.class);
                if (entity.getCode().equals(ResponseCodeUtils.OK)){
                    LocationBean bean = gson.fromJson(gson.toJson(entity.getData()),LocationBean.class);
                    vu.getTv_address().setVisibility(View.VISIBLE);
                    vu.getTv_name().setText(bean.getTrueName());
                    vu.getTv_phone().setText(bean.getMobile());
                    vu.getTv_address().setText(bean.getShengName()+bean.getShiName()+bean.getQuName()+bean.getAreaInfo());
                    addressId = bean.getId();
                    orderComputer();
                }else {
                    vu.getTv_address().setVisibility(View.GONE);
                    vu.getTv_name().setText("新增地址");
                }
            }
        });

    }

    private void orderComputer() {
        List<AddOrderInfoBean> list_data  = new ArrayList<>();
        for (NewCartBeanData bean : my_data){
            AddOrderInfoBean infoBean ;
            if (bean.getCartId().equals("")) {
                infoBean = new AddOrderInfoBean(null, bean.getGoodsId(), bean.getSpecId(), bean.getCount());
            }else {
                infoBean = new AddOrderInfoBean(bean.getCartId(), bean.getGoodsId(), bean.getSpecId(), bean.getCount());
            }
            list_data.add(infoBean);
        }
        String type = gson.toJson(new ComputerOrderMoneyBean(getAccessToken(),addressId,list_data,deliverType));
        OkHttpClientManager.postAsynJson(type, UrlUtils.COMPUTER_ORDER_MONEYY_URL, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                ParseComputerOrderBean bean = gson.fromJson(response,ParseComputerOrderBean.class);
                if (bean.getCode().equals(ResponseCodeUtils.OK)){
                    allMoney = String.valueOf(bean.getData().getTotalPrice());
                    vu.getTv_money_all().setText("￥"+String.valueOf(df.format(bean.getData().getTotalPrice())));
                    vu.getTv_money_base().setText("￥"+String.valueOf(df.format(bean.getData().getTotalGoodsPrice())));
                    vu.getTv_money_other().setText("￥"+String.valueOf(df.format(bean.getData().getShipPrice())));
                    vu.getTv_up_floor().setText("包含上楼费"+"￥"+String.valueOf(df.format(bean.getData().getUpFloorPrice())));
                }else {
                    Toast.makeText(context,bean.getMsg(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void newOrder() {
        dialog2.show();
        List<AddOrderInfoBean> list_data  = new ArrayList<>();
        for (NewCartBeanData bean : my_data){
            AddOrderInfoBean infoBean ;
            if (bean.getCartId().equals("")) {
                infoBean = new AddOrderInfoBean(null, bean.getGoodsId(), bean.getSpecId(), bean.getCount());
            }else {
                infoBean = new AddOrderInfoBean(bean.getCartId(), bean.getGoodsId(), bean.getSpecId(), bean.getCount());
            }
            list_data.add(infoBean);
        }
        String type = null ;
        if (!pickPlaceId.equals("")){
            type = gson.toJson(new AddOrderInfoBeanData(getAccessToken(),addressId,list_data,deliverType,skipTime,payType,vu.getEdt_meg().getText().toString(),pickPlaceId));
        }else {
            type = gson.toJson(new AddOrderInfoBeanData(getAccessToken(),addressId,list_data,deliverType,skipTime,payType,vu.getEdt_meg().getText().toString(),null));
        }
        Log.e("sssssssssss",type);
        OkHttpClientManager.postAsynJson(type, UrlUtils.ADD_NEW_ORDER_URL, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                dialog2.dismiss();
                ParseBaseEntity entity = gson.fromJson(response,ParseBaseEntity.class);
                Toast.makeText(context,entity.getMsg(),Toast.LENGTH_SHORT).show();
                if (entity.getCode().equals(ResponseCodeUtils.OK)){
                    OrderIdData orderId = gson.fromJson(gson.toJson(entity.getData()), OrderIdData.class);
                    if (payType.equals("0")) {
                        Intent intent = new Intent(context, PayWaysActivity.class);
                        intent.putExtra("orderId", orderId.getOrderId());
                        intent.putExtra("money", "￥" + allMoney);
                        startActivity(intent);
                        finish();
                    }else {
                        Intent i = new Intent(context,PaySuccessActivity.class);
                        i.putExtra("price",allMoney);
                        i.putExtra("from","3");
                        i.putExtra("orderId",orderId.getOrderId());
                        startActivity(i);
                        finish();
                    }
                }
            }
        });
    }

    @Override
    protected void onDestroyVu() {
        super.onDestroyVu();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        PaySendBean bean = new PaySendBean(payType,deliverType);
        EventBus.getDefault().post(bean);
    }

    @Subscribe
    public void onEvent(List<NewCartBeanData> list){
        my_data.clear();
        my_data.addAll(list);
        adapter.setData(my_data);
        adapter.notifyDataSetChanged();
        initAddress();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1&&resultCode==1){
            payType = data.getStringExtra("pay");
            deliverType = data.getStringExtra("send");
            skipTime = data.getStringExtra("time");
            pickPlaceId = data.getStringExtra("pickPlaceId");
            vu.getTv_time_nn().setText(skipTime+"配送");
            if (payType.equals("0")){
                vu.getTv_pay_nn().setText("在线支付");
            }else {
                vu.getTv_pay_nn().setText("货到付款");
            }
            if (deliverType.equals("0")){
                vu.getSelect_addr().setVisibility(View.VISIBLE);
                vu.getTv_send_nn().setText("运气来配送");
            }else {
                vu.getTv_send_nn().setText("自提");
                vu.getTv_time_nn().setText(data.getStringExtra("pickName"));
//                vu.getSelect_addr().setVisibility(View.GONE);
            }
            orderComputer();
        }
        if (requestCode==1&&resultCode==2){
            initAddress();
//            addressId = getIntent().getStringExtra("addressId");
        }

    }

    public void getAttention(){
        String s = gson.toJson(new AccessTokenBean(getAccessToken()));
        OkHttpClientManager.postAsynJson(s, UrlUtils.MY_ATTENTION_URL, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                ParseBaseEntity entity = gson.fromJson(response,ParseBaseEntity.class);
                if (entity.getCode().equals(ResponseCodeUtils.OK)){
                    final UserAttentionBean bean = gson.fromJson(gson.toJson(entity.getData()),UserAttentionBean.class);
                    bean.getStatus();
                    if (!bean.getStatus().equals("success")){
                        DeleteDialog deleteDialog = new DeleteDialog(context,"提示",StringUtils.attentionType(bean.getStatus())+"，实名认证后才可以下单哦？","立即前往");
                        deleteDialog.show();
                        deleteDialog.OnDeleteBtn(new DeleteDialogInterface() {
                            @Override
                            public void isDelete(boolean isdelete) {
                                if (isdelete){
                                    if (bean.getStatus().equals("ing")||bean.getStatus().equals("failed")){
                                        Intent intent = new Intent(context, RealNameActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }else {
                                        Intent intent = new Intent(context, ConfirmUserActivity.class);
                                        intent.putExtra("flag", "2");
                                        startActivity(intent);
                                        finish();
                                    }

                                }
                            }
                        });
                    }else {
                            if (deliverType.equals("0")){
                                if (!addressId.equals("")) {
                                    if (!skipTime.equals("")) {
                                        newOrder();
                                    } else {
                                        Toast.makeText(context, "选择时间段", Toast.LENGTH_SHORT).show();
                                    }
                                }else {
                                    Toast.makeText(context,"请选择地址",Toast.LENGTH_SHORT).show();
                                }
                            }
                            if (deliverType.equals("1")){
                                newOrder();
                            }

                    }
                }else {
                    DeleteDialog deleteDialog = new DeleteDialog(context,"提示","未认证"+"，实名认证通过后才可以下单哦？","立即前往");
                    deleteDialog.show();
                    deleteDialog.OnDeleteBtn(new DeleteDialogInterface() {
                        @Override
                        public void isDelete(boolean isdelete) {
                            if (isdelete){
                                Intent intent = new Intent(context, ConfirmUserActivity.class);
                                intent.putExtra("flag","2");
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                }
            }
        });
    }
}
