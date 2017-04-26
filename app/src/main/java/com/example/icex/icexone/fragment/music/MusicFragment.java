package com.example.icex.icexone.fragment.music;

import com.example.icex.icexone.R;
import com.example.library.base.BaseFragment;

/**
 * ProjectName：IcexOne
 * Describe：个人音乐
 * Author：Icex
 * CreationTime：2017/2/22
 */

public class MusicFragment extends BaseFragment {

    private String url = "https://api.douban.com/v2/book/search?tag=%E7%BB%BC%E5%90%88&start=10&count=5";


    @Override
    protected int setView() {
        return R.layout.fragment_music;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
    }

    @Override
    protected void getData() {

    }
}
