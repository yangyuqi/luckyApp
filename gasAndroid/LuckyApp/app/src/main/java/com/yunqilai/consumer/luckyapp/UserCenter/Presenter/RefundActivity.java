package com.yunqilai.consumer.luckyapp.UserCenter.Presenter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.squareup.okhttp.Request;
import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.Base.BasePresenterActivity;
import com.yunqilai.consumer.luckyapp.Common.Model.ParseBaseEntity;
import com.yunqilai.consumer.luckyapp.Common.Ui.BottomDialog;
import com.yunqilai.consumer.luckyapp.Common.Ui.BottomDialogRefund;
import com.yunqilai.consumer.luckyapp.Common.Ui.BottomDialogUI;
import com.yunqilai.consumer.luckyapp.Common.Utils.OkHttpClientManager;
import com.yunqilai.consumer.luckyapp.Common.Utils.ResponseCodeUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.UrlUtils;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.PostReasionBean;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.ReasionBean;
import com.yunqilai.consumer.luckyapp.UserCenter.View.RefundView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Created by Administrator on 2017/6/9.
 */

public class RefundActivity extends BasePresenterActivity<RefundView> {

    private String price ,orderId ,shopCartId;
    private ReasionBean reasionBean ;
    DecimalFormat df   = new DecimalFormat("######0.00");
    @Override
    protected Class<RefundView> getViewClass() {
        return RefundView.class;
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
        price = String.valueOf(df.format(Double.parseDouble(getIntent().getStringExtra("price"))));
        orderId = getIntent().getStringExtra("orderId");
        shopCartId = getIntent().getStringExtra("shopCartId");

        vu.getTv_price().setText("￥"+price);
        vu.getView_reason().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomDialogRefund dailog = new BottomDialogRefund(context, R.layout.refund_reason_layout,getAccessToken());
                dailog.show();
            }
        });

        vu.getBtn_commit().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (reasionBean!=null){
                    String type = null ;
                    if (shopCartId==null) {
                        type = gson.toJson(new PostReasionBean(getAccessToken(), orderId, null, reasionBean.getReasonId(), vu.getEdt_content().getText().toString()));
                    }else {
                        type = gson.toJson(new PostReasionBean(getAccessToken(), orderId, shopCartId, reasionBean.getReasonId(), vu.getEdt_content().getText().toString()));
                    }

                    Log.e("sssssssssss",gson.toJson(type));
                    OkHttpClientManager.postAsynJson(type, UrlUtils.APPLAY_REFUND_URL, new OkHttpClientManager.StringCallback() {
                        @Override
                        public void onFailure(Request request, IOException e) {

                        }

                        @Override
                        public void onResponse(String response) {
                            Log.e("sssssssssssh",gson.toJson(response));
                            try {
                                ParseBaseEntity entity = gson.fromJson(response,ParseBaseEntity.class);
                                Toast.makeText(context,entity.getMsg(),Toast.LENGTH_SHORT).show();
                                if (entity.getCode().equals(ResponseCodeUtils.OK)){
                                    EventBus.getDefault().post("refresh");
                                    finish();
                                }
                            }catch (Exception e){}
                        }
                    });
                }else {
                    Toast.makeText(context,"选择原因",Toast.LENGTH_SHORT).show();
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
    public void onEvent(ReasionBean bean){
        reasionBean = bean ;
        vu.getTv_reasion().setText(reasionBean.getReason());
    }

    @Override
    protected void afterResume() {
        super.afterResume();
        getRefundPrice();
    }

    private void getRefundPrice() {

    }
}
