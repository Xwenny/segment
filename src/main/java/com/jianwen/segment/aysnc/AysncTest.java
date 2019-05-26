package com.jianwen.segment.aysnc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author jianwen
 * @since 2019/04/09
 */
public class AysncTest {

    private long startTime;

    private long endTime;

    private ExecutorService executorService = Executors.newFixedThreadPool(100);

    public void updateBefore() {
        startTime = System.currentTimeMillis();
    }

    int taskNum = 100;

    /**
     * 同步执行任务，耗时taskNum *10
     */
    @Test
    public void testSync() {
        updateBefore();
        for (int i = 0 ;i < taskNum; i++) {
            task();
        }
        updateEndTime();
        System.out.println(getCost());
    }

    /**
     * 异步执行，不关心结果
     */
    @Test
    public void testAsync() {
        updateBefore();
        for (int i = 0 ;i < taskNum; i++) {
            executorService.submit(() -> {
                task();
            });
        }
        updateEndTime();
        System.out.println(getCost());
    }

    /**
     * 异步执行，等待所有任务都完成后再继续执行
     */
    @Test
    public void testAsyncAwaitResult() throws Exception {
        updateBefore();
        List<Future> result = new ArrayList();
        for (int i = 0 ; i < taskNum; i++) {
            Future future = executorService.submit(() -> {
                task();
            });
            result.add(future);
        }
        for ( int i = 0 ; i < result.size(); i ++) {
            result.get(i).get();
        }
        updateEndTime();
        System.out.println(getCost());
    }

    /**
     * 使用 countLatchDown 批量异步处理，会等待结果执行完成
     */
    @Test
    public void testCountDownLatch() throws InterruptedException {
        updateBefore();
       CountDownLatch latch = new CountDownLatch(taskNum);
       for (int i = 0 ; i < taskNum; i ++ ) {
           executorService.execute(() -> {
               task();
               latch.countDown();
           });
       }
       latch.await();
       updateEndTime();
        System.out.println(getCost());
    }

    /**
     * 单个任务耗时
     */
    public void task() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updateEndTime() {
        endTime = System.currentTimeMillis();
    }

    public long getCost() {
        return endTime - startTime;
    }
}
