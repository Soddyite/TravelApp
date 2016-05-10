package threadpool;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by dllo on 16/5/10.
 */
public class MyThreadPool {
    private static MyThreadPool myThreadPool;
    private ThreadPoolExecutor threadPool;

    private MyThreadPool() {
        int CPUCores = Runtime.getRuntime().availableProcessors();
        threadPool = new ThreadPoolExecutor(CPUCores + 1, CPUCores * 2 + 1, 60l,
                TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
    }

    public static MyThreadPool getInstance() {

        if (myThreadPool == null) {
            synchronized (MyThreadPool.class) {
                if (myThreadPool == null) {
                    myThreadPool = new MyThreadPool();
                }
            }
        }
        return myThreadPool;
    }

    public ThreadPoolExecutor getThreadPool() {
        return threadPool;
    }


}
