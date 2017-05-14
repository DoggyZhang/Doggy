package com.news.model.wiebo.key;

/**
 * Created by 阿飞 on 2017/4/18.
 */

public class KeyHelper {

    private static KeyHelper INSTANCE;

    public static KeyHelper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new KeyHelper();
        }
        return INSTANCE;
    }

    public String getAppKey() {
        return KeyContants.APP_KEY;
    }

}
