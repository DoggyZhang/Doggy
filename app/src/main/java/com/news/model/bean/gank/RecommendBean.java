package com.news.model.bean.gank;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 阿飞 on 2017/4/16.
 */

public class RecommendBean {

    /**
     * category : ["iOS","拓展资源","mAndroid","video","福利"]
     * error : false
     * results : {"mAndroid":[{"_id":"56cc6d23421aa95caa707bba","createdAt":"2015-08-06T02:05:32.826Z","desc":"一个查看设备规格的库，并且可以计算哪一年被定为\u201c高端\u201d机","publishedAt":"2015-08-06T04:16:55.575Z","type":"mAndroid","url":"https://github.com/facebook/device-year-class","used":true,"who":"有时放纵"},{"_id":"56cc6d23421aa95caa707c67","createdAt":"2015-08-05T16:36:19.591Z","desc":"一个好玩的自定义字体库","publishedAt":"2015-08-06T04:16:55.805Z","type":"mAndroid","url":"https://github.com/vsvankhede/easyfonts","used":true,"who":"有时放纵"},{"_id":"56cc6d23421aa95caa707c6a","createdAt":"2015-08-06T02:07:23.290Z","desc":"一个小清新spinner","publishedAt":"2015-08-06T04:16:55.582Z","type":"mAndroid","url":"https://github.com/arcadefire/nice-spinner","used":true,"who":"有时放纵"}],"iOS":[{"_id":"56cc6d1d421aa95caa70777e","createdAt":"2015-08-06T01:55:36.30Z","desc":"iOS 核心动画高级技巧","publishedAt":"2015-08-06T04:16:55.592Z","type":"iOS","url":"http://zsisme.gitbooks.io/ios-/content/","used":true,"who":"Andrew Liu"},{"_id":"56cc6d23421aa95caa707a65","createdAt":"2015-08-05T03:45:12.537Z","desc":"设定label，text等视图的属性，你喜欢这种方式么？","publishedAt":"2015-08-06T04:16:55.584Z","type":"iOS","url":"http://natashatherobot.com/ios-a-beautiful-way-of-styling-iboutlets-in-swift/?utm_campaign=This%2BWeek%2Bin%2BSwift&utm_medium=web&utm_source=This_Week_in_Swift_47","used":true,"who":"Huan"},{"_id":"56cc6d23421aa95caa707c62","createdAt":"2015-08-06T01:56:32.207Z","desc":"iOS开发之block终极篇","publishedAt":"2015-08-06T04:16:55.589Z","type":"iOS","url":"http://www.90159.com/2015/08/05/ios-block-ultimate/","used":true,"who":"Andrew Liu"},{"_id":"56cc6d23421aa95caa707c70","createdAt":"2015-08-05T12:17:08.481Z","desc":"Status Board 2 - Panic 公司推出的 iOS App，可以生成可视化数据图表，用以监测项目效率、Bug量，团队动态、销售情况等多种数据（稀土圈）","publishedAt":"2015-08-06T04:16:55.601Z","type":"iOS","url":"https://www.panic.com/statusboard/","used":true,"who":"LHF"}],"video":[{"_id":"56cc6d23421aa95caa707c2b","createdAt":"2015-08-06T03:55:07.175Z","desc":"重温字幕版倒鸭子~~~","publishedAt":"2015-08-06T04:16:55.578Z","type":"video","url":"http://video.weibo.com/show?fid=1034:0c79a69b1bafe17df62e750391d92118","used":true,"who":"代码家"}],"拓展资源":[{"_id":"56cc6d1d421aa95caa707781","createdAt":"2015-08-06T00:53:43.851Z","desc":"node express 源码接卸","publishedAt":"2015-08-06T04:16:55.601Z","type":"拓展资源","url":"https://gist.github.com/dlutwuwei/3faf88d535ac81c4e263","used":true,"who":"YJX"},{"_id":"56cc6d23421aa95caa707a66","createdAt":"2015-08-06T00:49:19.237Z","desc":"MongoDB从入门到精通系列","publishedAt":"2015-08-06T04:16:55.579Z","type":"拓展资源","url":"http://codefrom.com/t/mongodb%E4%BB%8E%E5%85%A5%E9%97%A8%E5%88%B0%E7%B2%BE%E9%80%9A%E7%B3%BB%E5%88%97%E4%B8%93%E9%A2%98","used":true,"who":"YJX"}],"福利":[{"_id":"56cc6d23421aa95caa707c6f","createdAt":"2015-08-06T01:33:55.463Z","desc":"8.6","publishedAt":"2015-08-06T04:16:55.601Z","type":"福利","url":"http://ww4.sinaimg.cn/large/7a8aed7bgw1eusn3niy2bj20hs0qo0wb.jpg","used":true,"who":"张涵宇"}]}
     */

