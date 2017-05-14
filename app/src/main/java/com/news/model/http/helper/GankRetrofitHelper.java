package com.news.model.http.helper;


import com.news.model.bean.gank.GankBean;
import com.news.model.http.api.GankApis;
import com.news.model.http.response.GankHttpResponse;

import java.util.List;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by codeest on 2016/8/3.
 */
public class GankRetrofitHelper {

    private GankApis mGankApiService;


    public GankRetrofitHelper() {
        mGankApiService = new Retrofit
                .Builder()
                .build()
                .create(GankApis.class);
    }

    public Observable<GankHttpResponse<List<GankBean>>> fetchTechList(String tech, int num, int page) {
        return mGankApiService.getTechList(tech, num, page);
    }

    public Observable<GankHttpResponse<List<GankBean>>> fetchGirlList(int num, int page) {
        return mGankApiService.getGirlList(num, page);
    }

    public Observable<GankHttpResponse<List<GankBean>>> fetchRandomGirl(int num) {
        return mGankApiService.getRandomGirl(num);
    }

//    public Observable<GankHttpResponse<List<GankSearchItemBean>>> fetchGankSearchList(String query, String type, int num, int page) {
//        return mGankApiService.getSearchList(query, type, num, page);
//    }

}
