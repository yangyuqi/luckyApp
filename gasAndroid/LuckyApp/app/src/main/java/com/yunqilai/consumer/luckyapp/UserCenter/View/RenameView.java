package com.yunqilai.consumer.luckyapp.UserCenter.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.View.Vu;

/**
 * Created by Administrator on 2017/6/12.
 */

public class RenameView implements Vu {

    private View view ;
    private ImageView iv_back ;
    private TextView tv_title ,tv_save ;
    private EditText edt ;

    public TextView getTv_save() {
        return tv_save;
    }

    public EditText getEdt() {
        return edt;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.re_name_layout,null);
        iv_back = (ImageView) view.findViewById(R.id.btnBack);
        tv_title = (TextView) view.findViewById(R.id.textHeadTitle);
        tv_title.setText("修改昵称");
        iv_back.setVisibility(View.VISIBLE);
        tv_save = (TextView) view.findViewById(R.id.textHeadNext);
        tv_save.setText("保存");
        tv_save.setVisibility(View.VISIBLE);
        edt = (EditText) view.findViewById(R.id.edt_name);
    }

    @Override
    public View getView() {
        return view;
    }

    public ImageView getIv_back() {
        return iv_back;
    }
}
