package com.news.ui.other.dialog;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.news.R;
import com.news.app.App;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 阿飞 on 2017/4/19.
 */

public class LoadingDialog extends Dialog {

    @InjectView(R.id.ic_dialog_loading)
    protected SimpleDraweeView ic_dialog_loading;

    public LoadingDialog(Context context) {
        super(context, R.style.NoTitleDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        ButterKnife.inject(this);
        setCanceledOnTouchOutside(false);
        setLoadingConfig();
    }

    private void setLoadingConfig() {
        DraweeController draweeController = Fresco
                .newDraweeControllerBuilder()
                .setUri(Uri.parse("res://" + App.getInstance().getPackageName() + "/" + R.drawable.ic_cool_loading))
                .setAutoPlayAnimations(true)
                .build();
        ic_dialog_loading.setController(draweeController);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}
