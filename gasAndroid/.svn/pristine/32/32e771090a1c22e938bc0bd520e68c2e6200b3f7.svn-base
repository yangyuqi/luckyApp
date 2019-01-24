package com.yunqilai.delivery.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.yunqilai.delivery.R;
import com.yunqilai.delivery.model.TankOperate;
import com.yunqilai.delivery.ui.adapter.TankOperateAdapter;

import java.util.List;

/**
 * 瓶罐操作
 * Created by KK on 2017/6/14.
 */

public class TankOperateDialog extends Dialog{

    private ListView listView;
    private TextView sureBtn;
    private TankOperateAdapter adapter;
    private List<TankOperate> tankOperates;

    private TankOperateCallback tankOperateCallback;

    public TankOperateDialog(Context context, List<TankOperate> tankOperates) {
        super(context, R.style.bottom_dialog);
        setContentView(R.layout.view_tank_operate_layout);

        listView = (ListView)findViewById(R.id.lv_operate);
        sureBtn = (TextView)  findViewById(R.id.tv_sure);
        this.tankOperates = tankOperates;

        adapter = new TankOperateAdapter(context,this.tankOperates);
        listView.setAdapter(adapter);

        sureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tankOperateCallback !=null){
                    for(TankOperate tankOperate:TankOperateDialog.this.tankOperates){
                        if(tankOperate.isChoose()){
                            tankOperateCallback.operate(tankOperate);
                        }
                    }
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                for(int t = 0;t<TankOperateDialog.this.tankOperates.size();t++){
                    if(t == i){
                        TankOperateDialog.this.tankOperates.get(i).setChoose(true);
                    }else{
                        TankOperateDialog.this.tankOperates.get(i).setChoose(false);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void show() {
        super.show();
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.gravity= Gravity.BOTTOM;
        layoutParams.width= WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height= WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setAttributes(layoutParams);
    }

    public interface TankOperateCallback {
        void operate(TankOperate tankOperate);
    }

    public void setTankOperateCallback(TankOperateCallback tankOperateCallback) {
        this.tankOperateCallback = tankOperateCallback;
    }
}
