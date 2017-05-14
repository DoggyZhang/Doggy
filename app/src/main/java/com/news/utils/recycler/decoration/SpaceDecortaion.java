package com.news.utils.recycler.decoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by 阿飞 on 2017/4/15.
 */

public class SpaceDecortaion extends RecyclerView.ItemDecoration {

    private int space;

    public SpaceDecortaion(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = space;
        outRect.right = space;
        outRect.bottom = space;
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = space;
        }

    }

}
