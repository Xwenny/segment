package com.jianwen.segment.designpatterns.singleton;

/**
 * 懒汉式
 *
 * @Author: jianwen
 * @since: 2018/12/16 下午4:37
 */
public class LazySingleton {
    private static LazySingleton instance = null;

    /**
     * 私有默认构造子
     */
    private LazySingleton() {
    }

    /**
     * 静态工厂方法
     */
    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
