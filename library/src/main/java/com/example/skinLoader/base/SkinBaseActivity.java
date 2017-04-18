package com.example.skinLoader.base;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.skinLoader.attr.DynamicAttr;
import com.example.skinLoader.changeSkin.SkinInflaterFactory;
import com.example.skinLoader.changeSkin.SkinManager;
import com.example.skinLoader.listener.IDynamicNewView;
import com.example.skinLoader.listener.ISkinUpdate;
import com.example.skinLoader.statusBar.StatusBarBackground;

import java.util.List;

/**
 * ProjectName：IcexOne
 * Describe：所有Activity需要换肤的基类
 * Author：Icex
 * CreationTime：2017/1/12
 */

public class SkinBaseActivity extends AppCompatActivity implements ISkinUpdate, IDynamicNewView {

    //当前activity是否需要响应皮肤更换
    private boolean isChangeSkin = true;
    private SkinInflaterFactory inflaterFactory;

    /**
     * 更换皮肤需要在onCreate之前
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        inflaterFactory = new SkinInflaterFactory();
        LayoutInflaterCompat.setFactory(getLayoutInflater(), inflaterFactory);
        super.onCreate(savedInstanceState);
        changeStatuColor();
    }

    /**
     * 设置状态栏的颜色
     */
    public void changeStatuColor() {
        //如果android版本>=4.4则更换状态栏颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int color = SkinManager.getInstance().getColorPrimaryDark();
            StatusBarBackground barBackground = new StatusBarBackground(this, color);
            if (color != -1) {
                barBackground.setStatusBarbackColor();
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        //设置回调接口对象
        SkinManager.getInstance().attach(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SkinManager.getInstance().detach(this);
        inflaterFactory.clean();
    }

    @Override
    public void onThemeUpdate() {
        if (!isChangeSkin) {
            return;
        }
        inflaterFactory.applySkin();
        changeStatuColor();
    }

    @Override
    public void dynamicAddView(View view, List<DynamicAttr> pDAttrs) {
        inflaterFactory.dynamicAddSkinEnableView(this, view, pDAttrs);
    }

    protected void dynamicAddSkinEnableView(View view, String attrName, int attrValueResId) {
        inflaterFactory.dynamicAddSkinEnableView(this, view, attrName, attrValueResId);
    }

    protected void dynamicAddSkinEnableView(View view, List<DynamicAttr> pDAttrs) {
        inflaterFactory.dynamicAddSkinEnableView(this, view, pDAttrs);
    }

    final protected void enableResponseOnSkinChanging(boolean enable) {
        isChangeSkin = enable;
    }
}
