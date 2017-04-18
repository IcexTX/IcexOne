package com.example.icex.icexone.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.example.icex.icexone.R;
import com.example.icex.icexone.adapter.Able.WelfareAble;
import com.example.icex.icexone.util.ImgLoadUtil;
import com.example.library.base.BaseAdapter;
import com.example.library.base.BaseViewHolder;
import com.example.library.ganHuo.WelfareBean;

import java.util.List;

/**
 * ProjectName：IcexOne
 * Describe：福利图集适配器
 * Author：Icex
 * CreationTime：2017/4/6
 */

public class WelfareAdapter extends BaseAdapter<WelfareBean.ResultsEntity> {

    private int img_atlas;
    private WelfareAble callBack;

    public WelfareAdapter(Context context, List<WelfareBean.ResultsEntity> list, WelfareAble welfareAble) {
        super(context, list);
        this.callBack = welfareAble;
    }


    @Override
    protected int setView(int viewType) {
        return R.layout.layout_learn_atlas_item;
    }

    @Override
    protected void initView() {
        img_atlas = R.id.img_atlas;
    }

    @Override
    protected void onBindDataToView(BaseViewHolder holder, final WelfareBean.ResultsEntity bean, final int position) {
        if (!TextUtils.isEmpty(bean.getUrl())) {
            //2代表使用妹子默认图像
            ImgLoadUtil.displayEspImage(bean.getUrl(), holder.getImageId(img_atlas), 1);
            holder.getImageId(img_atlas).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (callBack != null) {
                        callBack.selectImage(bean,position);
                    }
                }
            });

        }
    }

}
