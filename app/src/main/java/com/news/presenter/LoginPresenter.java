package com.news.presenter;

import com.news.R;
import com.news.app.App;
import com.news.app.Constants;
import com.news.model.http.api.WeiboApis;
import com.news.model.wiebo.bean.WeiboUser;
import com.news.presenter.contract.LoginContract;
import com.news.presenter.impl.PresenterImpl;
import com.news.ui.my.util.LoginUtils;
import com.news.utils.rx.retrofit.RxUtils;
import com.news.utils.rx.retrofit.ServiceFactory;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by 阿飞 on 2017/4/16.
 */

public class LoginPresenter extends PresenterImpl<LoginContract.View> implements LoginContract.Presenter {
    public LoginPresenter(LoginContract.View view) {
        super(view);
    }

    @Override
    public void loginByVisitor() {
        Observable
                .create(new Observable.OnSubscribe<Boolean>() {
                    @Override
                    public void call(Subscriber<? super Boolean> subscriber) {
                        LoginUtils login = LoginUtils.getInstance(App.getInstance());
                        login.setLoginInfo(Constants.SP_LOGIN_MODE_VISITOR,
                                "游客登录",
                                "res://" + App.getInstance().getPackageName() + "/" + R.mipmap.ic_visitor_normal,
                                System.currentTimeMillis()
                        );
                        subscriber.onNext(true);
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
                            getView().showError(new Exception(e));
                            getView().showLoginResult(LoginContract.LOGIN_TYPE_VISITOR, false);
                        }
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Boolean success) {
                        LoginUtils login = LoginUtils.getInstance(App.getInstance());
                        login.setLoginInfo(
                                Constants.SP_LOGIN_MODE_VISITOR,
                                App.getInstance().getResources().getString(R.string.login_visitor),
                                "res://" + App.getInstance().getPackageName() + "/" + R.mipmap.ic_logo,
                                System.currentTimeMillis()
                        );
                        if (getView() != null) {
                            getView().showLoginResult(LoginContract.LOGIN_TYPE_VISITOR, true);
                        }
                    }
                });
    }

    @Override
    public void getWeiboUserInfo(final Oauth2AccessToken accessToken) {
        final WeiboApis weiboApis = ServiceFactory
                .getInstance()
                .createService(WeiboApis.class);
        weiboApis
                .getWeiboUser(accessToken.getToken(), accessToken.getUid())
                .compose(RxUtils.<WeiboUser>defaultSchedulers())
                .subscribe(new Subscriber<WeiboUser>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (getView() != null) {
                            getView().showError(new Exception(e));
                            getView().showLoginResult(Constants.SP_LOGIN_MODE_WEIBO , false);
                        }
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(WeiboUser weiboUser) {
                        if (weiboUser != null) {
                            LoginUtils login = LoginUtils.getInstance(App.getInstance());
                            login.setLoginInfo(
                                    Constants.SP_LOGIN_MODE_WEIBO,
                                    weiboUser.getName(),
                                    weiboUser.getAvatar_hd(),
                                    System.currentTimeMillis()
                            );
                        }
                        if (getView() != null) {
                            getView().showLoginResult(Constants.SP_LOGIN_MODE_WEIBO , true);
                            getView().showWeiboUserInfo(weiboUser);
                        }
                    }
                });
    }

}
