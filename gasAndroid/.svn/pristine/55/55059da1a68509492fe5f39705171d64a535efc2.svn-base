package com.yunqilai.consumer.luckyapp.HomePage.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.View.Vu;

/**
 * Created by Administrator on 2017/6/29.
 */

public class ChannelGoodsView implements Vu {

    private TextView title ;
    private View view ;
    private ImageView iv_back;
    private GridView gridView ;

    public TextView getTitle() {
        return title;
    }

    public ImageView getIv_back() {
        return iv_back;
    }

    public GridView getGridView() {
        return gridView;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.channel_goods_layout,null);
        title = (TextView) view.findViewById(R.id.textHeadTitle);
        iv_back = (ImageView) view.findViewById(R.id.btnBack);
        iv_back.setVisibility(View.VISIBLE);
        gridView = (GridView) view.findViewById(R.id.channel_page_view_gv);
        title.setText("推荐商品");
    }

    @Override
    public View getView() {
        return view;
    }
}
