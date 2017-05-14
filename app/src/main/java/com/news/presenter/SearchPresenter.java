package com.news.presenter;

import com.news.model.bean.gank.SearchBean;
import com.news.model.http.api.GankApis;
import com.news.model.http.response.GankHttpResponse;
import com.news.presenter.contract.SearchContract;
import com.news.presenter.impl.PresenterImpl;
import com.news.ui.search.SearchActivity;
import com.news.utils.common.LogUtil;
import com.news.utils.rx.retrofit.RxUtils;
import com.news.utils.rx.retrofit.ServiceFactory;

import java.util.List;

import rx.Subscriber;

/**
 * Created by 阿飞 on 2017/4/15.
 */

public class SearchPresenter extends PresenterImpl<SearchContract.View> implements SearchContract.Presenter {

    public SearchPresenter(SearchContract.View view) {
        super(view);
    }

    @Override
    public void getSearchResult(int type, String query, int count, int page) {
        if (query == null || query.isEmpty()) {
            if (getView() != null) {
                getView().showError(new Exception("搜索设置参数错误"));
            }
        }
        GankApis gankService = ServiceFactory
                .getInstance()
                .createService(GankApis.class);
        gankService.getSearchList(query, getType(type), count, page)
                .compose(RxUtils.<GankHttpResponse<List<SearchBean>>>defaultSchedulers())
                .subscribe(new Subscriber<GankHttpResponse<List<SearchBean>>>() {
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
                    public void onNext(GankHttpResponse<List<SearchBean>> listGankHttpResponse) {
                        if (getView() != null) {
                            getView().showSearchResult(listGankHttpResponse.getResults());
                        }
                    }
                });
    }

    private String getType(int type) {
        switch (type) {
            case SearchActivity.SEARCH_TYPE_ALL:
                return "all";
            case SearchActivity.SEARCH_TYPE_ANDROID:
                return "Android";
            case SearchActivity.SEARCH_TYPE_IOS:
                return "ios";
            case SearchActivity.SEARCH_TYPE_WEB:
                return "前端";
            default:
                return "all";
        }
    }
}
