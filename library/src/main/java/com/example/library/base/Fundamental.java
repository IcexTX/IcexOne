package com.example.library.base;


import android.app.Activity;
import android.app.AlertDialog;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.example.library.util.ScreenUtil;

import java.util.Arrays;
import java.util.List;

/**
 * 处理类的基类
 */
public abstract class Fundamental {

    protected Activity activity;

    public Fundamental(Activity activity) {
        this.activity = activity;
    }

    /**
     * 利用泛型获取布局控件
     *
     * @param id  布局id
     * @param <E>
     * @return 实例化布局
     */
    protected final <E extends View> E getView(int id) {
        try {
            return (E) activity.getLayoutInflater().inflate(id, null);
        } catch (ClassCastException e) {
            throw e;
        }
    }

    /**
     * 利用泛型获取获取控件id
     *
     * @param view 布局文件
     * @param id   控件id
     * @param <E>
     * @return 实例化控件
     */
    protected final <E extends View> E getWidget(View view, int id) {
        try {
            return (E) view.findViewById(id);
        } catch (ClassCastException e) {
            throw e;
        }
    }

    /**
     * 返回对比值
     */
    protected String getValue(String value) {
        return TextUtils.isEmpty(value) ? "" : value;
    }

    /**
     * 自定义大小对话框
     */
    protected AlertDialog getDefinedDialog(View layout, double width, double height, boolean Outside, boolean setCancel) {
        layout.setFocusable(true);
        layout.setFocusableInTouchMode(true);
        AlertDialog dialog = new AlertDialog.Builder(activity).create();
        if (width > 0) {
            layout.setMinimumWidth((int) (ScreenUtil.getScreenWidth(activity) * width));
        }
        if (height > 0) {
            layout.setMinimumHeight((int) (ScreenUtil.getScreenHeight(activity) * height));
        }
        //去除边框
        dialog.setView(layout, 0, 0, 0, 0);
        dialog.setCanceledOnTouchOutside(Outside);
        dialog.setCancelable(setCancel);
        return dialog;
    }

    /**
     * 对话框
     */
    protected AlertDialog getDialog(boolean Outside, boolean setCancel) {
        AlertDialog dialog = new AlertDialog.Builder(activity).create();
        dialog.setCanceledOnTouchOutside(Outside);
        dialog.setCancelable(setCancel);
        return dialog;
    }

    protected void showToast(String content) {
        Toast toast = Toast.makeText(activity, content, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * 获取颜色
     */
    protected final int getColors(int id) {
        return ContextCompat.getColor(activity, id);
    }

    /**
     * 获取字符
     */
    protected final List<String> getList(int id) {
        return Arrays.asList(activity.getResources().getStringArray(id));
    }
}