    @SerializedName("error")
    private boolean error;
    @SerializedName("results")
    private ResultsBean results;
    @SerializedName("category")
    private List<String> category;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public ResultsBean getResults() {
        return results;
    }

    public void setResults(ResultsBean results) {
        this.results = results;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public static class ResultsBean {

        public static final String TYPE_ANDROID = "Android";
        public static final String TYPE_IOS = "iOS";
//        public static final String TYPE_VIDEO = "\\u4f11\\u606f\\u89c6\\u9891";
        public static final String TYPE_VIDEO = "休息视频";
//        public static final String TYPE_EXPAND_RESCOURCE = "\\u62d3\\u5c55\\u8d44\\u6e90";
        public static final String TYPE_EXPAND_RESCOURCE = "扩展资源";
//        public static final String TYPE_GIRLS = "\\u798f\\u5229";
        public static final String TYPE_GIRLS = "福利";

        /**
         * _id : 56cc6d23421aa95caa707bba
         * createdAt : 2015-08-06T02:05:32.826Z
         * desc : 一个查看设备规格的库，并且可以计算哪一年被定为“高端”机
         * publishedAt : 2015-08-06T04:16:55.575Z
         * type : mAndroid
         * url : https://github.com/facebook/device-year-class
         * used : true
         * who : 有时放纵
         */

        @SerializedName(TYPE_ANDROID)
        private List<GankBean> mAndroids;
        /**
         * _id : 56cc6d1d421aa95caa70777e
         * createdAt : 2015-08-06T01:55:36.30Z
         * desc : iOS 核心动画高级技巧
         * publishedAt : 2015-08-06T04:16:55.592Z
         * type : iOS
         * url : http://zsisme.gitbooks.io/ios-/content/
         * used : true
         * who : Andrew Liu
         */

        @SerializedName(TYPE_IOS)
        private List<GankBean> mIOSs;
        /**
         * _id : 56cc6d23421aa95caa707c2b
         * createdAt : 2015-08-06T03:55:07.175Z
         * desc : 重温字幕版倒鸭子~~~
         * publishedAt : 2015-08-06T04:16:55.578Z
         * type : video
         * url : http://video.weibo.com/show?fid=1034:0c79a69b1bafe17df62e750391d92118
         * used : true
         * who : 代码家
         */

        @SerializedName(TYPE_VIDEO)
        private List<GankBean> mVideos;
        /**
         * _id : 56cc6d1d421aa95caa707781
         * createdAt : 2015-08-06T00:53:43.851Z
         * desc : node express 源码接卸
         * publishedAt : 2015-08-06T04:16:55.601Z
         * type : 拓展资源
         * url : https://gist.github.com/dlutwuwei/3faf88d535ac81c4e263
         * used : true
         * who : YJX
         */

        @SerializedName(TYPE_EXPAND_RESCOURCE)
        private List<GankBean> mExpandResources;
        /**
         * _id : 56cc6d23421aa95caa707c6f
         * createdAt : 2015-08-06T01:33:55.463Z
         * desc : 8.6
         * publishedAt : 2015-08-06T04:16:55.601Z
         * type : 福利
         * url : http://ww4.sinaimg.cn/large/7a8aed7bgw1eusn3niy2bj20hs0qo0wb.jpg
         * used : true
         * who : 张涵宇
         */

        @SerializedName(TYPE_GIRLS)
        private List<GankBean> mGirls;

        public List<GankBean> getAndroid() {
            return mAndroids;
        }

        public void setAndroid(List<GankBean> Android) {
            this.mAndroids = Android;
        }

        public List<GankBean> getIOS() {
            return mIOSs;
        }

        public void setIOS(List<GankBean> iOS) {
            this.mIOSs = iOS;
        }

        public List<GankBean> getVideo() {
            return mVideos;
        }

        public void setVideo(List<GankBean> video) {
            this.mVideos = video;
        }

        public List<GankBean> getExpandResources() {
            return mExpandResources;
        }

        public void setExpandResources(List<GankBean> ExpandResources) {
            this.mExpandResources = ExpandResources;
        }

        public List<GankBean> getGirls() {
            return mGirls;
        }

        public void set福利(List<GankBean> girls) {
            this.mGirls = girls;
        }
    }
}
