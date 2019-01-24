package com.yunqilai.delivery.ui.presenter;

import android.content.Context;

import com.yunqilai.delivery.ui.interlayer.FindPwdInterlayer;

/**
 * Created by KK on 2017/6/6.
 */

public class FindPwdPresenter extends AbsPresenter<FindPwdInterlayer> {

    public FindPwdPresenter(Context context, FindPwdInterlayer interlayer) {
        super(context, interlayer);
    }

    public void changePwd(String userName, String newPwd,String code){
        interlayer.toLogin();
    }
}
