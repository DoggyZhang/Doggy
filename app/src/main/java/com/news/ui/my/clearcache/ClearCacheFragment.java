package com.news.ui.my.clearcache;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.news.R;
import com.news.app.App;
import com.news.base.BaseFragment;
import com.news.model.bean.cache.CacheBean;
import com.news.presenter.ClearCachePresenter;
import com.news.presenter.contract.ClearCacheContract;
import com.news.ui.other.dialog.LoadingDialog;
import com.news.utils.common.ToastUtils;

import butterknife.InjectView;

/**
 * Created by 阿飞 on 2017/4/16.
 */

public class ClearCacheFragment extends BaseFragment<ClearCachePresenter> implements ClearCacheContract.View, View.OnClickListener {
    public static final String TAG = "ClearCacheFragment";

    @InjectView(R.id.iv_back)
    protected SimpleDraweeView iv_back;

    @InjectView(R.id.tv_chache_size)
    protected TextView tv_chache_size;

    @InjectView(R.id.iv_cache_clear)
    protected SimpleDraweeView iv_cache_clear;

    private LoadingDialog loadingDialog;

    @Override
    protected ClearCachePresenter setPresenter() {
        return new ClearCachePresenter(this);
    }

    @Override
    protected void getData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_clear_cache;
    }

    @Override
    protected void handleArgument(Bundle argument) {

    }

    @Override
    protected void initEventAndData(View view, @Nullable Bundle savedInstanceState) {
        setBackConfig();
        setClearConfig();
        setLoadingDialogConfig();

        getCache();
    }

    @Override
    public void showError(Exception e) {
        stopLoading();
    }

    private void setLoadingDialogConfig() {
        loadingDialog = new LoadingDialog(getContext());
    }

    private void showLoading() {
        if (loadingDialog != null && !loadingDialog.isShowing()) {
            loadingDialog.show();
        }
    }

    private void stopLoading() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    private void setBackConfig() {
        iv_back.setImageURI(Uri.parse("res://" + App.getInstance().getPackageName() + "/" + R.mipmap.ic_goback));
        iv_back.setOnClickListener(this);
    }

    private void setClearConfig() {
        iv_cache_clear.setImageURI(Uri.parse("res://" + App.getInstance().getPackageName() + "/" + R.mipmap.ic_clear));
        iv_cache_clear.setOnClickListener(this);
    }

    @Override
    public void showCurrentCache(CacheBean cache) {
        float kb = cache.getHasUsed() * 1.0f / 1024;
        tv_chache_size.setText(String.format("%.2f", kb) + " KB");
    }

    @Override
    public void showClearResult(boolean success) {
        stopLoading();
        if (success) {
            ToastUtils.text("清理缓存成功");
            tv_chache_size.setText("0 KB");
        } else {
            ToastUtils.text("清理缓存失败");
        }
    }

    public void getCache() {
        if (getPresenter() != null) {
            getPresenter().getCurrentCache();
        }
    }

    public void clearCache() {
        showLoading();
        if (getPresenter() != null) {
            getPresenter().clearCache();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                getFragmentManager().popBackStack();
                break;
            case R.id.iv_cache_clear:
                clearCache();
                break;
        }
    }
}
