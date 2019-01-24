package com.yunqilai.consumer.luckyapp.UserCenter.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.View.Vu;

/**
 * Created by Administrator on 2017/8/17.
 */

public class DiscussHistroyView implements Vu {
    private View view ;
    private ListView ls ;
    private ImageView iv_back ;
    private TextView tv_title ;

    public ListView getLs() {
        return ls;
    }

    public ImageView getIv_back() {
        return iv_back;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.discuss_histroy_activity,null);
        ls = (ListView) view.findViewById(R.id.ls);
        iv_back = (ImageView) view.findViewById(R.id.btnBack);
        tv_title = (TextView) view.findViewById(R.id.textHeadTitle);
        tv_title.setText("协商历史");
        iv_back.setVisibility(View.VISIBLE);
    }

    @Override
    public View getView() {
        return view;
    }
}
