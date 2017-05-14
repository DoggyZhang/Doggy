package com.news.ui.search.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.news.R;
import com.news.model.bean.gank.SearchBean;
import com.news.ui.search.SearchDetailActivity;
import com.news.utils.adapter.BaseRecyclerAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 阿飞 on 2017/4/15.
 */

public class SearchRecyclerAdapter extends BaseRecyclerAdapter<SearchRecyclerAdapter.ViewHolder, SearchBean> {

    private static final int VIEW_TYPE_ITEM = 0x0001;
    private static final int VIEW_TYPE_LOAD_MORE = 0x0002;

    public SearchRecyclerAdapter(Context context) {
        super(context);
    }

//    @Override
//    public int getItemViewType(int position) {
////        if (position == getItemCount() - 1) {
////            return VIEW_TYPE_LOAD_MORE;
////        } else {
////            return VIEW_TYPE_ITEM;
////        }
//        return super.getItemViewType(position)
//    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
//        switch (viewType) {
//            case VIEW_TYPE_ITEM:
//                view = mLayoutInflater.inflate(R.layout.item_search, parent, false);
//                break;
//            case VIEW_TYPE_LOAD_MORE:
//                view = mLayoutInflater.inflate(R.layout.item_loadmore, parent, false);
//                break;
//        }
        view = mLayoutInflater.inflate(R.layout.item_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final SearchBean item = this.mItems.get(position);
        final String desc = item.getDesc();
        if (desc != null) {
            holder.tv_result_item.setText(desc);
            holder.tv_result_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent toDeatil = new Intent();
                    toDeatil.setClass(mContext, SearchDetailActivity.class);
                    toDeatil.putExtra(SearchDetailActivity.INTENT_SEARCH_BEAN, item);
                    mContext.startActivity(toDeatil);

                }
            });
        } else {
            holder.tv_result_item.setText("结果不明");
            holder.tv_result_item.setOnClickListener(null);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View root;

        @InjectView(R.id.tv_search_result_item)
        protected TextView tv_result_item;

        public ViewHolder(View itemView) {
            super(itemView);
            this.root = itemView;
            ButterKnife.inject(this, itemView);
        }
    }
}
