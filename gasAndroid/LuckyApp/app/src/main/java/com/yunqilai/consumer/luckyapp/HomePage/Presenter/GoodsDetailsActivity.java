package com.yunqilai.consumer.luckyapp.HomePage.Presenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;

import com.yunqilai.consumer.luckyapp.Common.Base.BasePresenterActivity;

import com.yunqilai.consumer.luckyapp.Common.Model.CloseLoginBean;
import com.yunqilai.consumer.luckyapp.HomePage.Model.NewCartBeanData;
import com.yunqilai.consumer.luckyapp.HomePage.Model.PositionBean;
import com.yunqilai.consumer.luckyapp.HomePage.Model.SpecBean;
import com.yunqilai.consumer.luckyapp.HomePage.View.GoodDetailsView;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/1.
 */

public class GoodsDetailsActivity extends BasePresenterActivity<GoodDetailsView> {

    String goodsId ;
    NewCartBeanData goodsData ;
    List<NewCartBeanData> dataList = new ArrayList<>();
    CloseLoginBean closeLoginBean ;

    @Override
    protected Class<GoodDetailsView> getViewClass() {
        return GoodDetailsView.class;
    }

    private List<Fragment> list = new ArrayList<>();
    private String[] titles = {"商品", "详情", "评价"};
    private String show_type ,com;


    @Override
    protected void onBindView() {
        super.onBindView();

        goodsId = getIntent().getStringExtra("id");

        EventBus.getDefault().register(this);

        vu.getIv_cart().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(3);
                if (!getAccessToken().equals("")) {
                    finish();
                }
            }
        });
        vu.getIv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        GoodsDetailsFragment frag= new GoodsDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", goodsId);
        frag.setArguments(bundle);
        fm.beginTransaction().replace(vu.getVp().getId(),frag).commit();

        vu.getTv_pay().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (goodsData!=null) {
                    dataList = new ArrayList<>();
                    dataList.add(goodsData);
                    startActivity(new Intent(context, OrderInfoActivity.class));
                }else {
                        show_type = "open";
                        EventBus.getDefault().post(show_type);
                }
            }
        });

        vu.getTb().addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition()==0){
                    EventBus.getDefault().post(0);
                }else if (tab.getPosition()==1){
                    EventBus.getDefault().post(1);
                }else {
                    EventBus.getDefault().post(2);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        vu.getTv_dd().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_type = "add";
                EventBus.getDefault().post(show_type);
            }
        });
    }

    @Subscribe
    public void onEvent(PositionBean bean){
        if (bean.getType() == 1){
            vu.getTb().getTabAt(bean.getType() ).select();
        }else if (bean.getType() == 0){
            vu.getTb().getTabAt(bean.getType() ).select();
        }else if (bean.getType()  ==2){
            vu.getTb().getTabAt(bean.getType() ).select();
        }
    }

    @Subscribe
    public void onEvent(NewCartBeanData data){
        if (data!=null){
            SpecBean bean = new SpecBean(data.getSpecId(),data.getSpecIdName());
            EventBus.getDefault().post(bean);
            if (show_type.equals("add")) {
                goodsData = data;
            }else if (show_type.equals("open")){
                goodsData = data ;
                dataList = new ArrayList<>();
                dataList.add(goodsData);
                startActivity(new Intent(context, OrderInfoActivity.class));
            }
        }
    }

    @Subscribe
    public void onEvent(String s){
        if (s.equals("open")) {
            show_type = "open";
        }
    }

    @Subscribe
    public void OnEvent(CloseLoginBean bean){
        if (bean!=null){
            closeLoginBean = bean ;
        }
    }

    @Override
    protected void onDestroyVu() {
        super.onDestroyVu();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().removeAllStickyEvents();
            EventBus.getDefault().unregister(this);
            show_type="";
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (dataList.size()>0){
            EventBus.getDefault().post(dataList);
        }
        if (closeLoginBean!=null){
            EventBus.getDefault().post(closeLoginBean);
        }
    }
}
