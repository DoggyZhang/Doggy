package com.news.model.bean.about;

/**
 * Created by 阿飞 on 2017/4/18.
 */

public class AboutBean {

    private String mType;
    private String mContent;
    private String mUrl;

    public AboutBean() {

    }

    public String getType() {
        return mType;
    }

    public void setType(String mType) {
        this.mType = mType;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String mContent) {
        this.mContent = mContent;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }
}
