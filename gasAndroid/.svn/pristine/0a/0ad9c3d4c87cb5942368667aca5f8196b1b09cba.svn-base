package com.yunqilai.consumer.luckyapp.HomePage.Presenter;

import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.Base.BasePresenterFragment;
import com.yunqilai.consumer.luckyapp.Common.Utils.adapter.CommonAdapter;
import com.yunqilai.consumer.luckyapp.Common.Utils.adapter.ViewHolder;
import com.yunqilai.consumer.luckyapp.HomePage.View.GoodsDetailsContentView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/1.
 */

public class GoodsDetailsContentFragment extends BasePresenterFragment<GoodsDetailsContentView> {

    private List<Integer> data = new ArrayList<>();
    private CommonAdapter<Integer> adapter ;

    @Override
    public Class<GoodsDetailsContentView> getViewClass() {
        return GoodsDetailsContentView.class;
    }

    @Override
    public void bindView() {
        super.bindView();

        data.clear();
        data.add(R.drawable.logo);
        data.add(R.drawable.ic_launcher);
        data.add(R.drawable.logo);
        data.add(R.drawable.ic_launcher);

        adapter = new CommonAdapter<Integer>(context,data,R.layout.goods_content_ls_item) {
            @Override
            public void convert(ViewHolder helper, Integer item) {
                helper.setImageResource(R.id.goods_details_ls_item_iv,item);
            }
        };

        vu.getLs().setAdapter(adapter);
    }
}
