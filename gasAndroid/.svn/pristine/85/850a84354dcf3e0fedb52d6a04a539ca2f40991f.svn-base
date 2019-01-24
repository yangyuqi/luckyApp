package com.yunqilai.consumer.luckyapp.Common.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunqilai.consumer.R;

/**
 * Created by Administrator on 2017/5/24.
 */

public class RegisterView implements Vu {

    protected View view ;
    protected Button btn_register ,btn_code;
    private VuCallback<View> vuCallback ;
    protected EditText edt_phone , edt_pwd,edt_again_pwd ,edt_code ;
    private ImageView iv_back ;
    private TextView tv_h5 ;
    private CheckBox cb ;


    public TextView getTv_h5() {
        return tv_h5;
    }

    public CheckBox getCb() {
        return cb;
    }

    public Button getBtn_register() {
        return btn_register;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.activity_register,null);
        btn_register = (Button) view.findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vuCallback.execute(v);
            }
        });
        edt_phone = (EditText) view.findViewById(R.id.edt_login_phone);
        edt_pwd = (EditText) view.findViewById(R.id.edt_login_pwd);
        edt_again_pwd = (EditText) view.findViewById(R.id.edt_reg_pwd);
        edt_code = (EditText) view.findViewById(R.id.edt_reg_code);
        btn_code = (Button) view.findViewById(R.id.btn_code);
        btn_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vuCallback.execute(v);
            }
        });
        iv_back = (ImageView) view.findViewById(R.id.iv_back_register);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vuCallback.execute(v);
            }
        });
        tv_h5 = (TextView) view.findViewById(R.id.tv_h5);
        cb = (CheckBox) view.findViewById(R.id.cb);
    }

    @Override
    public View getView() {
        return view;
    }

    public void onClickListener(VuCallback<View> callback){
        vuCallback = callback ;
    }

    public String getPhoneNum(){
        return edt_phone.getText().toString();
    }
    public String getPwdNum(){
        return edt_pwd.getText().toString();
    }

    public String getAgainPwd(){
        return edt_again_pwd.getText().toString();
    }

    public String getCodeNum(){
        return edt_code.getText().toString();
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

    public boolean pwdIsCorrect(){
        if (getPwdNum().equals(getAgainPwd())){
            return true;
        }
        return false;
    }

    public Button getBtn_code(){
        return btn_code;
    }
}
