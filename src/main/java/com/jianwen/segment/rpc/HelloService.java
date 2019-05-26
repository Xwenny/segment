package com.jianwen.segment.rpc;

/**
 * Created by lybuestc on 17/3/1.
 */
public interface HelloService {
    String hello(User user);

    String hello(User user, int time);
}
