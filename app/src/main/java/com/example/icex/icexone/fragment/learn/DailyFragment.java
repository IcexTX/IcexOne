package com.example.icex.icexone.fragment.learn;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.icex.icexone.R;
import com.example.icex.icexone.adapter.Able.DailyAble;
import com.example.icex.icexone.adapter.DailyAdapter;
import com.example.icex.icexone.model.Able.DailyModelAble;
import com.example.icex.icexone.model.DailyModel;
import com.example.icex.icexone.util.ACache;
import com.example.icex.icexone.util.GlideImageLoader;
import com.example.library.base.BaseFragment;
import com.example.library.bean.BannerBean;
import com.example.library.bean.GeneralContent;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import rx.Subscription;

/**
 * ProjectName：IcexOne
 * Describe：每日推荐页面
 * Author：Icex
 * CreationTime：2017/2/22
 */

public class DailyFragment extends BaseFragment implements View.OnClickListener, OnBannerListener,
        DailyAble {

    private ImageView img_banner;
    private LinearLayout layout_load;
    private ImageView img_load;
    private XRecyclerView xRecyclerView;
    private AnimationDrawable animationDrawable;
    //头部文件
    private View headerView;
    private Banner header_banner;
    private ImageButton imgBtn_personal;
    private ImageButton imgBtn_recommended;
    private TextView tv_day_title;
    private ImageButton imgBtn_read;
    private View bottomView;
    private Button btn_adjust_order;
    private LinearLayoutManager layoutManager;
    private DailyAdapter dailyAdapter;
    private List<List<GeneralContent>> dailyBeen;
    private String nowTime;
    private ACache aCache;
    private ArrayList<String> imgUrl;
    private ArrayList<String> titles;
    //加载轮播数据是否成功
    private boolean isBanner = false;
    //征文内容是否加载成功
    private boolean isContent = false;
    private DailyModel dailyModel;
    private Calendar calendar;

    @Override
    protected int setView() {
        return R.layout.fragment_learn_daily;
    }

    @Override
    protected void initView() {
        img_banner = getWidget(R.id.img_banner);
        layout_load = getWidget(R.id.layout_load);
        img_load = getWidget(R.id.img_load);
        xRecyclerView = getWidget(R.id.xRecyclerView);
        //加载头部布局文件
        headerView = getView(R.layout.layout_learn_daily_header);
        header_banner = getWidget(headerView, R.id.header_banner);
        imgBtn_personal = getWidget(headerView, R.id.imgBtn_personal);
        tv_day_title = getWidget(headerView, R.id.tv_day_title);
        imgBtn_recommended = getWidget(headerView, R.id.imgBtn_recommended);
        imgBtn_read = getWidget(headerView, R.id.imgBtn_read);
        //加载底部布局文件
        bottomView = getView(R.layout.layout_learn_daily_bottom);
        btn_adjust_order = getWidget(bottomView, R.id.btn_adjust_order);
    }

    @Override
    protected void initData() {
        calendar = Calendar.getInstance();
        dailyModel = new DailyModel();
        aCache = ACache.get(getActivity());
        //获取缓存中的图片地址数据
        //imgUrl = (ArrayList<String>) aCache.getAsObject(Constants.BANNER_PIC);
        //titles = (ArrayList<String>) aCache.getAsObject(Constants.BANNER_TITLE);
        imgUrl = new ArrayList<>();
        titles = new ArrayList<>();
        animationDrawable = (AnimationDrawable) img_load.getDrawable();
        if (!animationDrawable.isRunning()) {
            animationDrawable.run();
        }
        nowTime = getDate();
        String time = nowTime.substring(8, 10);
        tv_day_title.setText(time.indexOf("0") == 0 ? time.replace("0", "") : time);
        imgBtn_personal.setOnClickListener(this);
        imgBtn_recommended.setOnClickListener(this);
        imgBtn_read.setOnClickListener(this);
        btn_adjust_order.setOnClickListener(this);
        setDataXrecyc();

    }

    /**
     * 获取每日数据
     */
    private void getServiceData() {
        if (!isContent) {
            String time[] = nowTime.split("-");
            getContentData(time[0], time[1], time[2]);
        }
    }

    /**
     * 设置时间
     */
    private void setCalendar(String date) {
        String[] setTime;
        setTime = date.split("-");
        int year = Integer.parseInt(setTime[0]);
        int month = Integer.parseInt(setTime[1]);
        int set_data = Integer.parseInt(setTime[2].substring(0, 2));
        calendar.set(year, month - 1, set_data);
    }


    /**
     * 获取时间
     */
    private String getDate(Calendar calendar) {
        return dateFormat.format(calendar.getTime()).toString();
    }

    /**
     * 将头部布局以及底部布局文件载入RecyclerView
     */
    public void setDataXrecyc() {
        dailyBeen = new ArrayList<>();
        //设置不可下拉刷新
        xRecyclerView.setPullRefreshEnabled(false);
        //设置不可加载更多
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.addHeaderView(headerView);
        xRecyclerView.setFootView(bottomView);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(layoutManager);
        // 需加，不然滑动不流畅
        xRecyclerView.setNestedScrollingEnabled(false);
        xRecyclerView.setHasFixedSize(false);
        xRecyclerView.setItemAnimator(new DefaultItemAnimator());
        dailyAdapter = new DailyAdapter(getActivity(), dailyBeen, this);
        xRecyclerView.setAdapter(dailyAdapter);
        if (animationDrawable.isRunning()) {
            animationDrawable.stop();
            img_banner.setVisibility(View.GONE);
            layout_load.setVisibility(View.GONE);
        }

        //设置图片滚动显示
        if (header_banner != null) {
            //开始自动播放
            header_banner.startAutoPlay();
            //设置自动重播时间
            header_banner.setDelayTime(5000);
            //显示圆形指示器和标题（水平显示）
            header_banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
            //设置banner的动画效果
            header_banner.setBannerAnimation(Transformer.DepthPage);
            //设置指示器显示位置
            header_banner.setIndicatorGravity(BannerConfig.CENTER);
            setBannerData();
        }
        header_banner.setOnBannerListener(this);
        getServiceData();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBtn_personal:
                showToast("暫無數據");
                break;

            case R.id.imgBtn_recommended:
                showToast("暫無數據");
                break;

            case R.id.imgBtn_read:
                showToast("暫無數據");
                break;
            case R.id.btn_adjust_order:
                showToast("暫無數據");
                break;
        }
    }


    /**
     * 获取图片轮播文件
     */
    private void setBannerData() {
        dailyModel.getBannerData(new DailyModelAble() {
            @Override
            public void connectionFail() {
                isBanner = false;
            }

            @Override
            public void loadSuccess(Object result) {
                isBanner = true;
                //清除旧数据
                imgUrl.clear();
                List<BannerBean.DataEntity> entity = (List<BannerBean.DataEntity>) result;
                for (BannerBean.DataEntity dataEntity : entity) {
                    imgUrl.add(dataEntity.getPic_url());
                    titles.add(dataEntity.getTitle());

                }
                header_banner.setBannerTitles(titles);
                header_banner.setImages(imgUrl).setImageLoader(new GlideImageLoader()).start();
                //aCache.remove(Constants.BANNER_PIC);
                // aCache.remove(Constants.BANNER_TITLE);
                //缓存5分钟
                // aCache.put(Constants.BANNER_PIC, imgUrl, 1000 * 60 * 5);
                //aCache.put(Constants.BANNER_TITLE, titles, 1000 * 60 * 5);
            }

            @Override
            public void addSubscription(Subscription subscription) {
                DailyFragment.this.addSubscription(subscription);
            }
        });
    }

    /**
     * 获取要显示的内容数据
     */
    private void getContentData(String year, String month, String day) {
        dailyModel.getContentData(year, month, day, new DailyModelAble() {
            @Override
            public void connectionFail() {
                isContent = false;
                setCalendar(nowTime);
                calendar.add(Calendar.DATE, -1);
                nowTime = getDate(calendar);
                getServiceData();
            }

            @Override
            public void loadSuccess(Object result) {
                List<List<GeneralContent>> content = (List<List<GeneralContent>>) result;
                if (content == null || content.isEmpty()) {
                    isContent = false;
                    setCalendar(nowTime);
                    calendar.add(Calendar.DATE, -1);
                    nowTime = getDate(calendar);
                    getServiceData();
                } else {
                    isContent = true;
                    dailyAdapter.setDataList(content);
                }
            }

            @Override
            public void addSubscription(Subscription subscription) {
                DailyFragment.this.addSubscription(subscription);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        Glide.with(getActivity()).resumeRequests();
    }

    @Override
    public void onPause() {
        super.onPause();
        Glide.with(getActivity()).pauseRequests();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        header_banner.stopAutoPlay();
    }

    /**
     * 图片轮播点击事件
     *
     * @param position
     */
    @Override
    public void OnBannerClick(int position) {
        showToast(String.valueOf(position));
    }

    @Override
    public void returnDailyData(GeneralContent content) {
        if (TextUtils.isEmpty(content.getTitle())) {
            showToast("");
        }
    }


    @Override
    protected void getData() {
    }
}


