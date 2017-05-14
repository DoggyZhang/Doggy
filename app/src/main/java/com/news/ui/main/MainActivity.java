package com.news.ui.main;

import android.Manifest;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.news.R;
import com.news.app.App;
import com.news.base.BaseActivity;
import com.news.base.BaseFragment;
import com.news.presenter.MainPresenter;
import com.news.presenter.contract.MainContract;
import com.news.ui.find.fragment.FindFragment;
import com.news.ui.home.HomeFragment;
import com.news.ui.my.MyFragment;
import com.news.ui.recommend.fragment.RecommendFragment;
import com.news.ui.search.SearchActivity;
import com.news.utils.common.ActivityUtil;
import com.tbruyelle.rxpermissions.Permission;
import com.tbruyelle.rxpermissions.RxPermissions;

import butterknife.InjectView;
import rx.Subscriber;

/**
 * Created by 阿飞 on 2017/4/10.
 */

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View, View.OnClickListener {

    // 标题栏目搜素
    @InjectView(R.id.iv_main_search)
    protected ImageView iv_main_search;

    // 四大模块
    @InjectView(R.id.v_tab_home)
    protected View v_tab_home;
    @InjectView(R.id.iv_tab_home)
    protected ImageView iv_tab_home;
    @InjectView(R.id.tv_tab_home)
    protected TextView tv_tab_home;

    @InjectView(R.id.v_tab_recommend)
    protected View v_tab_recommend;
    @InjectView(R.id.iv_tab_recommend)
    protected ImageView iv_tab_recommend;
    @InjectView(R.id.tv_tab_recommend)
    protected TextView tv_tab_recommend;

    @InjectView(R.id.v_tab_find)
    protected View v_tab_find;
    @InjectView(R.id.iv_tab_find)
    protected ImageView iv_tab_find;
    @InjectView(R.id.tv_tab_find)
    protected TextView tv_tab_find;

    @InjectView(R.id.v_tab_my)
    protected View v_tab_my;
    @InjectView(R.id.iv_tab_my)
    protected ImageView iv_tab_my;
    @InjectView(R.id.tv_tab_my)
    protected TextView tv_tab_my;


    public static final int TAB_HOME = 0x0001;
    public static final int TAB_RECOMMEND = 0x0002;
    public static final int TAB_FIND = 0x0003;
    public static final int TAB_MY = 0x0004;
    private int mCurrentTab = TAB_HOME;
    private Fragment mCurrentFragment = null;

    private HomeFragment homeFragment;
    private RecommendFragment recommendFragment;
    private FindFragment findFragment;
    private MyFragment myFragment;

    @Override
    protected MainPresenter setPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void doBeforeContentView() {
        super.doBeforeContentView();
        // 如果版本号大于等于23，则提示获取权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setPermissionConfig();
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEvent() {
        iv_main_search.setOnClickListener(this);

        v_tab_home.setOnClickListener(this);
        iv_tab_home.setOnClickListener(this);
        tv_tab_home.setOnClickListener(this);

        v_tab_recommend.setOnClickListener(this);
        iv_tab_recommend.setOnClickListener(this);
        tv_tab_recommend.setOnClickListener(this);

        v_tab_find.setOnClickListener(this);
        iv_tab_find.setOnClickListener(this);
        tv_tab_find.setOnClickListener(this);

        v_tab_my.setOnClickListener(this);
        iv_tab_my.setOnClickListener(this);
        tv_tab_my.setOnClickListener(this);

        switchTab(TAB_HOME);
    }

    @Override
    public void useNightMode(boolean isNight) {

    }

    @Override
    public void showError(Exception e) {

    }

    public void switchTab(int toTab) {
        Fragment to = getSupportFragmentManager().findFragmentByTag(toTab + "");
        if (to == null) {
            switch (toTab) {
                case TAB_HOME:
                    if (homeFragment == null) {
                        homeFragment = new HomeFragment();
                    }
                    to = homeFragment;
                    break;
                case TAB_RECOMMEND:
                    if (recommendFragment == null) {
                        recommendFragment = new RecommendFragment();
                    }
                    to = recommendFragment;
                    break;
                case TAB_FIND:
                    if (findFragment == null) {
                        findFragment = new FindFragment();
                    }
                    to = findFragment;
                    break;
                case TAB_MY:
                    if (myFragment == null) {
                        myFragment = new MyFragment();
                    }
                    to = myFragment;
                    break;
                default:
                    if (homeFragment == null) {
                        homeFragment = new HomeFragment();
                    }
                    to = homeFragment;
                    break;
            }
        }
        if (mCurrentFragment == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.v_container, to, toTab + "").commit();
        } else if (to == mCurrentFragment && mCurrentFragment instanceof BaseFragment) {
            ((BaseFragment) mCurrentFragment).refresh();
        } else if (to.isAdded()) {
            getSupportFragmentManager().beginTransaction().hide(mCurrentFragment).show(to).commit();
        } else {
            to.setUserVisibleHint(true);
            if (mCurrentFragment != null)
                getSupportFragmentManager().beginTransaction().hide(mCurrentFragment).add(R.id.v_container, to, toTab + "").commit();
            else
                getSupportFragmentManager().beginTransaction().add(R.id.v_container, to, toTab + "").commit();
        }
        mCurrentFragment = to;

        updateTab(toTab);
    }

    private void updateTab(int toTab) {
        switch (mCurrentTab) {
            case TAB_HOME:
                v_tab_home.setEnabled(true);
                iv_tab_home.setEnabled(true);
                tv_tab_home.setEnabled(true);
                tv_tab_home.setTextColor(getResources().getColor(R.color.text_unselected));
                break;
            case TAB_RECOMMEND:
                v_tab_recommend.setEnabled(true);
                iv_tab_recommend.setEnabled(true);
                tv_tab_recommend.setEnabled(true);
                tv_tab_recommend.setTextColor(getResources().getColor(R.color.text_unselected));
                break;
            case TAB_FIND:
                v_tab_find.setEnabled(true);
                iv_tab_find.setEnabled(true);
                tv_tab_find.setEnabled(true);
                tv_tab_find.setTextColor(getResources().getColor(R.color.text_unselected));
                break;
            case TAB_MY:
                v_tab_my.setEnabled(true);
                iv_tab_my.setEnabled(true);
                tv_tab_my.setEnabled(true);
                tv_tab_my.setTextColor(getResources().getColor(R.color.text_unselected));
                break;
        }

        switch (toTab) {
            case TAB_HOME:
                v_tab_home.setEnabled(false);
                iv_tab_home.setEnabled(false);
                tv_tab_home.setEnabled(false);
                tv_tab_home.setTextColor(getResources().getColor(R.color.text_selected));
                break;
            case TAB_RECOMMEND:
                v_tab_recommend.setEnabled(false);
                iv_tab_recommend.setEnabled(false);
                tv_tab_recommend.setEnabled(false);
                tv_tab_recommend.setTextColor(getResources().getColor(R.color.text_selected));
                break;
            case TAB_FIND:
                v_tab_find.setEnabled(false);
                iv_tab_find.setEnabled(false);
                tv_tab_find.setEnabled(false);
                tv_tab_find.setTextColor(getResources().getColor(R.color.text_selected));
                break;
            case TAB_MY:
                v_tab_my.setEnabled(false);
                iv_tab_my.setEnabled(false);
                tv_tab_my.setEnabled(false);
                tv_tab_my.setTextColor(getResources().getColor(R.color.text_selected));
                break;
        }
        mCurrentTab = toTab;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_main_search:
                ActivityUtil.startActivity(this, SearchActivity.class);
                break;

            case R.id.v_tab_home:
            case R.id.iv_tab_home:
            case R.id.tv_tab_home:
                switchTab(TAB_HOME);
                break;

            case R.id.v_tab_recommend:
            case R.id.iv_tab_recommend:
            case R.id.tv_tab_recommend:
                switchTab(TAB_RECOMMEND);
                break;

            case R.id.v_tab_find:
            case R.id.iv_tab_find:
            case R.id.tv_tab_find:
                switchTab(TAB_FIND);
                break;

            case R.id.v_tab_my:
            case R.id.iv_tab_my:
            case R.id.tv_tab_my:
                switchTab(TAB_MY);
                break;
        }
    }

    private void setPermissionConfig() {
        RxPermissions permissions = new RxPermissions(this);
        permissions
                .requestEach(
                        Manifest.permission.INTERNET,
                        Manifest.permission.ACCESS_WIFI_STATE,
                        Manifest.permission.ACCESS_NETWORK_STATE,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Subscriber<Permission>() {
                               @Override
                               public void onCompleted() {

                               }

                               @Override
                               public void onError(Throwable e) {
                                   Toast.makeText(App.getInstance(), "请求权限失败", Toast.LENGTH_SHORT).show();
                               }

                               @Override
                               public void onNext(Permission permission) {
                                   if (permission.granted) {
                                       // `permission.name` is granted !
                                       Toast.makeText(App.getInstance(), "授予权限 " + permission.name + " 成功", Toast.LENGTH_SHORT).show();
                                   } else if (permission.shouldShowRequestPermissionRationale) {
                                       // Denied permission without ask never again

                                   } else {
                                       // Denied permission with ask never again
                                       // Need to go to the settings
                                   }
                               }
                           }
                );
    }


    private long preDownTime = 0;
    private static final long EXIT_OFFSET_TIME = 2000;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        int count = getSupportFragmentManager().getBackStackEntryCount();
        boolean empty = count == 0;
        if (empty) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                long thisDownTime = System.currentTimeMillis();
                if (preDownTime == 0) {
                    preDownTime = thisDownTime;
                    Toast.makeText(App.getInstance(), "再按一下退出", Toast.LENGTH_SHORT).show();
                    return true;
                } else {
                    long offset = thisDownTime - preDownTime;
                    if (offset <= EXIT_OFFSET_TIME) {
                        finish();
                        return true;
                    } else {
                        preDownTime = thisDownTime;
                        Toast.makeText(App.getInstance(), "再按一下退出", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                }
            } else {
                preDownTime = 0;
                return false;
            }
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }
}
