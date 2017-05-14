/*
 * Copyright (C) 2010-2013 The SINA WEIBO Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.news.model.wiebo.bean;

import com.google.gson.annotations.SerializedName;

/**
 * 用户信息结构体。
 */
public class WeiboUser {


    /**
     * id : 6048029149
     * idstr : 6048029149
     * class : 1
     * screen_name : _DoggyZhang
     * name : _DoggyZhang
     * province : 11
     * city : 8
     * location : 北京 海淀区
     * description :
     * url :
     * profile_image_url : http://tva2.sinaimg.cn/crop.18.28.239.239.50/006BiUFfjw8fb0heigg3bj307x07xglu.jpg
     * profile_url : u/6048029149
     * domain :
     * weihao :
     * gender : m
     * followers_count : 1
     * friends_count : 62
     * pagefriends_count : 0
     * statuses_count : 0
     * favourites_count : 0
     * created_at : Tue Nov 15 11:22:10 +0800 2016
     * following : false
     * allow_all_act_msg : false
     * geo_enabled : true
     * verified : false
     * verified_type : -1
     * remark :
     * insecurity : {"sexual_content":false}
     * ptype : 0
     * allow_all_comment : true
     * avatar_large : http://tva2.sinaimg.cn/crop.18.28.239.239.180/006BiUFfjw8fb0heigg3bj307x07xglu.jpg
     * avatar_hd : http://tva2.sinaimg.cn/crop.18.28.239.239.1024/006BiUFfjw8fb0heigg3bj307x07xglu.jpg
     * verified_reason :
     * verified_trade :
     * verified_reason_url :
     * verified_source :
     * verified_source_url :
     * follow_me : false
     * online_status : 0
     * bi_followers_count : 0
     * lang : zh-cn
     * star : 0
     * mbtype : 0
     * mbrank : 0
     * block_word : 0
     * block_app : 0
     * credit_score : 80
     * user_ability : 0
     * urank : 1
     */

    @SerializedName("id")
    private long id;
    @SerializedName("idstr")
    private String idstr;
    @SerializedName("screen_name")
    private String screen_name;
    @SerializedName("name")
    private String name;
    @SerializedName("province")
    private String province;
    @SerializedName("city")
    private String city;
    @SerializedName("location")
    private String location;
    @SerializedName("description")
    private String description;
    @SerializedName("profile_image_url")
    private String profile_image_url;
    @SerializedName("profile_url")
    private String profile_url;
    @SerializedName("avatar_large")
    private String avatar_large;
    @SerializedName("avatar_hd")
    private String avatar_hd;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdstr() {
        return idstr;
    }

    public void setIdstr(String idstr) {
        this.idstr = idstr;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public void setScreen_name(String screen_name) {
        this.screen_name = screen_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfile_image_url() {
        return profile_image_url;
    }

    public void setProfile_image_url(String profile_image_url) {
        this.profile_image_url = profile_image_url;
    }

    public String getProfile_url() {
        return profile_url;
    }

    public void setProfile_url(String profile_url) {
        this.profile_url = profile_url;
    }

    public String getAvatar_large() {
        return avatar_large;
    }

    public void setAvatar_large(String avatar_large) {
        this.avatar_large = avatar_large;
    }

    public String getAvatar_hd() {
        return avatar_hd;
    }

    public void setAvatar_hd(String avatar_hd) {
        this.avatar_hd = avatar_hd;
    }
}
