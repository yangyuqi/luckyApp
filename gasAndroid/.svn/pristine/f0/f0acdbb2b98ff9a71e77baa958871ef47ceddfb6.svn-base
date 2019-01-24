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
 * Created by Administrator on 2017/6/6.
 */

public class SearchResultView implements Vu {
    private View view ;

    public ImageView getIv_back() {
        return iv_back;
    }

    private GridView gv ;
    private ImageView iv_back ;
    private TextView tv_title ;
    private View no_data ;

    public View getNo_data() {
        return no_data;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.search_result_layout,null);
        gv = (GridView) view.findViewById(R.id.search_result_gv);
        iv_back = (ImageView) view.findViewById(R.id.btnBack);
        tv_title = (TextView) view.findViewById(R.id.textHeadTitle);
        tv_title.setText("煤气");
        iv_back.setVisibility(View.VISIBLE);
        no_data = view.findViewById(R.id.ll_show);
    }

    @Override
    public View getView() {
        return view;
    }

    public GridView getGv(){
        return gv;
    }
}
