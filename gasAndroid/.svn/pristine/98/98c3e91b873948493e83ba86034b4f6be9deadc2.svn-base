package com.yunqilai.consumer.luckyapp.HomePage.Presenter;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.hedgehog.ratingbar.RatingBar;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.squareup.okhttp.Request;
import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.ChoicePage.Bean.DiscussBean;
import com.yunqilai.consumer.luckyapp.ChoicePage.Bean.GoodsIdBean;
import com.yunqilai.consumer.luckyapp.ChoicePage.Bean.ParseGoodsDiscussBean;
import com.yunqilai.consumer.luckyapp.Common.Base.BasePresenterFragment;
import com.yunqilai.consumer.luckyapp.Common.Model.AllGoodsCountBean;
import com.yunqilai.consumer.luckyapp.Common.Model.ParseBaseEntity;
import com.yunqilai.consumer.luckyapp.Common.Ui.BottomDialog;
import com.yunqilai.consumer.luckyapp.Common.Ui.CircleImageView;
import com.yunqilai.consumer.luckyapp.Common.Utils.OkHttpClientManager;
import com.yunqilai.consumer.luckyapp.Common.Utils.ResponseCodeUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.UrlUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.adapter.CommonAdapter;
import com.yunqilai.consumer.luckyapp.Common.Utils.adapter.ViewHolder;
import com.yunqilai.consumer.luckyapp.HomePage.Model.AddCartBean;
import com.yunqilai.consumer.luckyapp.HomePage.Model.DetailsGoodsBean;
import com.yunqilai.consumer.luckyapp.HomePage.Model.DetailsGoodsDataBean;
import com.yunqilai.consumer.luckyapp.HomePage.Model.DisscussInfoBean;
import com.yunqilai.consumer.luckyapp.HomePage.Model.PositionBean;
import com.yunqilai.consumer.luckyapp.HomePage.Model.SpecBean;
import com.yunqilai.consumer.luckyapp.HomePage.View.GoodsDetailsFragmentView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/1.
 */

public class GoodsDetailsFragment extends BasePresenterFragment<GoodsDetailsFragmentView> {

    DecimalFormat df   = new DecimalFormat("######0.00");

    private CommonAdapter<ParseGoodsDiscussBean> adapter ;
    private List<ParseGoodsDiscussBean> data = new ArrayList<>();
    private String goodsId = new String();

    private BannerNormalAdapter normalAdapter ;
    private List<String> normol_data = new ArrayList<>();

    private AddCartBean spec_bean ;
    ProgressDialog dialog2 ;
    private int currentPage = 1 ;
    private int showCount = 8 ;

    private SpecBean specBean ;
    private AllGoodsCountBean goodsCount ;

    @Override
    public Class<GoodsDetailsFragmentView> getViewClass() {
        return GoodsDetailsFragmentView.class;
    }

