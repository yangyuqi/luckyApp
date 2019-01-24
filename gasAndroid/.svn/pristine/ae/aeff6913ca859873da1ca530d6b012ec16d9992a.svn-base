package com.yunqilai.delivery.ui.view;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yunqilai.delivery.R;

/**
 * 显示全局的Toast，防止Toast的重复弹出
 * Created by KK on 2017/2/20.
 */
public class ToastUtil {
    private static Toast mToast = null;

    public static void show(Context ctx, String text) {
        init(ctx, text, Toast.LENGTH_SHORT);
    }

    public static void show(Context ctx, Integer text) {
        init(ctx, text, Toast.LENGTH_SHORT);
    }

    public static void show(Context ctx, String text, int duration) {
        init(ctx, text, duration);
    }

    public static void show(Context ctx, Integer text, int duration) {
        init(ctx, text, duration);
    }

    private static void init(Context ctx, Object text, int duration) {
        if(ctx == null) {
            return;
        }

        if (mToast == null) {
            if(text instanceof Integer) {
                mToast = makeText(ctx, (Integer)text, duration);
            }
            else if(text instanceof String) {
                mToast = makeText(ctx, (String)text, duration);
            }
        } else {
            if(text instanceof Integer) {
                ((TextView) mToast.getView().findViewById(R.id.txt_viewinfo)).setText((Integer)text);
            }
            else if(text instanceof String) {
                ((TextView) mToast.getView().findViewById(R.id.txt_viewinfo)).setText((String)text);
            }
        }

        if(mToast != null) {
            mToast.show();
        }
    }

    public static void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
            mToast = null;
        }
    }

    private static Toast makeText(Context context, int msg, int duration) {
        View toastRoot = LayoutInflater.from(context).inflate(R.layout.toast_myself, null);
        Toast toast = new Toast(context);
        toast.setView(toastRoot);
        TextView tv = (TextView) toastRoot.findViewById(R.id.txt_viewinfo);
        tv.setText(msg);
        toast.setDuration(duration);
        return toast;
    }

    private static Toast makeText(Context context, String msg, int duration) {
        View toastRoot = LayoutInflater.from(context).inflate(R.layout.toast_myself, null);
        Toast toast = new Toast(context);
        toast.setView(toastRoot);
        TextView tv = (TextView) toastRoot.findViewById(R.id.txt_viewinfo);
        tv.setText(msg);
        toast.setDuration(duration);
        return toast;
    }
}