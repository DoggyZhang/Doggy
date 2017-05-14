package com.news.ui.recommend.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.news.R;
import com.news.app.App;
import com.news.base.BaseFragment;
import com.news.model.bean.gank.GankBean;
import com.news.presenter.RecommendPresenter;
import com.news.presenter.contract.RecommendContract;
import com.news.ui.other.calendar.CalendarActivity;
import com.news.ui.recommend.adapter.RecommendRecyclerAdapter;
import com.news.utils.recycler.decoration.SpaceDecortaion;

import java.util.Date;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by 阿飞 on 2017/4/11.
 */

public class RecommendFragment extends BaseFragment<RecommendPresenter> implements RecommendContract.View, View.OnClickListener {

    @InjectView(R.id.appbar_recommend_calendar)
    protected View appbar_recommend_calendar;
    @InjectView(R.id.tv_calendar_year)
    protected TextView tv_calendar_year;
    @InjectView(R.id.tv_calendar_month)
    protected TextView tv_calendar_month;
    @InjectView(R.id.tv_calendar_day)
    protected TextView tv_calendar_day;

    @InjectView(R.id.recycler_recommend)
    protected RecyclerView recycler_recommend;
    private RecommendRecyclerAdapter mAdapter;

    @InjectView(R.id.v_recommend_loading_area)
    protected View v_recommend_loading_area;
    @InjectView(R.id.iv_recommend_loading)
    protected SimpleDraweeView iv_recommend_loading;

    private static final Date DEFAULT_DATE = new Date(2016 - 1900, 5 - 1, 11);
    private Date mSelectedDate = null;

    @Override
    protected RecommendPresenter setPresenter() {
        return new RecommendPresenter(this);
    }

    @Override
    protected void getData() {
        if (getPresenter() != null) {
            if (mAdapter != null) {
                mAdapter.clear();
            }
            if (mSelectedDate == null) {
                Toast.makeText(App.getInstance(), "请设置时间", Toast.LENGTH_SHORT).show();
                return;
            }
            getPresenter().getRecommend(mSelectedDate.getYear() + 1900, mSelectedDate.getMonth() + 1, mSelectedDate.getDate());
            showLoading();
        } else {
            Toast.makeText(mActivity, "加载出错啦，请更换日期试试", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void handleArgument(Bundle argument) {

    }

    @Override
    protected void initEventAndData(View view, @Nullable Bundle savedInstanceState) {
        setCalendarConfig();
        setRecommendRecyclerConfig();
        setLoadingConfig();
        appbar_recommend_calendar.setOnClickListener(this);

        getData();
    }

    @Override
    public void showError(Exception e) {
        Toast.makeText(App.getInstance(), "加载出错啦，请更换日期试试", Toast.LENGTH_SHORT).show();
        hiddenLoading();
    }

    @Override
    public void showRecommend(List<GankBean> recommends) {
        mAdapter.add(recommends);
        hiddenLoading();
    }

    private void setCalendarConfig() {
        mSelectedDate = DEFAULT_DATE;
        updateCurrentDate();
    }

    private void setRecommendRecyclerConfig() {
        mAdapter = new RecommendRecyclerAdapter(getContext());
        recycler_recommend.setAdapter(mAdapter);
        recycler_recommend.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recycler_recommend.addItemDecoration(new SpaceDecortaion(20));
    }

    private void setLoadingConfig() {
        DraweeController draweeController = Fresco
                .newDraweeControllerBuilder()
                .setUri(Uri.parse("res://" + App.getInstance().getPackageName() + "/" + R.drawable.ic_cool_loading))
                .setAutoPlayAnimations(true)
                .build();
        iv_recommend_loading.setController(draweeController);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appbar_recommend_calendar:
                Intent toCalendar = new Intent();
                toCalendar.setClass(getContext(), CalendarActivity.class);
                toCalendar.putExtra(CalendarActivity.DEFAULT_CALENDAR, mSelectedDate);
                startActivityForResult(toCalendar, CalendarActivity.REQUEST_CODE);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CalendarActivity.REQUEST_CODE && resultCode == CalendarActivity.RESULT_CODE) {
            mSelectedDate = (Date) data.getSerializableExtra(CalendarActivity.RESULT_DATE);
            updateCurrentDate();
            getData();
        }
    }

    private void updateCurrentDate() {
        if (mSelectedDate == null) {
            return;
        }
        tv_calendar_year.setText(String.valueOf(mSelectedDate.getYear() + 1900));
        tv_calendar_month.setText(String.format("%02d", mSelectedDate.getMonth() + 1));
        tv_calendar_day.setText(String.format("%02d", mSelectedDate.getDate()));
    }

    private void showLoading() {
        v_recommend_loading_area.setVisibility(View.VISIBLE);
        recycler_recommend.setVisibility(View.GONE);
    }

    private void hiddenLoading() {
        v_recommend_loading_area.setVisibility(View.GONE);
        recycler_recommend.setVisibility(View.VISIBLE);
    }

}
