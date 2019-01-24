package com.yunqilai.consumer.luckyapp.Common.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunqilai.consumer.R;


/**
 * Created by Administrator on 2017/5/23.
 */

public class LoginView implements Vu ,View.OnClickListener{

    public View view;
    protected EditText edt_phone,edt_pwd;
    protected Button btn_login;
    protected TextView tv_forget_pwd,tv_register_user;
    private VuCallback<View> viewVuCallback;
    private ImageView iv_back ;

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.activity_login,null);
        edt_phone = (EditText) view.findViewById(R.id.edt_login_phone);
        edt_pwd = (EditText) view.findViewById(R.id.edt_login_pwd);
        btn_login = (Button) view.findViewById(R.id.btn_login);
        tv_forget_pwd = (TextView) view.findViewById(R.id.tv_forget_pwd);
        tv_register_user = (TextView) view.findViewById(R.id.tv_register);
        iv_back = (ImageView) view.findViewById(R.id.iv_back_register);
        btn_login.setOnClickListener(this);
        tv_forget_pwd.setOnClickListener(this);
        tv_register_user.setOnClickListener(this);
    }

    @Override
    public View getView() {
        return view;
    }

    @Override
    public void onClick(View v) {
        viewVuCallback.execute(v);
    }

    public void setOnClickListener(VuCallback<View> callback){
        viewVuCallback = callback ;
    }

    public String getPhoneNum(){
        return edt_phone.getText().toString();
    }

    public String getPwdNum(){
        return edt_pwd.getText().toString();
    }

    public ImageView getIv_back() {
        return iv_back;
    }

    public EditText getEdt_phone() {
        return edt_phone;
    }
}
