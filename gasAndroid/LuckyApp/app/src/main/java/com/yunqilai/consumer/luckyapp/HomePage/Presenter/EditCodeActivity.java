package com.yunqilai.consumer.luckyapp.HomePage.Presenter;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.Base.BasePresenterActivity;
import com.yunqilai.consumer.luckyapp.Common.Presenter.CaptureActivity;
import com.yunqilai.consumer.luckyapp.Common.Utils.OkHttpClientManager;
import com.yunqilai.consumer.luckyapp.Common.Utils.ResponseCodeUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.UrlUtils;
import com.yunqilai.consumer.luckyapp.HomePage.Model.BottleInfoBean;
import com.yunqilai.consumer.luckyapp.HomePage.Model.ScanCodeBean;
import com.yunqilai.consumer.luckyapp.HomePage.Model.ScanCodeResultData;
import com.yunqilai.consumer.luckyapp.HomePage.View.EditCodeView;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

/**
 * Created by Administrator on 2017/6/3.
 */

public class EditCodeActivity extends BasePresenterActivity<EditCodeView> implements View.OnClickListener{

    BottleInfoBean bottleInfoBean ;

    @Override
    protected Class<EditCodeView> getViewClass() {
        return EditCodeView.class;
    }

    @Override
    protected void onBindView() {
        super.onBindView();


        vu.getBtn_search().setOnClickListener(this);
        vu.getIv_back().setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnBack:
                finish();
                break;
            case R.id.btn_search:
                getData();
                break;
        }
    }

    private void getData() {
        final Gson gson = new Gson();
        String type = gson.toJson(new ScanCodeBean(getAccessToken(),vu.getEdt().getText().toString()));
        OkHttpClientManager.postAsynJson(type, UrlUtils.SCAN_CODER_URL, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                try {
                    ScanCodeResultData data = gson.fromJson(response,ScanCodeResultData.class);
                    if (data.getCode().equals(ResponseCodeUtils.OK)){
                        Intent intent = new Intent(EditCodeActivity.this, BottleInfoActivity.class);
                        bottleInfoBean = data.getData();
                        startActivity(intent);;
                    }else {
                        Toast.makeText(EditCodeActivity.this,data.getMsg(),Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){

                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (bottleInfoBean!=null) {
            EventBus.getDefault().post(bottleInfoBean);
        }
    }
}
