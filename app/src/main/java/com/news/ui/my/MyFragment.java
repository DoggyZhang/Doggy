package com.news.ui.my;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.news.R;
import com.news.base.BaseFragment;
import com.news.presenter.MyMenuPresenter;
import com.news.presenter.contract.MyMenuContract;

import static android.support.v4.app.FragmentManager.POP_BACK_STACK_INCLUSIVE;

/**
 * Created by 阿飞 on 2017/4/16.
 */

public class MyFragment extends BaseFragment<MyMenuPresenter> implements MyMenuContract.View {

    private int mMyFragmentID;

    private MyMenuFragment myMenuFragment;

    @Override
    protected MyMenuPresenter setPresenter() {
        return new MyMenuPresenter(this);
    }

    @Override
    protected void getData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    protected void handleArgument(Bundle argument) {

    }

    @Override
    protected void initEventAndData(View view, @Nullable Bundle savedInstanceState) {
        toMenuFragment();
    }

    @Override
    public void showError(Exception e) {

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            toMenuFragment();
        }

    }

    public void toMenuFragment() {
        if (myMenuFragment != null) {
            if (!myMenuFragment.getFragmentManager().beginTransaction().isEmpty()) {
                myMenuFragment.getFragmentManager().popBackStack(MyMenuFragment.TAG, POP_BACK_STACK_INCLUSIVE);
            }

            if (!myMenuFragment.getChildFragmentManager().beginTransaction().isEmpty()) {
                myMenuFragment.getChildFragmentManager().popBackStack(MyMenuFragment.TAG, POP_BACK_STACK_INCLUSIVE);
            }
        } else {
            myMenuFragment = new MyMenuFragment();
        }
        if (!myMenuFragment.isAdded()) {
            mMyFragmentID = getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.v_my_container, myMenuFragment, MyMenuFragment.TAG)
                    .commit();
        }
    }

}
