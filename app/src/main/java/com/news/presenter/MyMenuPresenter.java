package com.news.presenter;

import com.news.presenter.contract.MyMenuContract;
import com.news.presenter.impl.PresenterImpl;

/**
 * Created by 阿飞 on 2017/4/16.
 */

public class MyMenuPresenter extends PresenterImpl<MyMenuContract.View> implements MyMenuContract.Presenter {

    public MyMenuPresenter(MyMenuContract.View view) {
        super(view);
    }
}
