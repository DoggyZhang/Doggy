package com.news.presenter;

import com.news.presenter.contract.CalendarContract;
import com.news.presenter.impl.PresenterImpl;

/**
 * Created by 阿飞 on 2017/4/16.
 */

public class CalendarPresenter extends PresenterImpl<CalendarContract.View> implements CalendarContract.Presenter {

    public CalendarPresenter(CalendarContract.View view) {
        super(view);
    }
}
