package com.news.utils.download;

import android.content.Context;
import android.os.Environment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.news.R;
import com.news.app.App;

import java.io.File;
import java.io.FileOutputStream;

import static android.os.Environment.DIRECTORY_PICTURES;

/**
 * Created by 阿飞 on 2017/4/16.
 */

public class ImageDownload {

    private static ImageDownload INSTANCE;

    private OnImageDownListener mOnImageDownListener;

    public static ImageDownload getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ImageDownload();
        }
        return INSTANCE;
    }

    private Context mContext;

    private ImageDownload() {
        mContext = App.getInstance();
    }

    public void setOnImageDownListener(OnImageDownListener onImageDownListener) {
        this.mOnImageDownListener = onImageDownListener;
    }

    public void saveImage(String url) {
        saveImage(getImageName(url), url);
    }

    //Glide保存图片
    public void saveImage(final String imageName, String url) {
        Glide.with(mContext)
                .load(url)
                .asBitmap()
                .toBytes()
                .into(new SimpleTarget<byte[]>() {
                    @Override
                    public void onResourceReady(byte[] bytes, GlideAnimation<? super byte[]> glideAnimation) {
                        saveImage(imageName, bytes);
                    }
                });
    }

    //往SD卡写入文件的方法
    public void saveImage(String filename, byte[] bytes) {
        //如果手机已插入sd卡,且app具有读写sd卡的权限
        try {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                String filePath = Environment.getExternalStoragePublicDirectory(DIRECTORY_PICTURES) + "/" + mContext.getResources().getString(R.string.app_name);
                File dir = new File(filePath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                filename = filePath + "/" + filename;
                //这里就不要用openFileOutput了,那个是往手机内存中写数据的
                File file = new File(filename);
                if (!file.exists()) {
                    file.createNewFile();
                } else {
                    file.delete();
                    file.createNewFile();
                }
                FileOutputStream output = new FileOutputStream(filename);
                output.write(bytes);
                //将bytes写入到输出流中
                output.close();
                //关闭输出流
                if (this.mOnImageDownListener != null) {
                    this.mOnImageDownListener.onSuccess(filename);
                }
            } else {
                if (this.mOnImageDownListener != null) {
                    this.mOnImageDownListener.onFail(new Exception("SD卡不存在或者不可读写"));
                }
            }
        } catch (Exception e) {
            if (this.mOnImageDownListener != null) {
                this.mOnImageDownListener.onFail(new Exception("图片下载失败"));
            }
            e.printStackTrace();
        }
    }

    private String getImageName(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }

    public interface OnImageDownListener {
        void onSuccess(String path);

        void onFail(Exception e);
    }
}
