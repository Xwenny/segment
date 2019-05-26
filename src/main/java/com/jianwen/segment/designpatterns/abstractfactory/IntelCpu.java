package com.jianwen.segment.designpatterns.abstractfactory;

/**
 * @Author: jianwen
 * @since: 2018/12/16 下午4:09
 */
public class IntelCpu implements Cpu{
    /**
     * CPU的针脚数
     */
    private int pins = 0;
    public  IntelCpu(int pins){
        this.pins = pins;
    }
    @Override
    public void calculate() {
        // TODO Auto-generated method stub
        System.out.println("Intel CPU的针脚数：" + pins);
    }
}
