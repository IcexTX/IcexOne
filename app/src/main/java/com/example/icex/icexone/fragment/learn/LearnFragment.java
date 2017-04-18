package com.example.icex.icexone.fragment.learn;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.icex.icexone.R;
import com.example.icex.icexone.adapter.LearnAdapter;
import com.example.library.base.BaseFragment;
import com.example.skinLoader.attr.AttrFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ProjectName：IcexOne
 * Describe：Android学习页面
 * Author：Icex
 * CreationTime：2017/2/22
 */

public class LearnFragment extends BaseFragment implements ViewPager.OnPageChangeListener {

    private ViewPager viewPager_content;
    private TabLayout tabLayout_title;
    private LearnAdapter learnAdapter;
    private List<Fragment> fragmentList;
    private List<String> titleList;

    @Override
    protected int setView() {
        return R.layout.fragment_learn;
    }

    @Override
    protected void initView() {
        viewPager_content = getWidget(R.id.viewPager_content);
        tabLayout_title = getWidget(R.id.tabLayout_title);
    }

    @Override
    protected void initData() {
        fragmentList = new ArrayList<>();
        titleList = Arrays.asList(getActivity().getResources().getStringArray(R.array.LearnList));
        fragmentList.add(new DailyFragment());
        fragmentList.add(new AtlasFragment());
        fragmentList.add(new CustomFragment());
        fragmentList.add(new ReviewFragment());

        learnAdapter = new LearnAdapter(getChildFragmentManager(), fragmentList, titleList);
        viewPager_content.setOffscreenPageLimit(4);
        viewPager_content.addOnPageChangeListener(this);
        viewPager_content.setAdapter(learnAdapter);
        viewPager_content.setCurrentItem(0);
        tabLayout_title.setTabMode(TabLayout.MODE_FIXED);
        tabLayout_title.setupWithViewPager(viewPager_content);
        dynamicAddSkinView(tabLayout_title, AttrFactory.TAB_INDICATOR_COLOR, R.color.colorPrimary);
    }

    @Override
    protected void getData() {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * 滑动选择页面
     *
     * @param position
     */
    @Override
    public void onPageSelected(int position) {
        viewPager_content.setCurrentItem(position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
