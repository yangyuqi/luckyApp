package com.yunqilai.consumer.luckyapp.UserCenter.Presenter;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.okhttp.Request;
import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.Base.BasePresenterActivity;
import com.yunqilai.consumer.luckyapp.Common.Model.ParseBaseEntity;
import com.yunqilai.consumer.luckyapp.Common.Utils.OkHttpClientManager;
import com.yunqilai.consumer.luckyapp.Common.Utils.ResponseCodeUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.UrlUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.adapter.CommonAdapter;
import com.yunqilai.consumer.luckyapp.Common.Utils.adapter.ViewHolder;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.ParseRefundBean;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.RefundDetailsBean;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.RefundGoodsBean;
import com.yunqilai.consumer.luckyapp.UserCenter.StringUtils;
import com.yunqilai.consumer.luckyapp.UserCenter.View.RefundDetailsView;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/16.
 */

public class RefundDetailsActivity extends BasePresenterActivity<RefundDetailsView> {

    private String orderId ;
    DecimalFormat df   = new DecimalFormat("######0.00");

    private List<RefundGoodsBean> data = new ArrayList<>();
    private CommonAdapter<RefundGoodsBean> adapter ;

    @Override
    protected Class<RefundDetailsView> getViewClass() {
        return RefundDetailsView.class;
    }

    @Override
    protected void onBindView() {
        super.onBindView();
        orderId = getIntent().getStringExtra("orderId");
        vu.getIv_back().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        adapter = new CommonAdapter<RefundGoodsBean>(context,data, R.layout.order_info_ls_item_new) {
            @Override
            public void convert(ViewHolder helper, RefundGoodsBean item) {
                helper.setText(R.id.tv_content_cart,item.getGoodsName());
                helper.setText(R.id.tv_order_ls_item_money,"￥"+df.format(item.getPrice()));
                helper.setText(R.id.tv_style_cart ,"规格 :" +item.getSpec());
                ImageView imageView = helper.getView(R.id.iv_icon);
                Glide.with(context).load(UrlUtils.URL_BASE+item.getIcon()).placeholder(R.drawable.banner).into(imageView);
                helper.setText(R.id.tv_num,"×"+item.getCount());
            }
        };

        vu.getGv().setAdapter(adapter);

        vu.getView_histroy().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,DiscussHistroyActivity.class);
                i.putExtra("orderId",orderId);
                startActivity(i);
            }
        });
    }

    @Override
    protected void afterResume() {
        super.afterResume();

        initData();
    }

    private void initData() {
        String type = gson.toJson(new RefundDetailsBean(getAccessToken(),orderId));
        OkHttpClientManager.postAsynJson(type, UrlUtils.REFUNDED_DETAILS_URL, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                ParseBaseEntity entity = gson.fromJson(response,ParseBaseEntity.class);
                if (entity.getCode().equals(ResponseCodeUtils.OK)){
                    ParseRefundBean bean= gson.fromJson(gson.toJson(entity.getData()),ParseRefundBean.class);
                    vu.getTv_speed_status().setText(StringUtils.getRefundStatus(bean.getStatus()));
                    vu.getRefund_tv_money().setText("￥"+df.format(bean.getRefundPrice()));
                    vu.getRefund_reason().setText(bean.getReason());
                    vu.getRefund_money().setText("￥"+df.format(bean.getRefundPrice()));
                    try {
                        if (!bean.getMessage().equals("")) {
                            vu.getRefund_content().setText(bean.getMessage());
                        }else {
                            vu.getRefund_content().setText("无");
                        }
                    }catch (Exception e){}
                    vu.getRefund_number().setText(bean.getRefundNo());
                    vu.getRefund_time().setText(bean.getDate());
                    data.clear();
                    data.addAll(bean.getGoodsList());
                    adapter.setData(data);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
}
