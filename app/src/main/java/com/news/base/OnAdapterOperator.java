package com.news.base;

import java.util.Collection;

/**
 * Created by 阿飞 on 2017/4/12.
 */

public interface OnAdapterOperator<T> {

    void add(T item);

    void add(int index, T item);

    void add(Collection<T> items);

    void remove(T item);

    void remove(int index);

    void clear();
}
