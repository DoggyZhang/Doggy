package com.news.presenter.contract;

import com.news.base.presenter.BasePresenter;
import com.news.base.view.BaseView;

/**
 * Created by 阿飞 on 2017/4/11.
 */

public interface HomeContract {

    interface View extends BaseView {

    }

    interface Presenter extends BasePresenter<View> {

    }
}
