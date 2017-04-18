package com.example.icex.icexone.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * ProjectName：IcexOne
 * Describe：
 * Author：Icex
 * CreationTime：2017/2/22
 */

public class MainAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;

    public MainAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.fragmentList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.isEmpty() ? 0 : fragmentList.size();
    }
}
