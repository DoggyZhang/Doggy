package com.news.ui.my.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.news.R;
import com.news.app.App;
import com.news.base.BaseActivity;
import com.news.model.wiebo.AccessTokenKeeper;
import com.news.model.wiebo.bean.WeiboUser;
import com.news.model.wiebo.key.KeyContants;
import com.news.presenter.LoginPresenter;
import com.news.presenter.contract.LoginContract;
import com.news.utils.common.ToastUtils;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;

import butterknife.InjectView;

/**
 * Created by 阿飞 on 2017/4/16.
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View, View.OnClickListener {

    public static final String TAG = "LoginActivity";

    public static final int REQUEST_CODE = 0x0010;
    public static final int RESULT_CODE = 0x0020;

    public static final String LOGIN_MODE = "login_mode";

    @InjectView(R.id.v_login_visitor)
    protected View v_login_visitor;
    @InjectView(R.id.iv_login_visitor)
    protected ImageView iv_login_visitor;
    @InjectView(R.id.tv_login_visitor)
    protected TextView tv_login_visitor;

    @InjectView(R.id.v_login_wechat)
    protected View v_login_wechat;
    @InjectView(R.id.iv_login_wechat)
    protected ImageView iv_login_wechat;
    @InjectView(R.id.tv_login_wechat)
    protected TextView tv_login_wechat;

    @InjectView(R.id.v_login_weibo)
    protected View v_login_weibo;
    @InjectView(R.id.iv_login_weibo)
    protected ImageView iv_login_weibo;
    @InjectView(R.id.tv_login_weibo)
    protected TextView tv_login_weibo;

    @Override
    protected LoginPresenter setPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initEvent() {
        v_login_visitor.setOnClickListener(this);
        iv_login_visitor.setOnClickListener(this);
        tv_login_visitor.setOnClickListener(this);

        v_login_wechat.setOnClickListener(this);
        iv_login_wechat.setOnClickListener(this);
        tv_login_wechat.setOnClickListener(this);

        v_login_weibo.setOnClickListener(this);
        iv_login_weibo.setOnClickListener(this);
        tv_login_weibo.setOnClickListener(this);
    }

    @Override
    public void showError(Exception e) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.v_login_visitor:
            case R.id.iv_login_visitor:
            case R.id.tv_login_visitor:
                loginByVisitor();
                break;

            case R.id.v_login_wechat:
            case R.id.iv_login_wechat:
            case R.id.tv_login_wechat:
                loginByWeChat();
                break;

            case R.id.v_login_weibo:
            case R.id.iv_login_weibo:
            case R.id.tv_login_weibo:
                loginByWeiBo();
                break;
        }
    }


    @Override
    public void showLoginResult(int loginType, boolean success) {
        switch (loginType) {
            case LoginContract.LOGIN_TYPE_VISITOR:
                if (success) {
                    Toast.makeText(App.getInstance(), "游客 登录成功", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_CODE);
                    finish();
                } else {
                    Toast.makeText(App.getInstance(), "游客 登录失败", Toast.LENGTH_SHORT).show();
                }
                break;
            case LoginContract.LOGIN_TYPE_WEICHAT:
                if (success) {
                    Toast.makeText(App.getInstance(), "微信 登录成功", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_CODE);
                    finish();
                } else {
                    Toast.makeText(App.getInstance(), "微信 登录失败", Toast.LENGTH_SHORT).show();
                }
                break;
            case LoginContract.LOGIN_TYPE_WEIBO:
                if (success) {
                    Toast.makeText(App.getInstance(), "新浪 登录成功", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_CODE);
                    finish();
                } else {
                    Toast.makeText(App.getInstance(), "新浪 登录失败", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void showWeiboUserInfo(WeiboUser user) {

    }

    public void loginByVisitor() {
        if (getPresenter() != null) {
            getPresenter().loginByVisitor();
        }

    }

    public void loginByWeChat() {
        Toast.makeText(App.getInstance(), "暂不支持", Toast.LENGTH_SHORT).show();
    }


    private AuthInfo mAuthInfo;
    private SsoHandler mSsoHandler;

    public void loginByWeiBo() {
        try {
            if (mAuthInfo == null) {
                mAuthInfo = new AuthInfo(this, KeyContants.APP_KEY, KeyContants.REDIRECT_URL, null);
            }
            if (mSsoHandler == null) {
                mSsoHandler = new SsoHandler(LoginActivity.this, mAuthInfo);
            }
            mSsoHandler.authorize(new AuthListener());
        } catch (Exception e) {
            showError(e);
        }

    }

    @Override
    public void useNightMode(boolean isNight) {

    }

    class AuthListener implements WeiboAuthListener {

        @Override
        public void onComplete(Bundle values) {
            // 从 Bundle 中解析 Token
            Oauth2AccessToken mAccessToken = Oauth2AccessToken.parseAccessToken(values);
            if (mAccessToken.isSessionValid()) {
                // 保存 Token 到 SharedPreferences
                AccessTokenKeeper.writeAccessToken(App.getInstance(), mAccessToken);
                if (getPresenter() != null) {
                    getPresenter().getWeiboUserInfo(mAccessToken);
                }
            } else {
                // 当您注册的应用程序签名不正确时，就会收到 Code，请确保签名正确
                String code = values.getString("code", "");
                ToastUtils.text("错误码 ： " + code);
            }
        }

        @Override
        public void onCancel() {
        }

        @Override
        public void onWeiboException(WeiboException e) {
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }
}
