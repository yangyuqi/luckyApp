package com.yunqilai.consumer.luckyapp.UserCenter.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.View.Vu;

/**
 * Created by Administrator on 2017/6/8.
 */

public class GoodsOrderDetailsView implements Vu {

    private View view ,view_pay_time ,view_pay_type ,view_pay_send ,view_change_pay ,view_bottom_show;
    private ListView ls ;
    private ImageView iv_back ,change_icon;
    private TextView change_txt ;
    private TextView tv_title ,tv_heji ,tv_name ,tv_phone ,tv_address ,tv_message ,tv_pay ,tv_send ,tv_money ,tv_order_money ,tv_send_money ,tv_pay_time,tv_pay_type,tv_send_type ,tv_mmm ,tv_order_time ,tv_order_num;
    private Button btn_refuse ,btn_pay ;

    private TextView tv_floor_money ;

    public ListView getLs() {
        return ls;
    }

    public ImageView getIv_back() {
        return iv_back;
    }

    public Button getBtn_refuse() {
        return btn_refuse;
    }

    public Button getBtn_pay() {
        return btn_pay;
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

    public TextView getTv_message() {
        return tv_message;
    }

    public TextView getTv_pay() {
        return tv_pay;
    }

    public TextView getTv_send() {
        return tv_send;
    }

    public TextView getTv_money() {
        return tv_money;
    }

    public TextView getTv_order_money() {
        return tv_order_money;
    }

    public TextView getTv_send_money() {
        return tv_send_money;
    }

    public View getView_pay_time() {
        return view_pay_time;
    }

    public View getView_pay_type() {
        return view_pay_type;
    }

    public View getView_pay_send() {
        return view_pay_send;
    }

    public TextView getTv_pay_time() {
        return tv_pay_time;
    }

    public TextView getTv_pay_type() {
        return tv_pay_type;
    }

    public TextView getTv_send_type() {
        return tv_send_type;
    }

    public TextView getTv_mmm() {
        return tv_mmm;
    }

    public TextView getTv_order_time() {
        return tv_order_time;
    }

    public TextView getTv_order_num() {
        return tv_order_num;
    }

    public View getView_change_pay() {
        return view_change_pay;
    }

    public ImageView getChange_icon() {
        return change_icon;
    }

    public TextView getChange_txt() {
        return change_txt;
    }

    public View getView_bottom_show() {
        return view_bottom_show;
    }

    public TextView getTv_floor_money() {
        return tv_floor_money;
    }

    public TextView getTv_heji() {
        return tv_heji;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
            view = inflater.inflate(R.layout.goods_order_details_layout,null);
            ls = (ListView) view.findViewById(R.id.order_ls);
            iv_back = (ImageView) view.findViewById(R.id.btnBack);
            tv_title = (TextView) view.findViewById(R.id.textHeadTitle);
            tv_title.setText("订单详情");
            iv_back.setVisibility(View.VISIBLE);
            btn_refuse = (Button) view.findViewById(R.id.btn_refuse_order);
            btn_pay = (Button) view.findViewById(R.id.btn_pay);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_phone = (TextView) view.findViewById(R.id.tv_phone);
            tv_address = (TextView) view.findViewById(R.id.tv_address);
            tv_message = (TextView) view.findViewById(R.id.tv_message);
            tv_pay = (TextView) view.findViewById(R.id.tv_pay);
            tv_send = (TextView) view.findViewById(R.id.tv_send);
            tv_money = (TextView) view.findViewById(R.id.tv_money);
            tv_order_money = (TextView) view.findViewById(R.id.tv_order_money);
            tv_send_money = (TextView) view.findViewById(R.id.tv_send_money);
            view_pay_time = view.findViewById(R.id.ll_b);
            view_pay_type = view.findViewById(R.id.ll_x);
            view_pay_send = view.findViewById(R.id.ll_m);
            tv_pay_time = (TextView) view.findViewById(R.id.tv_time_time);
            tv_pay_type = (TextView) view.findViewById(R.id.tv_tv_pay);
            tv_send_type = (TextView) view.findViewById(R.id.tv_tv_send);
            tv_mmm = (TextView) view.findViewById(R.id.tv_mmm);
            tv_order_time = (TextView) view.findViewById(R.id.tv_order_time);
            tv_order_num = (TextView) view.findViewById(R.id.tv_order_num);
            view_change_pay = view.findViewById(R.id.rl_test_r);
            change_icon = (ImageView) view.findViewById(R.id.change_iv_icon);
            change_txt = (TextView) view.findViewById(R.id.change_iv_text);
            view_bottom_show = view.findViewById(R.id.rl_bottom_show);
            tv_heji = (TextView) view.findViewById(R.id.tv_heji);
            tv_floor_money = (TextView) view.findViewById(R.id.tv_floor_money);
    }

    @Override
    public View getView() {
        return view;
    }
}
