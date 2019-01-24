package com.yunqilai.delivery.manager;

import android.content.Context;

import java.io.Serializable;

/**
 * 负责提供系统运行时的全局数据存取，数据主要以键值对的方式进行存取操作。
 * 内部分两层存储参数，第一层为内存，第二层为文件。第二层主要存储一些重要的运行时参数，用来恢复第一层丢失的参数
 * ，比如系统被清理后，需要从非入口程序进行初始化启动。
 * 第二层中，对象类型的数据存储在普通文件中，基本数据类型的数据存储在 SharedPreferences 中。
 * <p/>
 */
public final class GlobalDataUtil {

    private static final String DEFAULT_SP_NAME = "runtime_global_data";

    /**
     * 保存String类型的数据参数，默认是浅保存
     *
     * @param key   查找的key
     * @param value 待保存的数据
     */
    public static void putString(Context context, String key, String value) {
        putString(context, key, value, false);
    }

    /**
     * 保存String类型的数据参数
     *
     * @param key      查找的key
     * @param value    待保存的数据
     * @param deepSave true-深度保存，String类型数据深度保存在SharedPreferences中。false-浅保存，保存在内存中。
     */
    public static void putString(Context context, String key, String value, boolean deepSave) {
        if (value == null) {
            throw new RuntimeException("the value which to be saved in global can not be null");
        }

        TempDataHolder.getInstance().put(key, value);

        if (deepSave) {
            SharedPreferencesUtil.save(context,DEFAULT_SP_NAME, key, value);
        }
    }

    /**
     * 保存对象数据参数，默认是浅保存
     *
     * @param key   查找的key
     * @param value 待保存的数据
     */
    public static void putObject(Context context, String key, Object value) {
        putObject(context, key, value, false);
    }

    /**
     * 保存对象数据参数
     *
     * @param key      查找的key
     * @param value    待保存的数据
     * @param deepSave true-深度保存，对象数据深度保存在普通文件中。false-浅保存，保存在内存中。
     */
    public static void putObject(Context context, String key, Object value, boolean deepSave) {
        if (value == null) {
            throw new RuntimeException("the value which to be saved in global can not be null");
        }
        if (deepSave && !(value instanceof Serializable)) {
            throw new RuntimeException("value object can not be serializabled");
        }

        TempDataHolder.getInstance().put(key, value);

        if (deepSave) {
            new FileStorage().savePrivate(context, key, (Serializable) value);
        }
    }

    /**
     * 根据key，按存储层级获取String类型的参数值
     *
     * @param key 查找的key
     */
    public static String getString(Context context, String key) {
        String value = (String) TempDataHolder.getInstance().get(key);
        if (value == null) {
            value = SharedPreferencesUtil.readString(context,DEFAULT_SP_NAME, key, null);
        }
        return value;
    }

    /**
     * 根据key，按存储层级获取对象类型的参数值
     *
     * @param key 查找的key
     */
    public static Object getObject(Context context, String key) {
        Object value = TempDataHolder.getInstance().get(key);
        if (value == null) {
            value = new FileStorage().readPrivateObject(context, key, Object.class);
        }
        return value;
    }

    /**
     * 删除指定key的字符串参数,深度删除。
     *
     * @param context
     * @param key
     */
    public static void removeString(Context context, String key) {
        TempDataHolder.getInstance().remove(key);
        SharedPreferencesUtil.delete(context,DEFAULT_SP_NAME, key);
    }

    /**
     * 删除指定key的对象参数,深度删除。
     *
     * @param context
     * @param key
     */
    public static void removeObject(Context context, String key) {
        TempDataHolder.getInstance().remove(key);
        new FileStorage().deletePrivate(context, key);
    }
}
