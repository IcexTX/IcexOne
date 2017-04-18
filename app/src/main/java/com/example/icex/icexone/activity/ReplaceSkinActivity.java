package com.example.icex.icexone.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.icex.icexone.R;
import com.example.icex.icexone.adapter.ReplaceSkinAdapter;
import com.example.icex.icexone.bean.ReplaceSkinBean;
import com.example.library.base.BaseActivity;
import com.example.library.util.FileUtils;
import com.example.skinLoader.attr.AttrFactory;
import com.example.skinLoader.changeSkin.SkinManager;
import com.example.skinLoader.listener.ILoaderListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * ProjectName：IcexOne
 * Describe：更换皮肤activity
 * Author：Icex
 * CreationTime：2017/2/20
 */

public class ReplaceSkinActivity extends BaseActivity implements View.OnClickListener, ReplaceSkinAdapter.ReplaceSkinAble {

    private LinearLayout layout_title_bar;
    private ImageButton imgBtn_back;
    private TextView tv_title_bar;
    private RecyclerView recyclerView;
    private ReplaceSkinAdapter skinAdapter;
    private GridLayoutManager gridLayoutManager;
    private List<ReplaceSkinBean> skinBeen;
    private String SKIN_ADDRESS;

    @Override
    protected int setView() {
        return R.layout.activity_replace_skin;
    }

    @Override
    protected void initView() {
        imgBtn_back = getWidget(R.id.imgBtn_back);
        tv_title_bar = getWidget(R.id.tv_title_bar);
        recyclerView = getWidget(R.id.recyclerView);
        layout_title_bar = getWidget(R.id.layout_title_bar);
    }

    @Override
    protected void initData() {
        dynamicAddSkinEnableView(layout_title_bar, AttrFactory.BACKGROUND, R.color.colorPrimary);


        imgBtn_back.setOnClickListener(this);
        application.addActivity(this);
        tv_title_bar.setText(R.string.replace_skin);
        skinBeen = getSkinData();
        SKIN_ADDRESS = FileUtils.getSkinDirPath(this);
        skinAdapter = new ReplaceSkinAdapter(this, skinBeen);
        skinAdapter.setCallBack(this);
        gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(skinAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBtn_back:
                application.deleteActivity(this);
                break;
        }

    }

    @Override
    public void skinName(String name) {
        skinAdapter.setName(name);
        if (name.equals("skin_green.skin")) {
            SkinManager.getInstance().restoreDefaultTheme();
            return;
        }
        if (!TextUtils.isEmpty(name)) {
            //获取问件路径
            String path = SKIN_ADDRESS + File.separator + name;
            //将assets下的文件写入到本地内存中
            FileUtils.moveRawToDir(this, name, path);
            File file = new File(path);
            //检查文件是否存在
            if (file == null && file.exists()) {
                showToast("本地皮肤文件不存在！");
                return;
            }
            SkinManager.getInstance().load(file.getAbsolutePath(), new ILoaderListener() {
                @Override
                public void onStart() {

                }

                @Override
                public void onSuccess() {
                    showToast("更换皮肤成功！");
                }

                @Override
                public void onFailed() {
                    showToast("更换皮肤失败！");
                }
            });
        }
    }


    private List<ReplaceSkinBean> getSkinData() {
        List<ReplaceSkinBean> replaceSkinBeen = new ArrayList<>();
        String[] skin = getResources().getStringArray(R.array.SkinData);
        for (int i = 0; i < skin.length; i++) {
            ReplaceSkinBean skinBean = new ReplaceSkinBean();
            skinBean.setTitle("纯色主题");
            skinBean.setName(skin[i]);
            skinBean.setSkinName(skin[i]);
            replaceSkinBeen.add(skinBean);
        }
        return replaceSkinBeen;
    }
}
