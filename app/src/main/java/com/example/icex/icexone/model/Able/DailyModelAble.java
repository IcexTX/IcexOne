package com.example.icex.icexone.model.Able;

import rx.Subscription;

/**
 * ProjectName：IcexOne
 * Describe：
 * Author：Icex
 * CreationTime：2017/3/2
 */

public interface DailyModelAble {

    void connectionFail();

    void loadSuccess(Object result);

    void addSubscription(Subscription subscription);

}
