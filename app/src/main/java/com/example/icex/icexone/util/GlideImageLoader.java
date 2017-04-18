package com.example.icex.icexone.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.icex.icexone.R;
import com.youth.banner.loader.ImageLoader;

/**
 * ProjectName：IcexOne
 * Describe：重写图片加载
 * Author：Icex
 * CreationTime：2017/2/23
 */

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context).load(path)
                .placeholder(R.mipmap.main_friends_normal)//加载过程中显示的图片
                .error(R.mipmap.main_friends_normal)//加载图片错误是显示的图片
                .crossFade(800)//使用淡入淡出效果（动画持续时间800毫秒）
                .into(imageView);

    }
}
