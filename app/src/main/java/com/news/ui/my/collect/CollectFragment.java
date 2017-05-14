package com.news.ui.my.collect;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.news.R;
import com.news.app.App;
import com.news.base.BaseFragment;
import com.news.model.bean.gank.CollectBean;
import com.news.presenter.CollectPresenter;
import com.news.presenter.contract.CollectContract;
import com.news.ui.my.collect.adapter.CollectRecyclerAdapter;
import com.news.utils.common.ToastUtils;
import com.news.utils.recycler.decoration.SpaceDecortaion;
import com.widget.OnSwipeRefreshContract;
import com.widget.XRecyclerView;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by 阿飞 on 2017/4/16.
 */

public class CollectFragment extends BaseFragment<CollectPresenter> implements CollectContract.View, View.OnClickListener {
    public static final String TAG = "CollectFragment";

    @InjectView(R.id.swipeRefresh)
    protected SwipeRefreshLayout v_refresh;
    @InjectView(R.id.recyclerView)
    protected XRecyclerView v_collects;
    @InjectView(R.id.iv_back)
    protected SimpleDraweeView iv_back;

    private CollectRecyclerAdapter mAdapter;

    @Override
    protected CollectPresenter setPresenter() {
        return new CollectPresenter(this);
    }

    @Override
    protected void getData() {
        if (getPresenter() != null) {
            if (mAdapter != null) {
                mAdapter.clear();
            }
            getPresenter().getCollects();
        }
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_collect;
    }

    @Override
    protected void handleArgument(Bundle argument) {

    }

    @Override
    protected void initEventAndData(View view, @Nullable Bundle savedInstanceState) {
        setBackConfig();
        setRefreshConfig();
        setRecyclerConfig();

        getData();
    }

    @Override
    public void showError(Exception e) {
        stopRefreshing();
        ToastUtils.text("请检查是否已登录");
    }

    @Override
    public void showCollects(List<CollectBean> collects) {
        stopRefreshing();
        if (collects != null) {
            if (collects.isEmpty()) {
                ToastUtils.text("你没有收藏东西哦");
            } else {
                mAdapter.add(collects);
            }
        }
    }

    @Override
    public void showDeleteCollectResult(boolean success) {
        if (success) {
            ToastUtils.text("删除成功");
        } else {
            ToastUtils.text("删除失败");
        }
        getData();
    }

    private void setBackConfig() {
        iv_back.setOnClickListener(this);
        iv_back.setImageURI(Uri.parse("res://" + App.getInstance().getPackageName() + "/" + R.mipmap.ic_goback));
    }

    private void setRecyclerConfig() {
        v_collects.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mAdapter = new CollectRecyclerAdapter(getContext());
        mAdapter.setOnItemDeleteListener(new CollectRecyclerAdapter.OnItemDeleteListener() {
            @Override
            public void onItemDelete(CollectBean collect) {
                if( getPresenter() != null){
                    getPresenter().deleteCollect(collect);
                    getData();
                }
            }
        });
        v_collects.setAdapter(mAdapter);
        v_collects.addItemDecoration(new SpaceDecortaion(10));
    }

    private void setRefreshConfig() {
        v_refresh.setColorSchemeColors(OnSwipeRefreshContract.SCHEME_COLOR_WHIEL_TO_BLACK);
        v_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });
    }

    private void stopRefreshing() {
        if (v_refresh != null && v_refresh.isRefreshing()) {
            v_refresh.setRefreshing(false);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                getFragmentManager().popBackStack();
                break;
        }
    }
}
