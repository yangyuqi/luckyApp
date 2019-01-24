package com.yunqilai.consumer.luckyapp.HomePage.Presenter;

import android.content.Intent;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.squareup.okhttp.Request;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yunqilai.consumer.luckyapp.Common.Base.BasePresenterActivity;
import com.yunqilai.consumer.luckyapp.Common.Model.ParseBaseEntity;
import com.yunqilai.consumer.luckyapp.Common.Utils.OkHttpClientManager;
import com.yunqilai.consumer.luckyapp.Common.Utils.ResponseCodeUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.UrlUtils;
import com.yunqilai.consumer.luckyapp.HomePage.View.PayWaysView;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.PayDataBean;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.PayTypeData;
import com.yunqilai.consumer.luckyapp.UserCenter.Presenter.GoodsOrderDetailsActivity;
import com.yunqilai.consumer.luckyapp.UserCenter.StringUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/6/7.
 */

public class PayWaysActivity extends BasePresenterActivity<PayWaysView> {

    private IWXAPI iwxapi ;
    DecimalFormat df   = new DecimalFormat("######0.00");
    private String money ;
    private String orderId ;
    private String payType ;
    private String Client_ali = "1" ,Client_ten = "2" ;
    private String clien_type = "";


    @Override
    protected Class<PayWaysView> getViewClass() {
        return PayWaysView.class;
    }

    @Override
    protected void onBindView() {
        super.onBindView();

        orderId = getIntent().getStringExtra("orderId") ;
        money = getIntent().getStringExtra("money") ;

        if (money!=null){
            vu.getTv_money().setText("￥"+df.format(Double.parseDouble(money.substring(1))));
        }
        vu.getIv_back().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        vu.getView_ali().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clien_type = "1" ;
                initData(Client_ali,"alipay");
            }
        });




        vu.getView_wx().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (isWXAppInstalledAndSupported()) {
                    if (StringUtils.isWeixinAvilible(context)) {
                        iwxapi = WXAPIFactory.createWXAPI(context,ResponseCodeUtils.APPID,false);
                        iwxapi.registerApp(ResponseCodeUtils.APPID);
                        clien_type = "2";
                        initData(Client_ten, "wxpay");
                    } else {
                    Toast.makeText(context,"请安装最新微信客户端",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void initData(final String client, String alipay) {
        String type = gson.toJson(new PayTypeData(getAccessToken(),orderId,alipay));
        OkHttpClientManager.postAsynJson(type, UrlUtils.PAY_MONEY_URL, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                ParseBaseEntity entity = gson.fromJson(response,ParseBaseEntity.class);
                if (entity.getCode().equals(ResponseCodeUtils.OK)){
                    PayDataBean bean = gson.fromJson(gson.toJson(entity.getData()),PayDataBean.class);
                    payType = bean.getOrderString();
                    if (client.equals("1")) {
                        rx.Observable.create(new rx.Observable.OnSubscribe<String>() {
                            @Override
                            public void call(Subscriber<? super String> subscriber) {
                                PayTask payTask = new PayTask(PayWaysActivity.this);
                                subscriber.onNext(payTask.pay(payType, true));
                                subscriber.onCompleted();
                            }
                        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>() {
                            @Override
                            public void call(String s) {
                                if (s.indexOf("系统繁忙，请稍后再试") > 0) {
                                    Toast.makeText(PayWaysActivity.this, "请安装最新支付宝客户端", Toast.LENGTH_SHORT).show();
                                } else {
                                    String code = s.substring(14, 18);
                                    if (code.equals("6001")) {
                                        Intent intent = new Intent(context, GoodsOrderDetailsActivity.class);
                                        intent.putExtra("orderId", orderId);
                                        startActivity(intent);
                                        finish();
                                        Toast.makeText(context, "取消支付", Toast.LENGTH_SHORT).show();
                                    } else if (code.equals("9000")) {
                                        Intent intent = new Intent(context, PaySuccessActivity.class);
                                        intent.putExtra("price", money);
                                        intent.putExtra("from", "1");
                                        intent.putExtra("orderId", orderId);
                                        startActivity(intent);
                                        Toast.makeText(context, "订单支付成功", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                }
                            }
                        });
                    }else {
                        String json = gson.toJson(entity.getData());
                        Log.e("sssssssssss",json);
                        try {
                            JSONObject object = new JSONObject(json);
                            PayReq req = new PayReq();
                            req.appId = object.getString("appid");
                            req.nonceStr = object.getString("noncestr");
                            req.packageValue = "Sign=WXPay";
                            req.timeStamp = String.valueOf(object.getLong("timestamp"));
                            req.sign = object.getString("sign");
                            req.prepayId = object.getString("prepayid");
                            req.partnerId = object.getString("partnerid");
                            iwxapi.sendReq(req);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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

    @Subscribe
    public void onEvent(Boolean is){
        if (is){
            Intent intent = new Intent(context,PaySuccessActivity.class);
            intent.putExtra("price",money);
            intent.putExtra("from","2");
            intent.putExtra("orderId",orderId);
            startActivity(intent);
            finish();
        }else {
            Intent intent = new Intent(context,GoodsOrderDetailsActivity.class);
            intent.putExtra("orderId",orderId);
            startActivity(intent);
            finish();
        }
    }


    @Override
    protected void afterResume() {
        super.afterResume();
        try {
            EventBus.getDefault().register(this);
        }catch (Exception e){}
    }

}
