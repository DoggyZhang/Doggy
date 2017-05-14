package com.news.presenter.contract;

import com.news.base.presenter.BasePresenter;
import com.news.base.view.BaseView;
import com.news.model.bean.user.LoginInfo;

/**
 * Created by 阿飞 on 2017/4/16.
 */

public interface LoginInfoContract {

    interface View extends BaseView {
        void showLoginInfo(LoginInfo loginInfo);
    }

    interface Presenter extends BasePresenter<View> {
        void getLoginInfo();
    }
}
