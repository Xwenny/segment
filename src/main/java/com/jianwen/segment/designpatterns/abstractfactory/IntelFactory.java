package com.jianwen.segment.designpatterns.abstractfactory;

/**
 * @Author: jianwen
 * @since: 2018/12/16 下午4:01
 */
public class IntelFactory implements AbstractFactory{
    @Override
    public Cpu createCpu() {
        // TODO Auto-generated method stub
        return new IntelCpu(755);
    }

    @Override
    public Mainboard createMainboard() {
        // TODO Auto-generated method stub
        return new IntelMainboard(755);
    }
}
