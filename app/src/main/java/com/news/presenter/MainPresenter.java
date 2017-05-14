package com.news.presenter;

import com.news.presenter.contract.MainContract;
import com.news.presenter.impl.PresenterImpl;

/**
 * Created by 阿飞 on 2017/4/10.
 */

public class MainPresenter extends PresenterImpl<MainContract.View> implements MainContract.Presenter {

    public MainPresenter(MainContract.View view) {
        super(view);
    }


}
