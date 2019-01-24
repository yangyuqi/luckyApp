package com.yunqilai.consumer.luckyapp.HomePage.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.View.Vu;

/**
 * Created by Administrator on 2017/6/6.
 */

public class LocationManagerView implements Vu {

    private View view ;

    public TextView getTv_add() {
        return tv_add;
    }

    private ImageView iv_back ;
    private TextView tv_title,tv_add ;
    private ListView ls ;
    private RelativeLayout ll_show;

    public ImageView getIv_back() {
        return iv_back;
    }

    public ListView getLs() {

        return ls;
    }

    public RelativeLayout getLl_show() {
        return ll_show;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.location_manager_layout,null);
        iv_back = (ImageView) view.findViewById(R.id.btnBack);
        tv_title = (TextView) view.findViewById(R.id.textHeadTitle);
        tv_title.setText("地址管理");
        iv_back.setVisibility(View.VISIBLE);
        tv_add = (TextView) view.findViewById(R.id.location_manager_tv_add);
        ls = (ListView) view.findViewById(R.id.location_manager_ls);
        ll_show = (RelativeLayout) view.findViewById(R.id.ll_show);
    }

    @Override
    public View getView() {
        return view;
    }
}
