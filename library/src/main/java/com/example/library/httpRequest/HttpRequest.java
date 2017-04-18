package com.example.library.httpRequest;

import com.example.library.ganHuo.BannerBean;
import com.example.library.ganHuo.DailyContentBean;
import com.example.library.ganHuo.WelfareBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * ProjectName：IcexOne
 * Describe：网络接口
 * Author：Icex
 * CreationTime：2017/3/2
 */

public interface HttpRequest {

    /**
     * 图片轮播
     */
    @GET("6?version=2.421&client_sys=android")
    Observable<BannerBean> getBanner();

    /**
     * 获取每日数据
     *
     * @param year  年份
     * @param month 月份
     * @param day   日期
     * @return
     */
    @GET("day/{year}/{month}/{day}")
    Observable<DailyContentBean> getContent(@Path("year") String year, @Path("month") String month, @Path("day") String day);

    /**
     * @param type   数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | App | 瞎推荐
     * @param number 请求个数： 数字，大于0
     * @param page   第几页：数字，大于0
     * @return
     */
    @GET("data/{type}/{number}/{page}")
    Observable<WelfareBean> getWelfare(@Path("type") String type, @Path("number") int number, @Path("page") int page);
}
