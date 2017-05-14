package com.news.ui.my.util;

import android.content.Context;

import com.news.R;
import com.news.app.Constants;
import com.news.model.bean.user.LoginInfo;
import com.news.utils.common.PrefUtils;

/**
 * Created by 阿飞 on 2017/4/16.
 */

public class LoginUtils {
    private Context mContext;

    private LoginUtils(Context context) {
        this.mContext = context;
    }

    private static LoginUtils INSTANCE = null;

    public static LoginUtils getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new LoginUtils(context);
        }
        return INSTANCE;
    }

    public boolean isLogin() {
        int loginState = PrefUtils.getInt(mContext, Constants.SP_LOGIN_STATE, Constants.SP_LOGIN_STATE_NO);
        return loginState == Constants.SP_LOGIN_STATE_YES;
    }

    public void setLoginInfo(int loginMode, String userName, String userIcon, long time) {
        // 设置当前已经登录
        PrefUtils.putInt(mContext, Constants.SP_LOGIN_STATE, Constants.SP_LOGIN_STATE_YES);
        switch (loginMode) {
            case Constants.SP_LOGIN_MODE_VISITOR:
                PrefUtils.putInt(mContext, Constants.SP_LOGIN_MODE, Constants.SP_LOGIN_MODE_VISITOR);
                break;
            case Constants.SP_LOGIN_MODE_WECHAT:
                PrefUtils.putInt(mContext, Constants.SP_LOGIN_MODE, Constants.SP_LOGIN_MODE_WECHAT);
                break;
        }
        PrefUtils.putString(mContext, Constants.SP_LOGIN_USER_NAME, userName);
        PrefUtils.putString(mContext, Constants.SP_LOGIN_USER_ICON, userIcon);
        PrefUtils.putLong(mContext, Constants.SP_LOGIN_USER_TIME, time);
    }

    public void signOut() {
        PrefUtils.putInt(mContext, Constants.SP_LOGIN_STATE, Constants.SP_LOGIN_STATE_NO);
    }

    public int getLoginMode() {
        if (isLogin()) {
            return PrefUtils.getInt(mContext, Constants.SP_LOGIN_MODE, Constants.SP_LOGIN_MODE_VISITOR);
        } else {
            return Constants.SP_LOGIN_STATE_NO;
        }
    }

    public String getLoginUserName() {
        if (isLogin()) {
            return PrefUtils.getString(mContext, Constants.SP_LOGIN_USER_NAME, mContext.getResources().getString(R.string.login_visitor));
        } else {
            return null;
        }
    }

    public LoginInfo getLoginInfo() {
        LoginInfo loginInfo = new LoginInfo();
        long loginTime = PrefUtils.getLong(mContext, Constants.SP_LOGIN_USER_TIME, System.currentTimeMillis());
        loginInfo.setLoginTime(loginTime);

        String userName = PrefUtils.getString(mContext, Constants.SP_LOGIN_USER_NAME, "");
        loginInfo.getUser().setUserName(userName);

        String userIcon = PrefUtils.getString(mContext, Constants.SP_LOGIN_USER_ICON, "");
        loginInfo.getUser().setUserIcon(userIcon);

        return loginInfo;
    }

}
