package com.news.presenter;

import com.news.app.App;
import com.news.db.util.CollectDBUtils;
import com.news.model.bean.gank.CollectBean;
import com.news.model.bean.gank.GankBean;
import com.news.presenter.contract.GankTechDetailContract;
import com.news.presenter.impl.PresenterImpl;
import com.news.ui.my.util.LoginUtils;
import com.news.utils.common.LogUtil;
import com.news.utils.rx.retrofit.RxUtils;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by 阿飞 on 2017/4/13.
 */

public class GankTechDetailPresenter extends PresenterImpl<GankTechDetailContract.View> implements GankTechDetailContract.Presenter {
    public GankTechDetailPresenter(GankTechDetailContract.View view) {
        super(view);
    }

    @Override
    public void savaCollect(final GankBean gank) {
        Observable
                .create(new Observable.OnSubscribe<Long>() {
                    @Override
                    public void call(Subscriber<? super Long> subscriber) {
                        LoginUtils login = LoginUtils.getInstance(App.getInstance());
                        if (login.isLogin()) {
                            CollectBean collectBean = gank.toCollectBean();
                            collectBean.setUser(login.getLoginUserName());
                            CollectDBUtils db = CollectDBUtils.getInstance(App.getInstance());
                            subscriber.onNext(db.save(collectBean));
                        } else {
                            subscriber.onError(new Throwable("未登录"));
                        }
                    }
                })
                .compose(RxUtils.<Long>defaultSchedulers())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (getView() != null) {
                            getView().showSaveCollectFail(new Exception(e.getMessage()));
                        }
                        LogUtil.e(this.getClass().getSimpleName(), e);
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Long saveID) {
                        if (getView() != null) {
                            getView().showSaveCollectSuccess(saveID);
                        }
                    }
                });
    }

    @Override
    public void deleteCollect(final String url) {
        Observable
                .create(new Observable.OnSubscribe<Boolean>() {
                    @Override
                    public void call(Subscriber<? super Boolean> subscriber) {
                        CollectDBUtils db = CollectDBUtils.getInstance(App.getInstance());
                        LoginUtils login = LoginUtils.getInstance(App.getInstance());
                        if (login.isLogin()) {
                            subscriber.onNext(db.delete(login.getLoginUserName(), url));
                        } else {
                            subscriber.onError(new Throwable("未登录"));
                        }
                    }
                })
                .compose(RxUtils.<Boolean>defaultSchedulers())
                .subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (getView() != null) {
                            getView().showDeleteResult(false);
                        }
                        LogUtil.e(this.getClass().getSimpleName(), e.getMessage());
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Boolean success) {
                        if (getView() != null) {
                            getView().showDeleteResult(success);
                        }
                    }
                });

    }

    @Override
    public void isCollect(final String url) {
        Observable
                .create(new Observable.OnSubscribe<Boolean>() {
                    @Override
                    public void call(Subscriber<? super Boolean> subscriber) {
                        CollectDBUtils db = CollectDBUtils.getInstance(App.getInstance());
                        LoginUtils login = LoginUtils.getInstance(App.getInstance());
                        if (login.isLogin()) {
                            List<CollectBean> collectBeen = db.loadAll(login.getLoginUserName(), url);
                            boolean hasCollect = collectBeen != null && collectBeen.size() > 0;
                            subscriber.onNext(hasCollect);
                        } else {
                            subscriber.onError(new Throwable("未登录"));
                        }
                    }
                })
                .compose(RxUtils.<Boolean>defaultSchedulers())
                .subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (getView() != null) {
                            getView().showError(new Exception(e.getMessage()));
                            getView().showQueryResult(false);
                        }
                        LogUtil.e(this.getClass().getSimpleName(), e);
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Boolean isCollect) {
                        if (getView() != null) {
                            getView().showQueryResult(isCollect);
                        }
                    }
                });
    }
}
