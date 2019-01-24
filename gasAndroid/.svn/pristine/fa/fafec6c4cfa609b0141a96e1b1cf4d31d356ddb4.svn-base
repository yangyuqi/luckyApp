package com.yunqilai.consumer.luckyapp.Common.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.yunqilai.consumer.R;

/**
 * Created by Administrator on 2017/5/24.
 */

public class FindPwdView implements Vu {

    protected View view ;
    private ImageView iv_back ;
    private Button btn_code ,btn_confirm;

    private EditText edt_phone ,edt_pwd ,edt_again_pwd ,edt_code;

    public EditText getEdt_phone() {
        return edt_phone;
    }

    public EditText getEdt_pwd() {
        return edt_pwd;
    }

    public EditText getEdt_again_pwd() {
        return edt_again_pwd;
    }

    public EditText getEdt_code() {
        return edt_code;
    }

    public Button getBtn_confirm() {
        return btn_confirm;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.activity_find_pwd,null);
        iv_back = (ImageView) view.findViewById(R.id.iv_back_register);
        btn_code = (Button) view.findViewById(R.id.btn_send_code);
        edt_phone = (EditText) view.findViewById(R.id.edt_login_phone);
        edt_pwd = (EditText) view.findViewById(R.id.edt_login_pwd);
        edt_again_pwd = (EditText) view.findViewById(R.id.edt_reg_pwd);
        edt_code = (EditText) view.findViewById(R.id.edt_reg_code);
        btn_confirm = (Button) view.findViewById(R.id.btn_findpwd);

    }

    @Override
    public View getView() {
        return view;
    }

    public ImageView getIv_back(){
        return iv_back;
    }

    public Button getBtn_code(){
        return btn_code ;
    }
}
