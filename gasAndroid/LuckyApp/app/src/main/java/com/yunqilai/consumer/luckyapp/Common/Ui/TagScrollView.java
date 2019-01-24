package com.yunqilai.consumer.luckyapp.Common.Ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class TagScrollView extends ScrollView implements OnTouchListener, OnGestureListener{
    private float mPosX, mPosY, mCurPosX, mCurPosY;
    private static final int FLING_MIN_DISTANCE = 20;// 移动最小距离
    private static final int FLING_MIN_VELOCITY = 200;// 移动最大速度
    //构建手势探测器
    GetScrollChangeInteface inteface ;

    GestureDetector mygesture = new GestureDetector(this);

    public TagScrollView(Context context) {
        super(context);
    }


    public void getStatus(GetScrollChangeInteface inteface){
        this.inteface = inteface;
    }

    public TagScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TagScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        //setGestureListener();
        //设置Touch监听
        this.setOnTouchListener(this);
        //允许长按
        this.setLongClickable(true);
    }





    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // TODO Auto-generated method stub
        return mygesture.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        // TODO Auto-generated method stub
        // e1：第1个ACTION_DOWN MotionEvent
        // e2：最后一个ACTION_MOVE MotionEvent
        // velocityX：X轴上的移动速度（像素/秒）
        // velocityY：Y轴上的移动速度（像素/秒）

        // X轴的坐标位移大于FLING_MIN_DISTANCE，且移动速度大于FLING_MIN_VELOCITY个像素/秒
        //向
        if (e1.getY() - e2.getY() > FLING_MIN_DISTANCE){
//                     && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
            Log.e("ssssssssssssssssssss","向下");
            inteface.getStaus("down");
        }
        //向上
        if (e2.getY() - e1.getY() > FLING_MIN_DISTANCE
                && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
            Log.e("ssssssssssssssssssss","向上");
            inteface.getStaus("up");

        }
        return false;
    }

    public interface GetScrollChangeInteface{
         String getStaus(String status);
    }

}