package com.news.ui.other.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.news.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 阿飞 on 2017/4/16.
 */

public class DeleteDialog<T> extends Dialog implements View.OnClickListener {

    @InjectView(R.id.tv_delete_dialog_title)
    protected TextView tv_delete_dialog_title;
    @InjectView(R.id.btn_dialog_delete_confirm)
    protected Button btn_dialog_delete_confirm;
    @InjectView(R.id.btn_dialog_delete_cancel)
    protected Button btn_dialog_delete_cancel;

    private OnChooseListener mOnChooseListener;

    private T target;

    public DeleteDialog(Context context) {
        super(context, R.style.NoTitleDialog);
        setContentView(R.layout.dialog_delete);
        ButterKnife.inject(this);
        initEvent();
    }

    public void setTitle(String title) {
        tv_delete_dialog_title.setText(title);
    }

    public void setTarget(T target) {
        this.target = target;
    }

    private void initEvent() {
        btn_dialog_delete_confirm.setOnClickListener(this);
        btn_dialog_delete_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dialog_delete_confirm:
                if (mOnChooseListener != null) {
                    mOnChooseListener.onConfirm(target);
                    target = null;
                }
                dismiss();
                break;
            case R.id.btn_dialog_delete_cancel:

                if (mOnChooseListener != null) {
                    mOnChooseListener.onCancel();
                }
                dismiss();
                break;
        }
    }

    public void setOnChooseListener(OnChooseListener onChooseListener) {
        this.mOnChooseListener = onChooseListener;
    }

    public interface OnChooseListener<T> {
        void onConfirm(T target);

        void onCancel();
    }
}
