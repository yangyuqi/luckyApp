package com.yunqilai.delivery.ui.activity.my;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.liaoinstan.springview.widget.SpringView;
import com.yunqilai.delivery.R;
import com.yunqilai.delivery.model.Bean.my.ArticleBean;
import com.yunqilai.delivery.model.Matter;
import com.yunqilai.delivery.ui.activity.BaseActivity;
import com.yunqilai.delivery.ui.view.CommonTitle;
import com.yunqilai.delivery.utils.Event;
import com.yunqilai.delivery.utils.ImageLoaderUtil;
import com.yunqilai.delivery.utils.UrlUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DispatchMatterDetailActivity extends BaseActivity {

    private CommonTitle commonTitle;
    private ImageView backgroundIv;
    private TextView titleTv;
    private TextView dateTv;
    private TextView contentTv;
    private WebView webView ;

    private ArticleBean mMatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_dispatch_matter_detail);

        mMatter = (ArticleBean)getIntent().getSerializableExtra("matter");
        if(mMatter == null){
            finish();
        }

        initView();
        initEvent();
        initData();
    }


    private void initView(){
        commonTitle = (CommonTitle)findViewById(R.id.common_title);
        backgroundIv = (ImageView)findViewById(R.id.iv_background);
        titleTv = (TextView)findViewById(R.id.tv_title);
        dateTv = (TextView)findViewById(R.id.tv_date);
        webView = (WebView) findViewById(R.id.ls_details);
//        contentTv = (TextView)findViewById(R.id.tv_content);
    }

    private void initEvent(){
        commonTitle.setOnTitleClickListener(new CommonTitle.OnTitleClickListener() {
            @Override
            public void onLeftClick() {
                finish();
            }

            @Override
            public void onRightClick() {

            }
        });
    }

    private void initData(){
        commonTitle.setTitle(mMatter.getTitle());
//        Glide.with(context).load(UrlUtils.PHOTO_URL_BASE+mMatter.getUrl()).into(backgroundIv);
        titleTv.setText(mMatter.getTitle());
//        dateTv.setText(mMatter.getDate());
        webView.loadDataWithBaseURL(null,getNewContent(mMatter.getContent()),"text/html","utf-8",null);
    }

    private String getNewContent(String htmltext){
        Document doc= Jsoup.parse(htmltext);
        Elements elements=doc.getElementsByTag("img");
        for (Element element : elements) {
            element.attr("width","100%").attr("height","auto");
        }
        return doc.toString();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(Event event){
        if (event == Event.EXITANDLOGIN) {
            finish();
        }
    }

}
