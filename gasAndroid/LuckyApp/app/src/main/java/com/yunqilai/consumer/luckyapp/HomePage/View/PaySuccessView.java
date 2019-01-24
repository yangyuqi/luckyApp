package com.yunqilai.consumer.luckyapp.HomePage.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.View.Vu;

/**
 * Created by Administrator on 2017/6/7.
 */

public class PaySuccessView implements Vu {

    private View view ;
    private GridView gv ;
    private ImageView iv_back ;
    private TextView tv_title ,tv_order ,tv_pay_way ,tv_money ,order_tv ,tv_back;

    public GridView getGv() {
        return gv;
    }

    public ImageView getIv_back() {
        return iv_back;
    }

    public TextView getTv_order() {
        return tv_order;
    }

    public TextView getTv_pay_way() {
        return tv_pay_way;
    }

    public TextView getTv_money() {
        return tv_money;
    }

    public TextView getOrder_tv() {
        return order_tv;
    }

    public TextView getTv_back() {
        return tv_back;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.pay_success_layout,null);
        gv = (GridView) view.findViewById(R.id.pay_success_no_gv);
        iv_back = (ImageView) view.findViewById(R.id.btnBack);
        tv_title = (TextView) view.findViewById(R.id.textHeadTitle);
        tv_title.setText("支付成功");
        iv_back.setVisibility(View.VISIBLE);
        tv_order = (TextView) view.findViewById(R.id.order_tv);
        tv_pay_way = (TextView) view.findViewById(R.id.tv_pay_way);
        tv_money = (TextView) view.findViewById(R.id.tv_money);
        tv_back = (TextView) view.findViewById(R.id.tv_back);
    }

    @Override
    public View getView() {
        return view;
    }
}
