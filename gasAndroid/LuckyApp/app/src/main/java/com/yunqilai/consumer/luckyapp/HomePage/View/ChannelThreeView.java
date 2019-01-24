package com.yunqilai.consumer.luckyapp.HomePage.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.View.Vu;

/**
 * Created by Administrator on 2017/6/29.
 */

public class ChannelThreeView implements Vu {

    private View view ;
    private ListView ls ;
    private TextView title ;
    private ImageView iv_back ;

    public ListView getLs() {
        return ls;
    }

    public ImageView getIv_back() {
        return iv_back;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.channel_three_layout,null);
        title = (TextView) view.findViewById(R.id.textHeadTitle);
        title.setText("详情");
        iv_back = (ImageView) view.findViewById(R.id.btnBack);
        iv_back.setVisibility(View.VISIBLE);
        ls = (ListView) view.findViewById(R.id.channel_three_ls);
    }

    @Override
    public View getView() {
        return view;
    }
}
