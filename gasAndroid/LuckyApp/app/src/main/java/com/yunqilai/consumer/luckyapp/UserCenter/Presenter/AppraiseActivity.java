package com.yunqilai.consumer.luckyapp.UserCenter.Presenter;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hedgehog.ratingbar.RatingBar;
import com.squareup.okhttp.Request;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.Base.BasePresenterActivity;
import com.yunqilai.consumer.luckyapp.Common.Model.FileDataBean;
import com.yunqilai.consumer.luckyapp.Common.Model.ParseBaseEntity;
import com.yunqilai.consumer.luckyapp.Common.Presenter.ConfirmUserActivity;
import com.yunqilai.consumer.luckyapp.Common.Utils.OkHttpClientManager;
import com.yunqilai.consumer.luckyapp.Common.Utils.ResponseCodeUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.UrlUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.adapter.CommonAdapter;
import com.yunqilai.consumer.luckyapp.Common.Utils.adapter.ViewHolder;
import com.yunqilai.consumer.luckyapp.UserCenter.Interface.SendData;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.AppraBean;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.OrderGoodsBean;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.PostAppraiseBean;
import com.yunqilai.consumer.luckyapp.UserCenter.View.AppraiseView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.iwf.photopicker.PhotoPicker;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/6/9.
 */

public class AppraiseActivity extends BasePresenterActivity<AppraiseView> {

    private List<AppraiseBean> goodsInfo = new ArrayList<>();
    private CommonAdapter<AppraiseBean> adapter ;

    private CommonAdapter<String> img_adapter ;

    private String orderId ;

    private int current_place ,service_score = 0 ,persion_score = 0;

    private SendData sendData ;

    @Override
    protected Class<AppraiseView> getViewClass() {
        return AppraiseView.class;
    }

