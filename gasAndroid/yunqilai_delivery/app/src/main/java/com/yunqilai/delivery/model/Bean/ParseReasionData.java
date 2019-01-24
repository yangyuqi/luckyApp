package com.yunqilai.delivery.model.Bean;

import java.util.List;

/**
 * Created by yangyuqi on 17-8-1.
 */

public class ParseReasionData {
    private List<ReasonInfoBean> list ;

    public List<ReasonInfoBean> getList() {
        return list;
    }

    public void setList(List<ReasonInfoBean> list) {
        this.list = list;
    }

    public ParseReasionData(List<ReasonInfoBean> list) {

        this.list = list;
    }
}
