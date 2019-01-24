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
 * Created by Administrator on 2017/6/5.
 */

public class TradeMessageView implements Vu {

    private View view ,no_data_view;
    private ImageView iv_back ;
    private TextView tv_title ;
    private ListView ls ;

    public View getNo_data_view() {
        return no_data_view;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.trade_message_layout,null);
        iv_back = (ImageView) view.findViewById(R.id.btnBack);
        tv_title = (TextView) view.findViewById(R.id.textHeadTitle);
        tv_title.setText("交易物流信息");
        iv_back.setVisibility(View.VISIBLE);
        ls = (ListView) view.findViewById(R.id.trade_message_layout_ls);
        no_data_view = view.findViewById(R.id.ll_show);

    }

    @Override
    public View getView() {
        return view;
    }

    public ImageView getIv_back(){
        return iv_back ;
    }

    public ListView getLs(){
        return ls;
    }
}
