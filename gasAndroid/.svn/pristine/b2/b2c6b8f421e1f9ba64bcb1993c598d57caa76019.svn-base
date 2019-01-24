package com.yunqilai.delivery.ui.activity.infomanage;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Request;
import com.yunqilai.delivery.R;
import com.yunqilai.delivery.model.Bean.Info.BarCodeResultBean;
import com.yunqilai.delivery.model.Bean.Info.BarcodeBean;
import com.yunqilai.delivery.model.Bean.Info.GetTankBean;
import com.yunqilai.delivery.model.Bean.Info.InfoDetailsBean;
import com.yunqilai.delivery.model.Bean.Info.InfoListData;
import com.yunqilai.delivery.model.Bean.Info.ReplaceBarBean;
import com.yunqilai.delivery.model.Bean.ParseBaseEntity;
import com.yunqilai.delivery.request.base.ErrorCode;
import com.yunqilai.delivery.ui.activity.BaseActivity;
import com.yunqilai.delivery.ui.activity.common.ScanCode2Activity;
import com.yunqilai.delivery.ui.interlayer.infomanage.TankDetailInterlayer;
import com.yunqilai.delivery.ui.presenter.infomanage.TankDetail2Presenter;
import com.yunqilai.delivery.ui.presenter.infomanage.TankDetailPresenter;
import com.yunqilai.delivery.ui.view.CommonTitle;
import com.yunqilai.delivery.utils.Event;
import com.yunqilai.delivery.utils.MyOkHttpClientManager;
import com.yunqilai.delivery.utils.ResponseUtils;
import com.yunqilai.delivery.utils.UrlUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

/***
 * 补码
 */
public class ReplaceCodeActivity extends BaseActivity<TankDetail2Presenter> implements View.OnClickListener,TankDetailInterlayer {

    private InfoDetailsBean tank;
    private String id;

    private CommonTitle commonTitle;

    private TextView tv_bar_code;
    private TextView tv_username;
    private TextView tv_phone;
    private TextView label_tank_code;
    private TextView label_tank_model;
    private TextView label_produce_time;
    private TextView tv_next_check_time;
    private TextView tv_tank_expired_time;
    private TextView tv_checked_times;
    private TextView tv_use_times;
    private LinearLayout ll_bar_code;

    private LinearLayout ll_scan;
    private TextView btn_bind;

