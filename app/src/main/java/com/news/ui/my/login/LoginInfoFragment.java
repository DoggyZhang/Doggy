package com.news.ui.my.login;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.news.R;
import com.news.app.App;
import com.news.base.BaseFragment;
import com.news.model.bean.user.LoginInfo;
import com.news.presenter.LoginInfoPresenter;
import com.news.presenter.contract.LoginInfoContract;

import java.util.Date;

import butterknife.InjectView;

/**
 * Created by 阿飞 on 2017/4/16.
 */

public class LoginInfoFragment extends BaseFragment<LoginInfoPresenter> implements LoginInfoContract.View, View.OnClickListener {

    public static final String TAG = "LoginInfoFragment";

    @InjectView(R.id.iv_login_info_userIcon)
    protected SimpleDraweeView iv_login_info_userIcon;

    @InjectView(R.id.tv_login_info_userName)
    protected TextView tv_login_info_userName;

    @InjectView(R.id.tv_login_info_logintime)
    protected TextView tv_login_info_logintime;

    @InjectView(R.id.iv_login_info_back)
    protected SimpleDraweeView iv_login_info_back;

    @Override
    protected LoginInfoPresenter setPresenter() {
        return new LoginInfoPresenter(this);
    }

    @Override
    protected void getData() {
        if (getPresenter() != null) {
            getPresenter().getLoginInfo();
        }
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_login_info;
    }

    @Override
    protected void handleArgument(Bundle argument) {

    }

    @Override
    protected void initEventAndData(View view, @Nullable Bundle savedInstanceState) {
        setBackConfig();

        getData();
    }

    @Override
    public void showError(Exception e) {

    }

    @Override
    public void showLoginInfo(LoginInfo loginInfo) {
        if (loginInfo != null) {
            iv_login_info_userIcon.setImageURI(Uri.parse(loginInfo.getUser().getUserIcon()));
            tv_login_info_userName.setText(loginInfo.getUser().getUserName());
            long loginTime = loginInfo.getLoginTime();
            Date time = new Date(loginTime);
            int year = time.getYear() + 1900;
            int month = time.getMonth() + 1;
            int day = time.getDate();
            int hour = time.getHours();
            int minute = time.getMinutes();
            int second = time.getSeconds();
            StringBuilder sb = new StringBuilder();
            sb.append(year);
            sb.append("年");
            sb.append(month);
            sb.append("月");
            sb.append(day);
            sb.append("日");
            sb.append("\n");
            sb.append(String.format("%2d", hour));
            sb.append(":");
            sb.append(String.format("%2d", minute));
            sb.append(":");
            sb.append(String.format("%2d", second));
            tv_login_info_logintime.setText(sb.toString());
        }
    }

    private void setBackConfig() {
        iv_login_info_back.setImageURI(Uri.parse("res://" + App.getInstance().getPackageName() + "/" + R.mipmap.ic_goback));
        iv_login_info_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_login_info_back:
                getFragmentManager().popBackStack();
                break;
        }
    }
}
