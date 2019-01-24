package com.yunqilai.delivery.ui.interlayer.infomanage;

import com.yunqilai.delivery.model.Bean.Info.GetTankBean;
import com.yunqilai.delivery.model.Tank;
import com.yunqilai.delivery.ui.interlayer.BaseInterlayer;

/**
 * Created by KK on 2017/6/12.
 */

public interface TankDetailInterlayer extends BaseInterlayer {
    void refreshDatas(GetTankBean tank);
}
