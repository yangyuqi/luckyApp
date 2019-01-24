package com.yunqilai.delivery.manager;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * SharedPreferences 数据存储对象
 */
public class SharedPreferencesUtil {
    private static final String FILE_NAME = "hxd_share_data";

    public static String accessToken = "accessToken";
    public static String userName = "userName";
    public static String role = "role";
    public static String mobile = "mobile";
    public static String orderId = "orderId";
    public static String isFristUse = "isFristUse";

    public static String icon = "icon";

    public static void put(Context context, String key, Object object)
    {
        if (object instanceof String)
        {
            save(context,FILE_NAME,key, (String) object);
        } else if (object instanceof Integer)
        {
            save(context,FILE_NAME,key, (Integer) object);
        } else if (object instanceof Boolean)
        {
            save(context,FILE_NAME,key, (Boolean) object);
        } else if (object instanceof Float)
        {
            save(context,FILE_NAME,key, (Float) object);
        } else if (object instanceof Long)
        {
            save(context,FILE_NAME,key, (Long) object);
        } else
        {
            save(context,FILE_NAME,key, object.toString());
        }
    }

    public static Object get(Context context, String key, Object defaultObject)
    {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);

        if (defaultObject instanceof String)
        {
            return sp.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer)
        {
            return sp.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean)
        {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float)
        {
            return sp.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long)
        {
            return sp.getLong(key, (Long) defaultObject);
        }

        return null;
    }

    /**
     * 移除某个key值已经对应的值
     * @param context
     * @param key
     */
    public static void remove(Context context, String key)
    {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 清除所有数据
     * @param context
     */
    public static void clear(Context context)
    {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }


    /**
     * 存储 String 类型数据
     *
     * @param sharedPName sharedpreferences名称
     * @param key         键
     * @param value       值
     * @return true-存储成功，false-存储失败
     */
    public static boolean save(Context context, String sharedPName, String key, String value) {
        SharedPreferences.Editor editor =
                context.getSharedPreferences(sharedPName, Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        return editor.commit();
    }

    /**
     * 存储 int 类型数据
     *
     * @param sharedPName sharedpreferences名称
     * @param key         键
     * @param value       值
     * @return true-存储成功，false-存储失败
     */
    public static boolean save(Context context, String sharedPName, String key, int value) {
        SharedPreferences.Editor editor =
                context.getSharedPreferences(sharedPName, Context.MODE_PRIVATE).edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    /**
     * 存储 Boolean 类型数据
     *
     * @param sharedPName sharedpreferences名称
     * @param key         键
     * @param value       值
     * @return true-存储成功，false-存储失败
     */
    public static boolean save(Context context, String sharedPName, String key, boolean value) {
        SharedPreferences.Editor editor =
                context.getSharedPreferences(sharedPName, Context.MODE_PRIVATE).edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    /**
     * 存储 float 类型数据
     *
     * @param sharedPName sharedpreferences名称
     * @param key         键
     * @param value       值
     * @return true-存储成功，false-存储失败
     */
    public static boolean save(Context context, String sharedPName, String key, float value) {
        SharedPreferences.Editor editor =
                context.getSharedPreferences(sharedPName, Context.MODE_PRIVATE).edit();
        editor.putFloat(key, value);
        return editor.commit();
    }

    /**
     * 存储 long 类型数据
     *
     * @param sharedPName sharedpreferences名称
     * @param key         键
     * @param value       值
     * @return true-存储成功，false-存储失败
     */
    public static boolean save(Context context, String sharedPName, String key, long value) {
        SharedPreferences.Editor editor =
                context.getSharedPreferences(sharedPName, Context.MODE_PRIVATE).edit();
        editor.putLong(key, value);
        return editor.commit();
    }

    /**
     * 获取 String 类型的数据
     *
     * @param sharedPName  sharedpreferences名称
     * @param key          键
     * @param defaultValue 默认值
     * @return
     */
    public static String readString(Context context, String sharedPName, String key, String defaultValue) {
        SharedPreferences sharedPreferences =
                context.getSharedPreferences(sharedPName, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, defaultValue);
    }

    /**
     * 获取 int 类型的数据
     *
     * @param sharedPName  sharedpreferences名称
     * @param key          键
     * @param defaultValue 默认值
     * @return
     */
    public static int readInt(Context context, String sharedPName, String key, int defaultValue) {
        SharedPreferences sharedPreferences =
                context.getSharedPreferences(sharedPName, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, defaultValue);
    }

    /**
     * 获取 boolean 类型的数据
     *
     * @param sharedPName  sharedpreferences名称
     * @param key          键
     * @param defaultValue 默认值
     * @return
     */
    public static boolean readBoolean(Context context, String sharedPName, String key, boolean defaultValue) {
        SharedPreferences sharedPreferences =
                context.getSharedPreferences(sharedPName, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    /**
     * 获取 float 类型的数据
     *
     * @param sharedPName  sharedpreferences名称
     * @param key          键
     * @param defaultValue 默认值
     * @return
     */
    public static float readFloat(Context context, String sharedPName, String key, float defaultValue) {
        SharedPreferences sharedPreferences =
                context.getSharedPreferences(sharedPName, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(key, defaultValue);
    }

    /**
     * 获取 long 类型的数据
     *
     * @param sharedPName  sharedpreferences名称
     * @param key          键
     * @param defaultValue 默认值
     * @return
     */
    public static long readLong(Context context, String sharedPName, String key, long defaultValue) {
        SharedPreferences sharedPreferences =
                context.getSharedPreferences(sharedPName, Context.MODE_PRIVATE);
        return sharedPreferences.getLong(key, defaultValue);
    }

    /**
     * 删除指定 key 的值
     *
     * @param sharedPName sharedpreferences名称
     * @param key         键
     * @return true-删除成功，false-删除失败
     */
    public static boolean delete(Context context, String sharedPName, String key) {
        SharedPreferences.Editor editor =
                context.getSharedPreferences(sharedPName, Context.MODE_PRIVATE).edit();
        editor.remove(key);
        return editor.commit();
    }

    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     *
     * @author zhy
     *
     */
    private static class SharedPreferencesCompat
    {
        private static final Method sApplyMethod = findApplyMethod();

        /**
         * 反射查找apply的方法
         *
         * @return
         */
        @SuppressWarnings({ "unchecked", "rawtypes" })
        private static Method findApplyMethod()
        {
            try
            {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e)
            {
            }

            return null;
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         *
         * @param editor
         */
        public static void apply(SharedPreferences.Editor editor)
        {
            try
            {
                if (sApplyMethod != null)
                {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalArgumentException e)
            {
            } catch (IllegalAccessException e)
            {
            } catch (InvocationTargetException e)
            {
            }
            editor.commit();
        }
    }

}
