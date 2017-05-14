package com.news.ui.home.gank.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.news.R;
import com.news.base.BaseFragment;
import com.news.model.bean.gank.GankBean;
import com.news.presenter.GankPresenter;
import com.news.presenter.contract.GankContract;
import com.news.ui.home.adapter.GankRecyclerAdapter;
import com.news.utils.common.LogUtil;
import com.widget.OnSwipeRefreshContract;
import com.widget.XRecyclerView;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by 阿飞 on 2017/4/11.
 */

public class GankFragment extends BaseFragment<GankPresenter> implements GankContract.View, OnSwipeRefreshContract.OnRefresh {

    public static final String TYPE_GANK = "type_gank";
    public static final String[] techs = {"Android", "iOS", "前端"};
    public static final int TYPE_ANDROID = 0x0000;
    public static final int TYPE_IOS = 0x0001;
    public static final int TYPE_WEB = 0x0002;

    private int mGankType = 0;
    private static final int DEFAULT_COUNT = 10;
    private int mCount = DEFAULT_COUNT;
    private static final int DEFAULT_PAGE = 1;
    private int mPage = 1;

    @InjectView(R.id.swipeRefresh)
    protected SwipeRefreshLayout v_refresh;
    @InjectView(R.id.recyclerView)
    protected XRecyclerView v_ganks;

    private GankRecyclerAdapter v_adapter;

    @Override
    protected GankPresenter setPresenter() {
        return new GankPresenter(this);
    }

    @Override
    protected void getData() {
        if (getPresenter() != null) {
            getPresenter().getGank(mGankType, mCount, mPage);
        }
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_gank;
    }

    @Override
    protected void handleArgument(Bundle argument) {
        if (argument == null) {
            return;
        }
        this.mGankType = argument.getInt(TYPE_GANK, TYPE_ANDROID);
    }

    @Override
    protected void initEventAndData(View view, @Nullable Bundle savedInstanceState) {
        setRecyclerConfig();
        setRefreshConfig();

        getData();
    }

    @Override
    public void showGank(List<GankBean> ganks) {
        LogUtil.e(this.getClass().getSimpleName(), "Gank " + techs[mGankType] + " ");
        LogUtil.e(this.getClass().getSimpleName(), "Data count : " + ganks == null ? 0 : ganks.size());
        if (v_adapter != null) {
            v_adapter.add(ganks);
        } else {
            LogUtil.e(this.getClass().getSimpleName(), "Gank Adapter is null .");
        }
        stopRefreshAndLoad();
    }

    @Override
    public void showError(Exception e) {
        LogUtil.e(this.getClass().getSimpleName(), e.getMessage());
        stopRefreshAndLoad();
    }

    private void setRecyclerConfig(){
        v_ganks.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        v_adapter = new GankRecyclerAdapter(getContext());
        v_ganks.setAdapter(v_adapter);

        v_ganks.setOnBottomRefreshListener(new XRecyclerView.OnBottomRefreshListener() {
            @Override
            public void onBottomRefresh() {
                onLoadMoreData();
            }
        });
        v_ganks.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                    case RecyclerView.SCROLL_STATE_SETTLING:
                        showItemMask();
                        break;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private void setRefreshConfig(){
        v_refresh.setColorSchemeColors(OnSwipeRefreshContract.SCHEME_COLOR_WHIEL_TO_BLACK);
        v_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onRefreshData();
            }
        });
    }

    public void showItemMask() {
        if (v_adapter != null) {
            v_adapter.showMask();
        }
    }

    public void stopRefreshAndLoad() {
        stopRefreshing();
        stopLoadMoreing();
    }

    public void stopRefreshing() {
        if (v_refresh != null) {
            if (v_refresh.isRefreshing()) {
                v_refresh.setRefreshing(false);
            }
        }
    }

    public void stopLoadMoreing() {
        if (v_ganks != null) {
            if (v_ganks.isBottomRefreshing()) {
                v_ganks.setBottomRefreshing(false);
            }
        }
    }

    public void setGankType(int type) {
        this.mGankType = type;
    }

    public int getGankType() {
        return mGankType;
    }

    @Override
    public void onRefreshData() {
        if (v_adapter != null) {
            v_adapter.clear();
        } else {
            v_adapter = new GankRecyclerAdapter(getContext());
        }
        setdefaultPage();
        getData();
    }

    @Override
    public void onLoadMoreData() {
        nextPage();
        getData();
    }

    public void nextPage() {
        mPage++;
    }

    public void setdefaultPage() {
        mPage = DEFAULT_PAGE;
    }

    public int getPage() {
        return mPage;
    }
}
