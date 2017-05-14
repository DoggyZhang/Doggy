package com.news.presenter.contract;

import android.content.Context;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.news.base.presenter.BasePresenter;
import com.news.base.view.BaseView;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by 阿飞 on 2017/4/13.
 */

public interface PictureControct {
    interface View extends BaseView {
        void show(GlideDrawable drawable);

        void showDownLoadResult(boolean success , String path);
    }

    interface Presenter extends BasePresenter<View> {
        void getPicture(Context context, String url, PhotoView view);

        void downLoadPicture(String url);
    }
}
