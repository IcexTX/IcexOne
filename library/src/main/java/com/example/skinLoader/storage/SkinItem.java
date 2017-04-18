package com.example.skinLoader.storage;

import android.view.View;

import com.example.skinLoader.attr.SkinAttr;

import java.util.ArrayList;
import java.util.List;

/**
 * ProjectName：IcexOne
 * Describe：用于存储需要更换皮肤的view以及对应属性
 * Author：Icex
 * CreationTime：2017/1/12
 */

public class SkinItem {

    public View view;

    public List<SkinAttr> attrs;

    public SkinItem() {
        attrs = new ArrayList<>();
    }

    /**
     * 更换皮肤
     */
    public void apply() {
        if (attrs == null || attrs.isEmpty()) {
            return;
        }
        for (SkinAttr attr : attrs) {
            attr.apply(view);
        }
    }

    public void clean() {
        if (attrs == null || attrs.isEmpty()) {
            return;
        }
        for (SkinAttr attr : attrs) {
            attr = null;
        }
    }

    @Override
    public String toString() {
        return "SkinItem [view=" + view.getClass().getSimpleName() + ", attrs=" + attrs + "]";
    }

}
