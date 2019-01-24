package com.yunqilai.delivery.utils;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.yunqilai.delivery.MyApplication;
import com.yunqilai.delivery.R;

/**
 * Created by TaoRan on 2016/4/22 0022.
 */
public class ImageLoaderUtil {

    /**
     * 加载自定义配置的图片
     *
     * @param url
     * @param imageView
     */
    public static void displayImage(String url, ImageView imageView) {
        displayImageAndDefaultImg(url,imageView, R.mipmap.ic_launcher);
    }

    /**
     * 加载自定义配置的图片,指定不成功的显示的默认图片
     * @param url
     * @param imageView
     * @param def
     */
    public static void displayImageAndDefaultImg(String url, ImageView imageView, int def) {
        Picasso.with(MyApplication.getContext())
                .load(url)
                .resize(800,800)
                .centerInside()
                .onlyScaleDown()
                .placeholder(def)
                .error(def)
                .into(imageView);
    }

    public static void displayFromSDCard(String uri, ImageView imageView) {
    }

    /***
     * 清除图片缓存
     */

    public static void clearImageCache(){
    }
}
