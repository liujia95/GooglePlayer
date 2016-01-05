package me.liujia95.googleplayer;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by Administrator on 2016/1/5 20:31.
 */
public class BaseApplication extends Application {

    private static Context mContext;
    private static Handler mHandler;
    private static Thread  mMainThread;
    private static long    mMainThreadId;

    public static Context getContext() {
        return mContext;
    }

    public static Handler getHandler() {
        return mHandler;
    }

    public static Thread getMainThread() {
        return mMainThread;
    }

    public static long getMainThreadId() {
        return mMainThreadId;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mHandler = new Handler();
        mMainThread = Thread.currentThread();
        mMainThreadId = mMainThread.getId();
    }
}
