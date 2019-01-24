package com.yunqilai.consumer.luckyapp.Common.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunqilai.consumer.R;

/**
 * Created by Administrator on 2017/5/25.
 */

public class ConfirmSuccessView implements Vu {

    private View view ;
    private Button btn_go_home;
    protected VuCallback<View> vuCallback ;
    protected TextView tv_title ;
    private ImageView iv_back ;
    private TextView tv_miao ;

    public TextView getTv_miao() {
        return tv_miao;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.confirm_success_activity,null);
        btn_go_home = (Button) view.findViewById(R.id.btn_go_home);
        btn_go_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vuCallback.execute(v);
            }
        });
        tv_title = (TextView) view.findViewById(R.id.textHeadTitle);
        tv_title.setText("实名认证");
        iv_back = (ImageView) view.findViewById(R.id.btnBack);
        iv_back.setVisibility(View.VISIBLE);
        tv_miao = (TextView) view.findViewById(R.id.tv_tv_miao);
    }

    @Override
    public View getView() {
        return view;
    }

    public void onClickListener(VuCallback<View> viewVuCallback){
        vuCallback = viewVuCallback ;
    }
}
