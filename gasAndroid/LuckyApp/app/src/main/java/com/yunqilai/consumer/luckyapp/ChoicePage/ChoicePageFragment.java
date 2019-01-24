package com.yunqilai.consumer.luckyapp.ChoicePage;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.okhttp.Request;
import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.ChoicePage.Bean.ChoiceBean;
import com.yunqilai.consumer.luckyapp.ChoicePage.Bean.ChoiceListBean;
import com.yunqilai.consumer.luckyapp.ChoicePage.Bean.ChoicePageBean;
import com.yunqilai.consumer.luckyapp.ChoicePage.Bean.ShowTypeBean;
import com.yunqilai.consumer.luckyapp.ChoicePage.View.ChoicePageView;
import com.yunqilai.consumer.luckyapp.Common.Base.BasePresenterFragment;
import com.yunqilai.consumer.luckyapp.Common.Utils.OkHttpClientManager;
import com.yunqilai.consumer.luckyapp.Common.Utils.ResponseCodeUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.UrlUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.adapter.CommonAdapter;
import com.yunqilai.consumer.luckyapp.Common.Utils.adapter.ViewHolder;
import com.yunqilai.consumer.luckyapp.HomePage.Presenter.GoodsDetailsActivity;
import com.yunqilai.consumer.luckyapp.ShoppingCart.Bean.AccessTokenBean;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/22.
 */

public class ChoicePageFragment extends BasePresenterFragment<ChoicePageView>{

   private List<ChoiceBean> data = new ArrayList<>();
    private CommonAdapter<ChoiceBean> gas_adapter ;
    DecimalFormat    df   = new DecimalFormat("######0.00");

    @Override
    public Class<ChoicePageView> getViewClass() {
        return ChoicePageView.class;
    }

    @Override
    public void bindView() {
        super.bindView();

        gas_adapter = new CommonAdapter<ChoiceBean>(context,data, R.layout.choice_ls_item) {
            @Override
            public void convert(ViewHolder helper, ChoiceBean item) {
                helper.setText(R.id.tv_content_title,item.getTitle());
                GridView gridView = helper.getView(R.id.choice_page_view_tro_gv);
                final List<ChoiceListBean> goodsList = new ArrayList<>();
                goodsList.clear();
                goodsList.addAll(item.getGoodsList());
                CommonAdapter<ChoiceListBean> adapter = new CommonAdapter<ChoiceListBean>(context,goodsList,R.layout.gas_gv_item) {
                    @Override
                    public void convert(ViewHolder helper, ChoiceListBean item) {
                        helper.setText(R.id.choice_page_view_tv_content,item.getGoodsName());
                        helper.setText(R.id.tv_pay_num,"￥"+String.valueOf(df.format(item.getGoodsPrice())));
                        ImageView imageView = helper.getView(R.id.iv_iv_icon);
                        Glide.with(context).load(UrlUtils.URL_BASE+item.getGoodsPhoto()).placeholder(R.drawable.banner).into(imageView);
                    }
                };

                gridView.setAdapter(adapter);

                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(context, GoodsDetailsActivity.class) ;
                        intent.putExtra("id",goodsList.get(position).getGoodsId());
                        startActivity(intent);
                    }
                });
            }
        };

        vu.getLs().setAdapter(gas_adapter);

    }

    @Override
    public void bindData() {
        super.bindData();
        postNum();
    }

    private void postNum() {
        OkHttpClientManager.postAsynJson(gson.toJson(new AccessTokenBean("")), UrlUtils.CHOICE_LIST_URL, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                ChoicePageBean pageBean = gson.fromJson(response,ChoicePageBean.class);
                if (pageBean.getCode().equals(ResponseCodeUtils.OK)) {
                    data.addAll(pageBean.getData().get("list"));
                    gas_adapter.setData(data);
                    gas_adapter.notifyDataSetChanged();
                }
            }
        });
    }
}
