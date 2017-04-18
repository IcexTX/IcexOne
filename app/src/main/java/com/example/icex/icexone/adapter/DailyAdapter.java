package com.example.icex.icexone.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.example.icex.icexone.R;
import com.example.icex.icexone.adapter.Able.DailyAble;
import com.example.icex.icexone.util.Constants;
import com.example.icex.icexone.util.ImgLoadUtil;
import com.example.library.base.BaseAdapter;
import com.example.library.base.BaseViewHolder;
import com.example.library.ganHuo.GeneralContent;

import java.util.List;

/**
 * ProjectName：IcexOne
 * Describe：每日推荐适配器
 * Author：Icex
 * CreationTime：2017/2/23
 */

public class DailyAdapter extends BaseAdapter<List<GeneralContent>> {

    private final int TYPE_TITLE = 0;
    private final int TYPE_ONE = 1;
    private final int TYPE_TWO = 2;
    private final int TYPE_THREE = 3;
    private final int TYPE_FOUR = 4;
    private DailyAble callBack;
    private int img_header;
    private int title_name;
    private int layout_more;
    //显示一张图
    private int img_first;
    private int tv_first_title_name;
    //显示2张图片
    private int img_second_one;
    private int tv_second_one_title;
    private int img_second_two;
    private int tv_second_two_title;
    //显示三张图片
    private int img_third_one;
    private int tv_third_one_title;
    private int img_third_two;
    private int tv_third_two_title;
    private int img_third_three;
    private int tv_third_three_title;
    //显示4张图片样式
    private int img_fourth_one;
    private int tv_fourth_one_title;
    private int img_fourth_two;
    private int tv_fourth_two_title;
    private int img_fourth_three;
    private int tv_fourth_three_title;
    private int img_fourth_four;
    private int tv_fourth_four_title;

    public DailyAdapter(Context context, List<List<GeneralContent>> list, DailyAble dailyAble) {
        super(context, list);
        this.callBack = dailyAble;
    }

