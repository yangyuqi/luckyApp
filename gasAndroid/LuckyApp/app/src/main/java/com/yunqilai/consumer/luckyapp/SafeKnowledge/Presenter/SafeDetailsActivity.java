package com.yunqilai.consumer.luckyapp.SafeKnowledge.Presenter;

import android.view.View;

import com.yunqilai.consumer.luckyapp.Common.Base.BasePresenterActivity;
import com.yunqilai.consumer.luckyapp.SafeKnowledge.View.SafeDetailsView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Administrator on 2017/6/6.
 */

public class SafeDetailsActivity extends BasePresenterActivity<SafeDetailsView> {

    private String content ;

    @Override
    protected Class<SafeDetailsView> getViewClass() {
        return SafeDetailsView.class;
    }

    @Override
    protected void onBindView() {
        super.onBindView();

        content = getIntent().getStringExtra("content");

        vu.getIv_back().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        vu.getWebView().loadDataWithBaseURL(null,getNewContent(content),"text/html","utf-8",null);
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
