package com.yunqilai.consumer.luckyapp.UserCenter.Presenter;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.okhttp.Request;
import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.ChoicePage.Bean.ChannelPageBean;
import com.yunqilai.consumer.luckyapp.ChoicePage.Bean.ChoiceListBean;
import com.yunqilai.consumer.luckyapp.Common.Base.BasePresenterActivity;
import com.yunqilai.consumer.luckyapp.Common.Utils.OkHttpClientManager;
import com.yunqilai.consumer.luckyapp.Common.Utils.ResponseCodeUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.UrlUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.adapter.CommonAdapter;
import com.yunqilai.consumer.luckyapp.Common.Utils.adapter.ViewHolder;
import com.yunqilai.consumer.luckyapp.HomePage.Presenter.GoodsDetailsActivity;
import com.yunqilai.consumer.luckyapp.MainActivity;
import com.yunqilai.consumer.luckyapp.ShoppingCart.Bean.AccessTokenBean;
import com.yunqilai.consumer.luckyapp.UserCenter.View.AppraiseSuccessView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangyuqi on 17-8-5.
 */

public class AppraiseSuccessActivity extends BasePresenterActivity<AppraiseSuccessView> {

    private List<ChoiceListBean> data = new ArrayList<>();
    private CommonAdapter<ChoiceListBean> adapter ;

    @Override
    protected Class<AppraiseSuccessView> getViewClass() {
        return AppraiseSuccessView.class;
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


        adapter = new CommonAdapter<ChoiceListBean>(context,data, R.layout.gas_gv_item) {
            @Override
            public void convert(ViewHolder helper, ChoiceListBean item) {
                helper.setText(R.id.choice_page_view_tv_content,item.getGoodsName());
                helper.setText(R.id.tv_pay_num,"￥"+String.valueOf(item.getGoodsPrice()));
                ImageView imageView = helper.getView(R.id.iv_iv_icon);
                Glide.with(context).load(UrlUtils.URL_BASE+item.getGoodsPhoto()).into(imageView);
            }
        };

        vu.getGv().setAdapter(adapter);

        vu.getTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        vu.getGv().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, GoodsDetailsActivity.class);
                intent.putExtra("id",data.get(position).getGoodsId());
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void afterResume() {
        super.afterResume();
        initData();
    }

    private void initData() {
        OkHttpClientManager.postAsynJson(gson.toJson(new AccessTokenBean("")), UrlUtils.INTRODUCE_GOODS_URL, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                ChannelPageBean entity = gson.fromJson(response,ChannelPageBean.class);
                if (entity.getCode().equals(ResponseCodeUtils.OK)){
                    data.clear();
                    data.addAll(entity.getData().get("list"));
                    adapter.setData(data);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
}
