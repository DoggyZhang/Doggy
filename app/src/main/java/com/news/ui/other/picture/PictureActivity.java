package com.news.ui.other.picture;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.news.R;
import com.news.base.BaseActivity;
import com.news.presenter.PicturePresenter;
import com.news.presenter.contract.PictureControct;
import com.news.utils.common.LogUtil;
import com.news.utils.common.ToastUtils;
import com.news.utils.download.ImageDownload;

import butterknife.InjectView;
import uk.co.senab.photoview.PhotoView;

/**
 * Created by 阿飞 on 2017/4/13.
 */

public class PictureActivity extends BaseActivity<PicturePresenter> implements PictureControct.View {
    public static final String PICTURE_URL = "PICTURE_URL";

    @InjectView(R.id.v_picture_show_area)
    protected View v_picture_show_area;
    @InjectView(R.id.photoview_picture)
    protected PhotoView photoview_picture;
    @InjectView(R.id.v_picture_downLoad)
    protected ImageView v_picture_downLoad;

    @InjectView(R.id.v_picture_loading_area)
    protected View v_picture_loading_area;
    @InjectView(R.id.iv_picture_loading)
    protected SimpleDraweeView iv_picture_loading;


    private String mPictureUrl;
    private boolean isInited;

    @Override
    protected PicturePresenter setPresenter() {
        return new PicturePresenter(this);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_picture;
    }

    @Override
    protected void doBeforeContentView() {
        super.doBeforeContentView();
        Intent intent = getIntent();
        if (intent != null) {
            String stringExtra = intent.getStringExtra(PICTURE_URL);
            if (stringExtra != null && !stringExtra.isEmpty()) {
                mPictureUrl = stringExtra;
                isInited = true;
            } else {
                isInited = false;
            }
        } else {
            isInited = false;
        }
    }

    @Override
    protected void doAfterContentView() {
        super.doAfterContentView();
        showLoading();
        if (!isInited) {
            hiddenLoading();
            v_picture_downLoad.setVisibility(View.INVISIBLE);
            ToastUtils.text("没有图片");
        } else {
            setLoadingConfig();
        }
    }

    @Override
    protected void initEvent() {
        getData();

        v_picture_downLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.text("下载图片");
                download();
            }
        });
    }

    @Override
    public void useNightMode(boolean isNight) {

    }

    @Override
    public void show(GlideDrawable drawable) {
        hiddenLoading();
        photoview_picture.setImageDrawable(drawable);
    }

    @Override
    public void showDownLoadResult(boolean success, String path) {
        if (success) {
            ToastUtils.text("图片保存位置：" + path);
        } else {
            ToastUtils.text("图片保存失败");
        }
    }

    @Override
    public void showError(Exception e) {
        hiddenLoading();
        v_picture_downLoad.setVisibility(View.INVISIBLE);
        LogUtil.e(this.getClass().getSimpleName(), e);
    }


    private void getData() {
        if (getPresenter() != null) {
            getPresenter().getPicture(this, mPictureUrl, photoview_picture);
        }
    }

    private void setLoadingConfig() {
        DraweeController draweeController = Fresco
                .newDraweeControllerBuilder()
                .setUri(Uri.parse("res://" + getPackageName() + "/" + R.drawable.ic_cool_loading))
                .setAutoPlayAnimations(true)
                .build();
        iv_picture_loading.setController(draweeController);
    }

    private void showLoading() {
        v_picture_loading_area.setVisibility(View.VISIBLE);
        v_picture_show_area.setVisibility(View.INVISIBLE);
    }

    private void hiddenLoading() {
        v_picture_loading_area.setVisibility(View.INVISIBLE);
        v_picture_show_area.setVisibility(View.VISIBLE);
    }

    private void download() {
        if (TextUtils.isEmpty(mPictureUrl)) {
            showDownLoadResult(false, null);
            return;
        }
        ImageDownload imageDownload = ImageDownload.getInstance();
        imageDownload.setOnImageDownListener(new ImageDownload.OnImageDownListener() {
            @Override
            public void onSuccess(String path) {
                showDownLoadResult(true, path);
            }

            @Override
            public void onFail(Exception e) {
                showDownLoadResult(false, null);
            }
        });
        imageDownload.saveImage(mPictureUrl);
    }
}
