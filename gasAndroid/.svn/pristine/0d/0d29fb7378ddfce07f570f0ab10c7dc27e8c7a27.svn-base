package com.yunqilai.consumer.luckyapp.UserCenter.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.View.Vu;

/**
 * Created by Administrator on 2017/6/9.
 */

public class RefundView implements Vu {

    private View view,view_reason ;
    private ImageView iv_back ;
    private TextView tv_title ,tv_price ,tv_reasion ;
    private EditText edt_content ;
    private Button btn_commit ;

    public ImageView getIv_back() {
        return iv_back;
    }

    public View getView_reason() {
        return view_reason;
    }

    public TextView getTv_price() {
        return tv_price;
    }

    public TextView getTv_reasion() {
        return tv_reasion;
    }

    public EditText getEdt_content() {
        return edt_content;
    }

    public Button getBtn_commit() {
        return btn_commit;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.refund_layout,null);
        view_reason = view.findViewById(R.id.rl_reason);
        iv_back = (ImageView) view.findViewById(R.id.btnBack);
        tv_title = (TextView) view.findViewById(R.id.textHeadTitle);
        tv_title.setText("申请退款");
        iv_back.setVisibility(View.VISIBLE);
        tv_price = (TextView) view.findViewById(R.id.tv_price);
        tv_reasion = (TextView) view.findViewById(R.id.tv_tv_reasion);
        edt_content = (EditText) view.findViewById(R.id.edt_edt_content);
        btn_commit = (Button) view.findViewById(R.id.btn_register);
    }

    @Override
    public View getView() {
        return view;
    }
}
