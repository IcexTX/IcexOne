package com.example.library.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * ProjectName：IcexOne
 * Describe：数据储存 （commit是同步储存并且返回执行结果，apply是异步储存，不返回结果）
 * Author：Icex
 * CreationTime：2017/1/16
 */

public class PreferencesUtils {

    private static final String PREFEREBCE_NAME = "ice_skin";

    /**
     * 获取SharedPreferences实例
     *
     * @param context
     * @return
     */
    public static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREFEREBCE_NAME, Context.MODE_PRIVATE);
    }

    /**
     * 获取key的对应值
     *
     * @param context
     * @param key
     * @param value   默认值
     * @return
     */
    public static String getString(Context context, String key, String value) {
        return getPreferences(context).getString(key, value);
    }


    /**
     * 获取key的对应值
     *
     * @param context
     * @param key
     * @param value   默认值
     * @return
     */
    public static int getInt(Context context, String key, int value) {
        return getPreferences(context).getInt(key, value);
    }


    /**
     * 获取key的对应值
     *
     * @param context
     * @param key
     * @param value   默认值
     * @return
     */
    public static boolean getBoolean(Context context, String key, boolean value) {
        return getPreferences(context).getBoolean(key, value);
    }

    /**
     * 根据key以及value储存
     * @param context
     * @param key
     * @param value
     */
    public static void saveStringValue(Context context, String key, String value) {
        getPreferences(context).edit().putString(key, value).commit();
    }

    /**
     * 根据key以及value储存
     * @param context
     * @param key
     * @param value
     */
    public static void saveIntValue(Context context, String key, int value) {
        getPreferences(context).edit().putInt(key, value).commit();
    }

    /**
     * 根据key以及value储存
     * @param context
     * @param key
     * @param value
     */
    public static void saveBooleanValue(Context context, String key, boolean value) {
        getPreferences(context).edit().putBoolean(key, value).commit();
    }
}
