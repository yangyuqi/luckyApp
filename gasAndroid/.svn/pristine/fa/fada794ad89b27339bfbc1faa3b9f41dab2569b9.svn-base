package com.yunqilai.consumer.luckyapp.HomePage.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.View.Vu;

/**
 * Created by Administrator on 2017/6/5.
 */

public class AuthMessageView implements Vu {
    private View view ,ll_show;
    private ImageView iv_back ;
    public TextView tv_title ;
    public ListView ls ;

    public View getLl_show() {
        return ll_show;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.auth_message_layout,null);
        iv_back = (ImageView) view.findViewById(R.id.btnBack);
        tv_title = (TextView) view.findViewById(R.id.textHeadTitle);
        tv_title.setText("实名认证消息");
        iv_back.setVisibility(View.VISIBLE);
        ls = (ListView) view.findViewById(R.id.auth_message_layout_ls);
        ll_show = view.findViewById(R.id.ll_show);

    }

    @Override
    public View getView() {
        return view;
    }

    public ListView getLs(){
        return ls ;
    }

    public ImageView getIv_back(){
        return iv_back;
    }
}
