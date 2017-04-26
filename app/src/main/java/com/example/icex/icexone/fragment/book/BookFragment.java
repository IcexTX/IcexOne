package com.example.icex.icexone.fragment.book;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;

import com.example.icex.icexone.R;
import com.example.icex.icexone.adapter.Able.BookAble;
import com.example.icex.icexone.adapter.BookAdapter;
import com.example.library.base.BaseFragment;
import com.example.library.bean.BookBean;
import com.example.library.httpRequest.HttpUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * ProjectName：IcexOne
 * Describe：加载书本内容
 * Author：Icex
 * CreationTime：2017/2/22
 */

public class BookFragment extends BaseFragment implements TabLayout.OnTabSelectedListener, BookAble {

    private XRecyclerView recyclerView;
    private List<BookBean.BooksEntity> entityList;
    private BookAdapter bookAdapter;
    private StaggeredGridLayoutManager layoutManager;
    private TabLayout tabLayout_title;
    private List<String> titleList;
    private int start = 0;
    private final int count = 18;
    private String tag = "综合";

    @Override
    protected int setView() {
        return R.layout.fragment_book;
    }

    @Override
    protected void initView() {
        recyclerView = getWidget(R.id.recyclerView);
        tabLayout_title = getWidget(R.id.tabLayout_title);
    }

    @Override
    protected void initData() {
        titleList = Arrays.asList(getActivity().getResources().getStringArray(R.array.BookList));
        for (int i = 0; i < titleList.size(); i++) {
            tabLayout_title.addTab(tabLayout_title.newTab().setText(titleList.get(i)));
        }
        tabLayout_title.addOnTabSelectedListener(this);
        entityList = new ArrayList<>();
        bookAdapter = new BookAdapter(getActivity(), entityList, this);
        layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);

        //设置不可下拉刷新
        recyclerView.setPullRefreshEnabled(true);
        //设置不可加载更多
        recyclerView.setLoadingMoreEnabled(true);
        recyclerView.setLayoutManager(layoutManager);
        // 需加，不然滑动不流畅
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(bookAdapter);
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                start = 0;
                getBookData();

            }

            @Override
            public void onLoadMore() {
                start += count;
                getBookData();
            }
        });
    }

    @Override
    protected void getData() {
        if (entityList != null && entityList.isEmpty()) {
            getBookData();
        }
    }

    private void getBookData() {
        Subscription subscription = HttpUtils.getInstance().getReadingRequest().getBookData(tag, start, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BookBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (start != 0) {
                            start -= count;
                        }
                    }

                    @Override
                    public void onNext(BookBean bookBean) {
                        if (start == 0) {
                            if (bookBean != null && bookBean.getBooks() != null && bookBean.getBooks().size() > 0) {
                                entityList = bookBean.getBooks();
                                bookAdapter.setDataList(entityList);
                                recyclerView.refreshComplete();
                            }
                        } else {
                            if (bookBean != null && bookBean.getBooks() != null && bookBean.getBooks().size() > 0) {
                                recyclerView.refreshComplete();
                                bookAdapter.addDataList(bookBean.getBooks());
                            } else {
                                recyclerView.loadMoreComplete();
                            }
                        }

                    }
                });
        addSubscription(subscription);
    }

    @Override
    public void onTabSelected(final TabLayout.Tab tab) {
        if (tab.getText().toString().equals(tag)) {
            showToast("已是该分类书籍");
            return;
        }
        tag = tab.getText().toString();
        if (!TextUtils.isEmpty(tag)) {
            start = 0;
            getBookData();
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void SelectBook(BookBean.BooksEntity bean) {

    }
}