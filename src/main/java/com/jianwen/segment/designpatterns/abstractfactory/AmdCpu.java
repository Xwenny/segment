package com.jianwen.segment.designpatterns.abstractfactory;

/**
 * @Author: jianwen
 * @since: 2018/12/16 下午4:10
 */
public class AmdCpu implements Cpu{
    /**
     * CPU的针脚数
     */
    private int pins = 0;
    public  AmdCpu(int pins){
        this.pins = pins;
    }
    @Override
    public void calculate() {
        // TODO Auto-generated method stub
        System.out.println("AMD CPU的针脚数：" + pins);
    }
}
