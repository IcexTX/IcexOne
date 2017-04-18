package com.example.skinLoader.attr;

import android.view.View;

/**
 * ProjectName：IcexOne
 * Describe：属性
 * Author：Icex
 * CreationTime：2017/1/12
 */

public abstract class SkinAttr {
    protected static final String RES_TYPE_NAME_COLOR = "color";
    protected static final String RES_TYPE_NAME_DRAWABLE = "drawable";

    //属性名
    public String attrName;

    //属性值的引用id
    public int attrValueRefId;

    //资源文件名
    public String attrValueRefName;

    //属性引用名
    public String attrValueTypeName;

    public abstract void apply(View view);

    @Override
    public String toString() {
        return "SkinAttr \n[\nattrName=" + attrName + ", \n"
                + "attrValueRefId=" + attrValueRefId + ", \n"
                + "attrValueRefName=" + attrValueRefName + ", \n"
                + "attrValueTypeName=" + attrValueTypeName
                + "\n]";
    }
}
