package me.liujia95.googleplayer.manager;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/1/6 20:41.
 *
 * @des 线程池代理
 */
public class ThreadPoolProxy {
    private ThreadPoolExecutor mExecutor;
    private int  mCorePoolSize    = 3;
    private int  mMaximumPoolSize = 5;
    private long mKeepAliveTime   = 5000;

    public ThreadPoolProxy(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
        mCorePoolSize = corePoolSize;
        mMaximumPoolSize = maximumPoolSize;
        mKeepAliveTime = keepAliveTime;
    }

    /**
     * 执行任务
     *
     * @param task
     */
    public void execute(Runnable task) {

        if (task == null) {
            return;
        }

        checkPool();

        mExecutor.execute(task);
    }

    /**
     * 执行任务
     *
     * @param task
     * @return
     */
    public Future<?> submit(Runnable task) {
        if (task == null) {
            return null;
        }

        checkPool();

        return mExecutor.submit(task);
    }

    /**
     * 从队列中移除任务
     *
     * @param task
     */
    public void remove(Runnable task) {
        if (mExecutor != null) {
            mExecutor.getQueue().remove(task);
        }
    }

    /**
     * 检查线程池
     */
    private void checkPool() {
        if (mExecutor == null || mExecutor.isShutdown()) {
            //int corePoolSize = 3;//核心线程数
            //int maximumPoolSize = 5;//最大线程数
            //long keepAliveTime = 5000;//超出线程的保持时间
            TimeUnit unit = TimeUnit.MILLISECONDS;//保持时间的单位
            //BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(3);//任务队列的大小
            BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>();//不固定任务队列的大小
            ThreadFactory threadFactory = Executors.defaultThreadFactory();//线程工厂(默认的线程工厂)
            RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();//错误捕获器（任务超出队列了，怎么处理）
            mExecutor = new ThreadPoolExecutor(mCorePoolSize, mMaximumPoolSize, mKeepAliveTime, unit, workQueue, threadFactory, handler);
        }
    }
}
