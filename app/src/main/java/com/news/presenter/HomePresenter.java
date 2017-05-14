package com.news.presenter;

import com.news.presenter.contract.HomeContract;
import com.news.presenter.impl.PresenterImpl;

/**
 * Created by 阿飞 on 2017/4/11.
 */

public class HomePresenter extends PresenterImpl<HomeContract.View> implements HomeContract.Presenter {
    public HomePresenter(HomeContract.View view) {
        super(view);
    }
}
