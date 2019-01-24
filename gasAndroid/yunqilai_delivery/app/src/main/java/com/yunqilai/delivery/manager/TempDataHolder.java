package com.yunqilai.delivery.manager;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * 负责在内存中存放全局数据
 * Created by huangjinfu on 2016/1/14.
 */
public final class TempDataHolder {

    private static final String TAG = "TempDataHolder";
    private static final boolean DEBUG = true;

    private static TempDataHolder sInstance = null;

    private Map<String, Object> mMap;

    private TempDataHolder() {
        if (DEBUG) Log.d(TAG, "create an new TempDataHolder instance, id : " + this);
        mMap = new HashMap<>();
    }

    static TempDataHolder getInstance() {
        if (sInstance == null) {
            synchronized (TempDataHolder.class) {
                if (sInstance == null) {
                    sInstance = new TempDataHolder();
                }
            }
        }
        return sInstance;
    }

    /**
     * 添加数据
     *
     * @param key   键
     * @param value 值
     */
    void put(String key, Object value) {
        mMap.put(key, value);
    }

    /**
     * 获取数据
     *
     * @param key 键
     * @return
     */
    Object get(String key) {
        return mMap.get(key);
    }

    /**
     * 移除数据
     *
     * @param key 键
     */
    void remove(String key) {
        mMap.remove(key);
    }

}
