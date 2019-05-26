package com.jianwen.segment.designpatterns.build.example;

/**
 * @Author: jianwen
 * @since: 2018/12/17 下午12:04
 */
public class GoodbyeBuilder extends Builder {
    public GoodbyeBuilder() {
        msg = new GoodbyeMessage();
    }

    @Override
    public void buildBody() {
        // TODO Auto-generated method stub
        msg.setBody("欢送内容");
    }

    @Override
    public void buildSubject() {
        // TODO Auto-generated method stub
        msg.setSubject("欢送标题");
    }

}
