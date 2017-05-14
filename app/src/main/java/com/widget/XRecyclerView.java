package com.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

/**
 * Created by 阿飞 on 2017/4/13.
 */

public class XRecyclerView extends RecyclerView {

    private OnBottomRefreshListener mBottomRefreshListener;
    private RecyclerView.OnScrollListener mOnScrollListener;
    private boolean mBottomRefreshable;
    private boolean isBottomRefreshing;

    public XRecyclerView(Context context) {
        super(context);
        init();
    }

    public XRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mBottomRefreshListener = null;
        mBottomRefreshable = false;
        mOnScrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isBottomViewVisible()) {
                    if (mBottomRefreshListener != null) {
                        mBottomRefreshListener.onBottomRefresh();
                        isBottomRefreshing = true;
                    }
                }
            }
        };
    }

    private int getLastVisibleItemPosition() {
        RecyclerView.LayoutManager manager = getLayoutManager();
        if (manager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) manager).findLastVisibleItemPosition();
        }
        if (manager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager m = (StaggeredGridLayoutManager) manager;
            int[] positions = new int[m.getSpanCount()];
            positions = m.findLastVisibleItemPositions(positions);
            return getMax(positions);
        }
        return NO_POSITION;
    }

    public int getMax(int[] nums) {
        int max = 0;
        for (int num : nums) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    private boolean isBottomViewVisible() {
        int lastVisibleItem = getLastVisibleItemPosition();
        return lastVisibleItem != NO_POSITION && lastVisibleItem == getAdapter().getItemCount() - 1;
    }

    public boolean isBottomRefreshable() {
        return mBottomRefreshable;
    }

    public boolean isBottomRefreshing() {
        return isBottomRefreshing;
    }

    public void setBottomRefreshing(boolean isRefreshing) {
        this.isBottomRefreshing = isRefreshing;
    }

    // 设置底部下拉刷新监听
    public void setOnBottomRefreshListener(OnBottomRefreshListener listener) {
        mBottomRefreshListener = listener;
        if (mBottomRefreshListener != null) {
            addOnScrollListener(mOnScrollListener);
            mBottomRefreshable = true;
        } else {
            removeOnScrollListener(mOnScrollListener);
            mBottomRefreshable = false;
        }
    }

    public interface OnBottomRefreshListener {
        void onBottomRefresh();
    }
}

