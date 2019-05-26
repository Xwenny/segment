package com.jianwen.segment.designpatterns.simplefactory;

/**
 * @Author: jianwen
 * @since: 2018/12/16 下午3:37
 */
public class DomainLogin implements Login{
    @Override
    public boolean verify(String name, String password) {
        // TODO Auto-generated method stub
        /**
         * 业务逻辑
         */
        return true;
    }
}
