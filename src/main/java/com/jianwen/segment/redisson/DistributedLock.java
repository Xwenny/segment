package com.jianwen.segment.redisson;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import redis.clients.jedis.JedisCommands;

/**
 * @author jianwen
 * @since 2019/04/02
 */
public class DistributedLock {
    private static final String INTELLIGENCE_REDIS_LOCK_KEY = "INTELLIGENCE_REDIS_LOCK_KEY";

    private static final String REDIS_LOCK_OK_STATUS = "OK";
    RedissonClient redissonClient;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    public void start() {
        Config config = new Config();
        config.useSingleServer().setAddress("127.0.0.1:6379");
        redissonClient =  Redisson.create(config);
    }

    //    public void testReentrantLock(){
    //        RLock lock = redissonClient.getLock("anyLock");
    //        try{
    //            // 1. 最常见的使用方法
    //            lock.lock();
    //            executeJob();
    //        } finally {
    //            lock.unlock();
    //        }
    //    }
    public void testReentrantLock(){
        String result = redisTemplate.execute((RedisCallback<String>) connection -> {
            JedisCommands commands = (JedisCommands) connection.getNativeConnection();
            return commands.set(INTELLIGENCE_REDIS_LOCK_KEY, INTELLIGENCE_REDIS_LOCK_KEY, "NX", "PX", 5000);
        });
        //        Boolean result = redisTemplate.opsForValue().setIfAbsent(INTELLIGENCE_REDIS_LOCK_KEY,
        //                INTELLIGENCE_REDIS_LOCK_KEY);
        if (REDIS_LOCK_OK_STATUS.equalsIgnoreCase(result)) {
            executeJob();
        }
    }

    public void noLock() {
        executeJob();
    }

    //    public void executeJob(){
    //        System.out.println(System.currentTimeMillis()/1000 + "---------开始执行 job");
    //        try {
    //            Thread.sleep(3000);
    //        } catch (InterruptedException e) {
    //            e.printStackTrace();
    //        }
    //        System.out.println(System.currentTimeMillis()/1000 + "完成执行 job");
    //    }


    public void executeJob(){
        System.out.println(System.currentTimeMillis()/1000 + "---------开始执行 job");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis()/1000 + "完成执行 job");
    }

}
