package com.news.ui.home.adapter;

import android.support.v4.app.FragmentManager;

import com.news.ui.home.gank.fragment.GankFragment;
import com.news.utils.adapter.BaseFragmentPageAdapter;

import java.util.List;

/**
 * Created by codeest on 16/8/19.
 */

public class GankPagerAdapter extends BaseFragmentPageAdapter<GankFragment> {

    public GankPagerAdapter(FragmentManager fm, List<GankFragment> fragments) {
        super(fm, fragments);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return GankFragment.techs[position];
    }
}
