package com.example.icex.icexone.fragment.learn;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.icex.icexone.R;
import com.example.icex.icexone.adapter.Able.WelfareAble;
import com.example.icex.icexone.adapter.WelfareAdapter;
import com.example.library.base.BaseFragment;
import com.example.library.bean.WelfareBean;
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
 * Describe：福利图集
 * Author：Icex
 * CreationTime：2017/2/22
 */

public class AtlasFragment extends BaseFragment implements WelfareAble {

    //请求的页数
    private int page = 1;
    private XRecyclerView xRecyclerView;
    private StaggeredGridLayoutManager gridLayoutManager;
    private List<String> imgList;
    private WelfareAdapter welfareAdapter;
    private List<WelfareBean.ResultsEntity> entityList;


    @Override
    protected int setView() {
        return R.layout.fragment_learn_atlas;
    }

    @Override
    protected void initView() {
        xRecyclerView = getWidget(R.id.xRecyclerView);
    }

    @Override
    protected void initData() {
        imgList = new ArrayList<>();
        entityList = new ArrayList<>();
        gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        welfareAdapter = new WelfareAdapter(getActivity(), entityList, this);
        xRecyclerView.setLayoutManager(gridLayoutManager);
        xRecyclerView.setAdapter(welfareAdapter);
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setPullRefreshEnabled(false);
        // 需加，不然滑动不流畅
        xRecyclerView.setNestedScrollingEnabled(false);
        xRecyclerView.setHasFixedSize(false);
        xRecyclerView.setItemAnimator(new DefaultItemAnimator());
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {
                page++;
                getWelfareData();
            }
        });
    }

    @Override
    protected void getData() {
        if (isVisible && entityList != null && entityList.isEmpty()) {
            getWelfareData();
        }
    }

    private void getWelfareData() {

        Subscription subscription = HttpUtils.getInstance().getGangHuoRequest().getWelfare("福利", HttpUtils.number, page)
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
                                imgList.clear();
                                entityList = welfareBean.getResults();
                                welfareAdapter.setDataList(entityList);
                                for (int i = 0; i < entityList.size(); i++) {
                                    imgList.add(entityList.get(i).getUrl());
                                }
                            }
                        } else {
                            if (welfareBean != null && welfareBean.getResults() != null && welfareBean.getResults().size() > 0) {
                                imgList.clear();
                                entityList.addAll(welfareBean.getResults());
                                xRecyclerView.refreshComplete();
                                welfareAdapter.addDataList(welfareBean.getResults());
                                for (int i = 0; i < entityList.size(); i++) {
                                    imgList.add(entityList.get(i).getUrl());
                                }
                            } else {
                              xRecyclerView.loadMoreComplete();
                            }
                        }

                    }
                });
        addSubscription(subscription);
    }

    @Override
    public void selectImage(WelfareBean.ResultsEntity entity, int position) {
        showToast(String.valueOf(position));
    }
}
