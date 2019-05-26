package com.jianwen.segment.thread;

/**
 * @Author: jianwen
 * @Date: 2018/6/21 下午5:45
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "正在执行。。。");
    }
}
