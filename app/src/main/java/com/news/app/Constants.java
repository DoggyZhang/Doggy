package com.news.app;

import android.os.Environment;

import com.news.R;

import java.io.File;

/**
 * Created by codeest on 2016/8/3.
 */
public class Constants {


    //================= KEY ====================

    //    public static final String KEY_API = "f95283476506aa756559dd28a56f0c0b"; //需要APIKEY请去 http://apistore.baidu.com/ 申请,复用会减少访问可用次数
    public static final String KEY_API = "52b7ec3471ac3bec6846577e79f20e4c"; //需要APIKEY请去 http://www.tianapi.com/#wxnew 申请,复用会减少访问可用次数

    public static final String KEY_ALIPAY = "aex07566wvayrgxicnaraae";

    public static final String LEANCLOUD_ID = "mhke0kuv33myn4t4ghuid4oq2hjj12li374hvcif202y5bm6";

    public static final String LEANCLOUD_SIGN = "badc5461a25a46291054b902887a68eb,1480438132702";

    public static final String BUGLY_ID = "257700f3f8";

    //================= PATH ====================

    public static final String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

    public static final String PATH_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + App.getInstance().getResources().getString(R.string.app_name) + File.separator + "data";

    //================= PREFERENCE ====================

    // 游客登录 ， 微信登录
    public static final String SP_LOGIN = "login";

    public static final String SP_LOGIN_STATE = "login_state";
    public static final int SP_LOGIN_STATE_NO = 0x0000;
    public static final int SP_LOGIN_STATE_YES = 0x0001;

    public static final String SP_LOGIN_MODE = "login_mode";
    public static final int SP_LOGIN_MODE_VISITOR = 0x0001;
    public static final int SP_LOGIN_MODE_WECHAT = 0x0002;
    public static final int SP_LOGIN_MODE_WEIBO = 0x0003;

    public static final String SP_LOGIN_USER_NAME = "login_user_name";
    public static final String SP_LOGIN_USER_ICON = "login_user_icon";
    // 登录时间
    public static final String SP_LOGIN_USER_TIME = "login_user_time";

    public static final String SP_NIGHT_MODE = "night_mode";

    public static final String SP_NO_IMAGE = "no_image";

    public static final String SP_AUTO_CACHE = "auto_cache";

    public static final String SP_CURRENT_ITEM = "current_item";

    public static final String SP_LIKE_POINT = "like_point";

    public static final String SP_VERSION_POINT = "version_point";

    public static final String SP_MANAGER_POINT = "manager_point";




}
