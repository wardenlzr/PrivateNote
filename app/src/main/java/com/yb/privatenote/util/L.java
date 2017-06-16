package com.yb.privatenote.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;


/**
 * 打印日志 转换时间等 工具类
 * Created by yb on 2016/10/31.
 */

public class L {
    private static final String TAG = "BG";

    // 下面四个是默认tag的函数
    public static void i(String msg) {
        i(TAG, msg);
    }

    // 下面四个是默认tag的函数
    public static void i(String tag, String msg) {
        if (true) {
            if (TextUtils.isEmpty(msg)) {
                msg = "null";
            }
            Log.e(tag, msg);
        }
    }
    public static void t(final Context c, final int s) {
        t(c,c.getResources().getString(s));
    }
    public static void t(final Context c, final String s) {
        runOnUIThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(c, s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void isMainThread() {
        i("isMainThread:" + (Looper.myLooper() == Looper.getMainLooper()));
    }

    //获取内存卡根目录
    public static String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED);//判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();//获取跟目录
        }
        return sdDir.toString();
    }

    private static Handler mHandler = new Handler(Looper.getMainLooper());

    // 在主线程执行的方法，主线程
    public static void runOnUIThread(Runnable task) {
        mHandler.post(task);
    }

    /**
     * 获取 的时间
     */
    @SuppressLint("SimpleDateFormat")
    public static String getTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        long currentTime = System.currentTimeMillis();
        String newTime = format.format(currentTime);
        return newTime;
    }

    /**
     * 隐藏软键盘
     *
     * @param c
     */
    public static void hideKeyBoard(Context c) {
        InputMethodManager imm = (InputMethodManager) c.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive())
            imm.hideSoftInputFromWindow(((Activity) c).getWindow().getDecorView().getWindowToken(), 0);
    }
    /**
     * 根据图片路径压缩图片并返回压缩后图片的路径
     * @param mCurrentPhotoPath
     * @param context
     * @return
     */
    public static String compressImage(String mCurrentPhotoPath, Context context) {

        if (mCurrentPhotoPath != null) {

            try {
                File f = new File(mCurrentPhotoPath);
                Bitmap bm = getSmallBitmap(mCurrentPhotoPath);
                //获取文件路径 即：/data/data/***/files目录下的文件
                String path = context.getFilesDir().getPath();
//                Log.e(TAG, "compressImage:path== "+path );
                //获取缓存路径
                File cacheDir = context.getCacheDir();
//                Log.e(TAG, "compressImage:cacheDir== "+cacheDir );
//                File newfile = new File(
//                getAlbumDir(), "small_" + f.getName());
                File newfile = new File(
                        cacheDir, "small_" + f.getName());
                FileOutputStream fos = new FileOutputStream(newfile);
                bm.compress(Bitmap.CompressFormat.JPEG, 100, fos);

                return newfile.getPath();

            } catch (Exception e) {
                Log.e(TAG, "error", e);
            }

        } else {
            Log.e(TAG, "save: 图片路径为空");
        }
        return mCurrentPhotoPath;
    }
    /**
     * 根据路径获得突破并压缩返回bitmap用于显示
     *
     * @param filePath
     * @return
     */
    public static Bitmap getSmallBitmap(String filePath) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        options.inSampleSize = calculateInSampleSize(options, 480, 800);

        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(filePath, options);
    }
    /**
     * 计算图片的缩放值
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height
                    / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

}
