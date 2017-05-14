package com.news.model.http.api;

import com.news.model.wiebo.bean.WeiboUser;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by 阿飞 on 2017/4/19.
 */

public interface WeiboApis {

    //    https://api.weibo.com/2/users/show.json?access_token=2.00PfuSbGe3s8nB8cb1417ee0Y_V_VC&uid=6048029149
    String BASE_URL = "https://api.weibo.com/2/";

    @GET("users/show.json")
    Observable<WeiboUser> getWeiboUser(@Query("access_token") String access_token, @Query("uid") String uid);
}
