package com.example.skinLoader.base;

import android.app.Application;

import com.example.skinLoader.changeSkin.SkinManager;

/**
 * ProjectName：IcexOne
 * Describe：换肤Application,初始化SkinManager
 * Author：Icex
 * CreationTime：2017/1/12
 */

public class SkinBaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initSkinLoader();
    }

    /**
     * 必须在Application里面初始化皮肤加载
     */
    private void initSkinLoader() {
        SkinManager.getInstance().init(this);
        SkinManager.getInstance().load();
    }
}
