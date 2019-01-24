package com.yunqilai.consumer.luckyapp.SafeKnowledge;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.okhttp.Request;
import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.Base.BasePresenterFragment;
import com.yunqilai.consumer.luckyapp.Common.Utils.OkHttpClientManager;
import com.yunqilai.consumer.luckyapp.Common.Utils.ResponseCodeUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.UrlUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.adapter.CommonAdapter;
import com.yunqilai.consumer.luckyapp.Common.Utils.adapter.ViewHolder;
import com.yunqilai.consumer.luckyapp.SafeKnowledge.Bean.SafeBean;
import com.yunqilai.consumer.luckyapp.SafeKnowledge.Bean.SafeBeanData;
import com.yunqilai.consumer.luckyapp.SafeKnowledge.Presenter.SafeDetailsActivity;
import com.yunqilai.consumer.luckyapp.SafeKnowledge.View.SafeKnowledgeView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/22.
 */

public class SafeKnowledgeFragment extends BasePresenterFragment<SafeKnowledgeView> {

    private List<SafeBean> data = new ArrayList<>();
    private CommonAdapter<SafeBean> adapter ;

    @Override
    public Class<SafeKnowledgeView> getViewClass() {
        return SafeKnowledgeView.class;
    }

    @Override
    public void bindView() {
        super.bindView();

        adapter = new CommonAdapter<SafeBean>(context,data, R.layout.home_page_ls_item) {
            @Override
            public void convert(ViewHolder helper, SafeBean item) {
                helper.setText(R.id.home_page_ls_item_tv_title,item.getTitle());
                ImageView imageView = helper.getView(R.id.iv_iv_icon);
                Glide.with(context).load(UrlUtils.URL_BASE+item.getUrl()).into(imageView);
            }
        };

        vu.getLs().setAdapter(adapter);

        vu.getLs().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, SafeDetailsActivity.class) ;
                intent.putExtra("content",data.get(position).getContent());
                startActivity(intent);
            }
        });
    }

    @Override
    public void bindData() {
        super.bindData();
        OkHttpClientManager.postAsynJson("", UrlUtils.SAFE_KNOWLEDGE_URL, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                SafeBeanData beanData = gson.fromJson(response,SafeBeanData.class);
                if (beanData.getCode().equals(ResponseCodeUtils.OK)){
                    data.addAll(beanData.getData());
                    adapter.setData(data);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
}
