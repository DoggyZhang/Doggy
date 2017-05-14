package com.news.model.bean.gank;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by 阿飞 on 2017/4/11.
 */

public class GankBean implements Serializable {
    /**
     * "_id": "58ed9624421aa9544825f85b",
     * "createdAt": "2017-04-12T10:51:16.759Z",
     * "desc": "React-native \u5b9e\u73b0\u7684 Android BottomSheetBehavior \u6548\u679c",
     * "images": [
     * "http://img.gank.io/475bb89a-a9c1-4464-adea-8ce583f7a14a"
     * ],
     * "publishedAt": "2017-04-12T12:12:18.213Z",
     * "source": "chrome",
     * "type": "Android",
     * "url": "https://github.com/cesardeazevedo/react-native-bottom-sheet-behavior",
     * "used": true,
     * "who": "\u4ee3\u7801\u5bb6"
     */

    @SerializedName("_id")
    private String _id;
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("desc")
    private String desc;
    @SerializedName("images")
    private List<String> images;
    @SerializedName("publishedAt")
    private String publishedAt;
    @SerializedName("source")
    private String source;
    @SerializedName("type")
    private String type;
    @SerializedName("url")
    private String url;
    @SerializedName("used")
    private boolean used;
    @SerializedName("who")
    private String who;
    @SerializedName("height")
    private int height;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public CollectBean toCollectBean() {
        CollectBean collect = new CollectBean();
        Date date = new Date();
        collect.set_id(System.currentTimeMillis());
        collect.setYear(date.getYear() + 1900);
        collect.setMonth(date.getMonth() + 1);
        collect.setDay(date.getDate());
        collect.setCollect(this);
        return collect;
    }

}
