package com.yunqilai.consumer.luckyapp.HomePage.View;

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
 * Created by Administrator on 2017/6/3.
 */

public class EditCodeView implements Vu {

    private View view ;
    private ImageView iv_back ;
    private TextView tv_title ;
    private Button btn_search ;
    private EditText edt ;

    public EditText getEdt() {
        return edt;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.edit_code_layout,null);
        iv_back = (ImageView) view.findViewById(R.id.btnBack);
        iv_back.setVisibility(View.VISIBLE);
        tv_title = (TextView) view.findViewById(R.id.textHeadTitle);
        tv_title.setText("手动输入");
        btn_search = (Button) view.findViewById(R.id.btn_search);
        edt = (EditText) view.findViewById(R.id.edt);
    }

    @Override
    public View getView() {
        return view;
    }
    public Button getBtn_search(){
        return btn_search ;
    }

    public ImageView getIv_back(){
        return iv_back;
    }
}