    private String tankId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replace_code);
        tank = (InfoDetailsBean) getIntent().getSerializableExtra("tankId");
        initViews();
        initDatas();
        initEvents();
    }

    private void initViews(){
        commonTitle = (CommonTitle) findViewById(R.id.common_title);
        tv_bar_code = (TextView) findViewById(R.id.tv_bar_code);
        tv_username = (TextView) findViewById(R.id.tv_username);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        label_tank_code = (TextView) findViewById(R.id.label_tank_code);
        label_tank_model = (TextView) findViewById(R.id.label_tank_model);
        label_produce_time = (TextView) findViewById(R.id.label_produce_time);
        tv_next_check_time = (TextView) findViewById(R.id.tv_next_check_time);
        tv_tank_expired_time = (TextView) findViewById(R.id.tv_tank_expired_time);
        tv_checked_times = (TextView) findViewById(R.id.tv_checked_times);
        tv_use_times = (TextView) findViewById(R.id.tv_use_times);

        ll_scan = (LinearLayout) findViewById(R.id.ll_scan);
        btn_bind = (TextView) findViewById(R.id.btn_bind);
        ll_bar_code = (LinearLayout) findViewById(R.id.ll_bar_code);
    }

    private void initDatas(){
        label_tank_code.setText(tank.getTankNumber());
        label_tank_model.setText(tank.getModel());
        label_produce_time.setText(tank.getProduceTime());
        tankId = tank.getTankId();
        presenter = new TankDetail2Presenter(this,this,getAccessToken(),tank.getBarCode());
        presenter.requestData();
        /*tv_next_check_time.setText(tank.getNextCheckTime());
        tv_tank_expired_time.setText(tank.getTankExpiredTime());
        tv_checked_times.setText(tank.getCheckTimes());
        tv_use_times.setText(tank.getUseTimes());*/
    }
    private void initNetworks(){

        /*presenter = new TankDetail2Presenter(this,this,getAccessToken(),id);
        presenter.requestData();*/

        MyOkHttpClientManager.postAsynJson(gson.toJson(new BarcodeBean(getAccessToken(), id)), UrlUtils.CHECK_FREE_BAR_CODE, new MyOkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Toast.makeText(ReplaceCodeActivity.this,R.string.service_error_500,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {
                try {
                    ParseBaseEntity entity = gson.fromJson(response, ParseBaseEntity.class);
                    if (entity.getCode().equals(ResponseUtils.OK)) {
                        BarCodeResultBean mdata = gson.fromJson(gson.toJson(entity.getData()), BarCodeResultBean.class);
                        if(mdata.isCanUse()){
                            ll_scan.setVisibility(View.GONE);
                            btn_bind.setVisibility(View.VISIBLE);
                        }else{
                            Toast.makeText(ReplaceCodeActivity.this,"条码不可用",Toast.LENGTH_SHORT).show();
                        }


                    } else if(entity.getCode().equals(ErrorCode.ERR_ACCESS_TOKEN_INVALID) || entity.getCode().equals(ErrorCode.ERR_REFRESH_TOKEN_INVALID)){
                        EventBus.getDefault().post(Event.EXITANDLOGIN);
                    }else {
                        Toast.makeText(context, entity.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(ReplaceCodeActivity.this,R.string.service_error_500,Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }

    private void initEvents(){
        ll_scan.setOnClickListener(this);
        btn_bind.setOnClickListener(this);
        commonTitle.setOnTitleClickListener(new CommonTitle.OnTitleClickListener() {
            @Override
            public void onLeftClick() {
                finish();
            }

            @Override
            public void onRightClick() {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if(resultCode == 1){
                    id= data.getStringExtra("id");
                    ll_bar_code.setVisibility(View.VISIBLE);
                    tv_bar_code.setText(id);
                    initNetworks();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.ll_scan:
                intent.setClass(this, ScanCode2Activity.class);
                startActivityForResult(intent,1);
                break;
            case R.id.btn_bind:


                final ProgressDialog dialog2 = ProgressDialog.show(this, "提示", "数据请求中");
                MyOkHttpClientManager.postAsynJson(gson.toJson(new ReplaceBarBean(getAccessToken(), tankId , id)), UrlUtils.REPLACE_CODE_URL, new MyOkHttpClientManager.StringCallback() {
                    @Override
                    public void onFailure(Request request, IOException e) {
                        Toast.makeText(ReplaceCodeActivity.this,R.string.service_error_500,Toast.LENGTH_SHORT).show();
                        dialog2.dismiss();
                    }

                    @Override
                    public void onResponse(String response) {
                        dialog2.dismiss();
                        try {
                            ParseBaseEntity entity = gson.fromJson(response, ParseBaseEntity.class);
                            if (entity.getCode().equals(ResponseUtils.OK)) {
                                Toast.makeText(context, "补码成功", Toast.LENGTH_SHORT).show();
                                finish();
                            } else if(entity.getCode().equals(ErrorCode.ERR_ACCESS_TOKEN_INVALID) || entity.getCode().equals(ErrorCode.ERR_REFRESH_TOKEN_INVALID)){
                                EventBus.getDefault().post(Event.EXITANDLOGIN);
                            }else {
                                Toast.makeText(context, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }catch (Exception e){
                            Toast.makeText(ReplaceCodeActivity.this,R.string.service_error_500,Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                });
                break;
        }
    }

    @Override
    public void refreshDatas(GetTankBean tank) {
        tv_username.setText(tank.getBuyerName());
        tv_phone.setText(tank.getPhone());
        tv_next_check_time.setText(tank.getNextCheckTime());
        tv_tank_expired_time.setText(tank.getTankExpiredTime());
        tv_checked_times.setText(tank.getCheckTimes());
        /*barCodeTv.setText(tank.getBarCode());
        tankCodeTv.setText(tank.getTankNumber());
        tankModelTv.setText(tank.getModel());*/
        label_produce_time.setText(tank.getProduceTime());
        tv_use_times.setText(tank.getCheckTimes());
    }
}
