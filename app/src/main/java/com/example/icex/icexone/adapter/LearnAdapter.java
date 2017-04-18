package com.example.icex.icexone.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * ProjectName：IcexOne
 * Describe：学习页面适配器
 * Author：Icex
 * CreationTime：2017/2/22
 * FragmentStatePagerAdapter与FragmentPagerAdapter的区别
 * FragmentStatePagerAdapter适合多页面，并且在页面离开后会被消除，释放资源
 */

public class LearnAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;
    private List<String> title;

    public LearnAdapter(FragmentManager fm, List<Fragment> fragments, List<String> stringList) {
        super(fm);
        this.fragmentList = fragments;
        this.title = stringList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.isEmpty() ? 0 : fragmentList.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return title.isEmpty() ? "" : title.get(position);
    }
}
