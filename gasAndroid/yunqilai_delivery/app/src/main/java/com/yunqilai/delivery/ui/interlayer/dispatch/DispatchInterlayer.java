package com.yunqilai.delivery.ui.interlayer.dispatch;

import com.yunqilai.delivery.model.Order;
import com.yunqilai.delivery.ui.interlayer.BaseInterlayer;

import java.util.List;

/**
 * Created by KK on 2017/6/6.
 */

public interface DispatchInterlayer extends BaseInterlayer {
    void setDatas(List<Order.STATU> datas);
}