    /**
     * 4种不同的布局文件
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (!TextUtils.isEmpty(dataList.get(position).get(0).getTitle())) {
            return TYPE_TITLE;
        } else if (dataList.get(position).size() == 1) {
            return TYPE_ONE;
        } else if (dataList.get(position).size() == 2) {
            return TYPE_TWO;
        } else if (dataList.get(position).size() == 3) {
            return TYPE_THREE;
        } else if (dataList.get(position).size() == 4) {
            return TYPE_FOUR;
        }
        return super.getItemViewType(position);
    }

    @Override
    protected int setView(int viewType) {
        switch (viewType) {
            case TYPE_TITLE:
                return R.layout.layout_learn_daily_title;
            case TYPE_ONE:
                return R.layout.layout_learn_daily_first;
            case TYPE_TWO:
                return R.layout.layout_learn_daily_second;
            case TYPE_THREE:
                return R.layout.layout_learn_daily_third;
            case TYPE_FOUR:
                return R.layout.layout_learn_daily_fourth;
            default:
                return R.layout.layout_learn_daily_title;
        }
    }

    @Override
    protected void initView() {
        img_header = R.id.img_header;
        title_name = R.id.title_name;
        layout_more = R.id.layout_more;
        //显示一张图
        img_first = R.id.img_first;
        tv_first_title_name = R.id.tv_first_title_name;
        //显示2张图片
        img_second_one = R.id.img_second_one;
        tv_second_one_title = R.id.tv_second_one_title;
        img_second_two = R.id.img_second_two;
        tv_second_two_title = R.id.tv_second_two_title;
        //显示三张图片
        img_third_one = R.id.img_third_one;
        tv_third_one_title = R.id.tv_third_one_title;
        img_third_two = R.id.img_third_two;
        tv_third_two_title = R.id.tv_third_two_title;
        img_third_three = R.id.img_third_three;
        tv_third_three_title = R.id.tv_third_three_title;
        //显示4张图片样式
        img_fourth_one = R.id.img_fourth_one;
        tv_fourth_one_title = R.id.tv_fourth_one_title;
        img_fourth_two = R.id.img_fourth_two;
        tv_fourth_two_title = R.id.tv_fourth_two_title;
        img_fourth_three = R.id.img_fourth_three;
        tv_fourth_three_title = R.id.tv_fourth_three_title;
        img_fourth_four = R.id.img_fourth_four;
        tv_fourth_four_title = R.id.tv_fourth_four_title;
    }

    @Override
    protected void onBindDataToView(BaseViewHolder holder, final List<GeneralContent> bean, int position) {
        String title = bean.get(0).getTitle();
        if (TextUtils.isEmpty(title)) {
            int length = bean.size();
            switch (length) {
                case 1:
                    holder.setText(tv_first_title_name, bean.get(0).getDesc());
                    ImgLoadUtil.displayRandom(1, holder.getImageId(img_first));
                    setOnClickListener(holder.getImageId(img_first), bean.get(0));
                    break;
                case 2:
                    holder.setText(tv_second_one_title, bean.get(0).getDesc());
                    ImgLoadUtil.displayRandom(2, holder.getImageId(img_second_one));
                    holder.setText(tv_second_two_title, bean.get(1).getDesc());
                    ImgLoadUtil.displayRandom(2, holder.getImageId(img_second_two));
                    setOnClickListener(holder.getImageId(img_second_one), bean.get(0));
                    setOnClickListener(holder.getImageId(img_second_two), bean.get(1));
                    break;
                case 3:
                    holder.setText(tv_third_one_title, bean.get(0).getDesc());
                    ImgLoadUtil.displayRandom(3, holder.getImageId(img_third_one));
                    holder.setText(tv_third_two_title, bean.get(1).getDesc());
                    ImgLoadUtil.displayRandom(3, holder.getImageId(img_third_two));
                    holder.setText(tv_third_three_title, bean.get(2).getDesc());
                    ImgLoadUtil.displayRandom(3, holder.getImageId(img_third_three));
                    setOnClickListener(holder.getImageId(img_third_one), bean.get(0));
                    setOnClickListener(holder.getImageId(img_third_two), bean.get(1));
                    setOnClickListener(holder.getImageId(img_third_three), bean.get(1));
                    break;

                case 4:
                    holder.setText(tv_fourth_one_title, bean.get(0).getDesc());
                    ImgLoadUtil.displayRandom(4, holder.getImageId(img_fourth_one));
                    holder.setText(tv_fourth_two_title, bean.get(1).getDesc());
                    ImgLoadUtil.displayRandom(4, holder.getImageId(img_fourth_two));
                    holder.setText(tv_fourth_three_title, bean.get(2).getDesc());
                    ImgLoadUtil.displayRandom(4, holder.getImageId(img_fourth_three));
                    holder.setText(tv_fourth_four_title, bean.get(3).getDesc());
                    ImgLoadUtil.displayRandom(4, holder.getImageId(img_fourth_four));
                    setOnClickListener(holder.getImageId(img_fourth_one), bean.get(0));
                    setOnClickListener(holder.getImageId(img_fourth_two), bean.get(1));
                    setOnClickListener(holder.getImageId(img_fourth_three), bean.get(2));
                    setOnClickListener(holder.getImageId(img_fourth_four), bean.get(3));
                    break;
                default:
                    break;
            }
        } else {
            if (Constants.DAILY_ANDROID.equals(title)) {
                holder.setText(title_name, Constants.DAILY_ANDROID);
                holder.setImageBackground(img_header, getDraw(R.mipmap.learn_daily_day_android));
            } else if (Constants.DAILY_IOS.equals(title)) {
                holder.setText(title_name, Constants.DAILY_IOS);
                holder.setImageBackground(img_header, getDraw(R.mipmap.learn_daily_day_ios));
            } else if (Constants.DAILY_RESOURCES.equals(title)) {
                holder.setText(title_name, Constants.DAILY_RESOURCES);
                holder.setImageBackground(img_header, getDraw(R.mipmap.learn_daily_day_resources));
            } else if (Constants.DAILY_VIDEO.equals(title)) {
                holder.setText(title_name, Constants.DAILY_VIDEO);
                holder.setImageBackground(img_header, getDraw(R.mipmap.learn_daily_day_movie));
            } else if (Constants.DAILY_WELFARE.equals(title)) {
                holder.setText(title_name, Constants.DAILY_WELFARE);
                holder.setImageBackground(img_header, getDraw(R.mipmap.learn_daily_day_welfare));
            } else if (Constants.DAILY_RECOMMEND.equals(title)) {
                holder.setText(title_name, Constants.DAILY_RECOMMEND);
                holder.setImageBackground(img_header, getDraw(R.mipmap.learn_daily_day_recommend));
            } else if (Constants.DAILY_WEB.equals(title)) {
                holder.setText(title_name, Constants.DAILY_WEB);
                holder.setImageBackground(img_header, getDraw(R.mipmap.learn_daily_day_web));
            } else if (Constants.DAILY_APP.equals(title)) {
                holder.setText(title_name, Constants.DAILY_APP);
                holder.setImageBackground(img_header, getDraw(R.mipmap.learn_daily_day_app));
            }
            holder.getLayoutId(layout_more).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (callBack != null) {
                        callBack.returnDailyData(bean.get(0));
                    }
                }
            });

        }
    }

    /**
     * 图片的监听事件
     */
    private void setOnClickListener(ImageView view, final GeneralContent content) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.returnDailyData(content);
                }
            }
        });
    }

}
