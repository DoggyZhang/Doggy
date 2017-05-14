package com.news.db.util;

import android.content.Context;

import com.news.app.App;
import com.news.db.dao.TABLE_COLLECT;
import com.news.db.dao.TABLE_COLLECTDao;
import com.news.db.session.DaoSession;
import com.news.model.bean.gank.CollectBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 阿飞 on 2017/4/16.
 */

public class CollectDBUtils {

    private Context mContext;
    private static DaoSession mDaoSession;
    private static TABLE_COLLECTDao mCollectDao;

    private static CollectDBUtils INSTANCE;

    private CollectDBUtils(Context context) {
        this.mContext = context;
    }

    public static CollectDBUtils getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new CollectDBUtils(context);
            mDaoSession = App.getDaoSession();
            mCollectDao = mDaoSession.getTABLE_COLLECTDao();
        }
        return INSTANCE;
    }


    public CollectBean load(String user, String url) {
        List<TABLE_COLLECT> list = mCollectDao
                .queryBuilder()
                .where(TABLE_COLLECTDao.Properties.User.eq(user))
                .where(TABLE_COLLECTDao.Properties.Url.eq(url))
                .build()
                .list();
        if (list.isEmpty()) {
            return null;
        } else {
            return toCollectList(list).get(0);
        }
    }

    public List<CollectBean> loadAll(String user) {
        List<TABLE_COLLECT> list = mCollectDao
                .queryBuilder()
                .where(TABLE_COLLECTDao.Properties.User.eq(user))
                .build()
                .list();
        return toCollectList(list);
    }

    public List<CollectBean> loadAll(String user, String url) {
        List<TABLE_COLLECT> list = mCollectDao
                .queryBuilder()
                .where(TABLE_COLLECTDao.Properties.User.eq(user))
                .where(TABLE_COLLECTDao.Properties.Url.eq(url))
                .build()
                .list();
        return toCollectList(list);
    }

    public long save(CollectBean collect) {
        List<CollectBean> collectBeen = loadAll(collect.getUser(), collect.getCollect().getUrl());
        if (collectBeen != null && collectBeen.size() > 0) {
            for (CollectBean queryItem : collectBeen) {
                delete(queryItem.get_id());
            }
        }
        return mCollectDao.insertOrReplace(collect.toTableCollect());
    }

    public boolean delete(String user, String url) {
        List<CollectBean> collectBeen = loadAll(user, url);
        if (collectBeen != null && collectBeen.size() > 0) {
            for (CollectBean queryItem : collectBeen) {
                delete(queryItem.get_id());
            }
            return true;
        } else {
            return false;
        }
    }

    public void delete(long id) {
        mCollectDao.deleteByKey(id);
    }

    public void deleteAll() {
        mCollectDao.deleteAll();
    }

    public List<CollectBean> toCollectList(List<TABLE_COLLECT> tableCollects) {
        List<CollectBean> collects = new ArrayList<>();
        if (tableCollects == null) {
            return collects;
        }
        for (int i = 0; i < tableCollects.size(); i++) {
            collects.add(tableCollects.get(i).toCollectBean());
        }
        return collects;
    }
}
