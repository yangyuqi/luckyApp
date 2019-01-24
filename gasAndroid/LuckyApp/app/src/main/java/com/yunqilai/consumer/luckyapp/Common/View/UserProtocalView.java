package com.yunqilai.consumer.luckyapp.Common.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunqilai.consumer.R;

/**
 * Created by yangyuqi on 17-7-29.
 */

public class UserProtocalView implements Vu {

    private View view ;
    private ImageView iv_back ;
    private TextView tv_title ;
    private WebView wb ;

    public ImageView getIv_back() {
        return iv_back;
    }

    public WebView getWb() {
        return wb;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.user_protocal_layout,null);
        iv_back = (ImageView) view.findViewById(R.id.btnBack);
        tv_title = (TextView) view.findViewById(R.id.textHeadTitle);
        tv_title.setText("用户协议");
        iv_back.setVisibility(View.VISIBLE);
        wb = (WebView) view.findViewById(R.id.ls_details);
    }

    @Override
    public View getView() {
        return view;
    }
}
