package com.news.presenter.contract;

import com.news.base.presenter.BasePresenter;
import com.news.base.view.BaseView;
import com.news.model.bean.gank.GankBean;

import java.util.List;

/**
 * Created by 阿飞 on 2017/4/11.
 */

public interface FindContract {
    interface View extends BaseView {
        void showGirl(List<GankBean> girls);
    }

    interface Presenter extends BasePresenter<View> {
        void getGirl(int count , int page);
    }
}
