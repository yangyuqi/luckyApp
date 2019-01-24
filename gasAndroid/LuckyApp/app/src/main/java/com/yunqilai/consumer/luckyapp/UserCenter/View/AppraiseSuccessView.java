package com.yunqilai.consumer.luckyapp.UserCenter.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.View.Vu;

/**
 * Created by yangyuqi on 17-8-5.
 */

public class AppraiseSuccessView implements Vu {

    private View view ;

    private TextView textView ,tv_title;
    private GridView gv ;
    private ImageView iv_back ;


    public TextView getTextView() {
        return textView;
    }

    public TextView getTv_title() {
        return tv_title;
    }

    public GridView getGv() {
        return gv;
    }

    public ImageView getIv_back() {
        return iv_back;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.appraise_success_layout,null);
        gv = (GridView) view.findViewById(R.id.pay_success_no_gv);
        iv_back = (ImageView) view.findViewById(R.id.btnBack);
        tv_title = (TextView) view.findViewById(R.id.textHeadTitle);
        tv_title.setText("支付成功");
        iv_back.setVisibility(View.VISIBLE);
        textView = (TextView) view.findViewById(R.id.tv_back);
    }

    @Override
    public View getView() {
        return view;
    }
}
