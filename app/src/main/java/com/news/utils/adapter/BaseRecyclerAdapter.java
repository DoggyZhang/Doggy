package com.news.utils.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.news.base.OnAdapterOperator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by 阿飞 on 2017/4/15.
 */

public abstract class BaseRecyclerAdapter<VH extends RecyclerView.ViewHolder, T> extends RecyclerView.Adapter<VH> implements OnAdapterOperator<T> {

    public Context mContext;
    public LayoutInflater mLayoutInflater;
    public List<T> mItems;

    public BaseRecyclerAdapter(@NonNull Context context) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mItems = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return this.mItems.size();
    }

    @Override
    public void add(T item) {
        if( item == null ){
            return;
        }
        this.mItems.add(item);
        notifyDataSetChanged();
    }

    @Override
    public void add(int index, T item) {
        if( item == null ){
            return;
        }
        if (index < -1 || index > this.mItems.size()) {
            return;
        }
        this.mItems.add(index , item);
        notifyDataSetChanged();
    }

    @Override
    public void add(Collection<T> items) {
        if( items == null || items.isEmpty()){
            return;
        }
        this.mItems.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public void remove(T item) {
        if( item == null ){
            return;
        }
        this.mItems.remove(item);
        notifyDataSetChanged();
    }

    @Override
    public void remove(int index) {
        this.mItems.remove(index);
        notifyDataSetChanged();
    }

    @Override
    public void clear() {
        this.mItems.clear();
        notifyDataSetChanged();
    }
}
