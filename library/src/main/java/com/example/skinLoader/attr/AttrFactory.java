package com.example.skinLoader.attr;

import android.util.Log;


/**
 * ProjectName：IcexOne
 * Describe：属性工厂
 * Author：Icex
 * CreationTime：2017/1/12
 *
 * @Version
 */
public class AttrFactory {

    private static String TAG = "AttrFactory";

    //背景颜色
    public static final String BACKGROUND = "background";
    //字体颜色
    public static final String TEXT_COLOR = "textColor";
    //TabLayout的下划线颜色
    public static final String TAB_INDICATOR_COLOR = "tabIndicatorColor";
    //可折叠式工具栏的背景颜色
    public static final String CONTENT_SCRIM_COLOR = "contentScrimColor";
    //悬浮按钮，backgroundTint是指给当前控件的背景颜色着色
    public static final String BACKGROUND_TINTLIST = "backgroundTint";
    //导航栏菜单
    public static final String NAVIGATION_VIEW_MENU = "navigationViewMenu";

    /**
     * @param attrName 属性名
     * @param attrValueRefId 属性值id
     * @param attrValueRefName 属性值名称
     * @param typeName 类型名
     * @return
     */
    public static SkinAttr getAttrList(String attrName, int attrValueRefId, String attrValueRefName, String typeName) {
        Log.i(TAG, "attrName:" + attrName);
        SkinAttr skinAttr;

        if (BACKGROUND.equals(attrName)) {
            skinAttr = new BackgroundAttr();
            Log.i(TAG, "create:BackgroundAttr");
        } else if (TEXT_COLOR.equals(attrName)) {
            skinAttr = new TextColorAttr();
            Log.i(TAG, "create:TextColorAttr");
        } else if (TAB_INDICATOR_COLOR.equals(attrName)) {
            skinAttr = new TabLayoutAttr();
            Log.i(TAG, "create:TabLayoutAttr");
        } else if (CONTENT_SCRIM_COLOR.equals(attrName)) {
            skinAttr = new CollapsingToolbarLayoutAttr();
            Log.i(TAG, "create:CollapsingToolbarLayoutAttr");
        } else if (BACKGROUND_TINTLIST.equals(attrName)) {
            skinAttr = new FabButtonAttr();
            Log.i(TAG, "create:FabButtonAttr");
        } else if (NAVIGATION_VIEW_MENU.equals(attrName)) {
            skinAttr = new NavigationViewAttr();
            Log.i(TAG, "create:FabButtonAttr");
        } else {
            return null;
        }
        skinAttr.attrName = attrName;
        skinAttr.attrValueRefId = attrValueRefId;
        skinAttr.attrValueRefName = attrValueRefName;
        skinAttr.attrValueTypeName = typeName;
        return skinAttr;
    }

    /**
     * 检测属性是否被支持
     *
     * @param attrName
     * @return true : supported <br>
     * false: not supported
     */
    public static boolean isSupportedAttr(String attrName) {
        if (BACKGROUND.equals(attrName)) {
            return true;
        }
        if (TEXT_COLOR.equals(attrName)) {
            return true;
        }
        if (TAB_INDICATOR_COLOR.equals(attrName)) {
            return true;
        }
        if (CONTENT_SCRIM_COLOR.equals(attrName)) {
            return true;
        }
        if (BACKGROUND_TINTLIST.equals(attrName)) {
            return true;
        }
        return NAVIGATION_VIEW_MENU.equals(attrName);
    }
}
