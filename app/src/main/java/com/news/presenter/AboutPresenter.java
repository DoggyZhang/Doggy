package com.news.presenter;

import com.news.model.bean.about.AboutBean;
import com.news.model.bean.about.AboutInfos;
import com.news.presenter.contract.AboutContract;
import com.news.presenter.impl.PresenterImpl;
import com.news.utils.rx.retrofit.RxUtils;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by 阿飞 on 2017/4/16.
 */

public class AboutPresenter extends PresenterImpl<AboutContract.View> implements AboutContract.Presenter {

    public AboutPresenter(AboutContract.View view) {
        super(view);
    }

    @Override
    public void getAbouts() {
        Observable
                .create(new Observable.OnSubscribe<List<AboutBean>>() {
                    @Override
                    public void call(Subscriber<? super List<AboutBean>> subscriber) {
                        AboutInfos abouts = new AboutInfos();
                        subscriber.onNext(abouts.getAbout());
                    }
                })
                .compose(RxUtils.<List<AboutBean>>defaultSchedulers())
                .subscribe(new Subscriber<List<AboutBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (getView() != null) {
                            getView().showError((Exception) e);
                        }
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<AboutBean> aboutBeen) {
                        if (getView() != null) {
                            getView().showAbout(aboutBeen);
                        }
                    }
                });
    }
}
