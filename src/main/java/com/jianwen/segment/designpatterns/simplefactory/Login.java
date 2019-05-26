package com.jianwen.segment.designpatterns.simplefactory;

/**
 * @Author: jianwen
 * @since: 2018/12/16 下午3:37
 */
public interface Login {
    //登录验证
    public boolean verify(String name , String password);
}
