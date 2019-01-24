package com.yunqilai.delivery.ui.interlayer.order;

import com.yunqilai.delivery.model.Bean.OrderDetailsBean;
import com.yunqilai.delivery.model.Order;
import com.yunqilai.delivery.ui.interlayer.BaseInterlayer;

import java.util.List;

/**
 * Created by KK on 2017/6/12.
 */

public interface SearchOrderResultInterlayer extends BaseInterlayer {
    void refreshDatas(List<OrderDetailsBean> orderList);
}
