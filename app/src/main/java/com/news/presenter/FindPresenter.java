package com.news.presenter;

import com.news.model.bean.gank.GankBean;
import com.news.model.http.api.GankApis;
import com.news.model.http.response.GankHttpResponse;
import com.news.presenter.contract.FindContract;
import com.news.presenter.impl.PresenterImpl;
import com.news.utils.common.LogUtil;
import com.news.utils.rx.retrofit.RxUtils;
import com.news.utils.rx.retrofit.ServiceFactory;

import java.util.List;

import rx.Subscriber;

/**
 * Created by 阿飞 on 2017/4/11.
 */

public class FindPresenter extends PresenterImpl<FindContract.View> implements FindContract.Presenter {
    public FindPresenter(FindContract.View view) {
        super(view);
    }

    @Override
    public void getGirl(int count, int page) {
        GankApis gankService = ServiceFactory
                .getInstance()
                .createService(GankApis.class);
        gankService.getGirlList(count, page)
                .compose(RxUtils.<GankHttpResponse<List<GankBean>>>defaultSchedulers())
                .subscribe(new Subscriber<GankHttpResponse<List<GankBean>>>() {
                    @Override
                    public void onCompleted() {
                        LogUtil.e(this.getClass().getSimpleName(), "Gank request Completed . ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (getView() == null) {
                            return;
                        }
                        getView().showError((Exception) e);
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(GankHttpResponse<List<GankBean>> listGankHttpResponse) {
                        if (getView() != null) {
                            getView().showGirl(listGankHttpResponse.getResults());
                        }
                    }
                });
    }
}
