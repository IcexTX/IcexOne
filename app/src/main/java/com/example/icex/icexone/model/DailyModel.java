package com.example.icex.icexone.model;

import com.example.icex.icexone.model.Able.DailyModelAble;
import com.example.icex.icexone.util.Constants;
import com.example.library.bean.BannerBean;
import com.example.library.bean.DailyContentBean;
import com.example.library.bean.GeneralContent;
import com.example.library.httpRequest.HttpUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * ProjectName：IcexOne
 * Describe：每日推荐处理类
 * Describe：每日推荐处理类
 * Author：Icex
 * CreationTime：2017/3/2
 */

public class DailyModel {

    private List<List<GeneralContent>> listList;

    public DailyModel() {
        this.listList = new ArrayList<>();
    }

    /**
     * 处理图片轮播类
     *
     * @param callBack
     */
    public void getBannerData(final DailyModelAble callBack) {
        Subscription subscription = HttpUtils.getInstance().getDouyuRequest().getBanner()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BannerBean>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {
                        callBack.connectionFail();
                    }

                    @Override
                    public void onNext(BannerBean bannerBean) {
                        //判断是否有图片轮播数据
                        if (bannerBean != null && bannerBean.getData().size() > 0) {
                            callBack.loadSuccess(bannerBean.getData());
                        } else {
                            callBack.connectionFail();
                        }
                    }
                });
        callBack.addSubscription(subscription);
    }

    /**
     * 获取显示内容
     */
    public void getContentData(String year, String month, String day, final DailyModelAble callBack) {
        //清除过往数据
        listList.clear();

        /**
         * 将获取到DailyContentBean数据转换为 List<List<GeneralContent>>
         */
        Func1<DailyContentBean, Observable<List<List<GeneralContent>>>> func1 = new Func1<DailyContentBean, Observable<List<List<GeneralContent>>>>() {
            @Override
            public Observable<List<List<GeneralContent>>> call(DailyContentBean dailyContentBean) {
                DailyContentBean.ResultsEntity entity = dailyContentBean.getResults();
                if (entity != null) {
                    if (entity.getAndroid() != null && entity.getAndroid().size() > 0) {
                        addListData(entity.getAndroid(), Constants.DAILY_ANDROID);
                    }
                    if (entity.getiOS() != null && entity.getiOS().size() > 0) {
                        addListData(entity.getiOS(), Constants.DAILY_IOS);
                    }
                    if (entity.getResources() != null && entity.getResources().size() > 0) {
                        addListData(entity.getResources(), Constants.DAILY_RESOURCES);
                    }
                    if (entity.getVideo() != null && entity.getVideo().size() > 0) {
                        addListData(entity.getVideo(), Constants.DAILY_VIDEO);
                    }
                    if (entity.getWelfare() != null && entity.getWelfare().size() > 0) {
                        addListData(entity.getWelfare(), Constants.DAILY_WELFARE);
                    }
                    if (entity.getRecommended() != null && entity.getRecommended().size() > 0) {
                        addListData(entity.getRecommended(), Constants.DAILY_RECOMMEND);
                    }
                    if (entity.getWeb() != null && entity.getWeb().size() > 0) {
                        addListData(entity.getWeb(), Constants.DAILY_WEB);
                    }
                    if (entity.getApp() != null && entity.getApp().size() > 0) {
                        addListData(entity.getApp(), Constants.DAILY_APP);
                    }
                }
                return Observable.just(listList);
            }
        };

        Observer<List<List<GeneralContent>>> observer = new Observer<List<List<GeneralContent>>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                callBack.connectionFail();
            }

            @Override
            public void onNext(List<List<GeneralContent>> lists) {
                callBack.loadSuccess(lists);
            }
        };


        /**
         * 请求每日推荐数据
         */
        Subscription subscription = HttpUtils.getInstance().getGangHuoRequest().getContent(year, month, day)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .flatMap(func1)
                .subscribe(observer);
        callBack.addSubscription(subscription);
    }

    /**
     * 因为适配器布局有多种
     * 所以加载新的带有标题的List<GeneralContent>
     * 作为显示title布局的数据
     * 提示:List.subList()方法不支持序列化
     *
     * @param contentList
     * @param title
     */
    private void addListData(List<GeneralContent> contentList, String title) {
        GeneralContent content = new GeneralContent();
        List<GeneralContent> arrayList = new ArrayList<>();
        content.setTitle(title);
        arrayList.add(content);
        listList.add(arrayList);
        //因为获取的数据大小不一，适配器能适配4中不同布局，所以限制list大小为4
        if (contentList.size() > 4) {
            List<GeneralContent> generalContents = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                generalContents.add(contentList.get(i));
            }
            listList.add(generalContents);
        } else {
            listList.add(contentList);
        }
    }

}
