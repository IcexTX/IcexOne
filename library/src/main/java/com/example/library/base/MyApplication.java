package com.example.library.base;

import android.app.Activity;

import com.example.library.httpRequest.HttpUtils;
import com.example.skinLoader.base.SkinBaseApplication;

import java.util.Stack;

/**
 * ProjectName：IcexOne
 * Describe：
 * Author：Icex
 * CreationTime：2017/1/16
 */

public class MyApplication extends SkinBaseApplication {

    private Stack<Activity> activityStack;

    @Override
    public void onCreate() {
        super.onCreate();
        HttpUtils.getInstance().setContext(getApplicationContext());
    }

    /**
     * 添加activity
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * 删除指定的activity
     *
     * @param activity
     */
    public void deleteActivity(Activity activity) {
        if (activityStack != null && activityStack.size() > 0) {
            if (activity != null) {
                activity.finish();
                activityStack.remove(activity);
            }
        }
    }

    /**
     * 删除所有的activity
     */
    public void exitAllActivity() {
        if (activityStack != null) {
            while (activityStack.size() > 0) {
                Activity activity = activityStack.lastElement();
                if (activity != null) {
                    deleteActivity(activity);
                } else {
                    break;
                }
            }
        }
    }

    /**
     * 退出程序
     */
    public void exitApplication() {
        //退出所有的activity
        exitAllActivity();
        //退出程序
        System.exit(0);

    }
}
