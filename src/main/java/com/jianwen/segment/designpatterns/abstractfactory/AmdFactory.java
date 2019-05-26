package com.jianwen.segment.designpatterns.abstractfactory;

/**
 * @Author: jianwen
 * @since: 2018/12/16 下午4:02
 */
public class AmdFactory implements AbstractFactory{
    @Override
    public Cpu createCpu() {
        // TODO Auto-generated method stub
        return new IntelCpu(938);
    }

    @Override
    public Mainboard createMainboard() {
        // TODO Auto-generated method stub
        return new IntelMainboard(938);
    }
}
