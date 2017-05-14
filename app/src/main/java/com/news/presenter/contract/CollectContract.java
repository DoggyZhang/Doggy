package com.news.presenter.contract;

import com.news.base.presenter.BasePresenter;
import com.news.base.view.BaseView;
import com.news.model.bean.gank.CollectBean;

import java.util.List;

/**
 * Created by 阿飞 on 2017/4/16.
 */

public interface CollectContract {
    interface View extends BaseView {
        void showCollects(List<CollectBean> collects);

        void showDeleteCollectResult(boolean success);
    }

    interface Presenter extends BasePresenter<View> {
        void getCollects();

        void deleteCollect(CollectBean collect);
    }
}
