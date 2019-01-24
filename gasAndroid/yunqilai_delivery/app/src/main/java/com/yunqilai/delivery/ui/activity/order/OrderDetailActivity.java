package com.yunqilai.delivery.ui.activity.order;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Request;
import com.yunqilai.delivery.R;
import com.yunqilai.delivery.model.Bean.GoodsBean;
import com.yunqilai.delivery.model.Bean.ParseBaseEntity;
import com.yunqilai.delivery.model.Bean.ParseOrderDetailsBean;
import com.yunqilai.delivery.model.Bean.PutOrderDetailsBean;
import com.yunqilai.delivery.model.Bean.attention.UseAttentionBean;
import com.yunqilai.delivery.model.Bean.home.GetOrderBean;
import com.yunqilai.delivery.model.Order;
import com.yunqilai.delivery.model.Product;
import com.yunqilai.delivery.request.base.ErrorCode;
import com.yunqilai.delivery.ui.Dialog.DeleteDialog;
import com.yunqilai.delivery.ui.activity.BaseActivity;
import com.yunqilai.delivery.ui.activity.MainActivity;
import com.yunqilai.delivery.ui.activity.attestation.AddAttestationActivity;
import com.yunqilai.delivery.ui.activity.common.ScanCodeActivity;
import com.yunqilai.delivery.ui.activity.my.ModifyPasswordActivity;
import com.yunqilai.delivery.ui.adapter.ProductListAdapter;
import com.yunqilai.delivery.ui.interf.DeleteDialogInterface;
import com.yunqilai.delivery.ui.interlayer.order.OrderDetailInterlayer;
import com.yunqilai.delivery.ui.presenter.order.OrderDetailPresenter;
import com.yunqilai.delivery.ui.view.CommonTitle;
import com.yunqilai.delivery.ui.view.NoScrollListView;
import com.yunqilai.delivery.utils.Event;
import com.yunqilai.delivery.utils.MyOkHttpClientManager;
import com.yunqilai.delivery.utils.ResponseUtils;
import com.yunqilai.delivery.utils.UrlUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

public class OrderDetailActivity extends BaseActivity<OrderDetailPresenter> implements OrderDetailInterlayer,View.OnClickListener {

    private CommonTitle commonTitle;

    private String orderId ,phone_num = "";
    private String buyName,buyPhone;

    private TextView payTypeHeadTv;
    private TextView payPriceLabelTv;
    private TextView payPriceTv;
    private TextView userNameTv;
    private TextView userPhoneTv;
    private TextView userAddressTv;
    private ImageView callIv;
    private TextView tv_status;
    private RelativeLayout rl_goto_auth;
    private TextView txt_auth;

    private NoScrollListView listView;
    private ProductListAdapter adapter;
    private List<GoodsBean> products;

    private TextView productTotalPriceTv;
    private TextView upFloorPriceTv;
    private TextView skipPriceTv;
    private TextView totalPriceTv;

    private TextView orderNumTv;
    private TextView orderTimeTv;
    private TextView refuseOrderTimeTv;
    private TextView payTypeTv;
    private TextView orderTypeTv;

    private TextView messageTv ;

    private LinearLayout lable_pay_time;
    private TextView tv_order_pay_time;

    private TextView refuseBtn;
    private TextView acceptBtn;

    private ImageView imageView ;

    private String orderStatus ;
    private String orderType;
    private String status;

    private List<String> coloum_data = new ArrayList<>();
    DecimalFormat df   = new DecimalFormat("######0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_order_detail);

        orderId = getIntent().getStringExtra("orderId");
        presenter = new OrderDetailPresenter(this,this,orderId);

        initView();
        initEvent();
        initData();

    }

