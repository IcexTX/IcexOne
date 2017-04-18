package com.example.skinLoader.attr;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.skinLoader.changeSkin.SkinManager;
import com.example.skinLoader.util.Logs;

/**
 * ProjectName：IcexOne
 * Describe：
 * Author：Icex
 * CreationTime：2017/1/12
 *
 * @Version
 */
public class BackgroundAttr extends SkinAttr {

    @Override
    public void apply(View view) {

        if (RES_TYPE_NAME_COLOR.equals(attrValueTypeName)) {
            int color = SkinManager.getInstance().getColor(attrValueRefId);
            //instanceof 指出对象是否是特定类的一个实例,这里判断view是否为CardView的一个实例
            if (view instanceof CardView) {
                CardView cardView = (CardView) view;
                //给CardView设置背景色应该使用cardBackgroundColor，直接使用background就会没有圆角效果
                cardView.setCardBackgroundColor(color);
            } else {
                view.setBackgroundColor(color);
            }
            Logs.i("applyAttr", "apply as color");
        } else if (RES_TYPE_NAME_DRAWABLE.equals(attrValueTypeName)) {
            Drawable bg = SkinManager.getInstance().getDrawable(attrValueRefId);
            // view.setBackground(bg);
            view.setBackgroundDrawable(bg);
            Logs.i("applyAttr", "apply as drawable");
        }
    }
}
