package com.jianwen.segment.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: jianwen
 * @Date: 2018/6/21 下午5:40
 */
public class CachedThreadExcutorImplTest {
    public static void main(String[] args) {
        // 创建一个缓存线程池
        ExecutorService pool = Executors.newCachedThreadPool();
        // 创建线程
        Thread t1 = new MyThread();
        Thread t2 = new MyThread();
        Thread t3 = new MyThread();
        Thread t4 = new MyThread();
        Thread t5 = new MyThread();
        // 将线程放入池中进行执行
        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5);
        // 关闭线程池
        pool.shutdown();
    }
}



