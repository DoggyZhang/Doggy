package com.news.presenter;

import com.news.presenter.contract.MyContract;
import com.news.presenter.impl.PresenterImpl;

/**
 * Created by 阿飞 on 2017/4/11.
 */

public class MyPresenter extends PresenterImpl<MyContract.View> implements MyContract.Presenter {
    public MyPresenter(MyContract.View view) {
        super(view);
    }
}
