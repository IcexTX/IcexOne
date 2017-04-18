package com.example.icex.icexone.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.example.icex.icexone.bean.ReplaceSkinBean;
import com.example.library.base.BaseAdapter;
import com.example.library.base.BaseViewHolder;
import com.example.icex.icexone.R;

import java.util.List;

/**
 * ProjectName：IcexOne
 * Describe：更换皮肤适配器
 * Author：Icex
 * CreationTime：2017/2/20
 */

public class ReplaceSkinAdapter extends BaseAdapter<ReplaceSkinBean> {

    private int layout_title;
    private int tv_skin_title;
    private int imageView;
    private int tv_skin_name;
    private String showType;
    private String name;
    private ReplaceSkinAble callBack;


    public ReplaceSkinAdapter(Context context, List<ReplaceSkinBean> list) {
        super(context, list);
    }

    @Override
    protected int setView(int viewType) {
        return R.layout.layout_replace_skin_item;
    }

    @Override
    protected void initView() {
        layout_title = R.id.layout_title;
        tv_skin_title = R.id.tv_skin_title;
        imageView = R.id.imageView;
        tv_skin_name = R.id.tv_skin_name;
    }

    @Override
    protected void onBindDataToView(BaseViewHolder holder, final ReplaceSkinBean bean, int position) {
        if (!TextUtils.isEmpty(showType) && showType.equals(bean.getTitle())) {
            holder.getLayoutId(layout_title).setVisibility(View.INVISIBLE);
            holder.setText(tv_skin_title, "");
        } else {
            holder.getLayoutId(layout_title).setVisibility(View.VISIBLE);
            holder.setText(tv_skin_title, bean.getTitle());
        }
        showType = bean.getTitle();
        holder.setImageBackColor(imageView, getColor(bean.getSkinName()));
        holder.setText(tv_skin_name, bean.getSkinName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.skinName(bean.getName());
                }
            }
        });
    }

    public void setName(String name) {
        this.showType = "";
        this.name = name;
        notifyDataSetChanged();
    }

    public interface ReplaceSkinAble {
        void skinName(String name);
    }

    private int getColor(String name) {
        System.out.println(name);
        if (TextUtils.isEmpty(name)) {
            return R.color.colorPrimary;
        }
        if (name.equals("skin_green.skin")) {
            return R.color.colorPrimary;
        } else if (name.equals("skin_brown.skin")) {
            return R.color.brown;
        } else if (name.equals("skin_black.skin")) {
            return R.color.black_percentage;
        } else {
            return R.color.colorPrimary;
        }
    }

    public void setCallBack(ReplaceSkinAble back) {
        this.callBack = back;
    }
}
