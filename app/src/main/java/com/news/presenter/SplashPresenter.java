package com.news.presenter;

import com.news.presenter.contract.SplashContract;
import com.news.presenter.impl.PresenterImpl;

/**
 * Created by 阿飞 on 2017/4/10.
 */

public class SplashPresenter extends PresenterImpl<SplashContract.View> implements SplashContract.Presenter {
    public SplashPresenter(SplashContract.View view) {
        super(view);
    }

    @Override
    public void getSplash() {

    }
}
