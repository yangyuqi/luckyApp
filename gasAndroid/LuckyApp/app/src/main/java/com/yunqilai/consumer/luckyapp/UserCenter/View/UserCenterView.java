package com.yunqilai.consumer.luckyapp.UserCenter.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.View.Vu;

/**
 * Created by Administrator on 2017/5/24.
 */

public class UserCenterView implements Vu {

    protected View view ,view_order ,view_photo ,view_safe ,view_addr ,view_confrim ,view_about ,view_update ,view_clear;

    protected View view_one ,view_two ,view_three,ｖiew_four ;
    private ScrollView sv ;
    private TextView tv_name,txt_version ;
    private Button btn_loginout ;
    private ImageView iv_photo ;

    private TextView tv_tv_auth ;

    private TextView tv_one,tv_two,tv_three,tv_four;

    public View getView_order() {
        return view_order;
    }

    public View getView_photo() {
        return view_photo;
    }

    public View getView_safe() {
        return view_safe;
    }

    public View getView_addr() {
        return view_addr;
    }

    public View getView_confrim() {
        return view_confrim;
    }

    public TextView getTv_name() {
        return tv_name;
    }

    public Button getBtn_loginout() {
        return btn_loginout;
    }

    public View getView_about() {
        return view_about;
    }

    public View getView_update() {
        return view_update;
    }

    public View getView_clear() {
        return view_clear;
    }

    public View getView_one() {
        return view_one;
    }

    public View getView_two() {
        return view_two;
    }

    public View getView_three() {
        return view_three;
    }

    public View getＶiew_four() {
        return ｖiew_four;
    }

    public TextView getTv_one() {
        return tv_one;
    }

    public TextView getTv_two() {
        return tv_two;
    }

    public TextView getTv_three() {
        return tv_three;
    }

    public TextView getTv_four() {
        return tv_four;
    }

    public ImageView getIv_photo() {
        return iv_photo;
    }

    public TextView getTv_tv_auth() {
        return tv_tv_auth;
    }

    public TextView getTxt_version() {
        return txt_version;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
            view = inflater.inflate(R.layout.user_center_layout,null);
            view_order = view.findViewById(R.id.user_rl_order);
            view_photo = view.findViewById(R.id.ll_photo);
            view_safe = view.findViewById(R.id.rl_safe);
            view_addr = view.findViewById(R.id.rl_select_addr_);
            view_confrim = view.findViewById(R.id.rl_confrim);
            sv = (ScrollView) view.findViewById(R.id.sv);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            btn_loginout = (Button) view.findViewById(R.id.btn_register);
            view_about = view.findViewById(R.id.rl_about_us);
            view_update = view.findViewById(R.id.rl_update);
            txt_version = (TextView) view.findViewById(R.id.txt_version);
            view_clear = view.findViewById(R.id.rl_clear);

            view_one = view.findViewById(R.id.view_rl_1);
            view_two = view.findViewById(R.id.view_rl_2);
            view_three = view.findViewById(R.id.view_rl_3);
            ｖiew_four = view.findViewById(R.id.view_rl_4);


            tv_one = (TextView) view.findViewById(R.id.tv_tv_one);
            tv_two = (TextView) view.findViewById(R.id.tv_tv_two);
            tv_three = (TextView) view.findViewById(R.id.tv_tv_three);
            tv_four = (TextView) view.findViewById(R.id.tv_tv_four);

            iv_photo = (ImageView) view.findViewById(R.id.iv_iv_photo);
            tv_tv_auth = (TextView) view.findViewById(R.id.tv_tv_auth);
    }

    @Override
    public View getView() {
        return view;
    }

    public void scrollTo(){
        sv.smoothScrollTo(0,0);
    }
}
