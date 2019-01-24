package com.yunqilai.consumer.luckyapp.HomePage.Presenter;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.squareup.okhttp.Request;
import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.Base.BasePresenterActivity;
import com.yunqilai.consumer.luckyapp.Common.Utils.OkHttpClientManager;
import com.yunqilai.consumer.luckyapp.Common.Utils.ResponseCodeUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.UrlUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.adapter.CommonAdapter;
import com.yunqilai.consumer.luckyapp.Common.Utils.adapter.ViewHolder;
import com.yunqilai.consumer.luckyapp.HomePage.Model.MessageCountBean;
import com.yunqilai.consumer.luckyapp.HomePage.Model.MessageFristBean;
import com.yunqilai.consumer.luckyapp.HomePage.View.MessageInfoView;
import com.yunqilai.consumer.luckyapp.ShoppingCart.Bean.UserTokenBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/5.
 */

public class MessageInfoActivity extends BasePresenterActivity<MessageInfoView> {

    private List<CommonTextBean> data = new ArrayList<>();

    private CommonAdapter<CommonTextBean> adapter ;

    @Override
    protected Class<MessageInfoView> getViewClass() {
        return MessageInfoView.class;
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

        vu.getLs().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (data.get(position).type.equals("tradeInfo")){
                    startActivity(new Intent(MessageInfoActivity.this,TradeMessageActivity.class));
                }else {
                    startActivity(new Intent(MessageInfoActivity.this,AuthMessageActivity.class));
                }
            }
        });

        adapter = new CommonAdapter<CommonTextBean>(context,data, R.layout.message_info_ls_item) {
            @Override
            public void convert(ViewHolder helper, CommonTextBean item) {
                helper.setText(R.id.message_tv_title,item.title);
                helper.setText(R.id.tv_meg_content ,item.content);
                ImageView iv_logo = helper.getView(R.id.iv_logo);
                if (item.type.equals("identificInfo")){
                    iv_logo.setImageResource(R.drawable.icon_autonym);
                }else {
                    iv_logo.setImageResource(R.drawable.icon_logistics);
                }
            }
        };

        vu.getLs().setAdapter(adapter);

        initData();
    }

    private void initData() {
        String type = gson.toJson(new UserTokenBean(getAccessToken()));
        OkHttpClientManager.postAsynJson(type, UrlUtils.FRIST_MESSAGE_URL, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                MessageFristBean bean = gson.fromJson(response,MessageFristBean.class);
                if (bean.getCode().equals(ResponseCodeUtils.OK)){
                    for (String key : bean.getData().keySet()){
                        if (key.equals("logistics")){
                            String mm = bean.getData().get("logistics");
                            data.add(new CommonTextBean("交易物流信息",mm,1,"tradeInfo"));
                        }else if (key.equals("attestation")){
                            String mm = bean.getData().get("attestation");
                            data.add(new CommonTextBean("实名认证通知",mm,1,"identificInfo"));
                        }
                        adapter.setData(data);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    public class CommonTextBean{
        String title ;
        String content ;
        int count ;
        String type ;

        public CommonTextBean(String title, String content, int count, String type) {
            this.title = title;
            this.content = content;
            this.count = count;
            this.type = type;
        }
    }
}
