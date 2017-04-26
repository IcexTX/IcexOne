package com.example.icex.icexone.fragment.learn;

import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.icex.icexone.R;
import com.example.icex.icexone.adapter.Able.CustomAble;
import com.example.icex.icexone.adapter.CustomAdapter;
import com.example.library.base.BaseFragment;
import com.example.library.bean.WelfareBean;
import com.example.library.httpRequest.HttpUtils;
import com.example.library.util.PreferencesKey;
import com.example.library.util.PreferencesUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * ProjectName：IcexOne
 * Describe：干货定制
 * Author：Icex
 * CreationTime：2017/2/22
 */

public class CustomFragment extends BaseFragment implements View.OnClickListener,
        XRecyclerView.LoadingListener, CustomAble {

    //查询页数
    private int page = 1;

    //选择分类头部布局文件
    private View headerView;
    private TextView tv_custom_title;
    private LinearLayout layout_type;

    //底部选择器
    private BottomSheetDialog sheetDialog;
    private View sheetView;
    private LinearLayout layout_all;
    private LinearLayout layout_ios;
    private LinearLayout layout_app;
    private LinearLayout layout_web;
    private LinearLayout layout_recommend;
    private LinearLayout layout_movie;
    private LinearLayout layout_resources;

    private XRecyclerView xRecyclerView;
    private LinearLayoutManager layoutManager;
    private CustomAdapter customAdapter;
    private List<WelfareBean.ResultsEntity> entityList;

    private String type = "全部";
    private String selectItem;

    @Override
    protected int setView() {
        return R.layout.fragment_learn_custom;
    }

    @Override
    protected void initView() {
        xRecyclerView = getWidget(R.id.xRecyclerView);
        //选择分类布局文件
        headerView = getView(R.layout.layout_learn_custom_header);
        tv_custom_title = getWidget(headerView, R.id.tv_custom_title);
        layout_type = getWidget(headerView, R.id.layout_type);

        //底部选择对话框
        sheetDialog = new BottomSheetDialog(getActivity());
        sheetView = getView(R.layout.layout_learn_custom_type);
        sheetDialog.setContentView(sheetView);
        layout_all = getWidget(sheetView, R.id.layout_all);
        layout_ios = getWidget(sheetView, R.id.layout_ios);
        layout_app = getWidget(sheetView, R.id.layout_app);
        layout_web = getWidget(sheetView, R.id.layout_web);
        layout_recommend = getWidget(sheetView, R.id.layout_recommend);
        layout_movie = getWidget(sheetView, R.id.layout_movie);
        layout_resources = getWidget(sheetView, R.id.layout_resources);
        layout_all.setOnClickListener(this);
        layout_ios.setOnClickListener(this);
        layout_app.setOnClickListener(this);
        layout_web.setOnClickListener(this);
        layout_recommend.setOnClickListener(this);
        layout_movie.setOnClickListener(this);
        layout_resources.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        type = PreferencesUtils.getString(getActivity(), PreferencesKey.CUSTOM_TYPE, "全部");
        tv_custom_title.setText(type);

        entityList = new ArrayList<>();
        //设置不可下拉刷新
        xRecyclerView.setPullRefreshEnabled(false);
        //设置不可加载更多
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.addHeaderView(headerView);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(layoutManager);
        // 需加，不然滑动不流畅
        xRecyclerView.setNestedScrollingEnabled(false);
        xRecyclerView.setHasFixedSize(false);
        xRecyclerView.setItemAnimator(new DefaultItemAnimator());
        customAdapter = new CustomAdapter(getActivity(), entityList, this);
        customAdapter.setIsAll("全部".equals(type) ? true : false);
        xRecyclerView.setAdapter(customAdapter);
        layout_type.setOnClickListener(this);
        xRecyclerView.setLoadingListener(this);
    }

    @Override
    protected void getData() {
        if (isVisible && entityList != null && entityList.isEmpty()) {
            getServiceData();
        }
    }

    /**
     * 获取服务器数据
     */
    private void getServiceData() {
        Subscription subscription = HttpUtils.getInstance().getGangHuoRequest().getWelfare(
                type.equals("全部") ? "all" : type, HttpUtils.number, page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<WelfareBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (page > 1) {
                            page--;
                        }
                    }

                    @Override
                    public void onNext(WelfareBean welfareBean) {
                        if (page == 1) {
                            if (welfareBean != null && welfareBean.getResults() != null && welfareBean.getResults().size() > 0) {
                                entityList = welfareBean.getResults();
                                customAdapter.setDataList(entityList);
                            }
                        } else {
                            if (welfareBean != null && welfareBean.getResults() != null && welfareBean.getResults().size() > 0) {
                                entityList.addAll(welfareBean.getResults());
                                xRecyclerView.refreshComplete();
                                customAdapter.addDataList(welfareBean.getResults());
                            } else {
                                xRecyclerView.loadMoreComplete();
                            }
                        }

                    }
                });
        addSubscription(subscription);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_type:
                if (!sheetDialog.isShowing()) {
                    sheetDialog.show();
                }
                break;
            case R.id.layout_all:
                selectItem = "全部";
                setSelectItem();
                break;
            case R.id.layout_app:
                selectItem = "App";
                setSelectItem();
                break;
            case R.id.layout_ios:
                selectItem = "iOS";
                setSelectItem();
                break;
            case R.id.layout_web:
                selectItem = "前端";
                setSelectItem();
                break;
            case R.id.layout_movie:
                selectItem = "休息视频";
                setSelectItem();
                break;
            case R.id.layout_resources:
                selectItem = "拓展资源";
                setSelectItem();
                break;
            case R.id.layout_recommend:
                selectItem = "瞎推荐";
                setSelectItem();
                break;
        }
    }

    private void setSelectItem() {
        if (!isSelectItem(selectItem)) {
            type = selectItem;
            tv_custom_title.setText(selectItem);
            customAdapter.setIsAll("全部".equals(type) ? true : false);
            PreferencesUtils.saveStringValue(getActivity(), PreferencesKey.CUSTOM_TYPE, type);
            setResetData();
        }
        sheetDialog.dismiss();
    }

    /**
     * 检查是否已选中的分类
     *
     * @param values
     * @return
     */
    private boolean isSelectItem(String values) {
        String selectItem = tv_custom_title.getText().toString();
        if (values.equals(selectItem)) {
            showToast("当前已是" + selectItem + "分类");
            return true;
        }
        return false;
    }

    /**
     * 重置数据
     */
    private void setResetData() {
        page = 1;
        entityList.clear();
        getServiceData();
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {
        page++;
        getServiceData();
    }

    @Override
    public void SelectItemUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            showToast("网址不能为空！");
        } else {
            showToast(url);
        }
    }
}
