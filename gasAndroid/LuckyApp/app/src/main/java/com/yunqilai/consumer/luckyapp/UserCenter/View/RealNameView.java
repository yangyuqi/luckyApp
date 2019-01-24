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

/**
 * Created by Administrator on 2017/6/12.
 */

public class RealNameView implements Vu {

    View view ;
    private ImageView iv_back ;
    private TextView tv_title , tv_content ;
    private TextView edt_name ,edt_num ;
    private ImageView iv_front ,iv_iv_back ,iv_icon;

    private View view_ll_show ,view_rl_show;

    private TextView rl_tv_show ;
    private Button rl_btn_show ;

    public ImageView getIv_back() {
        return iv_back;
    }

    public TextView getEdt_name() {
        return edt_name;
    }

    public TextView getEdt_num() {
        return edt_num;
    }

    public ImageView getIv_front() {
        return iv_front;
    }

    public ImageView getIv_iv_back() {
        return iv_iv_back;
    }

    public TextView getTv_content() {
        return tv_content;
    }

    public ImageView getIv_icon() {
        return iv_icon;
    }

    public View getView_ll_show() {
        return view_ll_show;
    }

    public View getView_rl_show() {
        return view_rl_show;
    }

    public TextView getRl_tv_show() {
        return rl_tv_show;
    }

    public Button getRl_btn_show() {
        return rl_btn_show;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.real_name_layout,null);
        iv_back = (ImageView) view.findViewById(R.id.btnBack);
        tv_title = (TextView) view.findViewById(R.id.textHeadTitle);
        tv_title.setText("实名认证");
        iv_back.setVisibility(View.VISIBLE);
        iv_iv_back = (ImageView) view.findViewById(R.id.iv_iv_back);
        iv_front = (ImageView) view.findViewById(R.id.iv_iv_front);
        edt_name = (TextView) view.findViewById(R.id.edt_edt_name);
        edt_num = (TextView) view.findViewById(R.id.editText_num);
        iv_icon = (ImageView) view.findViewById(R.id.iv_iv_icon);
        tv_content = (TextView) view.findViewById(R.id.tv_tv_content);
        view_ll_show = view.findViewById(R.id.ll_b);
        view_rl_show = view.findViewById(R.id.rl_show);
        rl_tv_show = (TextView) view.findViewById(R.id.tv_reasion);
        rl_btn_show = (Button) view.findViewById(R.id.btn);
    }

    @Override
    public View getView() {
        return view;
    }
}
