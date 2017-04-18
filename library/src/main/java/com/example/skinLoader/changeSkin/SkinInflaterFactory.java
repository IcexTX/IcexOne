package com.example.skinLoader.changeSkin;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.view.LayoutInflaterFactory;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.example.skinLoader.attr.AttrFactory;
import com.example.skinLoader.attr.DynamicAttr;
import com.example.skinLoader.attr.SkinAttr;
import com.example.skinLoader.config.SkinConfig;
import com.example.skinLoader.storage.SkinItem;
import com.example.skinLoader.util.ListUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * ProjectName：IcexOne
 * Describe：自定义View的InflaterFactory,替换默认Factory
 * Author：Icex
 * CreationTime：2017/1/12
 *
 * @Version
 */
public class SkinInflaterFactory implements LayoutInflaterFactory {

    private static String TAG = "SkinInflaterFactory";

    /**
     * 存储需要更换皮肤需求的View以及对应的属性集合
     */
    private List<SkinItem> skinItems = new ArrayList<>();

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {

        //检测当前View是否需要更换皮肤
        boolean isSkinEnable = attrs.getAttributeBooleanValue(SkinConfig.NAMESPACE, SkinConfig.ATTR_SKIN_ENABLE, false);

        if (!isSkinEnable) {
            //返回空就使用默认的InflaterFactory
            return null;
        }

        View view = createView(context, name, attrs);
        if (view == null) {
            //没有找到这个view
            return null;
        }
        return null;
    }


    /**
     * 通过name去实例化一个View
     *
     * @param context
     * @param name         要被实例化View的全名.
     * @param attributeSet View在布局文件中的XML的属性
     * @return View
     */
    private View createView(Context context, String name, AttributeSet attributeSet) {
        View view = null;
        try {
            //查找字符‘.’的位置，不存在返回-1
            if (-1 == name.indexOf('.')) {
                if ("View".equals(name)) {
                    view = LayoutInflater.from(context).createView(name, "android.view", attributeSet);
                }
                if (view == null) {
                    view = LayoutInflater.from(context).createView(name, "android.widget", attributeSet);
                }
                if (view == null) {
                    view = LayoutInflater.from(context).createView(name, "android.webkit", attributeSet);
                }
            } else {
                view = LayoutInflater.from(context).createView(name, null, attributeSet);
            }
        } catch (Exception e) {
            view = null;
        }
        return view;
    }

    /**
     * 收集可更换皮肤的属性
     *
     * @param context
     * @param attributeSet
     * @param view
     */
    private void parseSkinAttr(Context context, AttributeSet attributeSet, View view) {
        //存储View中可更换皮肤属性集合
        List<SkinAttr> viewAttrs = new ArrayList<>();
        //遍历View中的属性
        for (int i = 0; i < attributeSet.getAttributeCount(); i++) {
            //属性名
            String attrName = attributeSet.getAttributeName(i);
            //属性值
            String attrValue = attributeSet.getAttributeValue(i);
            //检测属性是否被支持
            if (!AttrFactory.isSupportedAttr(attrName)) {
                continue;
            }
            //判断字符串attrValue是否以@开头，如@color/red_text
            if (attrValue.startsWith("@")) {
                try {
                    //资源id，如color/red
                    int id = Integer.parseInt(attrValue.substring(1));
                    //获取资源id对应的字符名字，如red_text
                    String entryName = context.getResources().getResourceEntryName(id);
                    //获取资源id对应的类型名字，如color
                    String typeName = context.getResources().getResourceTypeName(id);
                    SkinAttr skinAttr = AttrFactory.getAttrList(attrName, id, entryName, typeName);
                    if (skinAttr != null) {
                        viewAttrs.add(skinAttr);
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        if (!ListUtils.isEmpty(viewAttrs)) {
            SkinItem skinItem = new SkinItem();
            skinItem.view = view;
            skinItem.attrs = viewAttrs;
            skinItems.add(skinItem);
            //如果当前皮肤来自外部
            if (SkinManager.getInstance().isExternalSkin()) {
                skinItem.apply();
            }
        }
    }

    /**
     * 应用皮肤
     */
    public void applySkin() {
        if (ListUtils.isEmpty(skinItems)) {
            return;
        }
        for (SkinItem item : skinItems) {
            if (item.view == null) {
                continue;
            }
            item.apply();
        }
    }

    /**
     * 清除有皮肤更改的view以及对应属性
     */
    public void clean() {
        if (ListUtils.isEmpty(skinItems)) {
            return;
        }
        for (SkinItem item : skinItems) {
            if (item.view == null) {
                continue;
            }
            item.clean();
        }
    }

    /**
     * 添加需要更改皮肤的view
     */
    public void addSkinView(SkinItem skinItem) {
        skinItems.add(skinItem);
    }

    /**
     * 动态添加那些有皮肤更改需求的View，及其对应的属性
     *
     * @param context
     * @param view
     * @param attrName       属性名
     * @param attrValueResId 属性资源id
     */
    public void dynamicAddSkinEnableView(Context context, View view, String attrName, int attrValueResId) {
        //属性集合
        List<SkinAttr> viewAttrs = new ArrayList<>();
        //属性名称
        String entryName = context.getResources().getResourceEntryName(attrValueResId);
        //属性类型
        String typeName = context.getResources().getResourceTypeName(attrValueResId);
        SkinAttr skinAttr = AttrFactory.getAttrList(attrName, attrValueResId, entryName, typeName);
        viewAttrs.add(skinAttr);
        SkinItem skinItem = new SkinItem();
        skinItem.view = view;
        skinItem.attrs = viewAttrs;
        skinItem.apply();
        addSkinView(skinItem);
    }

    /**
     * 动态添加那些有皮肤更改需求的View，及其对应的属性集合
     *
     * @param context
     * @param view
     * @param pDAttrs
     */
    public void dynamicAddSkinEnableView(Context context, View view, List<DynamicAttr> pDAttrs) {
        List<SkinAttr> viewAttrs = new ArrayList<>();
        SkinItem skinItem = new SkinItem();
        skinItem.view = view;
        for (DynamicAttr dAttr : pDAttrs) {
            String entryName = context.getResources().getResourceEntryName(dAttr.refResId);
            String typeName = context.getResources().getResourceTypeName(dAttr.refResId);
            SkinAttr mSkinAttr = AttrFactory.getAttrList(dAttr.attrName, dAttr.refResId, entryName, typeName);
            viewAttrs.add(mSkinAttr);
        }
        skinItem.attrs = viewAttrs;
        skinItem.apply();
        addSkinView(skinItem);
    }
}
