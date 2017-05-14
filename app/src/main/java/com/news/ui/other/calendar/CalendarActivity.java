package com.news.ui.other.calendar;

import android.content.Intent;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.TextView;

import com.news.R;
import com.news.app.App;
import com.news.base.BaseActivity;
import com.news.presenter.CalendarPresenter;
import com.news.presenter.contract.CalendarContract;
import com.squareup.timessquare.CalendarCellView;
import com.squareup.timessquare.CalendarPickerView;
import com.squareup.timessquare.DayViewAdapter;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import butterknife.InjectView;

/**
 * Created by 阿飞 on 2017/4/16.
 */

public class CalendarActivity extends BaseActivity<CalendarPresenter> implements CalendarContract.View, View.OnClickListener {

    // 传递默认的序列化 Date
    public static final String DEFAULT_CALENDAR = "DEFAULT_CALENDAR";

    public static final int REQUEST_CODE = 0x0001;
    public static final int RESULT_CODE = 0x0002;
    public static final String RESULT_DATE = "CALENDAR_DATE";

    @InjectView(R.id.iv_main_search)
    protected View iv_main_search;
    @InjectView(R.id.tv_calendar_year)
    protected TextView tv_calendar_year;
    @InjectView(R.id.tv_calendar_month)
    protected TextView tv_calendar_month;
    @InjectView(R.id.tv_calendar_day)
    protected TextView tv_calendar_day;
    @InjectView(R.id.tv_calendar_confirm)
    protected TextView tv_calendar_confirm;

    @InjectView(R.id.calendar_view)
    protected CalendarPickerView v_calendar;

    private static final Date MIN_DATE = new Date(2000 - 1900, 0, 1);
    private static final Date MAX_DATE = new Date(2017 - 1900, 11, 30);
    private Date mSelectedDate;

    @Override
    protected CalendarPresenter setPresenter() {
        return new CalendarPresenter(this);
    }

    @Override
    protected void doBeforeContentView() {
        super.doBeforeContentView();
        Intent intent = getIntent();
        if (intent != null) {
            Serializable serializableExtra = intent.getSerializableExtra(DEFAULT_CALENDAR);
            if (serializableExtra != null) {
                mSelectedDate = (Date) serializableExtra;
            }
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_calendar;
    }

    @Override
    protected void doAfterContentView() {
        super.doAfterContentView();
        setTitleBarConfig();
        setCalendarConfig();
        updateCurrentDate();
    }

    @Override
    protected void initEvent() {
        tv_calendar_confirm.setOnClickListener(this);
    }

    private void setResult(Date date) {
        Intent result = getIntent();
        result.putExtra(RESULT_DATE, date);
        setResult(RESULT_CODE, result);
    }

    @Override
    public void useNightMode(boolean isNight) {

    }

    @Override
    public void showError(Exception e) {

    }

    private void setTitleBarConfig() {
        iv_main_search.setVisibility(View.INVISIBLE);
    }

    private void setCalendarConfig() {
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);

        if (mSelectedDate == null) {
            mSelectedDate = new Date();
        }
        v_calendar
                .init(MIN_DATE, MAX_DATE)
                .withSelectedDate(mSelectedDate);
        v_calendar.setCustomDayView(new DayViewAdapter() {
            @Override
            public void makeCellView(CalendarCellView parent) {
                TextView textView = new TextView(
                        new ContextThemeWrapper(parent.getContext(), com.squareup.timessquare.R.style.CalendarCell_CalendarDate));
                textView.setBackgroundDrawable(App.getInstance().getResources().getDrawable(R.drawable.bg_calendar_cell_selector));
                textView.setDuplicateParentStateEnabled(true);
                parent.addView(textView);
                parent.setDayOfMonthTextView(textView);
            }
        });
        v_calendar.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                mSelectedDate = date;
                updateCurrentDate();
            }

            @Override
            public void onDateUnselected(Date date) {

            }
        });
    }

    private void updateCurrentDate() {
        if (mSelectedDate == null) {
            return;
        }
        tv_calendar_year.setText(String.valueOf(mSelectedDate.getYear() + 1900));
        tv_calendar_month.setText(String.format("%02d", mSelectedDate.getMonth() + 1));
        tv_calendar_day.setText(String.format("%02d", mSelectedDate.getDate()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_calendar_confirm:
                setResult(mSelectedDate);
                finish();
                break;
        }
    }
}
