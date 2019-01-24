package com.yunqilai.consumer.luckyapp.HomePage.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.View.Vu;

/**
 * Created by Administrator on 2017/6/5.
 */

public class SearchGoodsView implements Vu {
    private View view ;
    protected ImageView iv_back ;
    protected EditText edt_search ;
    protected TextView tv_search ;

    public ImageView getIv_back() {
        return iv_back;
    }

    public void setIv_back(ImageView iv_back) {
        this.iv_back = iv_back;
    }

    public EditText getEdt_search() {
        return edt_search;
    }

    public void setEdt_search(EditText edt_search) {
        this.edt_search = edt_search;
    }

    public TextView getTv_search() {
        return tv_search;
    }

    public void setTv_search(TextView tv_search) {
        this.tv_search = tv_search;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.serach_goods_layout,null);
        iv_back = (ImageView) view.findViewById(R.id.iv_search_lef);
        edt_search = (EditText) view.findViewById(R.id.search_edt);
        tv_search = (TextView) view.findViewById(R.id.tv_search);
    }

    @Override
    public View getView() {
        return view;
    }
}
