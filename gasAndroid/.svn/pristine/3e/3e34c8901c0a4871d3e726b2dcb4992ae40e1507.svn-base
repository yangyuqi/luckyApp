package com.yunqilai.consumer.luckyapp.HomePage.Presenter;

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
import com.yunqilai.consumer.luckyapp.Common.Model.ParseBaseEntity;
import com.yunqilai.consumer.luckyapp.Common.Utils.OkHttpClientManager;
import com.yunqilai.consumer.luckyapp.Common.Utils.ResponseCodeUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.UrlUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.adapter.CommonAdapter;
import com.yunqilai.consumer.luckyapp.Common.Utils.adapter.ViewHolder;
import com.yunqilai.consumer.luckyapp.HomePage.View.PaySuccessView;
import com.yunqilai.consumer.luckyapp.MainActivity;
import com.yunqilai.consumer.luckyapp.ShoppingCart.Bean.AccessTokenBean;
import com.yunqilai.consumer.luckyapp.UserCenter.Presenter.GoodsOrderDetailsActivity;
import com.yunqilai.consumer.luckyapp.UserCenter.Presenter.OrderAllInfoActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/7.
 */

public class PaySuccessActivity extends BasePresenterActivity<PaySuccessView> {

    private List<ChoiceListBean> data = new ArrayList<>();
    private CommonAdapter<ChoiceListBean> adapter ;

    private String price ,from ,orderId ,mm;
    DecimalFormat df   = new DecimalFormat("######0.00");


    @Override
    protected Class<PaySuccessView> getViewClass() {
        return PaySuccessView.class;
    }

    @Override
    protected void onBindView() {
        super.onBindView();

        EventBus.getDefault().register(this);

        price = getIntent().getStringExtra("price");
        from = getIntent().getStringExtra("from");
        orderId = getIntent().getStringExtra("orderId");

        if (from.equals("2")){
            mm = "来自腾讯客户端";
        }else if (from.equals("3")){
            mm = "货到付款";
        }else {
            mm = "来自支付宝客户端";
        }

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
                helper.setText(R.id.tv_pay_num,"￥"+String.valueOf(df.format(item.getGoodsPrice())));
                ImageView imageView = helper.getView(R.id.iv_iv_icon);
                Glide.with(context).load(UrlUtils.URL_BASE+item.getGoodsPhoto()).into(imageView);
            }
        };

        vu.getGv().setAdapter(adapter);

        vu.getTv_order().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GoodsOrderDetailsActivity.class);
                intent.putExtra("orderId",orderId);
                startActivity(intent);
            }
        });

        vu.getTv_pay_way().setText(mm);
        try {
            vu.getTv_money().setText("￥"+""+df.format(Double.parseDouble(price.substring(1))));
        }catch (Exception e){}

        vu.getTv_back().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, MainActivity.class));
            }
        });

        vu.getGv().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, GoodsDetailsActivity.class);
                intent.putExtra("id",data.get(position).getGoodsId());
                startActivity(intent);
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
