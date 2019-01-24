package com.yunqilai.consumer.luckyapp.HomePage.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.View.Vu;

/**
 * Created by Administrator on 2017/6/7.
 */

public class PayWaysView implements Vu {

    private View view ,view_ali ,view_wx;
    private ImageView iv_back ;
    private TextView tv_title ,tv_money;

    public ImageView getIv_back() {
        return iv_back;
    }

    public View getView_ali() {
        return view_ali;
    }

    public TextView getTv_money() {
        return tv_money;
    }

    public View getView_wx() {
        return view_wx;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.pay_ways_layout,null);
        iv_back = (ImageView) view.findViewById(R.id.btnBack);
        tv_title = (TextView) view.findViewById(R.id.textHeadTitle);
        tv_title.setText("收银台");
        iv_back.setVisibility(View.VISIBLE);
        view_ali = view.findViewById(R.id.rl_pay_alipay);
        tv_money = (TextView) view.findViewById(R.id.tv_money);
        view_wx = view.findViewById(R.id.rl_pay_wx);
    }

    @Override
    public View getView() {
        return view;
    }
}
