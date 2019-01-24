package com.yunqilai.delivery.ui.presenter.my;

import android.content.Context;
import android.content.Intent;

import com.yunqilai.delivery.model.Matter;
import com.yunqilai.delivery.ui.activity.my.DispatchMatterDetailActivity;
import com.yunqilai.delivery.ui.activity.my.ModifyNicknameActivity;
import com.yunqilai.delivery.ui.activity.my.ModifyPasswordActivity;
import com.yunqilai.delivery.ui.interlayer.my.AccountsSecurityInterlayer;
import com.yunqilai.delivery.ui.interlayer.my.DispatchMatterInterlayer;
import com.yunqilai.delivery.ui.presenter.AbsPresenter;

import java.util.ArrayList;

/**
 * Created by KK on 2017/6/13.
 */

public class AccountsSecurityPresenter extends AbsPresenter<AccountsSecurityInterlayer> {
    public AccountsSecurityPresenter(Context context, AccountsSecurityInterlayer interlayer) {
        super(context, interlayer);
    }

    public void setData(){
        interlayer.setNickname("KK");
    }

    public void modifyNickname(){
        Intent intent = new Intent(mContext, ModifyNicknameActivity.class);
        mContext.startActivity(intent);

    }
    public void modifyPassword(){
        Intent intent = new Intent(mContext, ModifyPasswordActivity.class);
        mContext.startActivity(intent);

    }


}
