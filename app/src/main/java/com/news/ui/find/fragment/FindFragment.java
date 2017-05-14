package com.news.ui.find.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.news.R;
import com.news.base.BaseFragment;
import com.news.model.bean.gank.GankBean;
import com.news.presenter.FindPresenter;
import com.news.presenter.contract.FindContract;
import com.news.ui.find.adapter.GirlRecyclerAdapter;
import com.news.utils.common.LogUtil;
import com.news.utils.common.ToastUtils;
import com.news.utils.recycler.decoration.SpaceDecortaion;
import com.widget.XRecyclerView;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by 阿飞 on 2017/4/11.
 */

public class FindFragment extends BaseFragment<FindPresenter> implements FindContract.View, View.OnClickListener {

    @InjectView(R.id.btn_find_show_type)
    protected Button btn_find_show_type;

    @InjectView(R.id.swipeRefresh)
    protected SwipeRefreshLayout v_refresh;
    @InjectView(R.id.recyclerView)
    protected XRecyclerView v_recycler;

    private GirlRecyclerAdapter mAdapter;
    private int mCount = 10;
    private static final int DEFAULT_PAGE = 1;
    private int mPage = DEFAULT_PAGE;

    private PopupMenu mShowTypeMenu;
    public static final int SHOW_TYPE_COL_1 = 1;
    public static final int SHOW_TYPE_COL_2 = 2;
    public static final int SHOW_TYPE_COL_3 = 3;
    public static final int SHOW_TYPE_COL_4 = 4;
    private int mColCount = SHOW_TYPE_COL_3;
    private StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(mColCount, StaggeredGridLayoutManager.VERTICAL);

    @Override
    protected FindPresenter setPresenter() {
        return new FindPresenter(this);
    }

    @Override
    protected void getData() {
        if (getPresenter() != null) {
            getPresenter().getGirl(mCount, mPage);
        } else {
            Toast.makeText(getContext(), getContext().getResources().getString(R.string.loadError), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_find;
    }

    @Override
    protected void handleArgument(Bundle argument) {

    }


    @Override
    protected void initEventAndData(View view, @Nullable Bundle savedInstanceState) {
        setShowTypeConfig();
        setRecyclerViewConfig();

        v_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });

        v_recycler.setOnBottomRefreshListener(new XRecyclerView.OnBottomRefreshListener() {
            @Override
            public void onBottomRefresh() {
                loadMore();
            }
        });

        getData();
    }

    @Override
    public void showGirl(List<GankBean> girls) {
        stopRefresh();
        mAdapter.add(girls);
    }

    @Override
    public void showError(Exception e) {
        stopRefresh();
        ToastUtils.text(getContext().getResources().getString(R.string.loadError));
        LogUtil.e(this.getClass().getSimpleName(), e);
    }

    private void setShowTypeConfig() {
        btn_find_show_type.setOnClickListener(this);
        mShowTypeMenu = new PopupMenu(getContext(), btn_find_show_type);
        mShowTypeMenu
                .getMenuInflater()
                .inflate(R.menu.menu_find_show_type, mShowTypeMenu.getMenu());
        mShowTypeMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_show_type_col_1:
                        mColCount = SHOW_TYPE_COL_1;
                        break;
                    case R.id.menu_show_type_col_2:
                        mColCount = SHOW_TYPE_COL_2;
                        break;
                    case R.id.menu_show_type_col_3:
                        mColCount = SHOW_TYPE_COL_3;
                        break;
                    case R.id.menu_show_type_col_4:
                        mColCount = SHOW_TYPE_COL_4;
                        break;
                    default:
                        mColCount = SHOW_TYPE_COL_3;
                        break;
                }
                setShowTypeCol(mColCount);
                return true;
            }
        });
        setShowTypeCol(mColCount);
    }

    private void setRecyclerViewConfig() {
        mAdapter = new GirlRecyclerAdapter(getContext());
        v_recycler.setAdapter(mAdapter);
        v_recycler.setLayoutManager(mLayoutManager);
        v_recycler.addItemDecoration(new SpaceDecortaion((int) getResources().getDimension(R.dimen.divider)));
    }

    private void setShowTypeCol(int col) {
        btn_find_show_type.setText("当前显示列数：" + col);
        mLayoutManager.setSpanCount(col);
    }

    private void showTypeMenu() {
        if (mShowTypeMenu != null) {
            mShowTypeMenu.show();
        }
    }

    private void hiddenTypeMenu() {
        if (mShowTypeMenu != null) {
            mShowTypeMenu.dismiss();
        }
    }

    public void refresh() {
        mPage = DEFAULT_PAGE;
        mAdapter.clear();
        getData();
    }

    public void stopRefresh() {
        if (v_refresh.isRefreshing()) {
            v_refresh.setRefreshing(false);
        }
    }

    public void loadMore() {
        mPage++;
        getData();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_find_show_type:
                showTypeMenu();
                break;
        }
    }
}
