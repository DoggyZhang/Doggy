package com.news.model.http.api;


import com.news.model.bean.gank.GankBean;
import com.news.model.bean.gank.RecommendBean;
import com.news.model.bean.gank.SearchBean;
import com.news.model.http.response.GankHttpResponse;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by codeest on 16/8/19.
 */

public interface GankApis {

    String BASE_URL = "http://gank.io/api/";

    /**
     * 技术文章列表
     * "Android", "iOS", "前端", "拓展资源"
     */
    @GET("data/{tech}/{num}/{page}")
    Observable<GankHttpResponse<List<GankBean>>> getTechList(@Path("tech") String tech, @Path("num") int num, @Path("page") int page);

    /**
     * 妹纸列表
     */
    @GET("data/福利/{num}/{page}")
    Observable<GankHttpResponse<List<GankBean>>> getGirlList(@Path("num") int num, @Path("page") int page);

    /**
     * 随机妹纸图
     */
    @GET("random/data/福利/{num}")
    Observable<GankHttpResponse<List<GankBean>>> getRandomGirl(@Path("num") int num);

    /**
     * 搜索
     */
    @GET("search/query/{query}/category/{type}/count/{count}/page/{page}")
    Observable<GankHttpResponse<List<SearchBean>>> getSearchList(@Path("query") String query, @Path("type") String type, @Path("count") int num, @Path("page") int page);

    /**
     * 推荐
     */
    @GET("day/{year}/{month}/{day}")
    Observable<GankHttpResponse<RecommendBean.ResultsBean>> getRecommend(@Path("year") int year, @Path("month") int month, @Path("day") int day);
}
