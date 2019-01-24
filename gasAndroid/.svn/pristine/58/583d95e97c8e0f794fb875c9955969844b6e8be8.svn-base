package com.yunqilai.consumer.luckyapp.HomePage.Presenter;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.squareup.okhttp.Request;
import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.Base.BasePresenterActivity;
import com.yunqilai.consumer.luckyapp.Common.Utils.OkHttpClientManager;
import com.yunqilai.consumer.luckyapp.Common.Utils.ResponseCodeUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.UrlUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.adapter.CommonAdapter;
import com.yunqilai.consumer.luckyapp.Common.Utils.adapter.ViewHolder;
import com.yunqilai.consumer.luckyapp.HomePage.Model.NewIdBean;
import com.yunqilai.consumer.luckyapp.HomePage.Model.ThreeChannelBean;
import com.yunqilai.consumer.luckyapp.HomePage.Model.ThreeChannelInfoBean;
import com.yunqilai.consumer.luckyapp.HomePage.Model.ThreeChannelInfoGoodsBean;
import com.yunqilai.consumer.luckyapp.HomePage.View.ChannelThreeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/29.
 */

public class ChannelThreeActivity extends BasePresenterActivity<ChannelThreeView> {

    private List<ThreeChannelInfoBean> data = new ArrayList<>();
    private CommonAdapter<ThreeChannelInfoBean> adapter ;
    DecimalFormat df   = new DecimalFormat("######0.00");
    private String id ;

    @Override
    protected Class<ChannelThreeView> getViewClass() {
        return ChannelThreeView.class;
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

        id = getIntent().getStringExtra("id");

        adapter = new CommonAdapter<ThreeChannelInfoBean>(context,data, R.layout.three_channel_ls_item) {
            @Override
            public void convert(ViewHolder helper, ThreeChannelInfoBean item) {
                helper.setText(R.id.three_tv_title,item.getTitle());
                helper.setText(R.id.three_tv_content,item.getContent());
                ListView ls = helper.getView(R.id.three_lssv);
                CommonAdapter<ThreeChannelInfoGoodsBean> comm_adapter = null ;
                if (item.getGoodsList().size()>0){
                    comm_adapter = new CommonAdapter<ThreeChannelInfoGoodsBean>(context,item.getGoodsList(),R.layout.ls_ls_three_item) {
                        @Override
                        public void convert(ViewHolder helper, final ThreeChannelInfoGoodsBean item) {
                            helper.setText(R.id.tv_content_cart,item.getGoodsName());
                            helper.setText(R.id.tv_style_cart,"￥"+String.valueOf(df.format(item.getGoodsPrice())));
                            helper.getView(R.id.btn_code).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(ChannelThreeActivity.this,GoodsDetailsActivity.class);
                                    intent.putExtra("id",item.getGoodsId());
                                    startActivity(intent);
                                }
                            });

                            ImageView imageView = helper.getView(R.id.iv_icon);
                            Glide.with(context).load(UrlUtils.URL_BASE+item.getGoodsPhoto()).into(imageView);
                        }
                    };

                }
                ls.setAdapter(comm_adapter);
            }
        };

        vu.getLs().setAdapter(adapter);

        initData();

    }

    private void initData() {
        OkHttpClientManager.postAsynJson(gson.toJson(new NewIdBean(id,1,20)), UrlUtils.SOFT_PIC_URL, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                Log.e("sssssss",response);
                ThreeChannelBean bean = gson.fromJson(response,ThreeChannelBean.class);
                if (bean.getCode().equals(ResponseCodeUtils.OK)){
                    data.addAll(bean.getData().get("list"));
                    adapter.setData(data);
                    adapter.notifyDataSetChanged();
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
    public void onEvent(Integer close){
        if (close==3){
            finish();
        }
    }
}
