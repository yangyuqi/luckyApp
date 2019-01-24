package com.yunqilai.consumer.luckyapp.HomePage.View;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.liaoinstan.springview.widget.SpringView;
import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.Utils.adapter.CommonAdapter;
import com.yunqilai.consumer.luckyapp.Common.View.Vu;
import com.yunqilai.consumer.luckyapp.Common.View.VuCallback;
import com.yunqilai.consumer.luckyapp.Common.View.VuRefreshCallBack;

/**
 * Created by Administrator on 2017/5/24.
 */

public class HomePageView  implements Vu {

    protected View view ;
    public RollPagerView pagerView ;
    protected Context context ;
    protected SpringView springView ;
    protected VuRefreshCallBack callBack ;
    protected ListView listView ;
    protected ImageView iv_scan,iv_msg ;
    protected ScrollView scrollView ;
    protected View search_view ;
    protected RelativeLayout home_page_rl_parent;

    public SpringView getSpringView() {
        return springView;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        context = inflater.getContext();
        view = inflater.inflate(R.layout.home_page_layout,null);
        pagerView = (RollPagerView) view.findViewById(R.id.roll_view_pager);
        scrollView = (ScrollView) view.findViewById(R.id.scroll_View);
        springView = (SpringView) view.findViewById(R.id.homepage_spring_view);
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                callBack.onRefresh();
            }

            @Override
            public void onLoadmore() {
                callBack.onLoadmore();
            }
        });
        listView = (ListView) view.findViewById(R.id.home_page_ls);

        iv_scan = (ImageView) view.findViewById(R.id.home_page_iv_scan);
        iv_msg = (ImageView) view.findViewById(R.id.home_page_iv_message);
        search_view = view.findViewById(R.id.home_page_rl_search);
        home_page_rl_parent = (RelativeLayout) view.findViewById(R.id.home_page_rl_parent);

    }

    @Override
    public View getView() {
        return view;
    }

    public void setBannerAdapter(StaticPagerAdapter adapter){
        pagerView.setAnimationDurtion(500);
        pagerView.setHintView(new ColorPointHintView(context, context.getResources().getColor(R.color.main_color),Color.WHITE));
        pagerView.setAdapter(adapter);
    }

    public void setOnRefreshListener(VuRefreshCallBack listener){
        callBack = listener ;
    }


    public void scrollTo(){
        if (scrollView!=null) {
            scrollView.smoothScrollTo(0, 0);
        }
    }

    public ListView getListView(){
        return listView;
    }

    public ImageView getIv_scan(){
        return iv_scan;
    }
    public ImageView getIv_msg(){
        return iv_msg;
    }
    public View getSearch_view(){
        return search_view;
    }
    public RelativeLayout getHome_page_rl_parent(){
        return home_page_rl_parent;
    }
}
