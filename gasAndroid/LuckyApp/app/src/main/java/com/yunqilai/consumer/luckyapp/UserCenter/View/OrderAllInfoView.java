package com.yunqilai.consumer.luckyapp.UserCenter.View;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.liaoinstan.springview.widget.SpringView;
import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.View.Vu;

/**
 * Created by Administrator on 2017/6/7.
 */

public class OrderAllInfoView implements Vu {

    private View view ;
    private TabLayout tb ;

    private SpringView springView ;
//    public ViewPager getVp() {
//        return vp;
//    }

    private ViewPager vp ;
    private ImageView iv_back ;
    private TextView tv_title ;
    private RecyclerView rv ;

    private View view_show ;

    public TabLayout getTb() {
        return tb;
    }

    public ImageView getIv_back() {
        return iv_back;
    }

    public SpringView getSpringView() {
        return springView;
    }

    public View getView_show() {
        return view_show;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.order_all_info_layout,null);
//        vp = (ViewPager) view.findViewById(R.id.order_vp);
        tb = (TabLayout) view.findViewById(R.id.goods_details_tl);
        iv_back = (ImageView) view.findViewById(R.id.btnBack);
        tv_title = (TextView) view.findViewById(R.id.textHeadTitle);
        tv_title.setText("我的订单");
        iv_back.setVisibility(View.VISIBLE);
        rv = (RecyclerView) view.findViewById(R.id.every_order_rv);
        springView = (SpringView) view.findViewById(R.id.homepage_spring_view);
        view_show = view.findViewById(R.id.ll_show);

    }

    @Override
    public View getView() {
        return view;
    }

    public RecyclerView getRv() {
        return rv;
    }
}
