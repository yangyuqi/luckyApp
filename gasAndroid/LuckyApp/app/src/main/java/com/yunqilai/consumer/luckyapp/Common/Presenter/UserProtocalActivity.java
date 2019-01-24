package com.yunqilai.consumer.luckyapp.Common.Presenter;

import android.util.Log;
import android.view.View;

import com.squareup.okhttp.Request;
import com.yunqilai.consumer.luckyapp.Common.Base.BasePresenterActivity;
import com.yunqilai.consumer.luckyapp.Common.Model.ParseBaseEntity;
import com.yunqilai.consumer.luckyapp.Common.Model.ProcotocalBean;
import com.yunqilai.consumer.luckyapp.Common.Utils.OkHttpClientManager;
import com.yunqilai.consumer.luckyapp.Common.Utils.ResponseCodeUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.UrlUtils;
import com.yunqilai.consumer.luckyapp.Common.View.UserProtocalView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by yangyuqi on 17-7-29.
 */

public class UserProtocalActivity extends BasePresenterActivity<UserProtocalView> {
    @Override
    protected Class<UserProtocalView> getViewClass() {
        return UserProtocalView.class;
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

        OkHttpClientManager.postAsynJson("", UrlUtils.USER_PROTOCOL_URL, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                ParseBaseEntity entity = gson.fromJson(response,ParseBaseEntity.class);
                if (entity.getCode().equals(ResponseCodeUtils.OK)){
                    ProcotocalBean bean = gson.fromJson(gson.toJson(entity.getData()),ProcotocalBean.class);
                    vu.getWb().loadDataWithBaseURL(null,getNewContent(bean.getContent()),"text/html","utf-8",null);
                }
            }
        });
//        vu.getWb().loadDataWithBaseURL(null,getNewContent(content),"text/html","utf-8",null);
    }
    private String getNewContent(String htmltext){
        Document doc= Jsoup.parse(htmltext);
        Elements elements=doc.getElementsByTag("img");
        for (Element element : elements) {
            element.attr("width","100%").attr("height","auto");
        }
        return doc.toString();
    }
}
