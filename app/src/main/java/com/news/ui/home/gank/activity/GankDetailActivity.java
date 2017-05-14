package com.news.ui.home.gank.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.news.R;
import com.news.app.App;
import com.news.base.BaseActivity;
import com.news.model.bean.gank.GankBean;
import com.news.presenter.GankTechDetailPresenter;
import com.news.presenter.contract.GankTechDetailContract;
import com.news.ui.other.picture.PictureActivity;
import com.news.utils.common.ToastUtils;
import com.news.utils.common.WebViewUtil;

import java.io.Serializable;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by 阿飞 on 2017/4/13.
 */

public class GankDetailActivity extends BaseActivity<GankTechDetailPresenter> implements GankTechDetailContract.View {

    @InjectView(R.id.appbar_gank_detail)
    protected AppBarLayout appbar_gank_detail;
    @InjectView(R.id.collapsing_toolbar_gank_detail)
    protected CollapsingToolbarLayout collapsing_toolbar_gank_detail;
    @InjectView(R.id.v_gank_detail_image)
    protected SimpleDraweeView v_gank_detail_image;
    @InjectView(R.id.toolbar_gank_detail)
    protected Toolbar toolbar_gank_detail;
    @InjectView(R.id.webview_gank_detail)
    protected WebView webview_gank_detail;

    @InjectView(R.id.progressbar_gank_detail)
    protected ProgressBar progressbar_gank_detail;

    @InjectView(R.id.fab_gank_detail_like)
    protected FloatingActionButton fab_gank_detail_like;
    private boolean isCollect;

    public static final String SERIALIZABLE_GANK_INFO = "GANK_DETAIL";
    private GankBean mGankInfo;
    private boolean isInited = false;

    @Override
    protected GankTechDetailPresenter setPresenter() {
        return new GankTechDetailPresenter(this);
    }

    @Override
    protected void doBeforeContentView() {
        super.doBeforeContentView();
        Intent intent = getIntent();
        if (intent != null) {
            Serializable serializableExtra = intent.getSerializableExtra(SERIALIZABLE_GANK_INFO);
            if (serializableExtra != null) {
                mGankInfo = (GankBean) serializableExtra;
                isInited = true;
            } else {
                isInited = false;
            }
        } else {
            isInited = false;
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_gank_detail;
    }

    @Override
    protected void doAfterContentView() {
        super.doAfterContentView();
        if (!isInited) {
            Toast.makeText(this, "没有数据哦", Toast.LENGTH_SHORT).show();
            finish();
        }
        List<String> images = mGankInfo.getImages();
        if (images != null && images.size() > 0) {
            v_gank_detail_image.setImageURI(Uri.parse(images.get(0)));
            v_gank_detail_image.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
        }
    }

    @Override
    protected void initEvent() {
        setToolBarConfig();
        setTitleImageConfig();
        setWebViewConfig();
        setCollectButtonConfig();
    }

    private boolean canWebViewBackup() {
        return webview_gank_detail != null && webview_gank_detail.canGoBack();
    }

    public void goBack() {
        if (webview_gank_detail != null) {
            webview_gank_detail.goBack();
        }
    }

    @Override
    public void useNightMode(boolean isNight) {

    }

    @Override
    public void showError(Exception e) {
        updateCollectState(false);
    }

    @Override
    public void showQueryResult(boolean success) {
        updateCollectState(success);
    }

    @Override
    public void showSaveCollectSuccess(long saveID) {
        updateCollectState(true);
        Toast.makeText(App.getInstance(), "收藏成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSaveCollectFail(Exception e) {
        updateCollectState(false);
        Toast.makeText(App.getInstance(), e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDeleteResult(boolean success) {
        updateCollectState(false);
        Toast.makeText(App.getInstance(), "取消收藏", Toast.LENGTH_SHORT).show();
    }

    private void setToolBarConfig() {
        toolbar_gank_detail.setTitle(mGankInfo.getDesc());
        setSupportActionBar(toolbar_gank_detail);
        toolbar_gank_detail.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setTitleImageConfig() {
        v_gank_detail_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toPicture = new Intent();
                toPicture.setClass(GankDetailActivity.this, PictureActivity.class);
                List<String> images = mGankInfo.getImages();
                if (images != null && images.size() > 0) {
                    toPicture.putExtra(PictureActivity.PICTURE_URL, images.get(0));
                }
                startActivity(toPicture);
            }
        });
    }

    private void setCollectButtonConfig() {
        if (getPresenter() != null) {
            getPresenter().isCollect(mGankInfo.getUrl());
        }
        fab_gank_detail_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCollect) {
                    delete();
                } else {
                    saveCollect();
                }
            }
        });
    }

    private void updateCollectState(boolean isCollect) {
        this.isCollect = isCollect;
        if (isCollect) {
            fab_gank_detail_like.setImageDrawable(App.getInstance().getResources().getDrawable(R.mipmap.ic_like_selected));
        } else {
            fab_gank_detail_like.setImageDrawable(App.getInstance().getResources().getDrawable(R.mipmap.ic_like_unselected));
        }
    }

    private void saveCollect() {
        if (getPresenter() != null) {
            getPresenter().savaCollect(mGankInfo);
        } else {
            ToastUtils.text("请重新打开页面并重试");
        }
    }

    private void delete() {
        if (getPresenter() != null) {
            getPresenter().deleteCollect(mGankInfo.getUrl());
        } else {
            ToastUtils.text("请重新打开页面并重试");
        }
    }

    private void setWebViewConfig() {
        progressbar_gank_detail.setMax(100);

        webview_gank_detail.setOverScrollMode(View.OVER_SCROLL_NEVER);
        WebViewUtil.setWebViewOptions(webview_gank_detail);
        webview_gank_detail.setWebViewClient(new MyWebViewClient());
        webview_gank_detail.setWebChromeClient(new MyWebChromeClient());
        String url = mGankInfo.getUrl();
        if (url == null || url.isEmpty()) {
            ToastUtils.text("URL数据错误");
        } else {
            webview_gank_detail.loadUrl(url);
        }
    }

    protected void onWebViewLoadStart() {
        progressbar_gank_detail.setVisibility(View.VISIBLE);
    }

    protected void onPageLoadFinished(WebView webview, String url) {
        progressbar_gank_detail.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onBackPressed() {
        if (canWebViewBackup()) {
            goBack();
        } else {
            super.onBackPressed();
        }
    }

    //WebViewClient就是帮助WebView处理各种通知、请求事件的。
    class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            onPageLoadFinished(view, url);
        }
    }

    //WebChromeClient是辅助WebView处理Javascript的对话框，网站图标，网站title，加载进度等
    class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            progressbar_gank_detail.setProgress(newProgress);
            if (newProgress == 0) {
                onWebViewLoadStart();
            } else if (newProgress > 90) {
                progressbar_gank_detail.setVisibility(View.GONE);
            } else {
                progressbar_gank_detail.setVisibility(View.VISIBLE);
            }
        }
    }
}
