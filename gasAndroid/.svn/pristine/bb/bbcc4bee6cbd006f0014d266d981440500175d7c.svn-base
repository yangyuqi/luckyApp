package com.yunqilai.delivery.ui.presenter.infomanage;

import android.content.Context;
import android.content.Intent;

import com.yunqilai.delivery.manager.SharedPreferencesUtil;
import com.yunqilai.delivery.model.Tank;
import com.yunqilai.delivery.ui.activity.common.ScanCodeActivity;
import com.yunqilai.delivery.ui.activity.infomanage.SearchTankActivity;
import com.yunqilai.delivery.ui.interlayer.infomanage.InfoManageInterlayer;
import com.yunqilai.delivery.ui.presenter.AbsPresenter;
import com.yunqilai.delivery.ui.view.ToastUtil;

import java.util.ArrayList;

/**
 * Created by KK on 2017/6/6.
 */

public class InfoManagePresenter extends AbsPresenter<InfoManageInterlayer> {
    private String type ;
    public InfoManagePresenter(Context context, InfoManageInterlayer interlayer) {
        super(context, interlayer);
        type = (String) SharedPreferencesUtil.get(context,SharedPreferencesUtil.role,"");
    }

    public void onCreateView(){

        ArrayList<Tank.STATU> status = new ArrayList<>();
        if(!type.equals("delivery")){
            status.add(Tank.STATU.IN_DEPOT);
            status.add(Tank.STATU.ENTER_DEPOT);
            status.add(Tank.STATU.OUT_DEPOT);
            status.add(Tank.STATU.IN_CHECK);
        }else{
            status.add(Tank.STATU.IN_DELIVERY);
            status.add(Tank.STATU.IN_USE);
            status.add(Tank.STATU.IN_USE);
        }

        interlayer.setDatas(status);
    }

    public void scan(){
        Intent intent = new Intent(mContext, ScanCodeActivity.class);
        mContext.startActivity(intent);
    }

    public void search(){
        Intent intent = new Intent(mContext, SearchTankActivity.class);
        mContext.startActivity(intent);
    }
}
