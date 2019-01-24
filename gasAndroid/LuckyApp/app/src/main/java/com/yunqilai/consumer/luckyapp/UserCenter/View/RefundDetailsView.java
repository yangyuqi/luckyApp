package com.yunqilai.consumer.luckyapp.UserCenter.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.View.Vu;

/**
 * Created by Administrator on 2017/8/16.
 */

public class RefundDetailsView implements Vu {

    private View view ,view_histroy;
    private TextView tv_speed_status ,refund_tv_money ,refund_reason ,refund_money ,refund_content ,refund_time ,refund_number ;
    private ListView gv ;

    private ImageView iv_back ;
    private TextView tv_title ;

    public TextView getTv_speed_status() {
        return tv_speed_status;
    }

    public TextView getRefund_tv_money() {
        return refund_tv_money;
    }

    public TextView getRefund_reason() {
        return refund_reason;
    }

    public TextView getRefund_money() {
        return refund_money;
    }

    public TextView getRefund_content() {
        return refund_content;
    }

    public TextView getRefund_time() {
        return refund_time;
    }

    public TextView getRefund_number() {
        return refund_number;
    }

    public ImageView getIv_back() {
        return iv_back;
    }

    public ListView getGv() {
        return gv;
    }

    public View getView_histroy() {
        return view_histroy;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.refund_details_layout,null);
        iv_back = (ImageView) view.findViewById(R.id.btnBack);
        tv_title = (TextView) view.findViewById(R.id.textHeadTitle);
        tv_title.setText("退款详情");
        iv_back.setVisibility(View.VISIBLE);
        tv_speed_status = (TextView) view.findViewById(R.id.tv_status_speed);
        refund_tv_money = (TextView) view.findViewById(R.id.tv_price);
        refund_reason = (TextView) view.findViewById(R.id.tv_reason);
        refund_money = (TextView) view.findViewById(R.id.refund_money);
        refund_content = (TextView) view.findViewById(R.id.refund_content);
        refund_time = (TextView) view.findViewById(R.id.refund_time);
        refund_number = (TextView) view.findViewById(R.id.refund_num);
        gv = (ListView) view.findViewById(R.id.ls_sv);
        view_histroy = view.findViewById(R.id.rl_histroy);

    }

    @Override
    public View getView() {
        return view;
    }
}
