package com.news.model.bean.about;

/**
 * Created by 阿飞 on 2017/4/18.
 */

public interface AboutContract {
    String TYPE_UI = "UI";
    String[] CONTENT_UI = {
            "com.android.support:design",
            "com.android.support:recyclerview",
            "com.android.support:cardview",
            "com.android.support:palette",
            "com.squareup:android-times-square",
            "com.github.chrisbanes.photoview"
    };
    String[] CONTENT_UI_URL = {
            "https://github.com/google",
            "https://github.com/google",
            "https://github.com/google",
            "https://github.com/google",
            "https://github.com/square/android-times-square",
            "https://github.com/chrisbanes/PhotoView"
    };

    String TYPE_NET = "Net";
    String[] CONTENT_NET = {
            "com.squareup.retrofit2:retrofit",
            "com.squareup.okhttp3:okhttp"
    };
    String[] CONTENT_NET_URL = {
            "https://github.com/square/retrofit",
            "https://github.com/square/okhttp"
    };

    String TYPE_DATA = "Data";
    String[] CONTENT_DATA = {
            "com.google.code.gson:gson",
            "com.squareup.retrofit2:converter-gson",
            "com.squareup.retrofit2:retrofit-adapters",
            "com.squareup.retrofit2:adapter-rxjava"
    };

    String[] CONTENT_DATA_URL = {
            "https://github.com/google/gson",
            "https://github.com/square/retrofit",
            "https://github.com/square/retrofit",
            "https://github.com/ReactiveX/RxJava"
    };

    String TYPE_IMAGE = "Image";
    String[] CONTENT_IMAGE = {
            "com.facebook.fresco:fresco",
            "com.github.bumptech.glide:glide"
    };
    String[] CONTENT_IMAGE_URL = {
            "https://github.com/facebook/fresco",
            "https://github.com/bumptech/glide"
    };

    String TYPE_ASYNC = "Async";
    String[] CONTENT_ASYNC = {
            "io.reactivex:rxjava",
            "io.reactivex:rxandroid",
            "com.tbruyelle.rxpermissions:rxpermissions"
    };
    String[] CONTENT_ASYNC_URL = {
            "https://github.com/ReactiveX/RxJava",
            "https://github.com/ReactiveX/RxAndroid",
            "https://github.com/tbruyelle/RxPermissions"
    };

    String TYPE_DEPENCE_INJECT = "Depence Inject";
    String[] CONTENT_DEPENCE_INJECT = {
            "com.jakewharton:butterknife"
    };
    String[] CONTENT_DEPENCE_INJECT_URL = {
            "https://github.com/JakeWharton/butterknife"
    };

    String TYPE_DB = "DB";
    String[] CONTENT_DB = {
            "de.greenrobot:greendao",
            "de.greenrobot:greendao-generator",
            "freemarker"
    };
    String[] CONTENT_DB_URL = {
            "https://github.com/greenrobot/greenDAO",
            "https://github.com/greenrobot/greenDAO",
            "https://github.com/greenrobot/greenDAO"
    };

    String TYPE_DEBUG = "Debug";
    String[] CONTENT_DEBUG = {
            "com.orhanobut:logger"
    };
    String[] CONTENT_DEBUG_URL = {
            "https://github.com/orhanobut/logger"
    };

    String TYPE_UTIL = "Util";
    String[] CONTENT_UTIL = {
            "com.google.guava:guava"
    };
    String[] CONTENT_UTIL_URL = {
            "https://github.com/google/guava"
    };


    String TYPE_ANNOTATION = "Annotation";
    String[] CONTENT_ANNOTATION = {
            "https://github.com/google"
    };
    String[] CONTENT_ANNOTATION_URL = {
            "com.android.support: support-annotations"
    };

    String TYPE_SUPPORT = "Support";
    String[] CONTENT_SUPPORT = {
            "com.android.support:design",
            "com.android.support:appcompat-v7",
            "com.android.support:support-v4",
            "com.android.support:support-v13",
            "com.android.support:multidex"
    };
    String[] CONTENT_SUPPORT_URL = {
            "https://github.com/google",
            "https://github.com/google",
            "https://github.com/google",
            "https://github.com/google",
            "https://github.com/google"
    };

    String TYPE_THIRD_PART = "Third Part";
    String[] CONTENT_THIRD_PART = {
            "com.sina.weibo.sdk:core"
    };
    String[] CONTENT_THIRD_PART_URL = {
            "https://github.com/sinaweibosdk/weibo_android_sdk"
    };
}
