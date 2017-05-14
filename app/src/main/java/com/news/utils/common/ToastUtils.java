package com.news.utils.common;

import android.text.TextUtils;
import android.widget.Toast;

import com.news.app.App;

/**
 * Created by 阿飞 on 2017/5/13.
 */

public class ToastUtils {

    public static void text(String text) {
        text(text, Toast.LENGTH_SHORT);
    }

    public static void text(String text, int duration) {
        if (TextUtils.isEmpty(text)) {
            return;
        }
        Toast.makeText(App.getInstance(), text, duration).show();
    }

}
