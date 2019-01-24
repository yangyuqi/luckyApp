package com.yunqilai.consumer.luckyapp.ChoicePage.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.Utils.adapter.CommonAdapter;
import com.yunqilai.consumer.luckyapp.Common.View.Vu;

/**
 * Created by Administrator on 2017/5/24.
 */

public class ChoicePageView implements Vu{

    private ListView ls ;

    protected View view ;
    private TextView tv_title ;
    protected GridView gv_gas;

    public GridView getGv_gas() {
        return gv_gas;
    }

    protected GridView gv_tro_gas;

    public GridView getGv_tro_gas() {
        return gv_tro_gas;
    }

    public ListView getLs() {
        return ls;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.choice_page_layout,null);
        tv_title = (TextView) view.findViewById(R.id.textHeadTitle);
        tv_title.setText("精选");
//        gv_gas = (GridView) view.findViewById(R.id.choice_page_view_gv);
//        gv_tro_gas = (GridView) view.findViewById(R.id.choice_page_view_tro_gv);
        ls = (ListView) view.findViewById(R.id.ls);
    }

    @Override
    public View getView() {
        return view;
    }


}
