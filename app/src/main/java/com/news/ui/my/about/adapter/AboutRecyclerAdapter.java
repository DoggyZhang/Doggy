package com.news.ui.my.about.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.news.R;
import com.news.model.bean.about.AboutBean;
import com.news.model.bean.gank.SearchBean;
import com.news.ui.search.SearchDetailActivity;
import com.news.utils.adapter.BaseRecyclerAdapter;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 阿飞 on 2017/4/18.
 */

public class AboutRecyclerAdapter extends BaseRecyclerAdapter<AboutRecyclerAdapter.ViewHolder, AboutBean> {
    // position , type
    private Map<Integer, String> hasAddType;

    public AboutRecyclerAdapter(@NonNull Context context) {
        super(context);
        hasAddType = new HashMap<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = mLayoutInflater.inflate(R.layout.item_about, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AboutBean about = mItems.get(position);
        String type = about.getType();
        final String content = about.getContent();
        final String url = about.getUrl();
        if (hasAddType.containsValue(type)) {
            if (type.equals(hasAddType.get(position))) {
                holder.tv_about_item_type.setVisibility(View.VISIBLE);
                holder.tv_about_item_type.setText(type);
            } else {
                holder.tv_about_item_type.setVisibility(View.INVISIBLE);
            }
        } else {
            hasAddType.put(position, type);
            holder.tv_about_item_type.setVisibility(View.VISIBLE);
            holder.tv_about_item_type.setText(type);
        }
        holder.tv_about_item_content.setText(content);

        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toDetail = new Intent();
                toDetail.setClass(mContext , SearchDetailActivity.class);
                SearchBean detail = new SearchBean();
                detail.setDesc(content);
                detail.setUrl(url);
                toDetail.putExtra(SearchDetailActivity.INTENT_SEARCH_BEAN , detail);
                mContext.startActivity(toDetail);
            }
        });
    }

    public void reset() {
        hasAddType.clear();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View root;
        @InjectView(R.id.tv_about_item_type)
        protected TextView tv_about_item_type;
        @InjectView(R.id.tv_about_item_content)
        protected TextView tv_about_item_content;

        public ViewHolder(View itemView) {
            super(itemView);
            root = itemView;
            ButterKnife.inject(this, itemView);
        }
    }
}