    @Override
    protected void onBindView() {
        super.onBindView();

        sendData = (SendData) getIntent().getSerializableExtra("send_data");
        orderId = sendData.getOrderId();

        vu.getBar_one().setStar(5);
        vu.getBar_two().setStar(5);

        vu.getIv_back().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        adapter = new CommonAdapter<AppraiseBean>(context,goodsInfo, R.layout.appraise_rv_item) {
            @Override
            public void convert(final ViewHolder helper, final AppraiseBean item) {
                ImageView imageView = helper.getView(R.id.iv_iv_goods);
                Glide.with(context).load(UrlUtils.URL_BASE+item.getIcon()).into(imageView);
                ImageView imageView1 = helper.getView(R.id.iv_select_photo);
                final RatingBar bar = helper.getView(R.id.ratingbar_two);
                bar.setStar(5);
                final EditText editText = helper.getView(R.id.edt_content);
                bar.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
                    @Override
                    public void onRatingChange(float RatingCount) {
                        for (int i = 0 ;i<goodsInfo.size() ; i++){
                            if (helper.getPosition() == i){
                                AppraiseBean bean = goodsInfo.get(i);
                                goodsInfo.remove(i);
                                AppraiseBean appraiseBean = new AppraiseBean(bean.getShopCartId(),bean.getGoodsId(),bean.getIcon(),(int)RatingCount,bean.getContent(),item.getImgs());
                                goodsInfo.add(appraiseBean);
                            }
                        }

                    }
                });

                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                            if (!s.toString().equals("")){
                                for (int i = 0 ;i<goodsInfo.size() ; i++){
                                    if (helper.getPosition() == i){
                                        AppraiseBean bean = goodsInfo.get(i);
                                        goodsInfo.remove(i);
                                        AppraiseBean appraiseBean = new AppraiseBean(bean.getShopCartId(),bean.getGoodsId(),bean.getIcon(),bean.getStars(),editText.getText().toString(),item.getImgs());
                                        goodsInfo.add(appraiseBean);
                                    }
                                }
                            }
                    }
                });

                imageView1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        current_place = helper.getPosition();

                        RxPermissions permissions = new RxPermissions(AppraiseActivity.this);
                        permissions.request(Manifest.permission.CAMERA,Manifest.permission.VIBRATE).subscribe(new Action1<Boolean>() {
                            @Override
                            public void call(Boolean aBoolean) {
                                if (aBoolean){
                                    PhotoPicker.builder()
                                            .setPhotoCount(6)
                                            .setShowCamera(true)
                                            .setShowGif(true)
                                            .setPreviewEnabled(false)
                                            .start(AppraiseActivity.this, PhotoPicker.REQUEST_CODE);
                                }
                            }
                        });
                    }
                });

                List<String> data_img = item.getImgs();

                GridView gv = helper.getView(R.id.gv);
                img_adapter = new CommonAdapter<String>(context,data_img,R.layout.more_goods_gv_item) {
                    @Override
                    public void convert(ViewHolder helper, String item) {
                        ImageView imageView2 = helper.getView(R.id.more_item_gv_iv);
                        Glide.with(context).load(UrlUtils.URL_BASE+item).placeholder(R.drawable.icon_logo).into(imageView2);
                    }
                };
                gv.setAdapter(img_adapter);
            }
        };

        vu.getLs().setAdapter(adapter);

        vu.getTv_next().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postAppraise();
            }
        });

        vu.getBar_one().setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float RatingCount) {
                service_score = (int)RatingCount ;
            }
        });

        vu.getBar_two().setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float RatingCount) {
                persion_score = (int)RatingCount ;
            }
        });

        onEvent(sendData.getList());
    }



    public void onEvent(List<OrderGoodsBean> info){
        if (info.size()>0){

            for (int i = 0 ;i<info.size();i++){
                List<String> img = new ArrayList<>();
                AppraiseBean bean = new AppraiseBean(info.get(i).getShopCartId(),info.get(i).getGoodsId(),info.get(i).getGoodsPhoto(),0,"",img);
                goodsInfo.add(bean);
            }
            adapter.setData(goodsInfo);
            adapter.notifyDataSetChanged();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
            if (data != null) {
                final ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);

                updatePhoto(photos);

            }
        }
    }

    private void updatePhoto(ArrayList<String> photos) {
        final ProgressDialog dialog2 = ProgressDialog.show(context, "提示", "图片上传中");
        final List<String> com_data = new ArrayList<>();
        for (int i = 0; i < photos.size(); i++) {
                    File file = new File(photos.get(i));
                try {
                    OkHttpClientManager.postAsyn(UrlUtils.UPLOAD_PHOTO_URL, new OkHttpClientManager.StringCallback() {
                        @Override
                        public void onFailure(Request request, IOException e) {

                        }

                        @Override
                        public void onResponse(String response) {
                            dialog2.dismiss();
                            ParseBaseEntity entity = gson.fromJson(response, ParseBaseEntity.class);
                            if (entity.getCode().equals(ResponseCodeUtils.OK)) {
                                FileDataBean dataBean = gson.fromJson(gson.toJson(entity.getData()), FileDataBean.class);
                                com_data.add(dataBean.getPath());
                                for (int i = 0 ;i<goodsInfo.size();i++){
                                    if (current_place == i){
                                        AppraiseBean bean = goodsInfo.get(i);
                                        goodsInfo.remove(i);
                                        AppraiseBean appraiseBean = new AppraiseBean(bean.getShopCartId(),bean.getGoodsId(),bean.getIcon(),bean.getStars(),bean.getContent(),com_data);
                                        goodsInfo.add(appraiseBean);
                                    }
                                }
                                adapter.setData(goodsInfo);
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }, file, "file");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }

    public class AppraiseBean{
        private String shopCartId ;
        private String goodsId ;
        private String icon ;
        private int  stars ;
        private String content ;
        private List<String> imgs ;

        public String getShopCartId() {
            return shopCartId;
        }

        public void setShopCartId(String shopCartId) {
            this.shopCartId = shopCartId;
        }

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getStars() {
            return stars;
        }

        public void setStars(int stars) {
            this.stars = stars;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public List<String> getImgs() {
            return imgs;
        }

        public void setImgs(List<String> imgs) {
            this.imgs = imgs;
        }

        public AppraiseBean(String shopCartId, String goodsId, String icon, int stars, String content, List<String> imgs) {

            this.shopCartId = shopCartId;
            this.goodsId = goodsId;
            this.icon = icon;
            this.stars = stars;
            this.content = content;
            this.imgs = imgs;
        }
    }

    private void postAppraise() {
        List<AppraBean> goodsEvaluates = new ArrayList<>();
        AppraiseBean bean ;
        for (int i = 0 ; i<goodsInfo.size() ;i++){
            bean = goodsInfo.get(i);
            AppraBean appraiseBean = new AppraBean(bean.getShopCartId(),bean.getGoodsId(),bean.getStars(),bean.getContent(),bean.getImgs());
            goodsEvaluates.add(appraiseBean);
        }

        String type = gson.toJson(new PostAppraiseBean(getAccessToken(),orderId,service_score,persion_score,goodsEvaluates));
        OkHttpClientManager.postAsynJson(type, UrlUtils.COMMENT_ORDER_URL, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                  ParseBaseEntity entity = gson.fromJson(response,ParseBaseEntity.class);
                Toast.makeText(context,entity.getMsg(),Toast.LENGTH_SHORT).show();
                  if (entity.getCode().equals(ResponseCodeUtils.OK)){
                      EventBus.getDefault().post("refresh");
                      finish();
                  }
            }
        });
    }
}
