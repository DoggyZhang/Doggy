package com.news.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.news.R;
import com.news.app.App;
import com.news.base.BaseFragment;
import com.news.presenter.HomePresenter;
import com.news.presenter.contract.HomeContract;
import com.news.ui.home.adapter.GankPagerAdapter;
import com.news.ui.home.gank.fragment.GankFragment;
import com.news.utils.common.LogUtil;
import com.news.utils.common.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by 阿飞 on 2017/4/11.
 */

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View {

    @InjectView(R.id.tab_home)
    protected TabLayout tab_home;
    @InjectView(R.id.vp_home)
    protected ViewPager vp_home;
    private GankPagerAdapter vpAdapter;

    private List<GankFragment> mGankFragments;
    private GankFragment mAndroidFragment;
    private GankFragment mIOSFragment;
    private GankFragment mWebFragment;

    @Override
    protected HomePresenter setPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void getData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void handleArgument(Bundle argument) {

    }

    @Override
    protected void initEventAndData(View view, @Nullable Bundle savedInstanceState) {
        setFragmentsConfig();
        setTabLayoutAndViewPagerConfig();

        vp_home.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_IDLE:
                    case ViewPager.SCROLL_STATE_SETTLING:
                        for (GankFragment fragment : mGankFragments) {
                            fragment.showItemMask();
                        }
                        break;
                }
            }
        });
    }

    @Override
    public void showError(Exception e) {
        LogUtil.e(this.getClass().getSimpleName(), e.getMessage());
        ToastUtils.text(App.getInstance().getResources().getString(R.string.loadError));
    }

    private void setFragmentsConfig() {
        mGankFragments = new ArrayList<>();

        Bundle argument = null;

        // Android
        mAndroidFragment = new GankFragment();
        argument = new Bundle();
        argument.putInt(GankFragment.TYPE_GANK, GankFragment.TYPE_ANDROID);
        mAndroidFragment.setArguments(argument);
        mGankFragments.add(mAndroidFragment);

        // IOS
        mIOSFragment = new GankFragment();
        argument = new Bundle();
        argument.putInt(GankFragment.TYPE_GANK, GankFragment.TYPE_IOS);
        mIOSFragment.setArguments(argument);
        mGankFragments.add(mIOSFragment);

        // Web
        mWebFragment = new GankFragment();
        argument = new Bundle();
        argument.putInt(GankFragment.TYPE_GANK, GankFragment.TYPE_WEB);
        mWebFragment.setArguments(argument);
        mGankFragments.add(mWebFragment);

    }

    private void setTabLayoutAndViewPagerConfig() {
        vpAdapter = new GankPagerAdapter(getFragmentManager(), mGankFragments);
        vp_home.setAdapter(vpAdapter);
        tab_home.setupWithViewPager(vp_home);
    }
}
