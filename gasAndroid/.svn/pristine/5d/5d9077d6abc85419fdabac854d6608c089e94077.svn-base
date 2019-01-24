package com.yunqilai.consumer.luckyapp.HomePage;

import android.Manifest;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.squareup.okhttp.Request;
import com.tbruyelle.rxpermissions.RxPermissions;

import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.Base.BasePresenterFragment;
import com.yunqilai.consumer.luckyapp.Common.Model.ParseBaseEntity;
import com.yunqilai.consumer.luckyapp.Common.Presenter.CaptureActivity;
import com.yunqilai.consumer.luckyapp.Common.Presenter.ConfirmUserActivity;
import com.yunqilai.consumer.luckyapp.Common.Presenter.LoginActivity;
import com.yunqilai.consumer.luckyapp.Common.Ui.TagScrollView;
import com.yunqilai.consumer.luckyapp.Common.Utils.OkHttpClientManager;
import com.yunqilai.consumer.luckyapp.Common.Utils.ResponseCodeUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.ScreenUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.SharedPreferencesUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.TimeUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.UrlUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.adapter.CommonAdapter;
import com.yunqilai.consumer.luckyapp.Common.Utils.adapter.ViewHolder;
import com.yunqilai.consumer.luckyapp.Common.View.VuRefreshCallBack;
import com.yunqilai.consumer.luckyapp.HomePage.Model.AdverInfoBean;
import com.yunqilai.consumer.luckyapp.HomePage.Model.BannerInfoBean;
import com.yunqilai.consumer.luckyapp.HomePage.Presenter.ChannelGoodsActivity;
import com.yunqilai.consumer.luckyapp.HomePage.Presenter.ChannelThreeActivity;
import com.yunqilai.consumer.luckyapp.HomePage.Presenter.GoodsDetailsActivity;
import com.yunqilai.consumer.luckyapp.HomePage.Presenter.MessageInfoActivity;
import com.yunqilai.consumer.luckyapp.HomePage.Presenter.SearchGoodsActivity;
import com.yunqilai.consumer.luckyapp.HomePage.View.HomePageView;
import com.yunqilai.consumer.luckyapp.MainActivity;
import com.yunqilai.consumer.luckyapp.ShoppingCart.Bean.AccessTokenBean;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.UserAttentionBean;
import com.yunqilai.consumer.luckyapp.UserCenter.Presenter.RealNameActivity;

import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Administrator on 2017/5/22.
 */

public class HomePageFragment extends BasePresenterFragment<HomePageView> implements View.OnClickListener{


    private String m_status ;
    private long splashTime ;

    private BannerNormalAdapter adapter ;
    private List<AdverInfoBean> banner_date = new ArrayList<>();

    private List<AdverInfoBean> date = new ArrayList<>();

    private CommonAdapter<AdverInfoBean> body_adapter ;

    private WindowManager windowManager ;


    @Override
    public Class<HomePageView> getViewClass() {
        return HomePageView.class;
    }

