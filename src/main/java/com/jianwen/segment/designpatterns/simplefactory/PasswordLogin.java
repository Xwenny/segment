package com.jianwen.segment.designpatterns.simplefactory;

/**
 * @Author: jianwen
 * @since: 2018/12/16 下午3:38
 */
public class PasswordLogin implements Login{
    @Override
    public boolean verify(String name, String password) {
        // TODO Auto-generated method stub
        /**
         * 业务逻辑
         */
        return true;
    }
}
