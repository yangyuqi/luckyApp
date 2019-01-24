package com.yunqilai.consumer.luckyapp.HomePage.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.View.Vu;

import java.util.concurrent.TimeUnit;

import rx.functions.Action1;

/**
 * Created by Administrator on 2017/6/5.
 */

public class MessageInfoView implements Vu {

    private View view ;
    private ListView ls ;
    private ImageView iv_back ;
    private TextView tv_title ;

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.message_info_layout,null);
        ls = (ListView) view.findViewById(R.id.message_indo_ls);
        iv_back = (ImageView) view.findViewById(R.id.btnBack);
        tv_title = (TextView) view.findViewById(R.id.textHeadTitle);
        tv_title.setText("消息");
        iv_back.setVisibility(View.VISIBLE);
        RxView.clicks(iv_back).throttleFirst(2, TimeUnit.SECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {

            }
        });
    }

    @Override
    public View getView() {
        return view;
    }

    public ListView getLs(){
        return ls ;
    }

    public ImageView getIv_back(){
        return iv_back ;
    }
}
