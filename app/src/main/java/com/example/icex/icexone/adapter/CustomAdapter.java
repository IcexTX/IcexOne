package com.example.icex.icexone.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.example.icex.icexone.R;
import com.example.icex.icexone.adapter.Able.CustomAble;
import com.example.icex.icexone.util.ImgLoadUtil;
import com.example.library.base.BaseAdapter;
import com.example.library.base.BaseViewHolder;
import com.example.library.ganHuo.WelfareBean;

import java.util.List;

/**
 * ProjectName：IcexOne
 * Describe：
 * Author：Icex
 * CreationTime：2017/4/10
 */

public class CustomAdapter extends BaseAdapter<WelfareBean.ResultsEntity> {

    private boolean isAll = true;
    private CustomAble callBack;
    private int img_welfare;
    private int layout_item;
    private int layout_msg;
    private int tv_content;
    private int img_icon;
    private int tv_author;
    private int tv_type;
    private int tv_time;

    public CustomAdapter(Context context, List<WelfareBean.ResultsEntity> list, CustomAble able) {
        super(context, list);
        this.callBack = able;
    }

    @Override
    protected int setView(int viewType) {
        return R.layout.layout_learn_custom_item;
    }

    @Override
    protected void initView() {
        img_welfare = R.id.img_welfare;
        layout_item = R.id.layout_item;
        layout_msg = R.id.layout_msg;
        tv_content = R.id.tv_content;
        img_icon = R.id.img_icon;
        tv_author = R.id.tv_author;
        tv_type = R.id.tv_type;
        tv_time = R.id.tv_time;
    }

    @Override
    protected void onBindDataToView(BaseViewHolder holder, final WelfareBean.ResultsEntity bean, int position) {
        if (isAll && !TextUtils.isEmpty(bean.getType()) && "福利".equals(bean.getType())) {
            holder.getImageId(img_welfare).setVisibility(View.VISIBLE);
            holder.getLayoutId(layout_msg).setVisibility(View.GONE);
            ImgLoadUtil.displayEspImage(bean.getUrl(), holder.getImageId(img_welfare), 1);
        } else {
            holder.getImageId(img_welfare).setVisibility(View.GONE);
            holder.getLayoutId(layout_msg).setVisibility(View.VISIBLE);
            holder.setText(tv_content, getValues(bean.getDesc()));
            if (bean.getImages() != null && bean.getImages().size() > 0 && !TextUtils.isEmpty(bean.getImages().get(0))) {
                holder.getImageId(img_icon).setVisibility(View.VISIBLE);
                ImgLoadUtil.displayGif(bean.getImages().get(0), holder.getImageId(img_icon));
            } else {
                holder.getImageId(img_icon).setVisibility(View.GONE);
            }
        }

        if (isAll) {
            holder.getText(tv_type).setVisibility(View.VISIBLE);
            holder.setText(tv_type, "-" + getValues(bean.getType()));
        } else {
            holder.getText(tv_type).setVisibility(View.GONE);
        }

        holder.setText(tv_author, getValues(bean.getWho()));
        if (TextUtils.isEmpty(bean.getCreatedAt())) {
            holder.setText(tv_time, getValues(bean.getCreatedAt()));
        } else {
            holder.setText(tv_time, bean.getCreatedAt().substring(0, 10));
        }

        holder.getLayoutId(layout_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.SelectItemUrl(bean.getUrl());
                }
            }
        });
    }

    /**
     * 设置是否为显示全部
     */
    public void setIsAll(boolean isAll) {
        this.isAll = isAll;
    }

}
