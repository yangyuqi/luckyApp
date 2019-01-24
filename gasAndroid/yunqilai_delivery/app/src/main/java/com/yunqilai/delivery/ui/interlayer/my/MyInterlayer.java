package com.yunqilai.delivery.ui.interlayer.my;

import com.yunqilai.delivery.model.User;
import com.yunqilai.delivery.ui.interlayer.BaseInterlayer;

/**
 * Created by KK on 2017/6/8.
 */

public interface MyInterlayer extends BaseInterlayer {
    void setUserInfo(User user);
    void setVersion(String version);
    void toLogin();
}
