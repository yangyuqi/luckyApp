package com.yunqilai.consumer.luckyapp.HomePage.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.View.Vu;

/**
 * Created by Administrator on 2017/6/3.
 */

public class BottleInfoView implements Vu {

    private View view ;
    private ImageView iv_back ;
    private TextView tv_title ,tv_name ,tv_phone ,tv_time ,tv_time_time ,tv_count ,tv_id ,tv_id_id,tv_model ,tv_create_time ,tv_uses_num ,tv_num_id,tv_address;

    public ImageView getIv_back() {
        return iv_back;
    }

    public TextView getTv_name() {
        return tv_name;
    }

    public TextView getTv_phone() {
        return tv_phone;
    }

    public TextView getTv_time() {
        return tv_time;
    }

    public TextView getTv_time_time() {
        return tv_time_time;
    }

    public TextView getTv_count() {
        return tv_count;
    }

    public TextView getTv_id() {
        return tv_id;
    }

    public TextView getTv_id_id() {
        return tv_id_id;
    }

    public TextView getTv_model() {
        return tv_model;
    }

    public TextView getTv_create_time() {
        return tv_create_time;
    }

    public TextView getTv_uses_num() {
        return tv_uses_num;
    }

    public TextView getTv_title() {
        return tv_title;
    }

    public TextView getTv_num_id() {
        return tv_num_id;
    }

    public TextView getTv_address() {
        return tv_address;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.bottle_info_activity,null);
        iv_back = (ImageView) view.findViewById(R.id.btnBack);
        iv_back.setVisibility(View.VISIBLE);
        tv_title = (TextView) view.findViewById(R.id.textHeadTitle);
        tv_title.setText("瓶罐信息");
        tv_name = (TextView) view.findViewById(R.id.tv_name_gas);
        tv_phone = (TextView) view.findViewById(R.id.tv_phone_gas);
        tv_time = (TextView) view.findViewById(R.id.tv_time_gas);
        tv_time_time = (TextView) view.findViewById(R.id.tv_time_time_gas);
        tv_count = (TextView) view.findViewById(R.id.tv_num_gas);
        tv_id = (TextView) view.findViewById(R.id.tv_gas_num);
        tv_id_id = (TextView) view.findViewById(R.id.tv_gas_count);
        tv_model = (TextView) view.findViewById(R.id.tv_gas_model);
        tv_create_time = (TextView) view.findViewById(R.id.tv_gas_produce);
        tv_uses_num = (TextView) view.findViewById(R.id.tv_gas_use_num);
        tv_address= (TextView) view.findViewById(R.id.tv_address_gas);
        tv_num_id= (TextView) view.findViewById(R.id.tv_id_gas);
    }

    @Override
    public View getView() {
        return view;
    }
}
