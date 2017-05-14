package com.news.presenter;

import com.news.model.bean.gank.GankBean;
import com.news.model.http.api.GankApis;
import com.news.model.http.response.GankHttpResponse;
import com.news.presenter.contract.GankContract;
import com.news.presenter.impl.PresenterImpl;
import com.news.ui.home.gank.fragment.GankFragment;
import com.news.utils.common.LogUtil;
import com.news.utils.rx.retrofit.RxUtils;
import com.news.utils.rx.retrofit.ServiceFactory;

import java.util.List;

import rx.Subscriber;

/**
 * Created by 阿飞 on 2017/4/12.
 */

public class GankPresenter extends PresenterImpl<GankContract.View> implements GankContract.Presenter {

    public GankPresenter(GankContract.View view) {
        super(view);
    }

    @Override
    public void getGank(int type, int count, int page) {
        GankApis gankService = ServiceFactory
                .getInstance()
                .createService(GankApis.class);
        gankService.getTechList(getType(type), count, page)
                .compose(RxUtils.<GankHttpResponse<List<GankBean>>>defaultSchedulers())
                .subscribe(new Subscriber<GankHttpResponse<List<GankBean>>>() {
                    @Override
                    public void onCompleted() {
                        LogUtil.e(this.getClass().getSimpleName(), "Gank request Completed . ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        if( getView() == null ){
                            return;
                        }
                        getView().showError(new Exception(e));
                        LogUtil.e(this.getClass().getSimpleName(), e.getMessage());
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(GankHttpResponse<List<GankBean>> listGankHttpResponse) {
                        if (getView() != null) {
                            getView().showGank(listGankHttpResponse.getResults());
                        }
                    }
                });

    }

    private String getType(int type) {
        return GankFragment.techs[type];
    }

}
