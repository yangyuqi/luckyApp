package com.yunqilai.consumer.luckyapp.HomePage.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.View.Vu;

/**
 * Created by Administrator on 2017/6/1.
 */

public class GoodsDetailsContentView implements Vu {

    private View view ;
    private ListView ls ;

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.goods_details_content_fragment_layout,null);
        ls = (ListView) view.findViewById(R.id.goods_details_content_ls);
    }

    @Override
    public View getView() {
        return view;
    }

    public ListView getLs(){
        return ls;
    }
}