    @Override
    public void bindView() {
        super.bindView();

        Bundle bundle = getArguments();
        if(bundle!=null){
            goodsId = bundle.getString("id");
        }
        dialog2 = ProgressDialog.show(context, "提示", "请稍后....");

        vu.getRl_color().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                EventBus.getDefault().post("");
                initBottomView("open");
            }
        });

        adapter = new CommonAdapter<ParseGoodsDiscussBean>(context,data, R.layout.goods_discuss_ls_item) {
            @Override
            public void convert(ViewHolder helper, final ParseGoodsDiscussBean item) {
                helper.setText(R.id.tv_content,item.getContent());
                helper.setText(R.id.discuss_tv_name,item.getUserName());
                CircleImageView imageView = helper.getView(R.id.discuss_ls_item_civ);
                helper.setText(R.id.discuss_tv_time,item.getDate());
                Glide.with(context).load(UrlUtils.URL_BASE+item.getIcon()).into(imageView);
                GridView gv = (GridView) helper.getConvertView().findViewById(R.id.gv_gv_goods);
                RatingBar bar = helper.getView(R.id.ratingbar_one);
                bar.setStar(item.getStars());
                CommonAdapter<String> commonAdapter = new CommonAdapter<String>(context,item.getImgs(),R.layout.more_goods_gv_item) {
                    @Override
                    public void convert(ViewHolder helper, String item) {
                        ImageView image = (ImageView) helper.getConvertView().findViewById(R.id.more_item_gv_iv);
                        Glide.with(context).load(UrlUtils.URL_BASE+item).placeholder(R.drawable.banner).into(image);
                    }
                };
                gv.setAdapter(commonAdapter);
            }
        };

        vu.getLs_discuss().setAdapter(adapter);

        normalAdapter = new BannerNormalAdapter(normol_data);

        vu.getPagerView().setAdapter(normalAdapter);

        vu.getSv().setOnTouchListener(new View.OnTouchListener() {

            private int lastY = 0;
            private int touchEventId = -9983761;
            Handler handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    View scroller = (View) msg.obj;

                    if (msg.what == touchEventId) {
                        if (lastY == scroller.getScrollY()) {
                            //停止了，此处你的操作业务
                            if (lastY>=0&&lastY<vu.getHeaderLength()){
                                EventBus.getDefault().post(new PositionBean(lastY,0));
                            }else if (lastY>=vu.getHeaderLength()&&lastY<=vu.getListViewLength()) {
                                EventBus.getDefault().post(new PositionBean(lastY,1));
                            }else {
                                EventBus.getDefault().post(new PositionBean(lastY,2));
                            }
                        } else {
                            handler.sendMessageDelayed(handler.obtainMessage(touchEventId, scroller), 1);
                            lastY = scroller.getScrollY();
                        }
                    }
                }
            };

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int eventAction = event.getAction();
                switch (eventAction) {
                    case MotionEvent.ACTION_UP:
                        handler.sendMessageDelayed(handler.obtainMessage(touchEventId, v), 5);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    private void initBottomView(String type) {
        BottomDialog bottomDialog = new BottomDialog(context,R.layout.bottom_goods_type ,type);
        bottomDialog.show();
        if (spec_bean!=null) {
            EventBus.getDefault().post(spec_bean);
        }
        if (specBean!=null){
            EventBus.getDefault().post(specBean);
        }
        if (!goodsCount.equals("")){
            EventBus.getDefault().post(goodsCount);
        }
    }

    private class BannerNormalAdapter extends StaticPagerAdapter {

        private List<String> mdata = new ArrayList<>();

        public BannerNormalAdapter(List<String> normol_data) {
            mdata = normol_data ;
        }

        public void setData(List<String> List){
                mdata = List;
                notifyDataSetChanged();
        }

        @Override
        public View getView(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            Glide.with(context).load(UrlUtils.URL_BASE+mdata.get(position)).into(view);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return view;
        }


        @Override
        public int getCount() {
            return mdata.size();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(final Integer position){
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                if (position == 0) {
                    vu.getSv().smoothScrollTo(0,0);
                }else if (position==1){
                    vu.getSv().smoothScrollTo(0,vu.getHeaderLength());
                }else {
                    vu.getSv().smoothScrollTo(0,vu.getListViewLength());
                }
            }
        });
    }

    @Subscribe
    public void onEvent(SpecBean bean){
        if (bean!=null){
            vu.getTv_show_type().setText("已选择"+bean.getSpecName());
            specBean = bean ;
        }
    }

    @Override
    public void bindData() {
        super.bindData();
        if (goodsId != null){
            dialog2.show();
            String type = gson.toJson(new GoodsIdBean(goodsId));
            OkHttpClientManager.postAsynJson(type, UrlUtils.GOODS_DETAILS_URL, new OkHttpClientManager.StringCallback() {
                @Override
                public void onFailure(Request request, IOException e) {

                }

                @Override
                public void onResponse(String response) {
                    dialog2.dismiss();
                    DetailsGoodsBean bean = gson.fromJson(response,DetailsGoodsBean.class);
                    if (bean.getCode().equals(ResponseCodeUtils.OK)){
                        DetailsGoodsDataBean decBean = bean.getData();
                        vu.getTv_content().setText(decBean.getGoodsName());
                        vu.getTv_price().setText("￥"+df.format(decBean.getGoodsPrice()));
                        goodsCount = new AllGoodsCountBean( bean.getData().getGoodsCount());
                        if (bean.getData().getSpecList()!=null){
                            spec_bean = new AddCartBean(bean.getData().getSpecList(),goodsId,decBean.getGoodsName(),decBean.getGoodsPhoto());
                        }
                        vu.getWebView().loadDataWithBaseURL(null,getNewContent(decBean.getDetails()),"text/html","utf-8",null);
                        normol_data.clear();
                        if (decBean.getPhotoList().size()>0) {
                            vu.getTv_no_data().setVisibility(View.GONE);
                            normol_data.addAll(decBean.getPhotoList());
                            normalAdapter.setData(normol_data);
                        }
                    }
                }
            });
        }

        if (goodsId!=null){
            String type = gson.toJson(new DiscussBean(getAccessToken(),goodsId,currentPage,showCount));
            OkHttpClientManager.postAsynJson(type, UrlUtils.SEARCH_ORDER_DICSUSS, new OkHttpClientManager.StringCallback() {
                @Override
                public void onFailure(Request request, IOException e) {

                }

                @Override
                public void onResponse(String response) {
                    ParseBaseEntity entity = gson.fromJson(response,ParseBaseEntity.class);
                    if (entity.getCode().equals(ResponseCodeUtils.OK)){
                        DisscussInfoBean bean = gson.fromJson(gson.toJson(entity.getData()),DisscussInfoBean.class);
                        if (bean.getList().size()>0){
                            try {
                                vu.getTv_no_data().setVisibility(View.GONE);
                            }catch (Exception e){}
                            data.addAll(bean.getList());
                            adapter.setData(data);
                            adapter.notifyDataSetChanged();
                        }else {
                            vu.getTv_no_data().setVisibility(View.VISIBLE);
                        }
                    }
                }
            });
        }
    }

    private String getNewContent(String htmltext){
        Document doc= Jsoup.parse(htmltext);
        Elements elements=doc.getElementsByTag("img");
        for (Element element : elements) {
            element.attr("width","100%").attr("height","auto");
        }
        return doc.toString();
    }

    @Subscribe
    public void onEvent(String str){
        initBottomView(str);
    }
}
