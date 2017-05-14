package com.news.presenter;

import com.news.model.bean.gank.RecommendBean;
import com.news.model.http.api.GankApis;
import com.news.model.http.response.GankHttpResponse;
import com.news.presenter.contract.RecommendContract;
import com.news.presenter.impl.PresenterImpl;
import com.news.utils.common.LogUtil;
import com.news.utils.rx.retrofit.RxUtils;
import com.news.utils.rx.retrofit.ServiceFactory;

import rx.Subscriber;

/**
 * Created by 阿飞 on 2017/4/11.
 */

public class RecommendPresenter extends PresenterImpl<RecommendContract.View> implements RecommendContract.Presenter {
    public RecommendPresenter(RecommendContract.View view) {
        super(view);
    }

    @Override
    public void getRecommend(int year, int month, int day) {
        GankApis gankService = ServiceFactory
                .getInstance()
                .createService(GankApis.class);
        gankService.getRecommend(year, month, day)
                .compose(RxUtils.<GankHttpResponse<RecommendBean.ResultsBean>>defaultSchedulers())
                .subscribe(new Subscriber<GankHttpResponse<RecommendBean.ResultsBean>>() {
                    @Override
                    public void onCompleted() {
                        LogUtil.e(this.getClass().getSimpleName(), "Gank request Completed . ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (getView() == null) {
                            return;
                        }
                        getView().showError(new Exception(e));
                        LogUtil.e(this.getClass().getSimpleName(), e.getMessage());
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(GankHttpResponse<RecommendBean.ResultsBean> response) {
                        if (getView() != null) {
                            getView().showRecommend(response.getResults().getAndroid());
                            getView().showRecommend(response.getResults().getIOS());
                            getView().showRecommend(response.getResults().getExpandResources());
                            getView().showRecommend(response.getResults().getVideo());
                            getView().showRecommend(response.getResults().getGirls());
                        }
                    }
                });
    }
}
