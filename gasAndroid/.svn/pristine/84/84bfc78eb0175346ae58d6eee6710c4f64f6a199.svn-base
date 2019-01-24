package com.yunqilai.consumer.luckyapp.HomePage.View;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.View.Vu;

/**
 * Created by Administrator on 2017/6/1.
 */

public class GoodDetailsView implements Vu {

    private View view ;
    private FrameLayout vp ;
    private TabLayout tb ;
    private TextView tv_pay ;
    private ImageView iv ,iv_cart;

    private TextView tv_dd ;

    public TextView getTv_pay() {
        return tv_pay;
    }

    public ImageView getIv() {
        return iv;
    }

    public TextView getTv_dd() {
        return tv_dd;
    }

    public ImageView getIv_cart() {
        return iv_cart;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.goods_details_layout,null);
        vp = (FrameLayout) view.findViewById(R.id.goods_details_vp);
        tb = (TabLayout) view.findViewById(R.id.goods_details_tl);
        tv_pay = (TextView) view.findViewById(R.id.tv_pay_now);
        iv = (ImageView) view.findViewById(R.id.btnBack);
        tv_dd = (TextView) view.findViewById(R.id.tv_add_cart);
        iv_cart = (ImageView) view.findViewById(R.id.ib_carts);
    }

    @Override
    public View getView() {
        return view;
    }

    public FrameLayout getVp(){
        return vp;
    }

    public TabLayout getTb(){
        return tb;
    }


}
