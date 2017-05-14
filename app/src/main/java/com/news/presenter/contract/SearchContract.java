package com.news.presenter.contract;

import com.news.base.presenter.BasePresenter;
import com.news.base.view.BaseView;
import com.news.model.bean.gank.SearchBean;

import java.util.List;

/**
 * Created by 阿飞 on 2017/4/15.
 */

public interface SearchContract {
    interface View extends BaseView {
        void showSearchResult(List<SearchBean> results);
    }

    interface Presenter extends BasePresenter<View> {
        void getSearchResult(int type, String query, int count, int page);
    }
}
