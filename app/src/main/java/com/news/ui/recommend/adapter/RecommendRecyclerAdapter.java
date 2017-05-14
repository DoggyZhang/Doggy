package com.news.ui.recommend.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.news.R;
import com.news.model.bean.gank.GankBean;
import com.news.model.bean.gank.RecommendBean;
import com.news.ui.home.gank.activity.GankDetailActivity;
import com.news.utils.adapter.BaseRecyclerAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 阿飞 on 2017/4/16.
 */

public class RecommendRecyclerAdapter extends BaseRecyclerAdapter<RecommendRecyclerAdapter.ViewHolder, GankBean> {

    private boolean isSetAndroidType = false;
    private boolean isSetIOSType = false;
    private boolean isSetExpandResourceType = false;
    private boolean isSetVideoType = false;
    private boolean isSetGirlsType = false;

    private String ANDROID_TYPE_TITLE = null;
    private String IOS_TYPE_TITLE = null;
    private String EXPANDRESOURCE_TYPE_TITLE = null;
    private String VIDEO_TYPE_TITLE = null;
    private String GIRL_TYPE_TITLE = null;

    public RecommendRecyclerAdapter(@NonNull Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = mLayoutInflater.inflate(R.layout.item_recommend, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final GankBean item = this.mItems.get(position);

        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toDetail = new Intent();
                toDetail.setClass(mContext, GankDetailActivity.class);
                toDetail.putExtra(GankDetailActivity.SERIALIZABLE_GANK_INFO, item);
                mContext.startActivity(toDetail);
            }
        });
        switch (item.getType()) {
            case RecommendBean.ResultsBean.TYPE_ANDROID:
                if (isSetAndroidType) {
                    if (ANDROID_TYPE_TITLE != null && ANDROID_TYPE_TITLE.equals(item.get_id())) {
                        holder.tv_recommend_item_type.setVisibility(View.VISIBLE);
                        holder.tv_recommend_item_type.setText("Android");
                    } else {
                        holder.tv_recommend_item_type.setVisibility(View.INVISIBLE);
                    }
                } else {
                    holder.tv_recommend_item_type.setVisibility(View.VISIBLE);
                    holder.tv_recommend_item_type.setText("Android");
                    ANDROID_TYPE_TITLE = item.get_id();
                    isSetAndroidType = true;
                }
                break;
            case RecommendBean.ResultsBean.TYPE_IOS:
                if (isSetIOSType) {
                    if (IOS_TYPE_TITLE != null && IOS_TYPE_TITLE.equals(item.get_id())) {
                        holder.tv_recommend_item_type.setVisibility(View.VISIBLE);
                        holder.tv_recommend_item_type.setText("IOS");
                    } else {
                        holder.tv_recommend_item_type.setVisibility(View.INVISIBLE);
                    }
                } else {
                    holder.tv_recommend_item_type.setVisibility(View.VISIBLE);
                    holder.tv_recommend_item_type.setText("IOS");
                    IOS_TYPE_TITLE = item.get_id();
                    isSetIOSType = true;
                }
                break;
            case RecommendBean.ResultsBean.TYPE_EXPAND_RESCOURCE:
                if (isSetExpandResourceType) {
                    if (EXPANDRESOURCE_TYPE_TITLE != null && EXPANDRESOURCE_TYPE_TITLE.equals(item.get_id())) {
                        holder.tv_recommend_item_type.setVisibility(View.VISIBLE);
                        holder.tv_recommend_item_type.setText("扩展资源");
                    } else {
                        holder.tv_recommend_item_type.setVisibility(View.INVISIBLE);
                    }
                } else {
                    holder.tv_recommend_item_type.setVisibility(View.VISIBLE);
                    holder.tv_recommend_item_type.setText("扩展资源");
                    EXPANDRESOURCE_TYPE_TITLE = item.get_id();
                    isSetExpandResourceType = true;
                }
                break;
            case RecommendBean.ResultsBean.TYPE_VIDEO:
                if (isSetVideoType) {
                    if (VIDEO_TYPE_TITLE != null && VIDEO_TYPE_TITLE.equals(item.get_id())) {
                        holder.tv_recommend_item_type.setVisibility(View.VISIBLE);
                        holder.tv_recommend_item_type.setText("休息视频");
                    } else {
                        holder.tv_recommend_item_type.setVisibility(View.INVISIBLE);
                    }
                } else {
                    holder.tv_recommend_item_type.setVisibility(View.VISIBLE);
                    holder.tv_recommend_item_type.setText("休息视频");
                    VIDEO_TYPE_TITLE = item.get_id();
                    isSetAndroidType = true;
                }
                break;
            case RecommendBean.ResultsBean.TYPE_GIRLS:
                if (isSetGirlsType) {
                    if (GIRL_TYPE_TITLE != null && GIRL_TYPE_TITLE.equals(item.get_id())) {
                        holder.tv_recommend_item_type.setVisibility(View.VISIBLE);
                        holder.tv_recommend_item_type.setText("福利");
                    } else {
                        holder.tv_recommend_item_type.setVisibility(View.INVISIBLE);
                    }
                } else {
                    holder.tv_recommend_item_type.setVisibility(View.VISIBLE);
                    holder.tv_recommend_item_type.setText("福利");
                    GIRL_TYPE_TITLE = item.get_id();
                    isSetGirlsType = true;
                }
                break;
        }
        holder.tv_recommend_item_desc.setText(item.getDesc());
        holder.tv_recommend_item_who.setText(item.getWho());
    }

    @Override
    public void clear() {
        super.clear();
        isSetAndroidType = false;
        isSetIOSType = false;
        isSetExpandResourceType = false;
        isSetVideoType = false;
        isSetGirlsType = false;

        ANDROID_TYPE_TITLE = null;
        IOS_TYPE_TITLE = null;
        EXPANDRESOURCE_TYPE_TITLE = null;
        VIDEO_TYPE_TITLE = null;
        GIRL_TYPE_TITLE = null;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View root;

        @InjectView(R.id.tv_recommend_item_type)
        protected TextView tv_recommend_item_type;
        @InjectView(R.id.tv_recommend_item_desc)
        protected TextView tv_recommend_item_desc;
        @InjectView(R.id.tv_recommend_item_who)
        protected TextView tv_recommend_item_who;

        public ViewHolder(View itemView) {
            super(itemView);
            this.root = itemView;

            ButterKnife.inject(this, itemView);
        }
    }
}
