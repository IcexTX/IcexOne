package com.example.library.base;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.example.skinLoader.base.SkinBaseActivity;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * ProjectName：IcexOne
 * Describe：Activity的基类
 * Author：Icex
 * CreationTime：2017/1/16
 */

public abstract class BaseActivity extends SkinBaseActivity {

    protected View parentView;
    protected MyApplication application;
    protected Intent intent;
    private CompositeSubscription subscription;

    protected abstract int setView();

    protected abstract void initView();

    protected abstract void initData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parentView = getView(setView());
        setContentView(parentView);
        intent = new Intent();
        application = (MyApplication) getApplicationContext();
        initView();
        initData();
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
            return (E) getLayoutInflater().inflate(id, null);
        } catch (ClassCastException e) {
            throw e;
        }
    }

    /**
     * 利用泛型获取布局控件
     *
     * @param activity 所在的activity
     * @param id       布局id
     * @param <E>
     * @return 实例化布局
     */
    protected final <E extends View> E getView(Activity activity, int id) {
        try {
            return (E) activity.getLayoutInflater().inflate(id, null);
        } catch (ClassCastException e) {
            throw e;
        }
    }

    /**
     * 利用泛型获取获取控件id
     *
     * @param id  控件id
     * @param <E>
     * @return 实例化控件
     */
    protected final <E extends View> E getWidget(int id) {
        try {
            return (E) findViewById(id);
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
     * 获取颜色
     *
     * @param id
     * @return
     */
    protected final int getColors(int id) {
        return ContextCompat.getColor(this, id);
    }

    protected final Drawable getDraw(int id) {
        return ContextCompat.getDrawable(this, id);
    }

    /**
     * 提示框
     */
    protected void showToast(String content) {
        Toast toast = Toast.makeText(this, content, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, Gravity.CENTER, Gravity.CENTER);
        toast.show();
    }

    /**
     * 页面跳转
     *
     * @param clazz
     */
    protected void skipIntent(Class<?> clazz) {
        intent.setClass(this, clazz);
        startActivity(intent);
    }


    /**
     * 页面跳转
     *
     * @param clazz
     */
    protected void skipIntentResult(Class<?> clazz, int code) {
        intent.setClass(this, clazz);
        startActivityForResult(intent, code);

    }

    protected void addSubscription(Subscription item) {
        if (subscription == null) {
            subscription = new CompositeSubscription();
        }
        subscription.add(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription != null && subscription.hasSubscriptions()) {
            subscription.unsubscribe();
        }
    }

    /**
     * 获取值
     *
     * @param value
     * @return
     */
    public String getValue(String value) {
        return TextUtils.isEmpty(value) ? "" : value;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        System.out.println("********************************");
        return super.onCreateOptionsMenu(menu);
    }
}
