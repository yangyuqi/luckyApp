package com.yunqilai.consumer.luckyapp.UserCenter.View;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.hedgehog.ratingbar.RatingBar;
import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.View.Vu;

/**
 * Created by Administrator on 2017/6/9.
 */

public class AppraiseView implements Vu {

    private View view ;
    private ListView ls ;
    private ImageView iv_back ;
    private TextView tv_title ,tv_next ;

    private RatingBar bar_one ,bar_two ;

    public ListView getLs() {
        return ls;
    }

    public RatingBar getBar_one() {
        return bar_one;
    }

    public RatingBar getBar_two() {
        return bar_two;
    }

    public ImageView getIv_back() {
        return iv_back;
    }

    public TextView getTv_next() {
        return tv_next;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.appraise_layout,null);
        iv_back = (ImageView) view.findViewById(R.id.btnBack);
        tv_title = (TextView) view.findViewById(R.id.textHeadTitle);
        tv_title.setText("评价");
        iv_back.setVisibility(View.VISIBLE);
        ls = (ListView) view.findViewById(R.id.ls_app);
        bar_one = (RatingBar) view.findViewById(R.id.ratingbar_one);
        bar_two = (RatingBar) view.findViewById(R.id.ratingbar_two);
        tv_next = (TextView) view.findViewById(R.id.textHeadNext);
        tv_next.setVisibility(View.VISIBLE);
        tv_next.setText("发布");
    }

    @Override
    public View getView() {
        return view;
    }
}
