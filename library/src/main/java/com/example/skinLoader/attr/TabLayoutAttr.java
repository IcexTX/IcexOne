package com.example.skinLoader.attr;

import android.content.res.ColorStateList;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.View;

import com.example.skinLoader.changeSkin.SkinManager;


/**
 * TabLayoutAttr样式重置
 */
public class TabLayoutAttr extends SkinAttr {
    @Override
    public void apply(View view) {
        if (view instanceof TabLayout) {
            Log.i("TabLayoutAttr", "apply");
            TabLayout tabLayout = (TabLayout) view;
            if (RES_TYPE_NAME_COLOR.equals(attrValueTypeName)) {
                Log.i("TabLayoutAttr", "apply color");
                int color = SkinManager.getInstance().getColor(attrValueRefId);
                tabLayout.setSelectedTabIndicatorColor(color);
                tabLayout.setTabTextColors(ColorStateList.valueOf(color));
            } else if (RES_TYPE_NAME_DRAWABLE.equals(attrValueTypeName)) {
                Log.i("TabLayoutAttr", "apply drawable");
                //  tv.setDivider(SkinManager.getInstance().getDrawable(attrValueRefId));
            }
        }
    }
}
