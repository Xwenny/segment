package com.jianwen.segment.designpatterns.build;

/**
 * @Author: jianwen
 * @since: 2018/12/17 上午11:44
 */
public interface Builder {
    public void buildPart1();
    public void buildPart2();
    public Product retrieveResult();
}
