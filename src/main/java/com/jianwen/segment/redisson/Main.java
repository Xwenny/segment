package com.jianwen.segment.redisson;

/**
 * @author jianwen
 * @since 2019/04/02
 */
public class Main {
    public static void main(String[] args) throws Exception {
        final DistributedLock distributedLock = new DistributedLock();
        distributedLock.start();
        for(int i = 0;i<10;i++) {
            new Thread(new Runnable() {
                public void run() {
                //distributedLock.noLock();
                    distributedLock.testReentrantLock();
                }
            }).start();
        }
        Thread.sleep(1000000);
    }
}
