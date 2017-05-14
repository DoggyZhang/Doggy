package com.news.presenter.contract;

import com.news.base.presenter.BasePresenter;
import com.news.base.view.BaseView;

/**
 * Created by 阿飞 on 2017/4/16.
 */

public interface CalendarContract {
    interface View extends BaseView {

    }

    interface Presenter extends BasePresenter<View> {

    }
}
