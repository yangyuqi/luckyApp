package com.yunqilai.consumer.luckyapp.Common.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunqilai.consumer.R;

/**
 * Created by Administrator on 2017/5/24.
 */

public class ConfirmUserView implements Vu ,View.OnClickListener{

    protected View view ,view_photo;
    protected TextView tv_title ,tv_next ,tv_photo_one ,tv_photo_two;
    private ImageView iv_back ,imageView ,imageView_two;
    protected Button btn_send ;
    protected VuCallback<View> vuCallback ;
    private EditText edt_name ,edt_body ;
    private ImageView iv_photo ,iv_iv_photo,img_name,img_body ;

    public ImageView getIv_photo() {
        return iv_photo;
    }

    public ImageView getIv_iv_photo() {
        return iv_iv_photo;
    }
    public ImageView getImageView() {
        return imageView;
    }

    public ImageView getImageView_two() {
        return imageView_two;
    }

    public TextView getTv_photo_two() {
        return tv_photo_two;
    }

    public View getView_photo() {
        return view_photo;
    }

    public TextView getTv_title() {
        return tv_title;
    }

    public TextView getTv_photo_one() {
        return tv_photo_one;
    }
    public ImageView getImg_name() {
        return img_name;
    }

    public ImageView getImg_body() {
        return img_body;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.activity_confirm_user,null);
        tv_title = (TextView) view.findViewById(R.id.textHeadTitle);
        tv_next = (TextView) view.findViewById(R.id.textHeadNext);
        iv_back = (ImageView) view.findViewById(R.id.btnBack);
        iv_back.setVisibility(View.VISIBLE);
        tv_title.setText("实名认证");
        tv_next.setVisibility(View.VISIBLE);
        btn_send = (Button) view.findViewById(R.id.btn_confrim_commit);
        btn_send.setOnClickListener(this);
        edt_body = (EditText) view.findViewById(R.id.editText_body);
        edt_name = (EditText) view.findViewById(R.id.edt_name);
        iv_photo = (ImageView) view.findViewById(R.id.iv_photo);
        iv_iv_photo = (ImageView) view.findViewById(R.id.iv_iv_photo);

        imageView_two = (ImageView) view.findViewById(R.id.imageView_two);
        imageView = (ImageView) view.findViewById(R.id.imageView);
        view_photo = view.findViewById(R.id.ll_show);
        tv_photo_one = (TextView) view.findViewById(R.id.tv_photo_one);
        tv_photo_two = (TextView) view.findViewById(R.id.tv_photo_two);

        img_name = (ImageView) view.findViewById(R.id.img_name);
        img_body = (ImageView) view.findViewById(R.id.img_body);
    }

    @Override
    public View getView() {
        return view;
    }

    @Override
    public void onClick(View v) {
        vuCallback.execute(v);
    }

    public void setOnClickListener(VuCallback<View> callback){
        vuCallback = callback ;
    }

    public TextView getTv_next() {
        return tv_next;
    }

    public ImageView getIv_back() {
        return iv_back;
    }

    public Button getBtn_send() {
        return btn_send;
    }

    public EditText getEdt_name() {
        return edt_name;
    }

    public EditText getEdt_body() {
        return edt_body;
    }
}
