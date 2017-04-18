package com.example.library.base;

import android.graphics.drawable.Drawable;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.skinLoader.base.SkinBaseFragment;

import java.util.Date;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * ProjectName：IcexOne
 * Describe：Fragment的基类
 * Author：Icex
 * CreationTime：2017/1/16
 */

public abstract class BaseFragment extends SkinBaseFragment {

    protected boolean isVisible = false;

    protected View parentView;

    protected abstract int setView();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void getData();

    protected SimpleDateFormat dateFormat;

    protected CompositeSubscription compositeSubscription;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        parentView = getView(inflater, setView());
        return parentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        /**
         * 判断视图是否可见
         */
        if (getUserVisibleHint()) {
            isVisible = true;
            getData();
        } else {
            isVisible = false;
        }
    }

    /**
     * @param layout LayoutInflater
     * @param id     布局id
     * @param <E>    类型
     * @return 返回view
     */
    protected final <E extends View> E getView(LayoutInflater layout, int id) {
        try {
            return (E) layout.inflate(id, null);
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
            return (E) parentView.findViewById(id);
        } catch (ClassCastException e) {
            throw e;
        }
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
            return (E) getActivity().getLayoutInflater().inflate(id, null);
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
        return ContextCompat.getColor(getActivity(), id);
    }

    protected final Drawable getDraw(int id) {
        return ContextCompat.getDrawable(getActivity(), id);
    }

    /**
     * 提示框
     */
    protected void showToast(String content) {
        Toast toast = Toast.makeText(getActivity(), content, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, Gravity.CENTER, Gravity.CENTER);
        toast.show();
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

    /**
     * 获取现在的时间
     */
    public String getDate() {
        if (dateFormat == null) {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        }
        return dateFormat.format(new Date());
    }

    /**
     * 添加subscription
     */
    public void addSubscription(Subscription subscription) {
        if (compositeSubscription == null) {
            compositeSubscription = new CompositeSubscription();
        }
        compositeSubscription.add(subscription);
    }

    /**
     * 移除subscription
     */
    public void removeSubscription(Subscription subscription) {
        if (compositeSubscription != null && compositeSubscription.hasSubscriptions()) {
            compositeSubscription.remove(subscription);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (compositeSubscription != null && compositeSubscription.hasSubscriptions()) {
            compositeSubscription.unsubscribe();
        }
    }
}
