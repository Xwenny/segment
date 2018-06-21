package com.jianwen.segment.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * newFixedThreadPool的参数指定了可以运行的线程的最大数目，超过这个数目的线程加进去以后，不会运行。其次，加入线程池的线程属于托管状态，线程的运行不受加入顺序的影响。
 * @Author: jianwen
 * @Date: 2018/6/21 下午5:33
 */
public class FixedThreadPoolImplTest {
    public static void main(String[] args) {
        // 创建一个可重用固定线程数的线程池
        ExecutorService pool = Executors.newFixedThreadPool(5);
        //ExecutorService pool = Executors.newFixedThreadPool(2);

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

