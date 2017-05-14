package com.widget;

import com.news.R;

/**
 * Created by 阿飞 on 2017/4/13.
 */

public interface OnSwipeRefreshContract {
    int[] SCHEME_COLOR_WHIEL_TO_BLACK = {
            R.color.md_white_1000,
            R.color.md_black_1000,
    };

    interface OnRefresh {
        void onRefreshData();

        void onLoadMoreData();
    }


}
