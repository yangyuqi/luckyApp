package com.yunqilai.consumer.luckyapp.UserCenter.Presenter;

import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.squareup.okhttp.Request;
import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.Base.BasePresenterActivity;
import com.yunqilai.consumer.luckyapp.Common.Ui.CircleImageView;
import com.yunqilai.consumer.luckyapp.Common.Utils.OkHttpClientManager;
import com.yunqilai.consumer.luckyapp.Common.Utils.ResponseCodeUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.UrlUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.adapter.CommonAdapter;
import com.yunqilai.consumer.luckyapp.Common.Utils.adapter.ViewHolder;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.DiscussHistroyBean;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.DiscussHistroyData;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.RefundDetailsBean;
import com.yunqilai.consumer.luckyapp.UserCenter.View.DiscussHistroyView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/17.
 */

public class DiscussHistroyActivity extends BasePresenterActivity<DiscussHistroyView> {

    private String orderId ;
    private List<DiscussHistroyBean> data = new ArrayList<>();
    private CommonAdapter<DiscussHistroyBean> adapter ;

    @Override
    protected Class<DiscussHistroyView> getViewClass() {
        return DiscussHistroyView.class;
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

        adapter = new CommonAdapter<DiscussHistroyBean>(context,data, R.layout.discuss_histroy_item) {
            @Override
            public void convert(ViewHolder helper, DiscussHistroyBean item) {
                helper.setText(R.id.tv_name , item.getName());
                helper.setText(R.id.tv_time , item.getDate());
                helper.setText(R.id.tv_content ,item.getContent());
                CircleImageView circleImageView = helper.getView(R.id.civ);
                Glide.with(context).load(UrlUtils.URL_BASE+item.getIcon()).into(circleImageView);
            }
        };
        vu.getLs().setAdapter(adapter);

        initData();
    }

    private void initData() {
        String type = gson.toJson(new RefundDetailsBean(getAccessToken(),orderId));
        OkHttpClientManager.postAsynJson(type, UrlUtils.DISCUSS_HISTROY_URL, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                DiscussHistroyData discussHistroyData = gson.fromJson(response,DiscussHistroyData.class);
                if (discussHistroyData.getCode().equals(ResponseCodeUtils.OK)){
                    data.clear();
                    data.addAll(discussHistroyData.getData());
                    adapter.setData(data);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
}
