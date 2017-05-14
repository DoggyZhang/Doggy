package com.news.ui.search;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.news.R;
import com.news.base.BaseActivity;
import com.news.model.bean.gank.SearchBean;
import com.news.presenter.SearchDetailPresenter;
import com.news.presenter.contract.SearchDetailContract;
import com.news.utils.common.WebViewUtil;

import java.io.Serializable;

import butterknife.InjectView;

/**
 * Created by 阿飞 on 2017/4/15.
 */

public class SearchDetailActivity extends BaseActivity<SearchDetailPresenter> implements SearchDetailContract.View{

    public static final String INTENT_SEARCH_BEAN = "SEARCH_BEAN";

    @InjectView(R.id.toolbar_search_detail)
    protected Toolbar toolbar_search_detail;
    @InjectView(R.id.progressbar_search_detail)
    protected ProgressBar progressbar_search_detail;
    @InjectView(R.id.webview_search_detail)
    protected WebView webview_search_detail;

    private SearchBean mSearchInfo;
    private boolean isInited;

    @Override
    protected SearchDetailPresenter setPresenter() {
        return new SearchDetailPresenter(this);
    }

    @Override
    protected void doBeforeContentView() {
        super.doBeforeContentView();
        Intent intent = getIntent();
        if (intent != null) {
            Serializable serializableExtra = intent.getSerializableExtra(INTENT_SEARCH_BEAN);
            if (serializableExtra != null) {
                mSearchInfo = (SearchBean) serializableExtra;
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
        return R.layout.activity_search_deatil;
    }

    @Override
    protected void doAfterContentView() {
        super.doAfterContentView();
        if (!isInited) {
            Toast.makeText(this, "找不到该信息", Toast.LENGTH_SHORT).show();
            finish();
        }else{
            setToolBarConfig();
            setProgressBarConfig();
            setWebViewConfig();
        }
    }

    @Override
    protected void initEvent() {
    }

    @Override
    public void useNightMode(boolean isNight) {

    }

    @Override
    public void showError(Exception e) {

    }

    private void setToolBarConfig() {
        toolbar_search_detail.setTitle(mSearchInfo.getDesc());
        setSupportActionBar(toolbar_search_detail);
        toolbar_search_detail.setNavigationOnClickListener(new View.OnClickListener() {
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

    private void setProgressBarConfig(){
        progressbar_search_detail.setMax(100);
    }

    private void setWebViewConfig() {
        webview_search_detail.setOverScrollMode(View.OVER_SCROLL_NEVER);
        WebViewUtil.setWebViewOptions(webview_search_detail);
        webview_search_detail.setWebViewClient(new MyWebViewClient());
        webview_search_detail.setWebChromeClient(new MyWebChromeClient());
        String url = mSearchInfo.getUrl();
        if (url == null || url.isEmpty()) {
            Toast.makeText(this, "URL数据错误", Toast.LENGTH_SHORT).show();
        } else {
            webview_search_detail.loadUrl(url);
        }
    }

    private boolean canWebViewBackup() {
        return webview_search_detail != null && webview_search_detail.canGoBack();
    }

    public void goBack() {
        if (webview_search_detail != null) {
            webview_search_detail.goBack();
        }
    }

    @Override
    public void onBackPressed() {
        if (canWebViewBackup()) {
            goBack();
        } else {
            super.onBackPressed();
        }
    }

    protected void onWebViewLoadStart() {
        progressbar_search_detail.setVisibility(View.VISIBLE);
    }

    protected void onPageLoadFinished(WebView webview, String url) {
        progressbar_search_detail.setVisibility(View.INVISIBLE);
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
            progressbar_search_detail.setProgress(newProgress);
            if (newProgress == 0) {
                onWebViewLoadStart();
            } else if (newProgress > 90) {
                progressbar_search_detail.setVisibility(View.GONE);
            } else {
                progressbar_search_detail.setVisibility(View.VISIBLE);
            }
        }
    }
}
