package com.yunqilai.delivery.ui.activity.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.yunqilai.delivery.R;
import com.yunqilai.delivery.third.scanqrcode.ScanCodeFragment;
import com.yunqilai.delivery.ui.activity.BaseFragmentActivity;
import com.yunqilai.delivery.ui.activity.infomanage.TankDetailActivity;
import com.yunqilai.delivery.ui.interlayer.infomanage.ScanCodeInterlayer;
import com.yunqilai.delivery.ui.presenter.infomanage.ScanCodePresenter;
import com.yunqilai.delivery.ui.view.CommonTitle;
import com.yunqilai.delivery.ui.view.ToastUtil;
import com.yunqilai.delivery.utils.Event;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScanCodeActivity extends BaseFragmentActivity<ScanCodePresenter> implements ScanCodeInterlayer,ScanCodeFragment.ICallback,View.OnClickListener {

    private FragmentManager mFragmentManager;
    private ScanCodeFragment mScanCodeFragment;
    private FragmentTransaction mTransaction;

    private CommonTitle commonTitle;
    private LinearLayout inputCodeBtn;

    private String orderId ="";
    private String buyPhone="";
    private String buyName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_scan_code);
        orderId = getIntent().getStringExtra("orderId");
        buyPhone = getIntent().getStringExtra("buyPhone");
        buyName = getIntent().getStringExtra("buyName");
        mFragmentManager = getSupportFragmentManager();

        initView();
        initEvent();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(Event event){
        if (event == Event.EXITANDLOGIN) {
            finish();
        }
    }

    private void initView(){
        commonTitle = (CommonTitle)findViewById(R.id.common_title);
        inputCodeBtn = (LinearLayout)findViewById(R.id.ll_input_code);
        mScanCodeFragment = (ScanCodeFragment) mFragmentManager.findFragmentById(R.id.fragment_scan_product_fg_scan);
    }

    private void initEvent(){
        inputCodeBtn.setOnClickListener(this);
        mScanCodeFragment.setCallback(this);
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
    public void onScanSuccess(String result) {
        String new_str = null ;
        if (result.length()>=10) {
            new_str = result.substring(result.length() - 10);
            if (isNumeric(new_str)){
                Intent intent = new Intent(context, TankDetailActivity.class);
                intent.putExtra("id",new_str);
                if (orderId==null){
                    orderId = "";
                }
                if (orderId.equals("")){
                    intent.putExtra("orderId","");
                    intent.putExtra("from","2");
                }else if (orderId.equals("testID")){
                    intent.putExtra("orderId","");
                    intent.putExtra("from","4");
                }else {
                    intent.putExtra("orderId",orderId);
                    intent.putExtra("buyName",buyName);
                    intent.putExtra("buyPhone",buyPhone);
                    intent.putExtra("from","2");
                }
                startActivity(intent);
                finish();
            }
        }
        ToastUtil.show(this, new_str, Toast.LENGTH_LONG);
        mScanCodeFragment.restartPreviewAfterDelay(5000);
    }

    /**
     * 利用正则表达式判断字符串是否是数字
     * @param str
     * @return
     */
    public boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTransaction = mFragmentManager.beginTransaction();
        mTransaction.show(mScanCodeFragment);
        mTransaction.commitAllowingStateLoss();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mTransaction = mFragmentManager.beginTransaction();
        mTransaction.hide(mScanCodeFragment);
        mTransaction.commitAllowingStateLoss();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_input_code:
                Intent intent = new Intent(this,InputCodeActivity.class);
                if (orderId==null){
                    orderId = "";
                }
                if ("".equals(orderId)){
                    intent.putExtra("orderId","");
                }else {
                    intent.putExtra("orderId",orderId);
                }
                startActivity(intent);
                finish();
                break;
        }
    }

    @Subscribe
    public void onEvent(String order){
        orderId = order ;

    }
}
