package com.yunqilai.consumer.luckyapp.UserCenter.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.View.Vu;


/**
 * Created by yangyuqi on 17-7-26.
 */

public class AboutUsView implements Vu {

    private WebView webView ;

    private View view ;
    private ImageView iv_back ;
    private TextView tv_title ,tv_content;

    public ImageView getIv_back() {
        return iv_back;
    }

    public TextView getTv_content() {
        return tv_content;
    }

    public WebView getWebView() {
        return webView;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.about_us_layout,null);
        iv_back = (ImageView) view.findViewById(R.id.btnBack);
        tv_title = (TextView) view.findViewById(R.id.textHeadTitle);
        tv_title.setText("关于我们");
        iv_back.setVisibility(View.VISIBLE);
//        tv_content = (TextView) view.findViewById(R.id.tv_content);
        webView = (WebView) view.findViewById(R.id.ls_details);
    }

    @Override
    public View getView() {
        return view;
    }
}
