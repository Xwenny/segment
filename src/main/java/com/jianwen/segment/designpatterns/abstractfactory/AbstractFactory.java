package com.jianwen.segment.designpatterns.abstractfactory;

/**
 * @Author: jianwen
 * @since: 2018/12/16 下午4:01
 */
public interface AbstractFactory {
    /**
     * 创建CPU对象
     * @return CPU对象
     */
    public Cpu createCpu();
    /**
     * 创建主板对象
     * @return 主板对象
     */
    public Mainboard createMainboard();
}
