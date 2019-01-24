package com.yunqilai.consumer.luckyapp.Common.Presenter;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.yunqilai.consumer.luckyapp.Common.Base.BasePresenterActivity;
import com.yunqilai.consumer.luckyapp.Common.View.ConfirmSuccessView;
import com.yunqilai.consumer.luckyapp.Common.View.ConfirmUserView;
import com.yunqilai.consumer.luckyapp.Common.View.VuCallback;
import com.yunqilai.consumer.luckyapp.MainActivity;

/**
 * Created by Administrator on 2017/5/25.
 */

public class ConfirmSuccessActivity extends BasePresenterActivity<ConfirmSuccessView> {

    private int count = 3;
    private int COUNT_TIME = 0;

    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
                if (count <= 0) {
                    count = 60;

                    return;
                }
                count--;

                vu.getTv_miao().setText("" + count + "S");
                sendEmptyMessageDelayed(COUNT_TIME, 1000);

        }
    };



    @Override
    protected Class<ConfirmSuccessView> getViewClass() {
        return ConfirmSuccessView.class;
    }

    @Override
    protected void onBindView() {
        super.onBindView();

        handler.sendEmptyMessage(COUNT_TIME);

        vu.onClickListener(new VuCallback<View>() {
            @Override
            public void execute(View result) {
//                handler.sendEmptyMessage(0x15);
//                startActivity(new Intent(ConfirmSuccessActivity.this, MainActivity.class));
//                finish();
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(ConfirmSuccessActivity.this, MainActivity.class));
                finish();
            }
        },3000);
    }
}
