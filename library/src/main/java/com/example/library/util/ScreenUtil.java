package com.example.library.util;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * ProjectName：IcexOne
 * Describe：关于屏幕的相关工具类
 * Author：Icex
 * CreationTime：2017/1/16
 */

public class ScreenUtil {

    public static DisplayMetrics getDisplayMetrics(Context context) {
        return context.getResources().getDisplayMetrics();
    }

    /**
     * 获取屏幕的宽度（像素）
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        return getDisplayMetrics(context).widthPixels;
    }

    /**
     * 获取屏幕的高度（像素）
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        return getDisplayMetrics(context).heightPixels;
    }


    /**
     * 获取屏幕密度（像素比例：0.75/1.0/1.5/2.0）
     *
     * @param context
     * @return
     */
    public static float getDensity(Context context) {
        return getDisplayMetrics(context).density;
    }

    /**
     * 获取屏幕密度（每寸像素： 120/160/240/320）
     *
     * @param context
     * @return
     */
    public static float getDensityDpi(Context context) {
        return getDisplayMetrics(context).densityDpi;
    }

    /**
     * 获取屏幕宽的密度
     *
     * @param context
     * @return
     */
    public static float getScreenXdpi(Context context) {
        return getDisplayMetrics(context).xdpi;
    }

    /**
     * 获取屏幕高的密度（每寸像素： 120/160/240/320）
     *
     * @param context
     * @return
     */
    public static float getScreenYdpi(Context context) {
        return getDisplayMetrics(context).ydpi;
    }

    /**
     *dip转为px
     * @param context
     * @param dip
     * @return
     */
    public static int dip2px(Context context, int dip) {
        return (int) (dip * getDensity(context) + 0.5f * (dip > 0 ? 1 : -1));
    }


    /**
     *px转为dip
     * @param context
     * @param px
     * @return
     */
    public static int px2dip(Context context, int px) {
        return (int) (px / getDensity(context) + 0.5f * (px > 0 ? 1 : -1));
    }


    /**
     * 转换sp为px
     * @param context
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        return (int) (spValue * getDisplayMetrics(context).scaledDensity + 0.5f);
    }

    /**
     * 转换px为sp
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        return (int) (pxValue / getDisplayMetrics(context).scaledDensity + 0.5f);
    }

}
