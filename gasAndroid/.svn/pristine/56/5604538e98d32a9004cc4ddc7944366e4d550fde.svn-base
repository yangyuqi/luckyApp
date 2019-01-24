package com.yunqilai.consumer.luckyapp.Common.View;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.rollviewpager.RollPagerView;
import com.yunqilai.consumer.R;

/**
 * Created by Administrator on 2017/6/14.
 */

public class SplashView implements Vu {

    private View view ;
    private ImageView iv ,iv_welcome;
    private RollPagerView vp ;


    public ImageView getIv() {
        return iv;
    }

    public RollPagerView getVp() {
        return vp;
    }

    public ImageView getIv_welcome() {
        return iv_welcome;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
            view = inflater.inflate(R.layout.splash_layout,null);
            iv = (ImageView) view.findViewById(R.id.iv_btn);
            vp = (RollPagerView) view.findViewById(R.id.splash_view_pager);
            iv_welcome = (ImageView) view.findViewById(R.id.iv_welcome);
    }

    @Override
    public View getView() {
        return view;
    }
}
