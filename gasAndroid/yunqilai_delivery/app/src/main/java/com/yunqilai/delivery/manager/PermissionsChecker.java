package com.yunqilai.delivery.manager;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;

/**
 * 检查权限的工具类
 */
public class PermissionsChecker {
    private final Context mContext;

    public PermissionsChecker(Context context) {
        mContext = context;
    }

    // 判断权限集合
    public boolean lacksPermissions(String[] permissions) {
        boolean result = true;
        for (String permission : permissions) {
            result = result & lacksPermission(permission);
        }
        return result;
    }

    // 判断是否缺少权限
    public boolean lacksPermission(String permission) {
        boolean result = false;
        result = ContextCompat.checkSelfPermission(mContext, permission) == PackageManager.PERMISSION_DENIED;
        Log.d("K_K", permission);
        if(result){
            Log.d("K_K", "need                        "+permission);
        }
        return result;
    }
}
