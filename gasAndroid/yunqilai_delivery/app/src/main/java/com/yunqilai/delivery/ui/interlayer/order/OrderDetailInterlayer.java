package com.yunqilai.delivery.ui.interlayer.order;

import com.yunqilai.delivery.model.Bean.ParseOrderDetailsBean;
import com.yunqilai.delivery.model.Order;
import com.yunqilai.delivery.model.Product;
import com.yunqilai.delivery.ui.interlayer.BaseInterlayer;

import java.util.List;

/**
 * Created by KK on 2017/6/12.
 */

public interface OrderDetailInterlayer extends BaseInterlayer {
    void refreshDatas(ParseOrderDetailsBean order);
//    void newRefreshDatas();
}
