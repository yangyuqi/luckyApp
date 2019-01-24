package com.yunqilai.consumer.luckyapp.SafeKnowledge.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.View.Vu;

/**
 * Created by Administrator on 2017/6/6.
 */

public class SafeDetailsView implements Vu {

    private View view ;
    private ImageView iv_back ;
    private TextView tv_title ;

    private WebView webView ;

    public ImageView getIv_back() {
        return iv_back;
    }

    public void setWebView(WebView webView) {
        this.webView = webView;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.safe_details_layout,null);
        iv_back = (ImageView) view.findViewById(R.id.btnBack);
        tv_title = (TextView) view.findViewById(R.id.textHeadTitle);
        tv_title.setText("煤气安全知识");
        iv_back.setVisibility(View.VISIBLE);
        webView = (WebView) view.findViewById(R.id.ls_details);
    }

    @Override
    public View getView() {
        return view;
    }

    public WebView getWebView() {
        return webView;
    }
}
