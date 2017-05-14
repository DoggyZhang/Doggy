package com.news.base.view;

/**
 * Created by 阿飞 on 2017/4/8.
 * <p>
 * 定义 BaseView
 * 显示公共方法
 */

public interface BaseView {
    /**
     * 显示加载失败的信息
     *
     * @param e 失败原因
     */
    void showError(Exception e);

}
