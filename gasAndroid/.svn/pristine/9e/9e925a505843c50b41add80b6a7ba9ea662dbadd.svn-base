package com.yunqilai.consumer.luckyapp.UserCenter.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.View.Vu;
import com.yunqilai.consumer.luckyapp.Common.View.VuCallback;

/**
 * Created by yangyuqi on 17-7-31.
 */

public class EditUserAuthenView implements Vu ,View.OnClickListener{
    protected View view ;
    protected TextView tv_title ,tv_next ,tv_again ,tv_again_tv;
    private ImageView iv_back ;
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

    public TextView getTv_again() {
        return tv_again;
    }

    public TextView getTv_again_tv() {
        return tv_again_tv;
    }

    public ImageView getImg_name() {
        return img_name;
    }

    public ImageView getImg_body() {
        return img_body;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.edit_user_authen_layout,null);
        tv_title = (TextView) view.findViewById(R.id.textHeadTitle);
        tv_next = (TextView) view.findViewById(R.id.textHeadNext);
        iv_back = (ImageView) view.findViewById(R.id.btnBack);
        iv_back.setVisibility(View.VISIBLE);
        tv_title.setText("实名认证修改");
        tv_next.setVisibility(View.GONE);
        btn_send = (Button) view.findViewById(R.id.btn_confrim_commit);
        btn_send.setOnClickListener(this);
        edt_body = (EditText) view.findViewById(R.id.editText_body);
        edt_name = (EditText) view.findViewById(R.id.edt_name);
        iv_photo = (ImageView) view.findViewById(R.id.iv_photo);
        iv_iv_photo = (ImageView) view.findViewById(R.id.iv_iv_photo);
        tv_again = (TextView) view.findViewById(R.id.tv_again);
        tv_again_tv = (TextView) view.findViewById(R.id.tv_again_tv);

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
