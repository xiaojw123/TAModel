package com.common.baseframe.util;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.common.baseframe.R;
import com.nostra13.universalimageloader.cache.disc.DiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.io.File;
import java.util.concurrent.Executors;
/*图片加载工具类*/
public class DrawableUtils {
    public static final int DISK_MAX_SIZE = 50 * 1024 * 1024;
    public static final int MEMORY_MAX_SIZE = 2 * 1024 * 1024;
    private static final int DRAWABLE_NULL = R.mipmap.ic_launcher_round;
    private static ImageLoader loader;

    //为图片着色
    public static Drawable tintDrawable(Drawable drawable, ColorStateList colors) {
        Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintList(wrappedDrawable, colors);
        return wrappedDrawable;
    }


    public static void displayImg(Context context, ImageView img, String url) {
        displayImg(context, img, url, 0, DRAWABLE_NULL, null);
    }

    public static void displayImg(Context context, ImageView img, String url, int drawable, ImageLoadingListener listener) {
        displayImg(context, img, url, 0, drawable, listener);
    }


    public static void displayImg(Context context, ImageView img, String url, int drawable) {
        displayImg(context, img, url, 0, drawable, null);
    }

    public static void displayImg(Context context, ImageView img, String url, int radius, int drawable) {
        displayImg(context, img, url, radius, drawable, null);
    }

    //  public static void displayAvatar

    public static Bitmap loadingBitMap(Context context, String url) {
        if (loader == null) {
            loader = ImageLoader.getInstance();
        }
        if (!loader.isInited()) {
            loader.init(getConfiguration(context));
        }
        return loader.loadImageSync(url);
    }
    //Glide加载图片
    public static void displayImgOnGlide(Context context, ImageView img, String url, int resFaildDrawableId) {
        RequestOptions options=new RequestOptions()
                .placeholder(resFaildDrawableId)
                .error(resFaildDrawableId)
                .priority(Priority.HIGH);
        Glide.with(context).load(url).apply(options).into(img);
    }

    public static void displayImg(Context context, ImageView img, String url, int radius, int drawable, ImageLoadingListener listener) {
        if (TextUtils.isEmpty(url)) {
            img.setImageResource(drawable);
            return;
        }
        if (loader == null) {
            loader = ImageLoader.getInstance();
        }
        if (!loader.isInited()) {
            loader.init(getConfiguration(context));
        }

        File imgFile = loader.getDiskCache().get(url);

        if (imgFile == null || !imgFile.exists()) {
            loader.displayImage(url, img, getImageOptions(radius, drawable), listener);

        } else {
            String fileUri = "file://" + imgFile.getAbsolutePath();
            loader.displayImage(fileUri, img, listener);
        }
    }

    public static void displayRadiusImg(Context context, ImageView img, String url, int radius, int drawable) {
        if (loader == null) {
            loader = ImageLoader.getInstance();
        }
        if (!loader.isInited()) {
            loader.init(getConfiguration(context));
        }


        File imgFile = loader.getDiskCache().get(url);
        loader.displayImage(url, img, getImageOptions(radius, drawable));
    }

    //imgloader若要设置MaxSzie  diskCache必须为null
    public static ImageLoaderConfiguration getConfiguration(Context context) {
        ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(context);
        builder.threadPoolSize(3);
        builder.threadPriority(Thread.MAX_PRIORITY - 2);
//        builder.memoryCache(new UsingFreqLimitedMemoryCache(MEMORY_MAX_SIZE));
        builder.memoryCacheSize(MEMORY_MAX_SIZE);
        builder.diskCacheSize(DISK_MAX_SIZE);
//        builder.diskCache(new UnlimitedDiskCache(getFileDir()));
        builder.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        builder.diskCacheFileCount(100);
        builder.tasksProcessingOrder(QueueProcessingType.FIFO);
        builder.taskExecutor(Executors.newCachedThreadPool());
        builder.writeDebugLogs();
        return builder.build();
    }


    private static DisplayImageOptions getImageOptions(int radius, int resID) {
        DisplayImageOptions.Builder builder = new DisplayImageOptions.Builder();

        if (resID != -1) {
            builder.showImageForEmptyUri(resID);
            builder.showImageOnLoading(resID);
            builder.showImageOnFail(resID);
        }
        if (radius > 0) {
            builder.displayer(new RoundedBitmapDisplayer(radius));
        }
        builder.cacheInMemory(true);
        builder.cacheOnDisk(true);
        builder.imageScaleType(ImageScaleType.EXACTLY);

        return builder.build();
    }


    public static File getCacheImgFile() {
        if (loader != null) {
            DiskCache diskCache = loader.getDiskCache();
            if (diskCache != null) {
                return diskCache.getDirectory();
            }
        }
        return null;
    }

    /**
     * 本地drawable文件中的drawable资源转uri
     *
     * @param resId
     * @return
     */
    public static Uri drawableRes2Uri(Context context, int resId) {
        if (context == null) {
            return null;
        }

        Resources r = context.getResources();
        String uri = ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + r.getResourcePackageName(resId) + "/"
                + r.getResourceTypeName(resId) + "/"
                + r.getResourceEntryName(resId);
        return Uri.parse(uri);
    }
}
