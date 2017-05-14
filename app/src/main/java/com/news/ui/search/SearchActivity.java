package com.news.ui.search;

import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.news.R;
import com.news.base.BaseActivity;
import com.news.model.bean.gank.SearchBean;
import com.news.presenter.SearchPresenter;
import com.news.presenter.contract.SearchContract;
import com.news.ui.search.adapter.SearchRecyclerAdapter;
import com.news.utils.common.LogUtil;
import com.widget.XRecyclerView;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by 阿飞 on 2017/4/15.
 */

public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchContract.View, View.OnClickListener {

    // 控件
    @InjectView(R.id.iv_search_back)
    protected ImageView iv_search_back;
    @InjectView(R.id.btn_search_type)
    protected Button btn_search_type;
    @InjectView(R.id.edit_search_word)
    protected EditText edit_search_word;
    @InjectView(R.id.iv_search_go)
    protected ImageView iv_search_go;

    @InjectView(R.id.v_search_loading_area)
    protected View v_search_loading_area;
    @InjectView(R.id.iv_search_loading)
    protected SimpleDraweeView iv_search_loading;

    @InjectView(R.id.v_search_result_area)
    protected View v_search_result_area;
    @InjectView(R.id.recyclerView_search_result)
    protected XRecyclerView recyclerView_search_result;

    // 变量
    public static final int SEARCH_TYPE_ALL = 0x0001;
    public static final int SEARCH_TYPE_ANDROID = 0x0002;
    public static final int SEARCH_TYPE_IOS = 0x0003;
    public static final int SEARCH_TYPE_WEB = 0x0004;
    private int mSearchType = SEARCH_TYPE_ALL;
    private PopupMenu mSearchTypeMenu;

    public static final int SEARCH_DEFAULT_COUNT = 30;
    private int mCount = SEARCH_DEFAULT_COUNT;

    public static final int SEARCH_DEFAULT_PAGE = 1;
    private int mPage = SEARCH_DEFAULT_PAGE;

    private SearchRecyclerAdapter mAdapter;

    @Override
    protected SearchPresenter setPresenter() {
        return new SearchPresenter(this);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_search;
    }

    @Override
    protected void doAfterContentView() {
        super.doAfterContentView();
        setSearchLoadingConfig();
        hiddenSearchLoading();
        setSearchTypeConfig();
        setSearchType(mSearchType);
        setSearchResultRecyclerConfig();
    }

    @Override
    protected void initEvent() {
        iv_search_back.setOnClickListener(this);
        btn_search_type.setOnClickListener(this);
        // TODO: edit text
//        edit_search_word.se

        iv_search_go.setOnClickListener(this);

        recyclerView_search_result.setOnBottomRefreshListener(new XRecyclerView.OnBottomRefreshListener() {
            @Override
            public void onBottomRefresh() {
                nextResultPage();
            }
        });
    }

    @Override
    public void useNightMode(boolean isNight) {

    }

    @Override
    public void showSearchResult(List<SearchBean> results) {
        hiddenSearchLoading();
        if (mAdapter != null) {
            mAdapter.add(results);
        }
    }

    @Override
    public void showError(Exception e) {
        hiddenSearchLoading();
        Toast.makeText(this, "加载数据失败", Toast.LENGTH_SHORT).show();
        LogUtil.e(this.getClass().getSimpleName(), e);
        e.printStackTrace();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_search_back:
                finish();
                break;

            case R.id.btn_search_type:
                showSearchTypeMenu();
                break;

            case R.id.iv_search_go:
                search();
                break;
        }
    }

    private void setSearchResultRecyclerConfig() {
        mAdapter = new SearchRecyclerAdapter(this);
        recyclerView_search_result.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView_search_result.setAdapter(mAdapter);
    }

    private void setSearchLoadingConfig() {
        DraweeController draweeController = Fresco
                .newDraweeControllerBuilder()
                .setUri(Uri.parse("res://" + getPackageName() + "/" + R.drawable.ic_cool_loading))
                .setAutoPlayAnimations(true)
                .build();
        iv_search_loading.setController(draweeController);
    }

    private void showSearchTypeMenu() {
        if (mSearchTypeMenu == null) {
            return;
        }
        mSearchTypeMenu.show();
    }

    private void setSearchTypeConfig() {
        if (mSearchTypeMenu == null) {
            mSearchTypeMenu = new PopupMenu(SearchActivity.this, btn_search_type);
            mSearchTypeMenu
                    .getMenuInflater()
                    .inflate(R.menu.menu_search, mSearchTypeMenu.getMenu());
            mSearchTypeMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.menu_search_type_all:
                            mSearchType = SEARCH_TYPE_ALL;
                            break;
                        case R.id.menu_search_type_android:
                            mSearchType = SEARCH_TYPE_ANDROID;
                            break;
                        case R.id.menu_search_type_ios:
                            mSearchType = SEARCH_TYPE_IOS;
                            break;
                        case R.id.menu_search_type_web:
                            mSearchType = SEARCH_TYPE_WEB;
                            break;
                        default:
                            mSearchType = SEARCH_TYPE_ALL;
                            break;
                    }
                    setSearchType(mSearchType);
                    return true;
                }
            });
        }
    }

    private void setSearchType(int type) {
        switch (type) {
            case SEARCH_TYPE_ALL:
                btn_search_type.setText("All");
                break;
            case SEARCH_TYPE_ANDROID:
                btn_search_type.setText("Android");
                break;
            case SEARCH_TYPE_IOS:
                btn_search_type.setText("IOS");
                break;
            case SEARCH_TYPE_WEB:
                btn_search_type.setText("Web");
                break;
            default:
                break;
        }
    }

    public void search() {
        if (getPresenter() == null) {
            Toast.makeText(this, "Preseter is null , Try again.", Toast.LENGTH_SHORT).show();
        }
        if (mAdapter != null) {
            mAdapter.clear();
        }
        showSearchLoading();
        getResultData();
    }

    public void showSearchLoading() {
        if (v_search_loading_area == null || v_search_result_area == null) {
            return;
        }
        v_search_loading_area.setVisibility(View.VISIBLE);
        v_search_result_area.setVisibility(View.GONE);
    }

    public void hiddenSearchLoading() {
        if (v_search_loading_area == null || v_search_result_area == null) {
            return;
        }
        v_search_loading_area.setVisibility(View.GONE);
        v_search_result_area.setVisibility(View.VISIBLE);
    }

    public void nextResultPage() {
        mPage++;
        getResultData();
    }

    public void getResultData() {
        if (getPresenter() != null) {
            getPresenter().getSearchResult(mSearchType, edit_search_word.getText().toString(), mCount, mPage);
        } else {
            showError(new Exception("Preseter is null."));
        }
    }

}
