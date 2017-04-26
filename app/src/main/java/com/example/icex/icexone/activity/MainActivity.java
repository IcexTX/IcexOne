package com.example.icex.icexone.activity;

import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.icex.icexone.R;
import com.example.icex.icexone.adapter.MainAdapter;
import com.example.icex.icexone.fragment.book.BookFragment;
import com.example.icex.icexone.fragment.learn.LearnFragment;
import com.example.icex.icexone.fragment.music.MusicFragment;
import com.example.library.base.BaseActivity;
import com.example.skinLoader.attr.AttrFactory;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import static com.example.icex.icexone.R.id.navigation;

public class MainActivity extends BaseActivity implements View.OnClickListener,
        NavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {

    private LinearLayout img_menu_bar;
    private LinearLayout img_menu_search;
    private ImageView img_menu_discover;
    private ImageView img_menu_music;
    private ImageView img_menu_personage;
    private ViewPager main_viewpager;
    private DrawerLayout main_drawer_layout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private LinearLayout layout_exit;
    private LinearLayout layout_setting;
    private LinearLayout layout_mode;
    private ImageView img_mode;
    private TextView tv_mode;
    private MainAdapter mainAdapter;
    private List<Fragment> fragments;

    private long WAIT_TIME = 2000;
    private long downTime;

    @Override
    protected int setView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        main_drawer_layout = getWidget(R.id.main_drawer_layout);
        StatusBarUtil.setColorNoTranslucentForDrawerLayout(this, main_drawer_layout, getColors(R.color.colorPrimary));
        img_menu_bar = getWidget(R.id.img_menu_bar);
        img_menu_search = getWidget(R.id.img_menu_search);
        img_menu_discover = getWidget(R.id.img_menu_discover);
        img_menu_music = getWidget(R.id.img_menu_music);
        img_menu_personage = getWidget(R.id.img_menu_personage);
        main_viewpager = getWidget(R.id.main_viewpager);
        navigationView = getWidget(navigation);
        toolbar = getWidget(R.id.toolbar);

        layout_exit = getWidget(R.id.layout_exit);
        layout_setting = getWidget(R.id.layout_setting);
        layout_mode = getWidget(R.id.layout_mode);
        img_mode = getWidget(R.id.img_mode);
        tv_mode = getWidget(R.id.tv_mode);
        application.addActivity(this);
    }

    @Override
    protected void initData() {
        dynamicAddSkinEnableView(toolbar, AttrFactory.BACKGROUND, R.color.colorPrimary);
        //dynamicAddSkinEnableView(navigationView.getHeaderView(0), AttrFactory.BACKGROUND, R.color.colorPrimary);
        dynamicAddSkinEnableView(navigationView, AttrFactory.NAVIGATION_VIEW_MENU, R.color.colorPrimary);
        navigationView.setNavigationItemSelectedListener(this);
        if (navigationView != null) {
            NavigationMenuView menuView = (NavigationMenuView) navigationView.getChildAt(0);
            if (menuView != null) {
                menuView.setVerticalScrollBarEnabled(false);
            }
        }
        img_menu_bar.setOnClickListener(this);
        img_menu_search.setOnClickListener(this);
        img_menu_discover.setOnClickListener(this);
        img_menu_music.setOnClickListener(this);
        img_menu_personage.setOnClickListener(this);
        layout_exit.setOnClickListener(this);
        layout_setting.setOnClickListener(this);
        layout_mode.setOnClickListener(this);

        //加载其他页面
        fragments = new ArrayList<>();
        fragments.add(new LearnFragment());
        fragments.add(new MusicFragment());
        fragments.add(new BookFragment());
        mainAdapter = new MainAdapter(getSupportFragmentManager(), fragments);
        main_viewpager.setOffscreenPageLimit(3);
        main_viewpager.setAdapter(mainAdapter);
        main_viewpager.setCurrentItem(0);
        setImageMenu(0);
        main_viewpager.addOnPageChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_menu_bar:
                main_drawer_layout.openDrawer(Gravity.LEFT);
                break;
            case R.id.img_menu_search:
                showToast("点击了搜索页面！");
                break;

            case R.id.layout_mode:

                break;

            case R.id.layout_setting:

                break;

            case R.id.layout_exit:
                application.exitApplication();
                break;

            case R.id.img_menu_discover:
                if (main_viewpager.getCurrentItem() != 0) {
                    setImageMenu(0);
                    main_viewpager.setCurrentItem(0);
                }
                break;

            case R.id.img_menu_music:
                if (main_viewpager.getCurrentItem() != 1) {
                    setImageMenu(1);
                    main_viewpager.setCurrentItem(1);
                }
                break;

            case R.id.img_menu_personage:
                if (main_viewpager.getCurrentItem() != 2) {
                    setImageMenu(2);
                    main_viewpager.setCurrentItem(2);
                }
                break;
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_discover:
                closeDrawer(0);
                break;

            case R.id.menu_music:
                closeDrawer(1);
                break;

            case R.id.menu_read:
                closeDrawer(2);
                break;

            case R.id.menu_move:

                break;

            case R.id.menu_switch_skin:
                skipIntent(ReplaceSkinActivity.class);
                break;

            case R.id.menu_clock:
                break;

            case R.id.menu_stop:
                break;

            case R.id.menu_update:
                break;
        }

        return true;
    }

    /**
     * 跳到指定页面
     * @param position
     */
    private void closeDrawer(int position){
        main_viewpager.setCurrentItem(position);
        setImageMenu(position);
        main_drawer_layout.closeDrawer(GravityCompat.START);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (main_drawer_layout.isDrawerOpen(Gravity.LEFT)) {
                main_drawer_layout.closeDrawers();
                return true;
            }
            if (downTime == 0) {
                downTime = System.currentTimeMillis();
                showToast("再按一次退出程序！");
                return true;
            } else {
                if (System.currentTimeMillis() - downTime < WAIT_TIME) {
                    application.exitApplication();
                } else {
                    downTime = System.currentTimeMillis();
                    showToast("再按一次退出程序！");
                }
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        main_viewpager.setCurrentItem(position);
        setImageMenu(position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    /**
     * 设置bar样式
     */
    private void setImageMenu(int position) {
        img_menu_discover.setSelected(false);
        img_menu_music.setSelected(false);
        img_menu_personage.setSelected(false);
        switch (position) {
            case 0:
                img_menu_discover.setSelected(true);
                break;
            case 1:
                img_menu_music.setSelected(true);
                break;
            case 2:
                img_menu_personage.setSelected(true);
                break;
            default:
                break;
        }
    }
}
