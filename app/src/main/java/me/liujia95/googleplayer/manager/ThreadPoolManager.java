package me.liujia95.googleplayer.manager;

/**
 * Created by Administrator on 2016/1/6 21:26.
 */
public class ThreadPoolManager {

    private static ThreadPoolProxy mLongPool;//耗时操作的线程池

    public static ThreadPoolProxy getLongPool() {
        if (mLongPool == null) {
            synchronized (ThreadPoolManager.class){
                if(mLongPool == null){
                    mLongPool = new ThreadPoolProxy(3, 3, 0L);
                }
            }
        }
        return mLongPool;
    }

}