    private void initView(){
        commonTitle = (CommonTitle)findViewById(R.id.common_title);
        payTypeHeadTv = (TextView)findViewById(R.id.tv_order_pay_type_head);
        payPriceLabelTv = (TextView)findViewById(R.id.tv_order_pay_label);
        payPriceTv = (TextView)findViewById(R.id.tv_order_pay_price);
        userNameTv = (TextView)findViewById(R.id.tv_username);
        userPhoneTv = (TextView)findViewById(R.id.tv_phone);
        userAddressTv = (TextView)findViewById(R.id.tv_address);
        callIv = (ImageView)findViewById(R.id.iv_phone);
        tv_status = (TextView) findViewById(R.id.tv_status);
        messageTv = (TextView) findViewById(R.id.tv_message);

        imageView = (ImageView) findViewById(R.id.iv_head_img);
        listView = (NoScrollListView)findViewById(R.id.product_list);

        productTotalPriceTv = (TextView)findViewById(R.id.tv_product_total_price);
        upFloorPriceTv = (TextView)findViewById(R.id.tv_up_floor_price);
        skipPriceTv = (TextView)findViewById(R.id.tv_skip_price);
        totalPriceTv = (TextView)findViewById(R.id.tv_total_price);

        rl_goto_auth = (RelativeLayout) findViewById(R.id.rl_goto_auth);
        txt_auth = (TextView) findViewById(R.id.txt_auth);

        tv_order_pay_time = (TextView) findViewById(R.id.tv_order_pay_time);
        lable_pay_time = (LinearLayout) findViewById(R.id.lable_pay_time);

        orderNumTv = (TextView)findViewById(R.id.tv_order_num);
        orderTimeTv = (TextView)findViewById(R.id.tv_order_time);
        refuseOrderTimeTv = (TextView)findViewById(R.id.tv_refuse_order_time);
        payTypeTv = (TextView)findViewById(R.id.tv_order_pay_type);
        orderTypeTv = (TextView)findViewById(R.id.tv_order_type);

        refuseBtn = (TextView) findViewById(R.id.tv_refuse);
        acceptBtn = (TextView) findViewById(R.id.tv_accept);

        callIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!phone_num.equals("")){
                    final DeleteDialog dialog = new DeleteDialog(context,"拨号",phone_num,"确认");
                    dialog.show();
                    dialog.OnDeleteBtn(new DeleteDialogInterface() {
                        @Override
                        public void isDelete(boolean isdelete) {
                            dialog.dismiss();
                            if (isdelete){
                                            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phone_num));
                                            OrderDetailActivity.this.startActivity(intent);
                            }
                        }
                    });
                }
            }
        });

        commonTitle.setRightIcon(R.mipmap.btn_sweep);
        commonTitle.showRightIcon(true);
    }

    private void initEvent(){
        commonTitle.setOnTitleClickListener(new CommonTitle.OnTitleClickListener() {
            @Override
            public void onLeftClick() {
                EventBus.getDefault().post(true);
                finish();
            }

            @Override
            public void onRightClick() {
                Intent intent = new Intent(context, ScanCodeActivity.class);
                intent.putExtra("orderId",orderId);
                intent.putExtra("buyName",buyName);
                intent.putExtra("buyPhone",buyPhone);
                startActivity(intent);
            }
        });
        refuseBtn.setOnClickListener(this);
        acceptBtn.setOnClickListener(this);
        txt_auth.setOnClickListener(this);
    }

    private void initData(){
        products = new ArrayList<>();
        adapter = new ProductListAdapter(this,products);
        listView.setAdapter(adapter);

        presenter.requestData();

    }

    @Override
    public void refreshDatas(ParseOrderDetailsBean order) {
        if (order.getMessage()!=null){
            messageTv.setText(order.getMessage());
        }

        orderStatus = order.getOrderStatus();
        if (orderStatus.equals("wait_pickup")){
            refuseBtn.setVisibility(View.GONE);
            acceptBtn.setText("取货");
        }else if (orderStatus.equals("wait_delivery")){
            refuseBtn.setVisibility(View.GONE);
            acceptBtn.setText("确认送达");
        }else if (orderStatus.equals("complete")||orderStatus.equals("refused")){
            refuseBtn.setVisibility(View.GONE);
            acceptBtn.setVisibility(View.GONE);
        }else if (orderStatus.equals("self_wait_extract")){
            refuseBtn.setVisibility(View.GONE);
            acceptBtn.setText("确认提取");
        }else if (orderStatus.equals("self_complete")){
            refuseBtn.setVisibility(View.GONE);
            acceptBtn.setVisibility(View.GONE);
        }

        try{
            orderType = order.getOrderType();
            status = order.getStatus();
            if("1".equals(order.getOrderType())){
                switch (order.getStatus()){
                    case "not":
                        tv_status.setText(R.string.user_status_not);
                        rl_goto_auth.setVisibility(View.VISIBLE);
                        break;
                    case "ing":
                        tv_status.setText(R.string.user_status_ing);
                        break;
                    case "failed":
                        tv_status.setText(R.string.user_status_failed);
                        break;
                    case "success":
                        tv_status.setText(R.string.user_status_success);
                        break;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        if(orderStatus.equals("wait_order") || orderStatus.equals("complete")){
            commonTitle.showRightIcon(false);
        }

        switch (order.getPayType()){
            case "0":
                payTypeHeadTv.setText(R.string.label_order_is_online);
                payPriceLabelTv.setText(R.string.label_user_has_pay);
                break;
            case "1":
                payTypeHeadTv.setText(R.string.label_order_is_on_delivery);
                payPriceLabelTv.setText(R.string.label_wait_user_pay);
                break;
        }

        if (orderStatus.equals("refused")){
            imageView.setImageResource(R.mipmap.icon_order_refuse);
            payTypeHeadTv.setText("已拒单");
            payPriceLabelTv.setText(order.getRefuseReason());
            payPriceTv.setVisibility(View.GONE);
        }

        if (orderStatus.equals("complete")){
            imageView.setImageResource(R.mipmap.icon_distribution_finish);
            payTypeHeadTv.setText("配送完成");
            payPriceLabelTv.setVisibility(View.GONE);
            payTypeHeadTv.setGravity(Gravity.CENTER);
        }

        payPriceTv.setText("￥"+df.format(order.getGoodsPrice())+"");
        userNameTv.setText(order.getBuyerName());
        userPhoneTv.setText(order.getPhone());

        buyName = order.getBuyerName();
        buyPhone = order.getPhone();

        phone_num = order.getPhone();
        userAddressTv.setText(order.getAddress());

        products.clear();
        products.addAll(order.getGoodsList());
        adapter.notifyDataSetChanged();

        productTotalPriceTv.setText(String.format(getString(R.string.label_price),order.getGoodsPrice()+""));
        if (!String.valueOf(order.getUpFloorPrice()).equals("")){
            upFloorPriceTv.setText("（含上楼配送费："+df.format(order.getUpFloorPrice())+"元)");
        }
        skipPriceTv.setText(String.format(getString(R.string.label_price),df.format(order.getShipPrice())+""));
        totalPriceTv.setText(String.format(getString(R.string.label_price),df.format(order.getTotalPrice())+""));

        orderNumTv.setText(order.getOrderNumber()+"");
        orderTimeTv.setText(order.getAddTime());
        refuseOrderTimeTv.setText(order.getRefuseTime());




        switch (order.getPayType()){
            case "0":
                payTypeTv.setText(R.string.label_pay_online);
                lable_pay_time.setVisibility(View.VISIBLE);
                tv_order_pay_time.setText(order.getPayTime());
                break;
            case "1":
                lable_pay_time.setVisibility(View.GONE);
                payTypeTv.setText(R.string.label_pay_on_delivery);
                break;
        }

        switch (order.getOrderType()){
            case "0":
                orderTypeTv.setText(R.string.label_online_order);
                break;
            case "1":
                orderTypeTv.setText(R.string.label_agent_order);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == 1){
                //刷新
                presenter.requestData();
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_refuse:
                presenter.refuse();
                break;
            case R.id.tv_accept:
//                presenter.accept();
                if (orderStatus.equals("wait_pickup")){//取货
                    try{
                        if("1".equals(orderType)){
                            if("not".equals(status)){
                                Toast.makeText(this,"请先实名认证",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else if (orderStatus.equals("self_wait_extract")){//确认提取
                    try{
                        if("1".equals(orderType)){
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                changeStatus();
                break;
            case R.id.txt_auth:
                Intent intent = new Intent(this, AddAttestationActivity.class);
                intent.putExtra("type","1");
                intent.putExtra("data",new UseAttentionBean(userNameTv.getText().toString(),userPhoneTv.getText().toString()));
                startActivityForResult(intent,1);
                break;
        }
    }

    public void changeStatus(){
        String content = null;
        String operate = null ;
        if (orderStatus.equals("wait_order")){
            content = "是否进行接单";
            operate = "accept_order" ;
        }else if (orderStatus.equals("wait_pickup")){
            content = "请您确认是否取货";
            operate = "pickup";
        }else if (orderStatus.equals("wait_delivery")){
            content = "是否确认送达";
            operate = "sure_arrive";
        }else if (orderStatus.equals("self_wait_extract")){
            content = "请您是否确认提取";
            operate = "sure_pick";
        }

        final DeleteDialog dialog = new DeleteDialog(context,"提示",content,"确定");
        dialog.show();
        final String finalOperate = operate;
        dialog.OnDeleteBtn(new DeleteDialogInterface() {
            @Override
            public void isDelete(boolean isdelete) {
                if (isdelete){
                    String mtype = null ;
                    mtype = gson.toJson(new GetOrderBean(getAccessToken(), orderId, finalOperate, null));
                    MyOkHttpClientManager.postAsynJson(mtype, UrlUtils.CHANGE_ORDER_STATUS_URL, new MyOkHttpClientManager.StringCallback() {
                        @Override
                        public void onFailure(Request request, IOException e) {
                            Toast.makeText(OrderDetailActivity.this,R.string.service_error_500,Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(String response) {
                            try {
                                ParseBaseEntity entity = gson.fromJson(response, ParseBaseEntity.class);
                                if (entity.getCode().equals(ResponseUtils.OK)) {
                                    dialog.dismiss();
                                    EventBus.getDefault().post(true);
                                    finish();
                                } else if(entity.getCode().equals(ErrorCode.ERR_ACCESS_TOKEN_INVALID) || entity.getCode().equals(ErrorCode.ERR_REFRESH_TOKEN_INVALID)){
                                    EventBus.getDefault().post(Event.EXITANDLOGIN);
                                }else {
                                    Toast.makeText(context, entity.getMsg(), Toast.LENGTH_SHORT).show();
                                }
                            }catch (Exception e){
                                Toast.makeText(OrderDetailActivity.this,R.string.service_error_500,Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().post(orderId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(Event event){
        if (event == Event.EXITANDLOGIN) {
            finish();
        }
    }
}
