package com.yunqilai.delivery.ui.activity.my;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

import com.squareup.okhttp.Request;
import com.yunqilai.delivery.R;
import com.yunqilai.delivery.model.Bean.ParseBaseEntity;
import com.yunqilai.delivery.model.Bean.my.AboutUsBean;
import com.yunqilai.delivery.request.base.ErrorCode;
import com.yunqilai.delivery.ui.activity.BaseFragmentActivity;
import com.yunqilai.delivery.ui.activity.infomanage.TankDetailActivity;
import com.yunqilai.delivery.ui.view.CommonTitle;
import com.yunqilai.delivery.utils.Event;
import com.yunqilai.delivery.utils.MyOkHttpClientManager;
import com.yunqilai.delivery.utils.ResponseUtils;
import com.yunqilai.delivery.utils.UrlUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class AboutUsActivity extends BaseFragmentActivity {

    private WebView webView ;
    private CommonTitle title ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_about_us);
        title = (CommonTitle) findViewById(R.id.common_title);
        webView = (WebView)findViewById(R.id.ls_details);

        title.setOnTitleClickListener(new CommonTitle.OnTitleClickListener() {
            @Override
            public void onLeftClick() {
                finish();
            }

            @Override
            public void onRightClick() {

            }
        });

        MyOkHttpClientManager.postAsynJson("", UrlUtils.ABOUT_US_URL, new MyOkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Toast.makeText(AboutUsActivity.this,R.string.service_error_500,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {
                try {
                    ParseBaseEntity entity = gson.fromJson(response, ParseBaseEntity.class);
                    if (entity.getCode().equals(ResponseUtils.OK)) {
                        AboutUsBean bean = gson.fromJson(gson.toJson(entity.getData()), AboutUsBean.class);
                        webView.loadDataWithBaseURL(null, getNewContent(bean.getContent()), "text/html", "utf-8", null);
                    }else if(entity.getCode().equals(ErrorCode.ERR_ACCESS_TOKEN_INVALID) || entity.getCode().equals(ErrorCode.ERR_REFRESH_TOKEN_INVALID)){
                        EventBus.getDefault().post(Event.EXITANDLOGIN);
                    }
                }catch (Exception e){
                    Toast.makeText(AboutUsActivity.this,R.string.service_error_500,Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
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
