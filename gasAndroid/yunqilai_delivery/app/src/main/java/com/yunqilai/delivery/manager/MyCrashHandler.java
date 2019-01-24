package com.yunqilai.delivery.manager;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import com.yunqilai.delivery.ui.activity.MainActivity;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/***
 * 系统崩溃
 * Created by wqd on 2016-03-15.
 */
public class MyCrashHandler implements UncaughtExceptionHandler {

    private Context mContext;
    private String CRASH_DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator +"yql_crash";
    // 用来存储设备信息和异常信息
    private Map<String, String> infos = new HashMap<String, String>();
    // 用于格式化日期,作为日志文件名的一部分
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");

    public MyCrashHandler(Context context) {
        this.mContext = context;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {

        try {
            // 收集设备参数信息
            collectDeviceInfo(mContext);
            // 保存日志文件
            saveCrashInfo2File(ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 重新启动程序，注释上面的退出程序
        Intent intent = new Intent();
        intent.setClass(mContext, MainActivity.class);
        intent.putExtra("flag", "crash");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        mContext.startActivity(intent);
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    /**
     * 收集设备参数信息
     *
     * @param ctx
     */
    public void collectDeviceInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(),
                    PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null"
                        : pi.versionName;
                String versionCode = pi.versionCode + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
            }
        } catch (NameNotFoundException e) {
            Log.e("K_K","an error occured when collect package info" + e.toString());
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
                Log.e("K_K",field.getName() + " : " + field.get(null));
            } catch (Exception e) {
                Log.e("K_K","an error occured when collect crash info" + e.toString());
            }
        }
    }

    /**
     * 保存错误信息到文件中
     *
     * @param ex
     */
    private void saveCrashInfo2File(Throwable ex) {

        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");
        }

        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);
        try {
            Log.e("K_K","crash -- " + sb.toString());
            long timestamp = System.currentTimeMillis();
            String time = formatter.format(new Date());
            String fileName = time + "-" + timestamp + ".log";

            //将错误日志保存在sdcard/中
            FileStorage fileStorage = new FileStorage();
            fileStorage.savePublic(CRASH_DIRECTORY,
                    fileName,sb.toString().getBytes());
        } catch (Exception e) {
            Log.e("K_K","an error occured while writing file..." + e.toString());
        }
    }

}
