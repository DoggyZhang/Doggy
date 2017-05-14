package com.news.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.news.R;
import com.news.presenter.contract.PictureControct;
import com.news.presenter.impl.PresenterImpl;
import com.news.utils.download.ImageDownload;
import com.news.utils.rx.retrofit.RxUtils;

import rx.Observable;
import rx.Subscriber;
import uk.co.senab.photoview.PhotoView;

/**
 * Created by 阿飞 on 2017/4/13.
 */

public class PicturePresenter extends PresenterImpl<PictureControct.View> implements PictureControct.Presenter {

    public PicturePresenter(PictureControct.View view) {
        super(view);
    }

    @Override
    public void getPicture(Context context, String url, PhotoView view) {
        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .skipMemoryCache(false)
                .error(R.mipmap.ic_error)
                .listener(new RequestListener<String, GlideDrawable>() {
                              @Override
                              public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                  if (getView() != null) {
                                      getView().showError(e);
                                      return true;
                                  }
                                  e.printStackTrace();
                                  return false;

                              }

                              @Override
                              public boolean onResourceReady(GlideDrawable resource, String
                                      model, Target<GlideDrawable> target, boolean isFromMemoryCache,
                                                             boolean isFirstResource) {
                                  if (getView() != null) {
                                      getView().show(resource);
                                      return true;
                                  }
                                  return false;
                              }
                          }

                ).into(view);
    }

    @Override
    public void downLoadPicture(final String url) {
        Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(final Subscriber<? super String> subscriber) {
                        if (TextUtils.isEmpty(url)) {
                            subscriber.onError(new Throwable("图片地址为空"));
                        } else {
                            ImageDownload imageDownload = ImageDownload.getInstance();
                            imageDownload.setOnImageDownListener(new ImageDownload.OnImageDownListener() {
                                @Override
                                public void onSuccess(String path) {
                                    subscriber.onNext(path);
                                }

                                @Override
                                public void onFail(Exception e) {
                                    subscriber.onError(e);
                                }
                            });
                            imageDownload.saveImage(url);
                        }
                    }
                })
                .compose(RxUtils.<String>defaultSchedulers())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (getView() != null) {
                            getView().showDownLoadResult(false, null);
                        }
                    }

                    @Override
                    public void onNext(String path) {
                        if (getView() != null) {
                            getView().showDownLoadResult(true, path);
                        }
                    }
                });

    }
}
