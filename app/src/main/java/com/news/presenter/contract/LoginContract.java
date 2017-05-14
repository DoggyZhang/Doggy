package com.news.presenter.contract;

import com.news.base.presenter.BasePresenter;
import com.news.base.view.BaseView;
import com.news.model.wiebo.bean.WeiboUser;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

/**
 * Created by 阿飞 on 2017/4/16.
 */

public interface LoginContract {
    int LOGIN_TYPE_VISITOR=  1;
    int LOGIN_TYPE_WEICHAT=  2;
    int LOGIN_TYPE_WEIBO=  3;

    interface View extends BaseView {
        void showLoginResult(int loginType , boolean success);

        void showWeiboUserInfo(WeiboUser user);
    }

    interface Presenter extends BasePresenter<View> {
        void loginByVisitor();

        void getWeiboUserInfo(Oauth2AccessToken accessToken);
    }
}
