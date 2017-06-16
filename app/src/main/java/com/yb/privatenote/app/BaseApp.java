package com.yb.privatenote.app;

import android.app.Application;
import android.content.Context;


/**
 * Application
 */
public class BaseApp extends Application {
    private static BaseApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        setInstance(this);
        CrashHandler.getInstance().init(getApplicationContext());
    }

    /**
     * 获取上下文
     */
    public static Context getInstance() {
        return instance;
    }

    public static void setInstance(BaseApp instance) {
        BaseApp.instance = instance;
    }

}

