package com.yunqilai.consumer.luckyapp.HomePage.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.View.Vu;

/**
 * Created by Administrator on 2017/6/6.
 */

public class OrderInfoView implements Vu {
    private View view ,select_addr ,select_style ;

    private TextView tv_pay ,tv_pay_nn ,tv_send_nn ,tv_time_nn ,tv_money_all ,tv_money_base ,tv_money_other ,tv_name,tv_phone,tv_address;
    private ListView ls ;

    public ImageView getIv_back() {
        return iv_back;
    }

    private ImageView iv_back ;
    private TextView tv_title ;
    private EditText edt_meg ;



    public ListView getLs() {
        return ls;
    }

    public TextView getTv_pay() {
        return tv_pay;
    }

    public View getSelect_addr() {
        return select_addr;
    }

    public View getSelect_style() {
        return select_style;
    }

    public EditText getEdt_meg() {
        return edt_meg;
    }

    public TextView getTv_pay_nn() {
        return tv_pay_nn;
    }

    public TextView getTv_send_nn() {
        return tv_send_nn;
    }

    public TextView getTv_time_nn() {
        return tv_time_nn;
    }

    public TextView getTv_money_all() {
        return tv_money_all;
    }

    public TextView getTv_money_base() {
        return tv_money_base;
    }

    public TextView getTv_money_other() {
        return tv_money_other;
    }

    public TextView getTv_name() {
        return tv_name;
    }

    public TextView getTv_phone() {
        return tv_phone;
    }

    public TextView getTv_address() {
        return tv_address;
    }

    private TextView tv_up_floor ;

    public TextView getTv_up_floor() {
        return tv_up_floor;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.order_info_activity_layout,null);
        tv_pay = (TextView) view.findViewById(R.id.order_tv_pay);
        ls = (ListView) view.findViewById(R.id.order_ls);
        select_addr = view.findViewById(R.id.rl_select_addr);
        iv_back = (ImageView) view.findViewById(R.id.btnBack);
        tv_title = (TextView) view.findViewById(R.id.textHeadTitle);
        tv_title.setText("确认订单");
        iv_back.setVisibility(View.VISIBLE);
        select_style = view.findViewById(R.id.rl_test_r);
        edt_meg = (EditText) view.findViewById(R.id.edt_message);
        tv_pay_nn = (TextView) view.findViewById(R.id.tv_pay_nn);
        tv_send_nn = (TextView) view.findViewById(R.id.tv_send_nn);
        tv_time_nn = (TextView) view.findViewById(R.id.tv_time_nn);
        tv_money_all = (TextView) view.findViewById(R.id.tv_all_get_money);
        tv_money_base = (TextView) view.findViewById(R.id.tv_order_money);
        tv_money_other = (TextView) view.findViewById(R.id.tv_send_money);
        tv_name = (TextView) view.findViewById(R.id.tv_name);
        tv_phone = (TextView) view.findViewById(R.id.tv_phone);
        tv_address = (TextView) view.findViewById(R.id.tv_address_mm);
        tv_up_floor = (TextView) view.findViewById(R.id.tv_up_floor);
    }

    @Override
    public View getView() {
        return view;
    }
}
