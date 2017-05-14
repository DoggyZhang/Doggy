package com.news.presenter.contract;

import com.news.base.presenter.BasePresenter;
import com.news.base.view.BaseView;
import com.news.model.bean.gank.GankBean;

import java.util.List;

/**
 * Created by 阿飞 on 2017/4/11.
 */

public interface RecommendContract {
    interface View extends BaseView {
        void showRecommend(List<GankBean> recommend);
    }

    interface Presenter extends BasePresenter<View> {
        void getRecommend(int year, int month, int day);
    }
}
