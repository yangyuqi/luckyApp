package com.yunqilai.delivery.ui.activity.common;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;

import com.yunqilai.delivery.R;
import com.yunqilai.delivery.manager.SharedPreferencesUtil;
import com.yunqilai.delivery.ui.activity.BaseActivity;
import com.yunqilai.delivery.ui.activity.MainActivity;

public class WelcomeActivity extends BaseActivity {

    private boolean canBackCode = false;

    private boolean isFristUser = false ;
    private String accesstoken ;
    /**
     * 页面停留时间
     */
    private int WELCOME_TIME = 1000;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        isFristUser = (boolean) SharedPreferencesUtil.get(context,SharedPreferencesUtil.isFristUse,true);
        accesstoken = (String) SharedPreferencesUtil.get(context,SharedPreferencesUtil.accessToken,"");
        handler.postDelayed(new WelcomeRunnable(),WELCOME_TIME);
    }


    class WelcomeRunnable implements Runnable {
        @Override
        public void run() {
            if (isFristUser) {
                Intent it = new Intent(WelcomeActivity.this, SplashActivity.class);
                startActivity(it);
            }else if (!accesstoken.equals("")){
                startActivity(new Intent(context,MainActivity.class));
                finish();
            }else {
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
            finish();
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN && !canBackCode) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
