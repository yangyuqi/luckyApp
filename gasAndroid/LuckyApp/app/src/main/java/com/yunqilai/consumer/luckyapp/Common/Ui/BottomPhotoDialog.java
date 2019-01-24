package com.yunqilai.consumer.luckyapp.Common.Ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.yunqilai.consumer.R;

/**
 * Created by Administrator on 2017/6/27.
 */

public class BottomPhotoDialog  extends Dialog {

    private Context context;

    private int layout ;
    View view ;

    public BottomPhotoDialog(Context context ,int mlayout) {
        super( context , R.style.BottomDialog);
        this.context = context;
        layout = mlayout ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

    }

    public View getView() {
        return view;
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(context);
        view =inflater.inflate(layout, null);
        setContentView(view);
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        params.width = d.widthPixels;
        params.height = d.heightPixels;
        params.gravity = Gravity.BOTTOM;
        window.setAttributes(params);
        window.setGravity(Gravity.BOTTOM);
    }

}
