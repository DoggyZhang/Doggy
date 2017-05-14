package com.news.ui.my;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.news.R;
import com.news.app.App;
import com.news.app.Constants;
import com.news.base.BaseFragment;
import com.news.model.bean.user.LoginInfo;
import com.news.presenter.MyPresenter;
import com.news.presenter.contract.MyContract;
import com.news.ui.my.about.AboutFragment;
import com.news.ui.my.clearcache.ClearCacheFragment;
import com.news.ui.my.collect.CollectFragment;
import com.news.ui.my.login.LoginActivity;
import com.news.ui.my.login.LoginInfoFragment;
import com.news.ui.my.util.LoginUtils;
import com.news.utils.common.PrefUtils;
import com.news.utils.common.ToastUtils;

import butterknife.InjectView;

/**
 * Created by 阿飞 on 2017/4/11.
 */

public class MyMenuFragment extends BaseFragment<MyPresenter> implements MyContract.View, View.OnClickListener {

    public static final String TAG = "MyFragment";

    @InjectView(R.id.iv_my_userIcon)
    protected SimpleDraweeView iv_my_userIcon;
    @InjectView(R.id.tv_my_userName)
    protected TextView tv_my_userName;

    @InjectView(R.id.tv_my_collect)
    protected TextView tv_my_collect;
    @InjectView(R.id.tv_my_clearCache)
    protected TextView tv_my_clearCache;
    @InjectView(R.id.tv_my_about)
    protected TextView tv_my_about;
    @InjectView(R.id.tv_my_signOut)
    protected TextView tv_my_signOut;

    private boolean isLogin;

    @Override
    protected MyPresenter setPresenter() {
        return new MyPresenter(this);
    }

    @Override
    protected void getData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_my_menu;
    }

    @Override
    protected void handleArgument(Bundle argument) {
        iv_my_userIcon.setImageURI(Uri.parse("res://com.news/" + R.mipmap.ic_logo));

    }

    @Override
    protected void initEventAndData(View view, @Nullable Bundle savedInstanceState) {
        setInitState();
        iv_my_userIcon.setOnClickListener(this);
        tv_my_userName.setOnClickListener(this);

        tv_my_collect.setOnClickListener(this);
        tv_my_clearCache.setOnClickListener(this);
        tv_my_about.setOnClickListener(this);
        tv_my_signOut.setOnClickListener(this);
    }

    @Override
    public void showError(Exception e) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_my_userIcon:
            case R.id.tv_my_userName:
                // TODO: 判断当前是否登录
                if (isLogin) {
                    showLoginInfo();
                } else {
                    chooseLoginMode();
                }
                break;
            case R.id.tv_my_collect:
                toCollectPage();
                break;
            case R.id.tv_my_clearCache:
                clearCache();
                break;
            case R.id.tv_my_about:
                toAboutPage();
                break;
            case R.id.tv_my_signOut:
                signOut();
                break;
        }
    }

    public void setInitState() {
        int loginState = PrefUtils.getInt(App.getInstance(), Constants.SP_LOGIN_STATE, Constants.SP_LOGIN_STATE_NO);
        switch (loginState) {
            // 没有登录
            case Constants.SP_LOGIN_STATE_NO:
                ToastUtils.text("请登录");
                setLoginNoConfig();
                isLogin = false;
                break;
            // 已经登录
            case Constants.SP_LOGIN_STATE_YES:
                setLoginYesConfig();
                isLogin = true;
                break;
        }
    }

    private void setLoginNoConfig() {
        isLogin = false;
        tv_my_signOut.setVisibility(View.INVISIBLE);
        tv_my_userName.setText(App.getInstance().getResources().getString(R.string.my_sign_in));

        iv_my_userIcon.setImageURI(Uri.parse("res://" + App.getInstance().getPackageName() + "/" + R.mipmap.ic_no_login));
    }

    private void setLoginYesConfig() {
        isLogin = true;
        tv_my_signOut.setVisibility(View.VISIBLE);
        LoginUtils login = LoginUtils.getInstance(App.getInstance());
        if (login.isLogin()) {
            LoginInfo loginInfo = login.getLoginInfo();
            LoginInfo.User user = loginInfo.getUser();

            tv_my_userName.setText(user.getUserName());
            iv_my_userIcon.setImageURI(Uri.parse(user.getUserIcon()));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LoginActivity.REQUEST_CODE && resultCode == LoginActivity.RESULT_CODE) {
            LoginUtils login = LoginUtils.getInstance(App.getInstance());
            if (login.isLogin()) {
                int loginMode = login.getLoginMode();
                switch (loginMode) {
                    case Constants.SP_LOGIN_MODE_VISITOR:
                        ToastUtils.text("当前登录模式：游客");
                        setLoginYesConfig();
                        break;
                    case Constants.SP_LOGIN_MODE_WECHAT:
                        ToastUtils.text("当期登录模式：微信");
                        setLoginYesConfig();
                        break;
                    case Constants.SP_LOGIN_MODE_WEIBO:
                        ToastUtils.text("当期登录模式：微博");
                        setLoginYesConfig();
                }
            } else {
                ToastUtils.text("请登录");
                setLoginNoConfig();
            }

        }
    }

    public void showLoginInfo() {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.v_my_container, new LoginInfoFragment())
                .addToBackStack(TAG)
                .commit();
    }

    public void chooseLoginMode() {
        Intent toLogin = new Intent();
        toLogin.setClass(App.getInstance(), LoginActivity.class);
        startActivityForResult(toLogin, LoginActivity.REQUEST_CODE);
    }

    public void toCollectPage() {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.v_my_container, new CollectFragment())
                .addToBackStack(TAG)
                .commit();
    }

    public void clearCache() {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.v_my_container, new ClearCacheFragment())
                .addToBackStack(TAG)
                .commit();
    }

    public void toAboutPage() {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.v_my_container, new AboutFragment())
                .addToBackStack(TAG)
                .commit();
    }

    public void signOut() {
        LoginUtils.getInstance(App.getInstance()).signOut();
        ToastUtils.text("已退出登录");
        setInitState();
    }
}
