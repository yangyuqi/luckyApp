package com.yunqilai.consumer.luckyapp.HomePage.Presenter;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

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
import com.yunqilai.consumer.luckyapp.HomePage.Model.SearchGoodsBean;
import com.yunqilai.consumer.luckyapp.HomePage.View.SearchResultView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/6.
 */

public class SearchResultActivity extends BasePresenterActivity<SearchResultView> {

    private List<ChoiceListBean> data = new ArrayList<>();
    private CommonAdapter<ChoiceListBean> adapter ;
    private String key = null ;
    DecimalFormat df   = new DecimalFormat("######0.00");

    @Override
    protected Class<SearchResultView> getViewClass() {
        return SearchResultView.class;
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
        key = getIntent().getStringExtra("key");

        initData();

        adapter = new CommonAdapter<ChoiceListBean>(context,data, R.layout.gas_gv_item) {
            @Override
            public void convert(ViewHolder helper, ChoiceListBean item) {
                helper.setText(R.id.tv_pay_num,"￥"+String.valueOf(df.format(item.getGoodsPrice())));
                helper.setText(R.id.choice_page_view_tv_content,item.getGoodsName());
                ImageView imageView = helper.getView(R.id.iv_iv_icon);
                Glide.with(context).load(UrlUtils.URL_BASE+item.getGoodsPhoto()).placeholder(R.drawable.banner).into(imageView);
            }
        };

        vu.getGv().setAdapter(adapter);

        vu.getIv_back().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        vu.getGv().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SearchResultActivity.this,GoodsDetailsActivity.class);
                intent.putExtra("id",data.get(position).getGoodsId());
                startActivity(intent);
                finish();
            }
        });

    }

    private void initData() {
        if (key!=null){
            String type = gson.toJson(new SearchGoodsBean(key,1,20));
            OkHttpClientManager.postAsynJson(type, UrlUtils.KEY_SEARCH_URL, new OkHttpClientManager.StringCallback() {
                @Override
                public void onFailure(Request request, IOException e) {

                }

                @Override
                public void onResponse(String response) {
                    Log.e("ssssss",response);
                    ChannelPageBean bean = gson.fromJson(response,ChannelPageBean.class);
                    if (bean.getCode().equals(ResponseCodeUtils.OK)){
                        if (bean.getData().get("goodslist").size()>0) {
                            vu.getGv().setVisibility(View.VISIBLE);
                            vu.getNo_data().setVisibility(View.GONE);
                            data.clear();
                            data.addAll(bean.getData().get("goodslist"));
                            adapter.setData(data);
                            adapter.notifyDataSetChanged();
                        }else {
                            vu.getGv().setVisibility(View.GONE);
                            vu.getNo_data().setVisibility(View.VISIBLE);
                        }
                    }else {
                        vu.getGv().setVisibility(View.GONE);
                        vu.getNo_data().setVisibility(View.VISIBLE);
                        Toast.makeText(context,bean.getMsg(),Toast.LENGTH_SHORT).show();
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
