package com.news.presenter;

import com.news.app.App;
import com.news.db.util.CollectDBUtils;
import com.news.model.bean.gank.CollectBean;
import com.news.presenter.contract.CollectContract;
import com.news.presenter.impl.PresenterImpl;
import com.news.ui.my.util.LoginUtils;
import com.news.utils.common.LogUtil;
import com.news.utils.rx.retrofit.RxUtils;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by 阿飞 on 2017/4/16.
 */

public class CollectPresenter extends PresenterImpl<CollectContract.View> implements CollectContract.Presenter {

    public CollectPresenter(CollectContract.View view) {
        super(view);
    }

    @Override
    public void getCollects() {
        Observable
                .create(new Observable.OnSubscribe<List<CollectBean>>() {
                    @Override
                    public void call(Subscriber<? super List<CollectBean>> subscriber) {
                        LoginUtils login = LoginUtils.getInstance(App.getInstance());
                        CollectDBUtils db = CollectDBUtils.getInstance(App.getInstance());
                        if (login.isLogin()) {
                            subscriber.onNext(db.loadAll(login.getLoginUserName()));
                        } else {
                            subscriber.onError(new Throwable("未登录"));
                        }

                    }
                })
                .compose(RxUtils.<List<CollectBean>>defaultSchedulers())
                .subscribe(new Subscriber<List<CollectBean>>() {
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
                    public void onNext(List<CollectBean> collects) {
                        if (getView() != null) {
                            getView().showCollects(collects);
                        }
                    }
                });
    }

    @Override
    public void deleteCollect(final CollectBean collect) {
        Observable
                .create(new Observable.OnSubscribe<Void>() {
                    @Override
                    public void call(Subscriber<? super Void> subscriber) {
                        CollectDBUtils db = CollectDBUtils.getInstance(App.getInstance());
                        db.delete(collect.get_id());
                    }
                })
                .compose(RxUtils.<Void>defaultSchedulers())
                .subscribe(new Subscriber<Void>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (getView() != null) {
                            getView().showDeleteCollectResult(false);
                        }
                        LogUtil.e(this.getClass().getSimpleName(), e);
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Void aVoid) {
                        if (getView() != null) {
                            getView().showDeleteCollectResult(true);
                        }
                    }
                });
    }
}
