package com.yunqilai.consumer.luckyapp.HomePage.Presenter;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

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
import com.yunqilai.consumer.luckyapp.HomePage.Model.SecondDataListBean;
import com.yunqilai.consumer.luckyapp.HomePage.Model.SecondMessageBean;
import com.yunqilai.consumer.luckyapp.HomePage.Model.SecondParseMessageBean;
import com.yunqilai.consumer.luckyapp.HomePage.View.TradeMessageView;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.OrderGoodsBean;
import com.yunqilai.consumer.luckyapp.UserCenter.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/6/5.
 */

public class TradeMessageActivity extends BasePresenterActivity<TradeMessageView> {

    private List<SecondParseMessageBean> dates = new ArrayList<>();
    private CommonAdapter<SecondParseMessageBean> adapter ;

    @Override
    protected Class<TradeMessageView> getViewClass() {
        return TradeMessageView.class;
    }

    @Override
    protected void onBindView() {
        super.onBindView();
        vu.getIv_back().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        adapter = new CommonAdapter<SecondParseMessageBean>(context,dates, R.layout.msg_ls_item) {
            @Override
            public void convert(ViewHolder helper, SecondParseMessageBean item) {
                helper.setText(R.id.tv_time,item.getDate());
                final String status = item.getOrder().getOrderStatus();
                final String num = item.getOrder().getOrderNo();
                ListView listView = helper.getView(R.id.ls_sv);
                List<OrderGoodsBean> list = item.getOrder().getGoodsList();
                CommonAdapter<OrderGoodsBean> com_adapter = new CommonAdapter<OrderGoodsBean>(context,list,R.layout.trade_message_ls_item) {
                    @Override
                    public void convert(ViewHolder helper, OrderGoodsBean item) {
                        helper.setText(R.id.tv_order_q, StringUtils.getName(status));
                        helper.setText(R.id.tv_num,"订单编号 :"+num);
                        helper.setText(R.id.tv_content,item.getGoodsName());
                        ImageView imageView = helper.getView(R.id.order_iv);
                        Glide.with(context).load(UrlUtils.URL_BASE+item.getGoodsPhoto()).into(imageView);

                    }
                };
                listView.setAdapter(com_adapter);
            }
        };

        vu.getLs().setAdapter(adapter);

        initData();
    }

    private void initData() {
        String type = gson.toJson(new SecondMessageBean(getAccessToken(),1,20,"logistics"));
        OkHttpClientManager.postAsynJson(type, UrlUtils.SECOND_MESSAGE_URL, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                ParseBaseEntity entity = gson.fromJson(response,ParseBaseEntity.class);
                if (entity.getCode().equals(ResponseCodeUtils.OK)){
                    SecondDataListBean listBean = gson.fromJson(gson.toJson(entity.getData()),SecondDataListBean.class);
                    if (listBean.getList().size()>0) {
                        vu.getNo_data_view().setVisibility(View.GONE);
                        dates.clear();
                        dates.addAll(listBean.getList());
                        adapter.notifyDataSetChanged();
                    }else {
                        vu.getLs().setVisibility(View.GONE);
                        vu.getNo_data_view().setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }
}
