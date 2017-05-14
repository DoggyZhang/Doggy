package com.news.presenter.contract;

import com.news.base.presenter.BasePresenter;
import com.news.base.view.BaseView;
import com.news.model.bean.gank.GankBean;

/**
 * Created by 阿飞 on 2017/4/13.
 */

public interface GankTechDetailContract {
    interface View extends BaseView {
        void showQueryResult(boolean success);

        void showSaveCollectSuccess(long saveID);

        void showSaveCollectFail(Exception e);

        void showDeleteResult(boolean success);
    }

    interface Presenter extends BasePresenter<View> {
        void isCollect(String url);

        void savaCollect(GankBean gank);

        void deleteCollect(String url);
    }
}
