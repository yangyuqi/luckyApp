package com.yunqilai.consumer.luckyapp.HomePage.Presenter;

import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.Base.BasePresenterFragment;
import com.yunqilai.consumer.luckyapp.Common.Utils.adapter.CommonAdapter;
import com.yunqilai.consumer.luckyapp.Common.Utils.adapter.ViewHolder;
import com.yunqilai.consumer.luckyapp.HomePage.View.GoodsDetailsDiscussView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/1.
 */

public class GoodsDetailsDiscussFragment extends BasePresenterFragment<GoodsDetailsDiscussView> {

    private CommonAdapter<String> adapter ;
    private List<String> data = new ArrayList<>();

    @Override
    public Class<GoodsDetailsDiscussView> getViewClass() {
        return GoodsDetailsDiscussView.class;
    }

    @Override
    public void bindView() {
        super.bindView();

        data.add("杨要去");data.add("yyyyyyy");data.add("加速度计");

        adapter = new CommonAdapter<String>(context,data, R.layout.goods_discuss_ls_item) {
            @Override
            public void convert(ViewHolder helper, String item) {
                    helper.setText(R.id.discuss_tv_name,item);
            }
        };

        vu.getLs().setAdapter(adapter);
    }



}
