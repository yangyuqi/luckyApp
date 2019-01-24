package com.yunqilai.delivery.ui.fragment.my;


import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.okhttp.Request;
import com.yunqilai.delivery.R;
import com.yunqilai.delivery.manager.SharedPreferencesUtil;
import com.yunqilai.delivery.model.Bean.ParseBaseEntity;
import com.yunqilai.delivery.model.Bean.my.FileDataBean;
import com.yunqilai.delivery.model.Bean.my.UpdatePhotoBean;
import com.yunqilai.delivery.model.User;
import com.yunqilai.delivery.request.base.ErrorCode;
import com.yunqilai.delivery.ui.Dialog.BottomPhotoDialog;
import com.yunqilai.delivery.ui.activity.MainActivity;
import com.yunqilai.delivery.ui.fragment.BaseFragment;
import com.yunqilai.delivery.ui.interlayer.my.MyInterlayer;
import com.yunqilai.delivery.ui.presenter.my.MyPresenter;
import com.yunqilai.delivery.utils.Event;
import com.yunqilai.delivery.utils.MyOkHttpClientManager;
import com.yunqilai.delivery.utils.ResponseUtils;
import com.yunqilai.delivery.utils.StringUtils;
import com.yunqilai.delivery.utils.UrlUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.utils.ImageCaptureManager;
import rx.functions.Action1;

import static android.app.Activity.RESULT_OK;

public class MyFragment extends BaseFragment<MyPresenter> implements MyInterlayer,View.OnClickListener {

    private TextView tv_take_photo , tv_select_photo ,tv_cancel;
    private ImageView headIv;
    private TextView nameTv;
    private TextView txt_version;
    private RelativeLayout dispatchStatisticsLayout;
    private RelativeLayout dispatchMatterLayout;
    private RelativeLayout accountsSecurityLayout;
    private RelativeLayout aboutUsLayout;
    private RelativeLayout cleanCacheLayout;
    private RelativeLayout checkUpdateLayout;
    private Button logoutBtn;
    private String type ;
    ImageCaptureManager captureManager ;

    private String name ,icon;
    private BottomPhotoDialog dialog ;


    public MyFragment() {
        // Required empty public constructor
    }

    public static MyFragment newInstance() {
        MyFragment fragment = new MyFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = (String) SharedPreferencesUtil.get(context,SharedPreferencesUtil.role,"");
        icon = (String) SharedPreferencesUtil.get(context,SharedPreferencesUtil.icon,"");
        if (getArguments() != null) {
        }

        presenter = new MyPresenter(getActivity(),this);
    }

    @Override
    public void onResume() {
        super.onResume();
        name = (String) SharedPreferencesUtil.get(context,SharedPreferencesUtil.userName,"");
        nameTv.setText(name);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            icon = (String) SharedPreferencesUtil.get(context,SharedPreferencesUtil.icon,"");
            Glide.with(context).load(UrlUtils.PHOTO_URL_BASE+icon).placeholder(R.mipmap.icon_logo4).into(headIv);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);

        initView(view);
        initEvent();

