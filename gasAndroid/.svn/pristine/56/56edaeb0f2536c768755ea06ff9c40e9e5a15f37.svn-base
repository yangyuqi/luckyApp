package com.yunqilai.delivery.ui.presenter.infomanage;

import android.content.Context;

import com.yunqilai.delivery.model.Address;
import com.yunqilai.delivery.model.Order;
import com.yunqilai.delivery.model.Tank;
import com.yunqilai.delivery.ui.interlayer.dispatch.DispatchListInterlayer;
import com.yunqilai.delivery.ui.interlayer.infomanage.InfoManageInterlayer;
import com.yunqilai.delivery.ui.interlayer.infomanage.InfoManageListInterlayer;
import com.yunqilai.delivery.ui.presenter.AbsPresenter;
import com.yunqilai.delivery.ui.view.ToastUtil;

import java.util.ArrayList;

/**
 * Created by KK on 2017/6/7.
 */

public class InfoManageListPresenter extends AbsPresenter<InfoManageListInterlayer> {
    public InfoManageListPresenter(Context context, InfoManageListInterlayer interlayer) {
        super(context, interlayer);
    }

    public void requestData(){
        ArrayList<Tank> tanks = new ArrayList<>();
        //造数据
        Tank tank = new Tank();
        tank.setBarCode("123456");
        tank.setTankCode("00000001");
        tank.setTankModel("15L");
        tank.setProduceTime("2017-06-08 15:31:00");

        tanks.add(tank);
        tanks.add(tank);
        tanks.add(tank);
        tanks.add(tank);
        tanks.add(tank);
        tanks.add(tank);
        tanks.add(tank);
        tanks.add(tank);

        interlayer.refreshDatas(tanks);
    }

}
