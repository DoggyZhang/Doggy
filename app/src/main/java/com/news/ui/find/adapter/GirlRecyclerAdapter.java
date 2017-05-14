package com.news.ui.find.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.news.R;
import com.news.model.bean.gank.GankBean;
import com.news.ui.other.picture.PictureActivity;
import com.news.utils.adapter.BaseRecyclerAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 阿飞 on 2017/4/15.
 */

public class GirlRecyclerAdapter extends BaseRecyclerAdapter<GirlRecyclerAdapter.ViewHolder, GankBean> {


    public GirlRecyclerAdapter(@NonNull Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_girl, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        GankBean girl = this.mItems.get(position);

        final String url = girl.getUrl();
        if (url != null && !url.isEmpty()) {
            Uri uri = Uri.parse(url);
            DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                    .setUri(uri)
                    .setAutoPlayAnimations(true)
                    .setControllerListener(new BaseControllerListener<ImageInfo>() {
                        @Override
                        public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                            if (imageInfo == null) {
                                return;
                            }
                            float aspectRatio = imageInfo.getWidth() * 1.0f / imageInfo.getHeight();
                            holder.iv_girl.setAspectRatio(aspectRatio);
                        }
                    })
                    .setOldController(holder.iv_girl.getController())
                    .build();
            holder.iv_girl.setController(draweeController);
            holder.iv_girl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent toPicture = new Intent();
                    toPicture.setClass(mContext, PictureActivity.class);
                    toPicture.putExtra(PictureActivity.PICTURE_URL, url);
                    mContext.startActivity(toPicture);
                }
            });
        } else {
            holder.iv_girl.setImageURI(Uri.parse("res://" + mContext.getPackageName() + "/" + R.mipmap.ic_error));
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View root;

        @InjectView(R.id.iv_girl_item)
        SimpleDraweeView iv_girl;

        public ViewHolder(View itemView) {
            super(itemView);
            this.root = itemView;
            ButterKnife.inject(this, itemView);
        }
    }
}
