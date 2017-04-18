package com.example.icex.icexone.fragment.learn;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;

import com.example.icex.icexone.R;
import com.example.icex.icexone.adapter.Able.CustomAble;
import com.example.icex.icexone.adapter.CustomAdapter;
import com.example.library.base.BaseFragment;
import com.example.library.ganHuo.WelfareBean;
import com.example.library.httpRequest.HttpUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * ProjectName：IcexOne
 * Describe：知识回顾
 * Author：Icex
 * CreationTime：2017/2/22
 */

public class ReviewFragment extends BaseFragment implements XRecyclerView.LoadingListener, CustomAble {

    //查询页数
    private int page = 1;

    private XRecyclerView xRecyclerView;
    private LinearLayoutManager layoutManager;
    private CustomAdapter customAdapter;
    private List<WelfareBean.ResultsEntity> entityList;

    @Override
    protected int setView() {
        return R.layout.fragment_learn_custom;
    }

    @Override
    protected void initView() {
        xRecyclerView = getWidget(R.id.xRecyclerView);
    }

    @Override
    protected void initData() {
        entityList = new ArrayList<>();
        //设置不可下拉刷新
        xRecyclerView.setPullRefreshEnabled(true);
        //设置不可加载更多
        xRecyclerView.setLoadingMoreEnabled(true);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(layoutManager);
        // 需加，不然滑动不流畅
        xRecyclerView.setNestedScrollingEnabled(false);
        xRecyclerView.setHasFixedSize(false);
        xRecyclerView.setItemAnimator(new DefaultItemAnimator());
        customAdapter = new CustomAdapter(getActivity(), entityList, this);
        customAdapter.setIsAll(false);
        xRecyclerView.setAdapter(customAdapter);
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
        Subscription subscription = HttpUtils.getInstance().getGangHuoRequest().getWelfare("Android", HttpUtils.number, page)
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
                                xRecyclerView.refreshComplete();
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
        setResetData();
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
