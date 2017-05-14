package com.news.model.base;

/**
 * Created by 阿飞 on 2017/3/27.
 */

public interface BaseModel {
    void getData(OnLoadCompleteListener onLoadCompleteListener, String requestUrl);

    interface OnLoadCompleteListener {
        void onLoadComplete(byte[] bytes, String requestUrl);

        void onLoadError(Exception e);
    }
}