    @Override
    public void bindView() {

        splashTime =  System.currentTimeMillis()/(1000*60);

        vu.getIv_msg().setOnClickListener(this);
        vu.getIv_scan().setOnClickListener(this);
        vu.getSearch_view().setOnClickListener(this);
        vu.getHome_page_rl_parent().setOnClickListener(this);

        body_adapter = new CommonAdapter<AdverInfoBean>(context,date,R.layout.home_page_ls_item) {
            @Override
            public void convert(ViewHolder helper, AdverInfoBean item) {
                helper.setText(R.id.home_page_ls_item_tv_title,item.getTitle());
                ImageView imageView = helper.getView(R.id.iv_iv_icon);
                Glide.with(context).load(UrlUtils.URL_BASE+item.getPhoto()).placeholder(R.drawable.banner).into(imageView);
            }
        };
        vu.getListView().setAdapter(body_adapter);
        vu.setOnRefreshListener(new VuRefreshCallBack() {
            @Override
            public void onRefresh() {
                initData();
                vu.getSpringView().onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                vu.getSpringView().onFinishFreshAndLoad();
            }
        });

        vu.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null ;
                switch (date.get(position).getForwardType()){
                    case "goods" :
                        intent = new Intent(context, GoodsDetailsActivity.class) ;
                        intent.putExtra("id",date.get(position).getForwardId());
                        startActivity(intent);
                        break;
                    case "goodsList":
                        intent = new Intent(context,ChannelGoodsActivity.class);
                        intent.putExtra("id",date.get(position).getForwardId());
                        startActivity(intent);
                        break;
                    case "adver":
                        intent = new Intent(context,ChannelThreeActivity.class);
                        intent.putExtra("id",date.get(position).getForwardId());
                        startActivity(intent);
                        break;
                }
            }
        });

        vu.pagerView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = null ;
                switch (banner_date.get(position).getForwardType()){
                    case "goods" :
                        intent = new Intent(context, GoodsDetailsActivity.class) ;
                        intent.putExtra("id",banner_date.get(position).getForwardId());
                        startActivity(intent);
                        break;
                    case "goodsList":
                        intent = new Intent(context,ChannelGoodsActivity.class);
                        intent.putExtra("id",banner_date.get(position).getForwardId());
                        startActivity(intent);
                        break;
                    case "adver":
                        intent = new Intent(context,ChannelThreeActivity.class);
                        intent.putExtra("id",banner_date.get(position).getForwardId());
                        startActivity(intent);
                        break;

                }
            }
        });

    }

    private void initData() {
        date.clear();
        OkHttpClientManager.postAsynJson(gson.toJson(new AccessTokenBean("")),UrlUtils.BANNER_HOME_URL, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                ParseBaseEntity baseEntity = gson.fromJson(response,ParseBaseEntity.class);
                if (baseEntity.getCode().equals(ResponseCodeUtils.OK)){
                    BannerInfoBean bean = gson.fromJson(gson.toJson(baseEntity.getData()),BannerInfoBean.class);
                    date.addAll(bean.getItemList());
                    body_adapter.setData(date);
                    body_adapter.notifyDataSetChanged();
                    banner_date.clear();
                    banner_date.addAll(bean.getBanners());
                    adapter = new BannerNormalAdapter(banner_date);
                    vu.setBannerAdapter(adapter);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home_page_iv_scan:
                RxPermissions permissions = new RxPermissions((MainActivity)context);
                permissions.request(Manifest.permission.CAMERA,Manifest.permission.VIBRATE).subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean){
                            startActivity(new Intent(context,CaptureActivity.class));
                        }
                    }
                });
                break;
            case R.id.home_page_iv_message:
                startActivity(new Intent(context, MessageInfoActivity.class));
                break;

            case R.id.home_page_rl_search:
                startActivity(new Intent(context, SearchGoodsActivity.class));
                break;
            case R.id.home_page_rl_parent:
                startActivity(new Intent(context, SearchGoodsActivity.class));
                break;
        }
    }


    private class BannerNormalAdapter extends StaticPagerAdapter {


        public BannerNormalAdapter(List<AdverInfoBean> entity) {
            banner_date = entity;
        }


        @Override
        public View getView(ViewGroup container, final int position) {
            ImageView view = new ImageView(container.getContext());
            Glide.with(context).load(UrlUtils.URL_BASE+banner_date.get(position).getPhoto()).placeholder(R.drawable.banner).into(view);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return view;
        }


        @Override
        public int getCount() {
            return banner_date.size();
        }
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden){
            vu.scrollTo();
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK) {

//            Toast.makeText(context,data.getStringExtra(CaptureActivity.EXTRA_RESULT),Toast.LENGTH_SHORT).show();

        } else {
        }
    }

    @Override
    public void bindData() {
        super.bindData();
        initData();
        vu.getSpringView().onFinishFreshAndLoad();
    }

    public void initLayer(){
        final View view = LayoutInflater.from(context).inflate(R.layout.image_mengce_layout,null);
        ImageView iv = (ImageView) view.findViewById(R.id.iv_mengceng);
        TagScrollView gv = (TagScrollView) view.findViewById(R.id.sv);
        if (m_status.equals("not")){
            iv.setImageResource(R.drawable.icon_attestation);
        }else if (m_status.equals("failed")){
            iv.setImageResource(R.drawable.icon_authentication_failure);
        }
        ScreenUtils.addLayer((MainActivity)context,view);

        gv.getStatus(new TagScrollView.GetScrollChangeInteface() {
            @Override
            public String getStaus(String status) {
                if (status.equals("down")){
                    ScreenUtils.removeLayer((MainActivity)getActivity(),view);
                }else if (status.equals("up")){
                    if (m_status.equals("not")) {
                        if (!getAccessToken().equals("")) {
                            Intent intent = new Intent(context, ConfirmUserActivity.class);
                            intent.putExtra("flag", "2");
                            startActivity(intent);
                            ScreenUtils.removeLayer((MainActivity) getActivity(), view);
                        }else {
                            Intent intent = new Intent(context, LoginActivity.class);
                            startActivity(intent);
                        }

                    }else if (m_status.equals("failed")){
                        Intent intent = new Intent(context, RealNameActivity.class);
                        startActivity(intent);
                        ScreenUtils.removeLayer((MainActivity) getActivity(), view);
                    }
                }
                return null;
            }
        });

    }

    public void getAttention(){
        String s = gson.toJson(new AccessTokenBean(getAccessToken()));
        OkHttpClientManager.postAsynJson(s, UrlUtils.MY_ATTENTION_URL, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                ParseBaseEntity entity = gson.fromJson(response,ParseBaseEntity.class);
                if (entity.getCode().equals(ResponseCodeUtils.OK)) {
                    UserAttentionBean bean = gson.fromJson(gson.toJson(entity.getData()), UserAttentionBean.class);
                    m_status = bean.getStatus();
                    if (bean.getStatus().equals("not")||bean.getStatus().equals("failed")){
                        initLayer();
                        SharedPreferencesUtils.setParam(context,SharedPreferencesUtils.attestationTipDate, TimeUtils.getYMD());
                    }
                }else {
                    m_status = "not" ;
                    initLayer();
                    SharedPreferencesUtils.setParam(context,SharedPreferencesUtils.attestationTipDate,TimeUtils.getYMD());
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!getAccessToken().equals("")) {
            String role = (String) SharedPreferencesUtils.getParam(context,SharedPreferencesUtils.role,"");
            if (role.equals("manager")||role.equals("delivery")){
                return;
            }else {
                String tipDate = (String)SharedPreferencesUtils.getParam(context,SharedPreferencesUtils.attestationTipDate,"");
                if (!TimeUtils.getYMD().equals(tipDate)) {
                    getAttention();
                }
            }
        }else {
            String tipDate = (String)SharedPreferencesUtils.getParam(context,SharedPreferencesUtils.attestationTipDate,"");
            if (!TimeUtils.getYMD().equals(tipDate)) {
                getAttention();
            }
        }
    }

    @Subscribe
    public void onEvent(String m_status){
        if (m_status.equals("refresh")){
            getAttention();
        }
    }
}
