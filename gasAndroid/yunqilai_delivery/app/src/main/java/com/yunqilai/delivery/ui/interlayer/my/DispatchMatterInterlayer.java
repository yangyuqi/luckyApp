package com.yunqilai.delivery.ui.interlayer.my;

import com.yunqilai.delivery.model.Bean.my.ArticleBean;
import com.yunqilai.delivery.model.Matter;
import com.yunqilai.delivery.ui.interlayer.BaseInterlayer;

import java.util.List;

/**
 * Created by KK on 2017/6/13.
 */

public interface DispatchMatterInterlayer extends BaseInterlayer {
    void refreshDatas(List<ArticleBean> matterList);
}
