package com.news.presenter.contract;

import com.news.base.presenter.BasePresenter;
import com.news.base.view.BaseView;

/**
 * Created by 阿飞 on 2017/4/10.
 */

public interface SplashContract {

    public interface View extends BaseView {
        void show();
    }

    public interface Presenter extends BasePresenter<View> {
        void getSplash();
    }
}
