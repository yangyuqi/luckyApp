package com.yunqilai.consumer.luckyapp.HomePage.View;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.View.Vu;

/**
 * Created by Administrator on 2017/6/1.
 */

public class GoodsDetailsFragmentView implements Vu {

    private View view,rl_color ,rl_details;
    private RollPagerView pagerView ;

    private TextView tv_content ,tv_price ,tv_show_type ,tv_no_data;


    public RollPagerView getPagerView() {
        return pagerView;
    }

    public ScrollView getSv() {

        return sv;
    }

    public TextView getTv_content() {
        return tv_content;
    }

    public TextView getTv_price() {
        return tv_price;
    }

    private ListView ls_details , ls_discuss ;

    private WebView webView;

    private ScrollView sv ;

    public WebView getWebView() {
        return webView;
    }

    public ListView getLs_details() {
        return ls_details;
    }

    public ListView getLs_discuss() {
        return ls_discuss;
    }

    public TextView getTv_show_type() {
        return tv_show_type;
    }

    public TextView getTv_no_data() {
        return tv_no_data;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.goods_details_fragment_layout,null);
        rl_color = view.findViewById(R.id.rl_goods_color);
        webView = (WebView) view.findViewById(R.id.ls_details);
        ls_discuss = (ListView) view.findViewById(R.id.ls_discuss);
        sv = (ScrollView) view.findViewById(R.id.sv_details);
        webView.setFocusable(false);
        ls_discuss.setFocusable(false);
        pagerView = (RollPagerView) view.findViewById(R.id.rpv);
        pagerView.setHintView(new ColorPointHintView(inflater.getContext(), Color.TRANSPARENT,Color.TRANSPARENT));
        tv_content = (TextView) view.findViewById(R.id.tv_goods_title);
        tv_price = (TextView) view.findViewById(R.id.tv_goods_price);
        rl_details = view.findViewById(R.id.view_ff);
        tv_show_type = (TextView) view.findViewById(R.id.tv_show_type);
        webView.getSettings().setJavaScriptEnabled(true);
        tv_no_data = (TextView) view.findViewById(R.id.tv_no_data);
    }

    @Override
    public View getView() {
        return view;
    }

    public View getRl_color(){
        return rl_color ;
    }

    public void scrollTo(){
            sv.scrollTo(0,0);
            sv.smoothScrollTo(0,0);
    }

    public int getHeaderLength(){
        return pagerView.getHeight()+tv_content.getHeight()+tv_price.getHeight()+rl_color.getHeight();
    }
    public int getListViewLength(){
        return getHeaderLength()+webView.getHeight()+rl_details.getHeight();
    }
}
