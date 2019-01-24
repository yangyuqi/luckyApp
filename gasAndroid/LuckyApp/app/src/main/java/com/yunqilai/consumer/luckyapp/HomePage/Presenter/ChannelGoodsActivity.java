package com.yunqilai.consumer.luckyapp.HomePage.Presenter;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.okhttp.Request;
import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.ChoicePage.Bean.ChannelPageBean;
import com.yunqilai.consumer.luckyapp.ChoicePage.Bean.ChoiceListBean;
import com.yunqilai.consumer.luckyapp.ChoicePage.Bean.ChoicePageBean;
import com.yunqilai.consumer.luckyapp.Common.Base.BasePresenterActivity;
import com.yunqilai.consumer.luckyapp.Common.Utils.OkHttpClientManager;
import com.yunqilai.consumer.luckyapp.Common.Utils.ResponseCodeUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.UrlUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.adapter.CommonAdapter;
import com.yunqilai.consumer.luckyapp.Common.Utils.adapter.ViewHolder;
import com.yunqilai.consumer.luckyapp.HomePage.Model.NewIdBean;
import com.yunqilai.consumer.luckyapp.HomePage.View.ChannelGoodsView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/29.
 */

public class ChannelGoodsActivity extends BasePresenterActivity<ChannelGoodsView> {
    private CommonAdapter<ChoiceListBean> gas_adapter ;
    private List<ChoiceListBean> data = new ArrayList<>();
    String id ;
    DecimalFormat df   = new DecimalFormat("######0.00");

    @Override
    protected Class<ChannelGoodsView> getViewClass() {
        return ChannelGoodsView.class;
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

        gas_adapter = new CommonAdapter<ChoiceListBean>(context,data, R.layout.gas_gv_item) {
            @Override
            public void convert(ViewHolder helper, ChoiceListBean item) {
                helper.setText(R.id.choice_page_view_tv_content,item.getGoodsName());
                helper.setText(R.id.tv_pay_num,"￥"+String.valueOf(df.format(item.getGoodsPrice())));
                ImageView imageView = helper.getView(R.id.iv_iv_icon);
                Glide.with(context).load(UrlUtils.URL_BASE+item.getGoodsPhoto()).into(imageView);
            }
        };
        vu.getGridView().setAdapter(gas_adapter);

        initData(id);

        vu.getGridView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, GoodsDetailsActivity.class) ;
                intent.putExtra("id",String.valueOf(data.get(position).getGoodsId()));
                startActivity(intent);
            }
        });
    }

    private void initData(String id) {
        if (id!=null){
            String url = UrlUtils.CHANNEL_GOODS_ONE_URL ;
            OkHttpClientManager.postAsynJson(gson.toJson(new NewIdBean(id,1,20)), url, new OkHttpClientManager.StringCallback() {
                @Override
                public void onFailure(Request request, IOException e) {

                }

                @Override
                public void onResponse(String response) {
                    ChannelPageBean pageBean = gson.fromJson(response,ChannelPageBean.class);
                    if (pageBean.getCode().equals(ResponseCodeUtils.OK)){
                        data.clear();
                        data.addAll(pageBean.getData().get("list"));
                        gas_adapter.setData(data);
                        gas_adapter.notifyDataSetChanged();
                    }
                }
            });
        }
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
