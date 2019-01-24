package com.yunqilai.consumer.luckyapp.HomePage.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.View.Vu;

import java.util.List;

/**
 * Created by Administrator on 2017/6/7.
 */

public class SelectPayWayView implements Vu {

    private ListView ls ;

    private View view ,view_time ;
    private ImageView iv_back ,iv_bottom;
    private TextView tv_title ,tv_online,tv_send,tv_yun,tv_self ,tv_select_time ,tv_confrim ,tv_show_tiime ,tv_h;

    public ImageView getIv_back() {
        return iv_back;
    }

    public TextView getTv_online() {
        return tv_online;
    }

    public TextView getTv_send() {
        return tv_send;
    }

    public TextView getTv_yun() {
        return tv_yun;
    }

    public TextView getTv_self() {
        return tv_self;
    }

    public View getView_time() {
        return view_time;
    }

    public TextView getTv_select_time() {
        return tv_select_time;
    }

    public TextView getTv_confrim() {
        return tv_confrim;
    }

    public ListView getLs() {
        return ls;
    }

    public TextView getTv_show_tiime() {
        return tv_show_tiime;
    }

    public TextView getTv_h() {
        return tv_h;
    }

    public ImageView getIv_bottom() {
        return iv_bottom;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.select_pay_way_layout,null);
        iv_back = (ImageView) view.findViewById(R.id.btnBack);
        tv_title = (TextView) view.findViewById(R.id.textHeadTitle);
        tv_title.setText("选择支付配送");
        iv_back.setVisibility(View.VISIBLE);
        view_time = view.findViewById(R.id.rl_select_time);
        tv_online = (TextView) view.findViewById(R.id.tv_pay_style_q);
        tv_send = (TextView) view.findViewById(R.id.tv_pay_style_w);
        tv_yun = (TextView) view.findViewById(R.id.tv_send_q);
        tv_self = (TextView) view.findViewById(R.id.tv_send_w);
        tv_select_time = (TextView) view.findViewById(R.id.tv_select_time);
        tv_confrim = (TextView) view.findViewById(R.id.tv_get_confrim);
        ls = (ListView) view.findViewById(R.id.ls_ls);
        tv_show_tiime = (TextView) view.findViewById(R.id.tv_show_tiime);
        tv_h = (TextView) view.findViewById(R.id.tv_h);
        iv_bottom = (ImageView) view.findViewById(R.id.iv_bottom);
    }

    @Override
    public View getView() {
        return view;
    }
}
