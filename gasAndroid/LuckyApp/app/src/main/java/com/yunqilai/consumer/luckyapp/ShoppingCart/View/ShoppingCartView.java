package com.yunqilai.consumer.luckyapp.ShoppingCart.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.View.Vu;

/**
 * Created by Administrator on 2017/5/24.
 */

public class ShoppingCartView implements Vu {

    protected View view ,view_show;
    private TextView tv_title ,tv_all_money ,tv_pay ,tv_go;

    public TextView getTv_edit() {
        return tv_edit;
    }

    private TextView tv_edit;
    private ListView ls ;
    private CheckBox cb ;

    private View view_no_data,rl_ls ;

    public CheckBox getCb() {
        return cb;
    }

    public TextView getTv_pay() {
        return tv_pay;
    }

    public View getView_show() {
        return view_show;
    }

    public TextView getTv_go() {
        return tv_go;
    }

    public View getView_no_data() {
        return view_no_data;
    }

    public View getRl_ls() {
        return rl_ls;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.shopping_cart_layout,null);
        tv_title = (TextView) view.findViewById(R.id.textHeadTitle);
        tv_edit = (TextView) view.findViewById(R.id.textHeadNext);
        tv_edit.setVisibility(View.VISIBLE);
        tv_edit.setText("编辑");
        tv_title.setText("购物车");
        ls = (ListView) view.findViewById(R.id.shopping_cart_ls);
        cb = (CheckBox) view.findViewById(R.id.cart_rb);
        tv_all_money = (TextView) view.findViewById(R.id.tv_all_money);
        tv_pay = (TextView) view.findViewById(R.id.tv_pay);
        view_show = view.findViewById(R.id.ll_test_a);
        tv_go = (TextView) view.findViewById(R.id.tv_go);
        view_no_data = view.findViewById(R.id.ll_show);
        rl_ls = view.findViewById(R.id.rl_ls);

    }

    @Override
    public View getView() {
        return view;
    }

    public ListView getLs(){
        return ls;
    }

    public TextView getTv_all_money() {
        return tv_all_money;
    }
}
