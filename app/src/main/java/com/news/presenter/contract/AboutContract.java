package com.news.presenter.contract;

import com.news.base.presenter.BasePresenter;
import com.news.base.view.BaseView;
import com.news.model.bean.about.AboutBean;

import java.util.List;

/**
 * Created by 阿飞 on 2017/4/16.
 */

public interface AboutContract {
    interface View extends BaseView {
        void showAbout(List<AboutBean> abouts);

    }

    interface Presenter extends BasePresenter<View> {
        void getAbouts();
    }
}
