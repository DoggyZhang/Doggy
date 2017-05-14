package com.news.presenter;

import com.news.model.bean.cache.CacheBean;
import com.news.presenter.contract.ClearCacheContract;
import com.news.presenter.impl.PresenterImpl;
import com.news.ui.my.clearcache.helper.CacheHelper;
import com.news.utils.rx.retrofit.RxUtils;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by 阿飞 on 2017/4/16.
 */

public class ClearCachePresenter extends PresenterImpl<ClearCacheContract.View> implements ClearCacheContract.Presenter {

    private CacheHelper cacheHelper = CacheHelper.getInstance();

    public ClearCachePresenter(ClearCacheContract.View view) {
        super(view);
    }

    @Override
    public void getCurrentCache() {
        Observable
                .create(new Observable.OnSubscribe<CacheBean>() {
                    @Override
                    public void call(Subscriber<? super CacheBean> subscriber) {
                        subscriber.onNext(cacheHelper.getCache());
                    }
                })
                .compose(RxUtils.<CacheBean>defaultSchedulers())
                .subscribe(new Subscriber<CacheBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (getView() != null) {
                            getView().showError((Exception) e);
                        }
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(CacheBean cache) {
                        if (getView() != null) {
                            getView().showCurrentCache(cache);
                        }
                    }
                });
    }

    @Override
    public void clearCache() {
        Observable
                .create(new Observable.OnSubscribe<CacheBean>() {
                    @Override
                    public void call(final Subscriber<? super CacheBean> subscriber) {
                        cacheHelper.clearCache();
                        cacheHelper.setOnClearCacheListener(new CacheHelper.OnClearCacheListener() {
                            @Override
                            public void finish() {
                                subscriber.onCompleted();
                            }
                        });
                    }
                })
                .compose(RxUtils.<CacheBean>defaultSchedulers())
                .subscribe(new Subscriber<CacheBean>() {
                    @Override
                    public void onCompleted() {
                        if (getView() != null) {
                            getView().showClearResult(true);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (getView() != null) {
                            getView().showClearResult(false);
                        }
                    }

                    @Override
                    public void onNext(CacheBean cache) {
                        if (getView() != null) {
                            getView().showClearResult(true);
                        }
                    }
                });

    }
}
