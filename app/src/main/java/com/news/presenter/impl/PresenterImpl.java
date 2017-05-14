package com.news.presenter.impl;

import com.news.base.presenter.BasePresenter;
import com.news.base.view.BaseView;

import java.lang.ref.WeakReference;


/**
 * Created by 阿飞 on 2017/3/27.
 */
public class PresenterImpl<V extends BaseView> implements BasePresenter<V> {

    protected WeakReference<V> mView;

    public PresenterImpl(V view) {
        attachView(view);
    }

    @Override
    public void attachView(V view) {
        if (mView != null) {
            detachView();
        }
        this.mView = new WeakReference<V>(view);
    }

    @Override
    public void detachView() {
        if (mView != null) {
            mView.clear();
            mView = null;
        }
    }

    public V getView() {
        return mView == null ? null : mView.get();
    }
}