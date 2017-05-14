package com.news.model.bean.about;

import java.util.ArrayList;
import java.util.List;

import static com.news.model.bean.about.AboutContract.CONTENT_ANNOTATION;
import static com.news.model.bean.about.AboutContract.CONTENT_ANNOTATION_URL;
import static com.news.model.bean.about.AboutContract.CONTENT_ASYNC;
import static com.news.model.bean.about.AboutContract.CONTENT_ASYNC_URL;
import static com.news.model.bean.about.AboutContract.CONTENT_DATA;
import static com.news.model.bean.about.AboutContract.CONTENT_DATA_URL;
import static com.news.model.bean.about.AboutContract.CONTENT_DB;
import static com.news.model.bean.about.AboutContract.CONTENT_DB_URL;
import static com.news.model.bean.about.AboutContract.CONTENT_DEBUG;
import static com.news.model.bean.about.AboutContract.CONTENT_DEBUG_URL;
import static com.news.model.bean.about.AboutContract.CONTENT_DEPENCE_INJECT;
import static com.news.model.bean.about.AboutContract.CONTENT_DEPENCE_INJECT_URL;
import static com.news.model.bean.about.AboutContract.CONTENT_IMAGE;
import static com.news.model.bean.about.AboutContract.CONTENT_IMAGE_URL;
import static com.news.model.bean.about.AboutContract.CONTENT_NET;
import static com.news.model.bean.about.AboutContract.CONTENT_NET_URL;
import static com.news.model.bean.about.AboutContract.CONTENT_SUPPORT;
import static com.news.model.bean.about.AboutContract.CONTENT_SUPPORT_URL;
import static com.news.model.bean.about.AboutContract.CONTENT_THIRD_PART;
import static com.news.model.bean.about.AboutContract.CONTENT_THIRD_PART_URL;
import static com.news.model.bean.about.AboutContract.CONTENT_UI;
import static com.news.model.bean.about.AboutContract.CONTENT_UI_URL;
import static com.news.model.bean.about.AboutContract.CONTENT_UTIL;
import static com.news.model.bean.about.AboutContract.CONTENT_UTIL_URL;
import static com.news.model.bean.about.AboutContract.TYPE_ANNOTATION;
import static com.news.model.bean.about.AboutContract.TYPE_ASYNC;
import static com.news.model.bean.about.AboutContract.TYPE_DATA;
import static com.news.model.bean.about.AboutContract.TYPE_DB;
import static com.news.model.bean.about.AboutContract.TYPE_DEBUG;
import static com.news.model.bean.about.AboutContract.TYPE_DEPENCE_INJECT;
import static com.news.model.bean.about.AboutContract.TYPE_IMAGE;
import static com.news.model.bean.about.AboutContract.TYPE_NET;
import static com.news.model.bean.about.AboutContract.TYPE_SUPPORT;
import static com.news.model.bean.about.AboutContract.TYPE_THIRD_PART;
import static com.news.model.bean.about.AboutContract.TYPE_UI;
import static com.news.model.bean.about.AboutContract.TYPE_UTIL;

/**
 * Created by 阿飞 on 2017/4/18.
 */

public class AboutInfos {
    List<AboutBean> mAbouts;

    public AboutInfos() {
        mAbouts = new ArrayList<>();

        addToList(TYPE_UI, CONTENT_UI, CONTENT_UI_URL);
        addToList(TYPE_NET, CONTENT_NET, CONTENT_NET_URL);
        addToList(TYPE_DATA, CONTENT_DATA, CONTENT_DATA_URL);
        addToList(TYPE_IMAGE, CONTENT_IMAGE, CONTENT_IMAGE_URL);
        addToList(TYPE_ASYNC, CONTENT_ASYNC, CONTENT_ASYNC_URL);
        addToList(TYPE_DEPENCE_INJECT, CONTENT_DEPENCE_INJECT, CONTENT_DEPENCE_INJECT_URL);
        addToList(TYPE_DB, CONTENT_DB, CONTENT_DB_URL);
        addToList(TYPE_DEBUG, CONTENT_DEBUG, CONTENT_DEBUG_URL);
        addToList(TYPE_UTIL, CONTENT_UTIL, CONTENT_UTIL_URL);
        addToList(TYPE_ANNOTATION, CONTENT_ANNOTATION, CONTENT_ANNOTATION_URL);
        addToList(TYPE_SUPPORT, CONTENT_SUPPORT, CONTENT_SUPPORT_URL);
        addToList(TYPE_THIRD_PART, CONTENT_THIRD_PART, CONTENT_THIRD_PART_URL);
    }

    public List<AboutBean> getAbout() {
        return mAbouts;
    }

    public void addToList(String type, String[] content, String[] url) {
        for (int i = 0; i < content.length; i++) {
            AboutBean aboutBean = new AboutBean();
            aboutBean.setType(type);
            aboutBean.setContent(content[i]);
            aboutBean.setUrl(url[i]);
            mAbouts.add(aboutBean);
        }
    }
}
