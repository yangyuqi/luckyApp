package com.yunqilai.delivery.ui.activity.order;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.yunqilai.delivery.R;
import com.yunqilai.delivery.ui.activity.BaseFragmentActivity;
import com.yunqilai.delivery.ui.view.ToastUtil;
import com.yunqilai.delivery.utils.Event;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class SearchOrderActivity extends BaseFragmentActivity {


    private ImageView iv ;

    private EditText edt ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_search_order);

        iv = (ImageView) findViewById(R.id.iv_back);
        edt = (EditText) findViewById(R.id.et_search);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.tv_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edt.getText().length() ==0){
                    Toast.makeText(context,"搜索内容不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(SearchOrderActivity.this, SearchOrderResultActivity.class);
                intent.putExtra("desc",edt.getText().toString());
                startActivity(intent);
            }
        });
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
}
