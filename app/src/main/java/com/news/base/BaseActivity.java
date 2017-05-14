package com.news.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.news.base.mode.OnNightMode;
import com.news.base.view.BaseView;
import com.news.presenter.impl.PresenterImpl;

import butterknife.ButterKnife;

/**
 * Created by 阿飞 on 2017/3/27.
 */

public abstract class BaseActivity<P extends PresenterImpl>
        extends AppCompatActivity implements BaseView, OnNightMode {

    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = setPresenter();
        mPresenter.attachView(this);
        resumeState(savedInstanceState);
        doBeforeContentView();
        setContentView(getContentView());
        doAfterContentView();
        initEvent();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.mPresenter != null) {
            this.mPresenter.detachView();
            this.mPresenter = null;
        }
    }

    /**
     * 设置当前视图的 Presenter
     *
     * @return
     */
    protected abstract P setPresenter();

    public P getPresenter() {
        return mPresenter;
    }

    /**
     * 获取当前布局资源 ID
     *
     * @return
     */
    protected abstract int getContentView();

    /**
     * 方法在 setContentView（）之前调用
     */
    protected void doBeforeContentView() {

    }

    /**
     * 该方法在 setContentView（）之后调用
     */
    protected void doAfterContentView() {
        ButterKnife.inject(this);
    }

    /**
     * 初始化 控件事件
     */
    protected abstract void initEvent();

    /**
     * 恢复 Activity 状态
     *
     * @param savedInstanceState
     */
    protected void resumeState(Bundle savedInstanceState) {

    }
}