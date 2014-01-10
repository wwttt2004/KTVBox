package com.yiqiding.ktvbox.util;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.yiqiding.ktvbox.R;

public class SyncCommonLoadImage {
    final private static DisplayImageOptions.Builder DEFAULT_DISPLAY_IMAGE_OPTIONS_BUILDER = new DisplayImageOptions.Builder()
            .resetViewBeforeLoading().cacheInMemory().cacheOnDisc().imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
            .bitmapConfig(Bitmap.Config.RGB_565).cacheInMemory().cacheOnDisc();
    private static ImageLoader IMAGE_LOADER = null;

    private ImageLoadingListener listener;

    private static ImageLoader getImageLoader() {
        if (IMAGE_LOADER == null) {
            IMAGE_LOADER = ImageLoader.getInstance();
        }
        return IMAGE_LOADER;
    }

    public int getDefaultImageResource() {
        return R.drawable.blank_button;
    }

    public void loadImage(final String uri, final ImageView imageView) {
        final DisplayImageOptions options = DEFAULT_DISPLAY_IMAGE_OPTIONS_BUILDER
                .showStubImage(getDefaultImageResource()).showImageForEmptyUri(getDefaultImageResource())
                .displayer(new RoundedBitmapDisplayer(5)).build();

        getImageLoader().cancelDisplayTask(imageView);
        getImageLoader().displayImage(uri, imageView, options);
    }

    public ImageLoadingListener getListener() {
        return listener;
    }

    public void setListener(ImageLoadingListener listener) {
        this.listener = listener;
    }


}
