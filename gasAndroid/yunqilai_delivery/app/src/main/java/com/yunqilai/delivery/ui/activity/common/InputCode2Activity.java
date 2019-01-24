package com.yunqilai.delivery.ui.activity.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yunqilai.delivery.R;
import com.yunqilai.delivery.ui.activity.infomanage.TankDetailActivity;

public class InputCode2Activity extends Activity {
    private EditText edt ;
    private Button btn ;

    private String orderId ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_code);
        initView();
        orderId = getIntent().getStringExtra("orderId");
    }

    private void initView() {
        edt = (EditText) findViewById(R.id.edt_input);
        btn = (Button) findViewById(R.id.btn_search);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("id",edt.getText().toString());
                setResult(1,intent);
                finish();
            }
        });
    }
}
