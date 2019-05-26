package com.jianwen.segment.designpatterns.singleton;

/**
 * 饿汉式
 *
 * @Author: jianwen
 * @since: 2018/12/16 下午4:36
 */
public class EagerSingleton {
    private static EagerSingleton instance = new EagerSingleton();

    /**
     * 私有默认构造子
     */
    private EagerSingleton() {
    }

    /**
     * 静态工厂方法
     */
    public static EagerSingleton getInstance() {
        return instance;
    }
}
