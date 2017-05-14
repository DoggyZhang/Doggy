package com.news.presenter;

import com.news.app.App;
import com.news.model.bean.user.LoginInfo;
import com.news.presenter.contract.LoginInfoContract;
import com.news.presenter.impl.PresenterImpl;
import com.news.ui.my.util.LoginUtils;
import com.news.utils.common.LogUtil;
import com.news.utils.rx.retrofit.RxUtils;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by 阿飞 on 2017/4/16.
 */

public class LoginInfoPresenter extends PresenterImpl<LoginInfoContract.View> implements LoginInfoContract.Presenter {
    public LoginInfoPresenter(LoginInfoContract.View view) {
        super(view);
    }

    @Override
    public void getLoginInfo() {
        Observable
                .create(
                        new Observable.OnSubscribe<LoginInfo>() {
                            @Override
                            public void call(Subscriber<? super LoginInfo> subscriber) {
                                LoginUtils loginUtils = LoginUtils.getInstance(App.getInstance());
                                subscriber.onNext(loginUtils.getLoginInfo());
                            }
                        })
                .compose(RxUtils.<LoginInfo>defaultSchedulers())
                .subscribe(new Subscriber<LoginInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (getView() != null) {
                            getView().showError(new Exception(e));
                        }
                        LogUtil.e(this.getClass().getSimpleName(), e);
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(LoginInfo loginInfo) {
                        if (getView() != null) {
                            getView().showLoginInfo(loginInfo);
                        }
                    }
                })
        ;
    }
}
