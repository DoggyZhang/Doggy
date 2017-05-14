package com.news.ui.splash;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.net.Uri;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.news.R;
import com.news.base.BaseActivity;
import com.news.presenter.SplashPresenter;
import com.news.presenter.contract.SplashContract;
import com.news.ui.main.MainActivity;
import com.news.utils.common.ActivityUtil;

import butterknife.InjectView;

/**
 * Created by 阿飞 on 2017/4/3.
 */

public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashContract.View {

    @InjectView(R.id.iv_splash_logo)
    protected SimpleDraweeView iv_splash_logo;
    @InjectView(R.id.tv_splash_desc)
    protected TextView tv_splash_desc;

    @Override
    protected SplashPresenter setPresenter() {
        return new SplashPresenter(this);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_splash;
    }


    @Override
    protected void doBeforeContentView() {
        super.doBeforeContentView();
        setTheme(R.style.SplashTheme);
    }

    @Override
    protected void doAfterContentView() {
        super.doAfterContentView();
        iv_splash_logo.setImageURI(Uri.parse("res://com.news/" + R.mipmap.ic_logo));
    }

    @Override
    protected void initEvent() {
        ObjectAnimator rotate = ObjectAnimator.ofFloat(iv_splash_logo, "rotationY", 180, 360);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(rotate);
        animatorSet.setDuration(2000);
        animatorSet.start();
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                ActivityUtil.startActivity(SplashActivity.this, MainActivity.class);
                finish();
            }
        });
    }

    @Override
    public void useNightMode(boolean isNight) {

    }

    @Override
    public void show() {

    }

    @Override
    public void showError(Exception e) {
        Toast.makeText(SplashActivity.this, "Error", Toast.LENGTH_SHORT).show();
        e.printStackTrace();
    }
}
