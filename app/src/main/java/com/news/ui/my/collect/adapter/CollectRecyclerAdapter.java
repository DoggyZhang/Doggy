package com.news.ui.my.collect.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.news.R;
import com.news.model.bean.gank.CollectBean;
import com.news.ui.home.gank.activity.GankDetailActivity;
import com.news.ui.other.dialog.DeleteDialog;
import com.news.utils.adapter.BaseRecyclerAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 阿飞 on 2017/4/16.
 */

public class CollectRecyclerAdapter extends BaseRecyclerAdapter<CollectRecyclerAdapter.ViewHolder, CollectBean> {

    private DeleteDialog mDeleteDialog;

    private OnItemDeleteListener mOnItemDeleteListener;

    public CollectRecyclerAdapter(@NonNull Context context) {
        super(context);
        setDeleteDialogConfig();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = mLayoutInflater.inflate(R.layout.item_collect, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final CollectBean collect = this.mItems.get(position);
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toDeatil = new Intent();
                toDeatil.setClass(mContext, GankDetailActivity.class);
                toDeatil.putExtra(GankDetailActivity.SERIALIZABLE_GANK_INFO, collect.getCollect());
                mContext.startActivity(toDeatil);
            }
        });

        holder.root.setLongClickable(true);
        holder.root.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mDeleteDialog.setTitle(collect.getCollect().getDesc());
                mDeleteDialog.setTarget(collect);
                mDeleteDialog.show();
                return false;
            }
        });

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%04d", collect.getYear()));
        sb.append("年");
        sb.append(String.format("%02d", collect.getMonth()));
        sb.append("月");
        sb.append(String.format("%02d", collect.getDay()));
        sb.append("日");
        holder.tv_collect_item_time.setText(sb.toString());
        holder.tv_collect_item_desc.setText(collect.getCollect().getDesc());
        holder.tv_collect_item_who.setText(collect.getCollect().getWho());
    }

    private void setDeleteDialogConfig() {
        mDeleteDialog = new DeleteDialog(mContext);
        mDeleteDialog.setOnChooseListener(new DeleteDialog.OnChooseListener<CollectBean>() {

            @Override
            public void onConfirm(CollectBean target) {
                if( mOnItemDeleteListener != null ){
                    mOnItemDeleteListener.onItemDelete(target);
                }
            }

            @Override
            public void onCancel() {

            }
        });
    }

    public void setOnItemDeleteListener(OnItemDeleteListener onItemDeleteListener) {
        this.mOnItemDeleteListener = onItemDeleteListener;
    }

    public interface OnItemDeleteListener {
        void onItemDelete(CollectBean collect);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View root;
        @InjectView(R.id.tv_collect_item_time)
        protected TextView tv_collect_item_time;
        @InjectView(R.id.tv_collect_item_desc)
        protected TextView tv_collect_item_desc;
        @InjectView(R.id.tv_collect_item_who)
        protected TextView tv_collect_item_who;

        public ViewHolder(View itemView) {
            super(itemView);
            root = itemView;
            ButterKnife.inject(this, itemView);
        }
    }
}
