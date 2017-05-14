package com.news.ui.my.about;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.news.R;
import com.news.app.App;
import com.news.base.BaseFragment;
import com.news.model.bean.about.AboutBean;
import com.news.presenter.contract.AboutContract;
import com.news.presenter.AboutPresenter;
import com.news.ui.my.about.adapter.AboutRecyclerAdapter;
import com.news.utils.recycler.decoration.SpaceDecortaion;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by 阿飞 on 2017/4/16.
 */

public class AboutFragment extends BaseFragment<AboutPresenter> implements AboutContract.View, View.OnClickListener {
    public static final String TAG = "AboutFragment";

    @InjectView(R.id.iv_back)
    protected SimpleDraweeView iv_back;

    @InjectView(R.id.iv_about_app_logo)
    protected SimpleDraweeView iv_about_app_logo;

    @InjectView(R.id.recycler_about)
    protected RecyclerView recycler_about;

    private AboutRecyclerAdapter mAdapter;

    @Override
    protected AboutPresenter setPresenter() {
        return new AboutPresenter(this);
    }

    @Override
    protected void getData() {
        if (getPresenter() != null) {
            if (mAdapter != null) {
                mAdapter.clear();
            }
            getPresenter().getAbouts();
        }
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_about;
    }

    @Override
    protected void handleArgument(Bundle argument) {

    }

    @Override
    protected void initEventAndData(View view, @Nullable Bundle savedInstanceState) {
        setBackConfig();
        setAppLogoConfig();
        setAboutRecyclerConfig();

        getData();
    }

    @Override
    public void showError(Exception e) {

    }

    private void setBackConfig() {
        iv_back.setOnClickListener(this);
        iv_back.setImageURI(Uri.parse("res://" + App.getInstance().getPackageName() + "/" + R.mipmap.ic_goback));
    }

    private void setAppLogoConfig() {
        iv_about_app_logo.setImageURI(Uri.parse("res://" + App.getInstance().getPackageName() + "/" + R.mipmap.ic_logo));
    }

    private void setAboutRecyclerConfig() {
        recycler_about.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mAdapter = new AboutRecyclerAdapter(getContext());
        recycler_about.setAdapter(mAdapter);
        recycler_about.addItemDecoration(new SpaceDecortaion(5));
    }

    @Override
    public void showAbout(List<AboutBean> abouts) {
        if (mAdapter != null) {
            mAdapter.add(abouts);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                getFragmentManager().popBackStack();
                break;
        }

    }
}
