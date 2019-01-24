package com.yunqilai.consumer.luckyapp.HomePage.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.View.Vu;

/**
 * Created by Administrator on 2017/6/6.
 */

public class AddLocationView implements Vu {
    public View getAdd_addr() {
        return add_addr;
    }

    private View view ,add_addr ,view_flat;
    private Button btn ;

    private EditText edt_name ,edt_phone ,edt_details;

    public ImageView getIv_back() {
        return iv_back;
    }

    private ImageView iv_back ;
    private TextView tv_title ,tv_next ,tv_address ,tv_flat;

    public TextView getTv_title() {
        return tv_title;
    }

    public TextView getTv_next() {
        return tv_next;
    }

    public TextView getTv_address() {
        return tv_address;
    }

    public View getView_flat() {
        return view_flat;
    }

    public TextView getTv_flat() {
        return tv_flat;
    }

    public Button getBtn() {
        return btn;
    }

    public EditText getEdt_name() {
        return edt_name;
    }

    public EditText getEdt_phone() {
        return edt_phone;
    }

    public EditText getEdt_details() {
        return edt_details;
    }
    private CheckBox cb ;

    public CheckBox getCb() {
        return cb;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.add_location_layout,null);
        iv_back = (ImageView) view.findViewById(R.id.btnBack);
        tv_title = (TextView) view.findViewById(R.id.textHeadTitle);
        tv_title.setText("新增地址");
        iv_back.setVisibility(View.VISIBLE);
        cb = (CheckBox) view.findViewById(R.id.cart_rb_q);
        add_addr = view.findViewById(R.id.rl_select_position);
        tv_next = (TextView) view.findViewById(R.id.textHeadNext);
        tv_address = (TextView) view.findViewById(R.id.tv_address);
        view_flat = view.findViewById(R.id.rl_flat);
        tv_flat = (TextView) view.findViewById(R.id.tv_flat);
        btn = (Button) view.findViewById(R.id.btn_login);
        edt_name = (EditText) view.findViewById(R.id.edt_name);
        edt_phone = (EditText) view.findViewById(R.id.edt_phone);
        edt_details = (EditText) view.findViewById(R.id.edt_details_addr);
    }

    @Override
    public View getView() {
        return view;
    }
}
