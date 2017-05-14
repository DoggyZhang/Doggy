package com.news.model.bean.gank;

import com.news.db.dao.TABLE_COLLECT;

import java.util.Date;
import java.util.List;

/**
 * Created by 阿飞 on 2017/4/16.
 */

public class CollectBean implements Comparable<CollectBean> {
    private String user;
    private long _id;
    private int year;
    private int month;
    private int day;
    private GankBean collect;

    public CollectBean() {
        collect = new GankBean();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public GankBean getCollect() {
        return collect;
    }

    public void setCollect(GankBean collect) {
        this.collect = collect;
    }

    @Override
    public int compareTo(CollectBean o) {
        if (o == null) {
            return 1;
        }
        int day1 = year * 3 + month * 2 + day;
        int day2 = o.year * 3 + o.month * 2 + o.day;

        if (day1 < day2) {
            return -1;
        } else if (day1 > day2) {
            return 1;
        } else {
            return 0;
        }
    }

    public TABLE_COLLECT toTableCollect() {
        Date now = new Date();
        return new TABLE_COLLECT(
                System.currentTimeMillis(),
                getUser(),
                now.getYear() + 1900,
                now.getMonth() + 1,
                now.getDate(),
                getCollect().getDesc(),
                getCollect().getSource(),
                toString(getCollect().getImages()),
                getCollect().getType(),
                getCollect().getUrl(),
                getCollect().getWho()
        );
    }

    public String toString(List<String> images) {
        if (images == null || images.isEmpty()) {
            return new String();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < images.size(); i++) {
            if (i == images.size() - 1) {
                sb.append(images.get(i));
            } else {
                sb.append(images.get(i));
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
