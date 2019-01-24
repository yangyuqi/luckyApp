package com.yunqilai.consumer.luckyapp.UserCenter.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.View.Vu;

/**
 * Created by Administrator on 2017/6/12.
 */

public class AccountSafeView implements Vu {

    private View view ,view_ni ,view_pwd;
    private ImageView iv_back ;
    private TextView tv_title ,tv_name;

    public ImageView getIv_back() {
        return iv_back;
    }

    public View getView_ni() {
        return view_ni;
    }

    public View getView_pwd() {
        return view_pwd;
    }

    public TextView getTv_name() {
        return tv_name;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.account_safe_layout,null);
        view_ni = view.findViewById(R.id.rl_nicheng);
        view_pwd = view.findViewById(R.id.rl_pwd);
        iv_back = (ImageView) view.findViewById(R.id.btnBack);
        tv_title = (TextView) view.findViewById(R.id.textHeadTitle);
        tv_title.setText("账户与安全");
        iv_back.setVisibility(View.VISIBLE);
        tv_name = (TextView) view.findViewById(R.id.tv_nickName);

    }

    @Override
    public View getView() {
        return view;
    }
}
