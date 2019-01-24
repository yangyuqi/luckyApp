package com.yunqilai.consumer.luckyapp.Common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.yunqilai.consumer.luckyapp.Common.Presenter.LoginActivity;
import com.yunqilai.consumer.luckyapp.Common.Ui.DeleteDialog;
import com.yunqilai.consumer.luckyapp.HomePage.Presenter.MessageInfoActivity;
import com.yunqilai.consumer.luckyapp.UserCenter.Interface.DeleteDialogInterface;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by yangyuqi on 17-8-7.
 */

public class MyJushReceiver extends BroadcastReceiver {
    private static final String TAG = "JPush";

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Log.d(TAG, "[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle,context));

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            Log.d(TAG, "[MyReceiver] 接收Registration Id : " + regId);
            //send the Registration Id to your server...

        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
            processCustomMessage(context, bundle);

        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 接收到推送下来的通知");
            int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
            Log.d(TAG, "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);

        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 用户点击打开了通知");
//
//          //打开自定义的Activity
//          Intent i = new Intent(context, TestActivity.class);
//          i.putExtras(bundle);
//          //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//          i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
//          context.startActivity(i);

        } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
            //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

        } else if(JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
            boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
            Log.w(TAG, "[MyReceiver]" + intent.getAction() +" connected state change to "+connected);
        } else {
            Log.d(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
        }
    }

    // 打印所有的 intent extra 数据
    private static String printBundle(final Bundle bundle ,final Context context) {

        try {

            DeleteDialog dialog = null;
            String type = bundle.getString(JPushInterface.EXTRA_EXTRA);
            if (type.indexOf("login") > 0) {

                Toast.makeText(context, "账号已被登录", Toast.LENGTH_SHORT).show();
                //打开自定义的Activity
//            dialog = new DeleteDialog(context,"提示","您账号已被其他用户登录","去登陆");
//            dialog.show();
//            dialog.OnDeleteBtn(new DeleteDialogInterface() {
//                @Override
//                public void isDelete(boolean isdelete) {
//                    if (isdelete){
                Intent i = new Intent(context, LoginActivity.class);
                i.putExtras(bundle);
                //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(i);
//                    }
//                }
//            });

            } else if (type.indexOf("logistics") > 0) {
                dialog = new DeleteDialog(context, "提示", "您有新的物流信息", "去查看");
                dialog.show();
                dialog.OnDeleteBtn(new DeleteDialogInterface() {
                    @Override
                    public void isDelete(boolean isdelete) {
                        if (isdelete) {
                            Intent i = new Intent(context, MessageInfoActivity.class);
                            i.putExtras(bundle);
                            //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            context.startActivity(i);
                        }
                    }
                });
            } else if (type.indexOf("attestation") > 0) {
                dialog = new DeleteDialog(context, "提示", "您认证有新消息", "去查看");
                dialog.show();
                dialog.OnDeleteBtn(new DeleteDialogInterface() {
                    @Override
                    public void isDelete(boolean isdelete) {
                        if (isdelete) {
                            Intent i = new Intent(context, MessageInfoActivity.class);
                            i.putExtras(bundle);
                            //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            context.startActivity(i);
                        }
                    }
                });
            }
        }catch (Exception e){

        }


        return bundle.getString(JPushInterface.EXTRA_EXTRA);

    }

    //send msg to MainActivity
    private void processCustomMessage(Context context, Bundle bundle) {

    }
}
