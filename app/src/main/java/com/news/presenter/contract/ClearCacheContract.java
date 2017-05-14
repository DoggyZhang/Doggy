package com.news.presenter.contract;

import com.news.base.presenter.BasePresenter;
import com.news.base.view.BaseView;
import com.news.model.bean.cache.CacheBean;

/**
 * Created by 阿飞 on 2017/4/16.
 */

public interface ClearCacheContract {
    interface View extends BaseView {
        void showCurrentCache(CacheBean cache);

        void showClearResult(boolean success);
    }

    interface Presenter extends BasePresenter<View> {

        void getCurrentCache();

        void clearCache();

    }
}