        return view;
    }

    private void initView(View view){

        headIv = (ImageView)view.findViewById(R.id.iv_head_icon);
        nameTv = (TextView)view.findViewById(R.id.tv_name);
        txt_version = (TextView) view.findViewById(R.id.txt_version);
        dispatchStatisticsLayout = (RelativeLayout)view.findViewById(R.id.rl_dispatch_statistics);
        dispatchMatterLayout = (RelativeLayout)view.findViewById(R.id.rl_dispatch_matter);
        accountsSecurityLayout = (RelativeLayout)view.findViewById(R.id.rl_accounts_and_security);
        aboutUsLayout = (RelativeLayout)view.findViewById(R.id.rl_about_us);
        cleanCacheLayout = (RelativeLayout)view.findViewById(R.id.rl_clean_cache);
        checkUpdateLayout = (RelativeLayout)view.findViewById(R.id.rl_check_update);
        logoutBtn = (Button)view.findViewById(R.id.btn_logout);
        if (!type.equals("delivery")){
            dispatchStatisticsLayout.setVisibility(View.GONE);
            dispatchMatterLayout.setVisibility(View.GONE);
        }
        Glide.with(context).load(UrlUtils.PHOTO_URL_BASE+icon).placeholder(R.mipmap.icon_logo4).into(headIv);

        txt_version.setText("当前版本v"+ StringUtils.getAppVersionName(getActivity()));
    }


    private void initEvent(){
        dispatchStatisticsLayout.setOnClickListener(this);
        dispatchMatterLayout.setOnClickListener(this);
        accountsSecurityLayout.setOnClickListener(this);
        aboutUsLayout.setOnClickListener(this);
        cleanCacheLayout.setOnClickListener(this);
        checkUpdateLayout.setOnClickListener(this);
        logoutBtn.setOnClickListener(this);

        headIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new BottomPhotoDialog(context, R.layout.view_popupwindow);
                dialog.show();
                initDialog(dialog.getView());
            }
        });
    }

    @Override
    public void setUserInfo(User user) {

    }

    @Override
    public void setVersion(String version) {

    }

    @Override
    public void toLogin() {
        String phone = (String) SharedPreferencesUtil.get(context,SharedPreferencesUtil.mobile,"");
        SharedPreferencesUtil.clear(context);
        SharedPreferencesUtil.put(context,SharedPreferencesUtil.isFristUse,false);
        SharedPreferencesUtil.put(context,SharedPreferencesUtil.mobile,phone);
        ((MainActivity)context).finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
//            case R.id.iv_head_icon:
////                presenter.modifyHeadIcon();
//                dialog = new BottomPhotoDialog(context, R.layout.view_popupwindow);
//                dialog.show();
//                initDialog(dialog.getView());
//                break;
            case R.id.rl_dispatch_statistics:
                presenter.enterDispatchStatistics();
                break;
            case R.id.rl_dispatch_matter:
                presenter.enterDispatchMatter();
                break;
            case R.id.rl_accounts_and_security:
                presenter.enterAccountsSecurity();
                break;
            case R.id.rl_about_us:
                presenter.enterAboutUs();
                break;
            case R.id.rl_clean_cache:
                presenter.cleanCache();
                break;
            case R.id.rl_check_update:
                presenter.checkUpdate();
                break;
            case R.id.btn_logout:
                //presenter.logout();
                EventBus.getDefault().post(Event.EXITANDLOGIN);
                break;
            case R.id.tv_pick_phone :
                takePhoto();
                dialog.dismiss();
                break;

            case R.id.tv_pick_zone :
                selectPhoto();
                dialog.dismiss();
                break;
            case R.id.tv_cancel:
                dialog.dismiss();
                break;
        }
    }

    private void initDialog(View view) {
        tv_take_photo = (TextView) view.findViewById(R.id.tv_pick_phone);
        tv_select_photo = (TextView) view.findViewById(R.id.tv_pick_zone);
        tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
        tv_take_photo.setOnClickListener(this);
        tv_select_photo.setOnClickListener(this);
    }

    private void selectPhoto() {
                    //选择相册
                    PhotoPicker.builder()
                            .setPhotoCount(1)
                            .setShowCamera(true)
                            .setShowGif(true)
                            .setPreviewEnabled(false)
                            .start(context, MyFragment.this ,PhotoPicker.REQUEST_CODE);
    }

    private void takePhoto() {
                    captureManager = new ImageCaptureManager(context);
                    Intent intent = null;
                    try {
                        intent = captureManager.dispatchTakePictureIntent();
                        startActivityForResult(intent, ImageCaptureManager.REQUEST_TAKE_PHOTO);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            switch (requestCode){
                // 拍照
                case ImageCaptureManager.REQUEST_TAKE_PHOTO:
                    if(captureManager.getCurrentPhotoPath() != null) {
                        captureManager.galleryAddPic();
                        // 照片地址
                        String imagePaht = captureManager.getCurrentPhotoPath();
                        try {
                            updatePhoto(imagePaht);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
        }
        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE){
            if (data != null) {
                ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                String imagePaht = photos.get(0) ;
                try {
                    updatePhoto(imagePaht);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void updatePhoto(String path) throws IOException {
        if (path!=null) {
            Glide.with(context).load(path).into(headIv);
            File file = new File(path);
            MyOkHttpClientManager.postAsyn(UrlUtils.UPLOAD_PHOTO_URL, new MyOkHttpClientManager.StringCallback() {
                @Override
                public void onFailure(Request request, IOException e) {

                }

                @Override
                public void onResponse(String response) {
                    ParseBaseEntity entity = gson.fromJson(response,ParseBaseEntity.class);
                    if (entity.getCode().equals(ResponseUtils.OK)){
                        FileDataBean dataBean = gson.fromJson(gson.toJson(entity.getData()),FileDataBean.class);
                        EditUserPhoto(dataBean.getPath());
                    }else if(entity.getCode().equals(ErrorCode.ERR_ACCESS_TOKEN_INVALID) || entity.getCode().equals(ErrorCode.ERR_REFRESH_TOKEN_INVALID)){
                        EventBus.getDefault().post(Event.EXITANDLOGIN);
                    }
                }
            },file,"file");
        }
    }

    private void EditUserPhoto(final String path) {
        MyOkHttpClientManager.postAsynJson(gson.toJson(new UpdatePhotoBean(getAccessToken(), path)), UrlUtils.EDIT_USER_PHOTO_URL, new MyOkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Toast.makeText(context,R.string.service_error_500,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {
                try {
                    ParseBaseEntity entity = gson.fromJson(response, ParseBaseEntity.class);
                    if (entity.getCode().equals(ResponseUtils.OK)) {
                        //Glide.with(context).load(UrlUtils.PHOTO_URL_BASE + path).into(headIv);
                        SharedPreferencesUtil.put(context, SharedPreferencesUtil.icon, path);
                    }else if(entity.getCode().equals(ErrorCode.ERR_ACCESS_TOKEN_INVALID) || entity.getCode().equals(ErrorCode.ERR_REFRESH_TOKEN_INVALID)){
                        EventBus.getDefault().post(Event.EXITANDLOGIN);
                    }
                    Toast.makeText(context, entity.getMsg(), Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(context,R.string.service_error_500,Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }

}
