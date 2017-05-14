package com.news.presenter;

import com.news.presenter.contract.SearchDetailContract;
import com.news.presenter.impl.PresenterImpl;

/**
 * Created by 阿飞 on 2017/4/15.
 */

public class SearchDetailPresenter extends PresenterImpl<SearchDetailContract.View> implements SearchDetailContract.Presenter {

    public SearchDetailPresenter(SearchDetailContract.View view) {
        super(view);
    }
}
