package com.news.ui.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.news.R;
import com.news.model.bean.gank.GankBean;
import com.news.ui.home.gank.activity.GankDetailActivity;
import com.news.utils.adapter.BaseRecyclerAdapter;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 阿飞 on 2017/4/12.
 */

public class GankRecyclerAdapter extends BaseRecyclerAdapter<GankRecyclerAdapter.ViewHolder, GankBean> {

    private RecyclerView mRecyclerView;
    private ViewHolder mPressedHolder = null;

    public GankRecyclerAdapter(@NonNull Context context) {
        super(context);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.mRecyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.mRecyclerView = null;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_gank, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.v_gank_mask_detail.setVisibility(View.VISIBLE);
        final GankBean item = this.mItems.get(position);

        List<String> images = item.getImages();
        if (images != null && images.size() != 0) {
            String imageUrl = images.get(0);
            Uri uri = Uri.parse(imageUrl);
            if (uri != null) {
                holder.iv_gank_item_image.setVisibility(View.VISIBLE);
                holder.iv_gank_item_image.setImageURI(uri);
            }
        } else {
            holder.iv_gank_item_image.setVisibility(View.INVISIBLE);
        }
        // 2017-04-12T09:26:26.434Z
        String createdAt = item.getCreatedAt();
        if (createdAt != null && !createdAt.isEmpty()) {
            String[] split = createdAt.split("\\D");
            holder.tv_gank_createAt.setText("#" + split[0] + "-" + split[1] + "-" + split[2]);
        }

        String desc = item.getDesc();
        if (desc != null && !desc.isEmpty()) {
            holder.tv_gank_desc.setText(desc);
        }

        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMask();
                Intent toGankDetail = new Intent();
                toGankDetail.setClass(mContext, GankDetailActivity.class);
                toGankDetail.putExtra(GankDetailActivity.SERIALIZABLE_GANK_INFO, item);
                mContext.startActivity(toGankDetail);
            }
        });
    }

    public void showMask() {
        if (mPressedHolder != null) {
            mPressedHolder.v_gank_mask_detail.setVisibility(View.VISIBLE);
        }
        mPressedHolder = null;
    }

    public void hiddeMask(ViewHolder holder) {
        mPressedHolder = holder;
        if (mPressedHolder != null) {
            mPressedHolder.v_gank_mask_detail.setVisibility(View.INVISIBLE);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View root;
        @InjectView(R.id.iv_gank_item_image)
        SimpleDraweeView iv_gank_item_image;

        @InjectView(R.id.v_gank_mask_detail)
        View v_gank_mask_detail;
        @InjectView(R.id.v_gank_mask)
        SimpleDraweeView v_gank_mask;
        @InjectView(R.id.tv_gank_createAt)
        TextView tv_gank_createAt;
        @InjectView(R.id.tv_gank_desc)
        TextView tv_gank_desc;

        public ViewHolder(View itemView) {
            super(itemView);
            this.root = itemView;
            ButterKnife.inject(this, itemView);
            initEvent();
        }

        private void initEvent() {
            root.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            hiddeMask(ViewHolder.this);
                            break;
                    }
                    return false;
                }
            });

        }
    }
}
