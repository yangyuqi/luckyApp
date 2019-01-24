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

public class RePwdView implements Vu {

    private View view ;
    private ImageView iv_back ;
    private TextView tv_title ;
    private EditText edt_one ,edt_two ,edt_old;

    private Button btn ;

    public ImageView getIv_back() {
        return iv_back;
    }

    public EditText getEdt_one() {
        return edt_one;
    }

    public EditText getEdt_two() {
        return edt_two;
    }

    public EditText getEdt_old() {
        return edt_old;
    }

    public Button getBtn() {
        return btn;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.re_pwd_layout,null);
        iv_back = (ImageView) view.findViewById(R.id.btnBack);
        tv_title = (TextView) view.findViewById(R.id.textHeadTitle);
        tv_title.setText("修改密码");
        iv_back.setVisibility(View.VISIBLE);
        edt_one = (EditText) view.findViewById(R.id.edt_pwd_one);
        edt_two = (EditText) view.findViewById(R.id.edt_pwd_two);
        edt_old = (EditText) view.findViewById(R.id.edt_old_pwd);
        btn = (Button) view.findViewById(R.id.btn_register);
    }

    @Override
    public View getView() {
        return view;
    }

    public boolean PwdIsSame(){
        if (edt_one.getText().toString().equals(edt_two.getText().toString())){
            return true ;
        }
        return false ;
    }
}
