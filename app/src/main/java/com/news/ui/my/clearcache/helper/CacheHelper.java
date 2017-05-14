package com.news.ui.my.clearcache.helper;

import com.facebook.cache.disk.DiskStorageCache;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.news.model.bean.cache.CacheBean;

/**
 * Created by 阿飞 on 2017/4/19.
 */

public class CacheHelper {

    private static CacheHelper INSTANCE;

    private OnClearCacheListener mOnClearCacheListener;

    public static CacheHelper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CacheHelper();
        }
        return INSTANCE;
    }

    public void setOnClearCacheListener(OnClearCacheListener onClearCacheListener) {
        this.mOnClearCacheListener = onClearCacheListener;
    }

    public CacheBean getCache() {
        CacheBean cache = new CacheBean();
        DiskStorageCache mainDiskStorageCache = Fresco
                .getImagePipelineFactory()
                .getMainDiskStorageCache();
        mainDiskStorageCache.trimToMinimum();

        long size = Fresco
                .getImagePipelineFactory()
                .getMainDiskStorageCache()
                .getSize();
        cache.setHasUsed(size);
        return cache;
    }

    public void clearCache() {
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        imagePipeline.clearMemoryCaches();
        imagePipeline.clearDiskCaches();
        if( this.mOnClearCacheListener != null ){
            this.mOnClearCacheListener.finish();
        }
    }

    public interface OnClearCacheListener {
        void finish();
    }
}

