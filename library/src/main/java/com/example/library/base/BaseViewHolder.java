package com.example.library.base;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * ProjectName：IcexOne
 * Describe：ViewHolder通用类
 * Author：Icex
 * CreationTime：2017/1/16
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {

    private final SparseArray<View> viewSparseArray;

    public BaseViewHolder(View itemView) {
        super(itemView);
        this.viewSparseArray = new SparseArray<>();
    }

    /**
     * 获取控件实例
     *
     * @param id
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int id) {
        View view = viewSparseArray.get(id);
        if (view == null) {
            view = itemView.findViewById(id);
            viewSparseArray.put(id, view);
        }
        return (T) view;
    }


    /**
     * 根据控件id，设置字体颜色
     *
     * @param id
     * @param color
     */
    public void setColor(int id, int color) {
        TextView textView = getView(id);
        textView.setTextColor(color);
    }


    /**
     * 根据控件id，设置控件背景样式
     *
     * @param id
     * @param backId
     */
    public void setBackgroundResource(int id, int backId) {
        LinearLayout layout = getView(id);
        layout.setBackgroundResource(backId);
    }

    /**
     * 根据控件id，设置控件背景样式
     *
     * @param id
     * @param backId
     */
    public void setTextBackground(int id, int backId) {
        TextView textView = getView(id);
        textView.setBackgroundResource(backId);
    }

    /**
     * 根据控件id，设置控件背景样式
     *
     * @param id
     * @param background
     */
    public void setImageBackground(int id, Drawable background) {
        ImageView imageView = getView(id);
        imageView.setBackground(background);
    }


    /**
     * 根据控件id，设置控件背景样式
     *
     * @param id
     * @param color
     */
    public void setImageBackColor(int id, int color) {
        ImageView imageView = getView(id);
        imageView.setBackgroundColor(color);
    }

    /**
     * 控件id
     *
     * @param id
     */
    public LinearLayout getLayoutId(int id) {
        LinearLayout layout = getView(id);
        return layout;
    }

    /**
     * 控件id
     *
     * @param id
     */
    public ImageButton getImageButtonId(int id) {
        ImageButton imageButton = getView(id);
        return imageButton;
    }

    /**
     * 根据控件id，设置背景样式
     *
     * @param id
     * @param resId
     */
    public void setBackground(int id, int resId) {
        LinearLayout layout = getView(id);
        layout.setBackgroundResource(resId);
    }

    /**
     * 获取值
     *
     * @param value
     * @return
     */
    public String getValue(String value) {
        return TextUtils.isEmpty(value) ? "" : value;
    }

    /**
     * 根据控件id，设置控件的text
     *
     * @param id
     * @param value
     */
    public void setText(int id, String value) {
        TextView textView = getView(id);
        textView.setText(getValue(value));
    }

    /**
     * 根据控件id，获取Text控件
     *
     * @param id
     */
    public TextView getText(int id) {
        TextView textView = getView(id);
        return textView;
    }

    /**
     * 控件id
     *
     * @param id
     */
    public ImageView getImageId(int id) {
        ImageView imageView = getView(id);
        return imageView;
    }

    /**
     * 加载drawable中的图片
     *
     * @param viewId
     * @param resId
     */
    public void setImage(int viewId, int resId) {
        ImageView imageView = getView(viewId);
        imageView.setImageResource(resId);
    }

    /**
     * 加载网络上的图片
     *
     * @param viewId
     * @param url
     */
    public void setImageFromInternet(int viewId, String url) {
        ImageView iv = getView(viewId);
      /*  ImageRequest imageRequest = new ImageRequest.Builder().imgView(iv).url(url).create();
        ImageLoader.getProvider().loadImage(imageRequest);*/
    }
}
