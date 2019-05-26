package com.jianwen.segment.rpc;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * Created by lybuestc on 17/3/1.
 */
public class HelloServiceImpl implements HelloService {
    public String hello(User user) {
        return "Hello " + user.toString();
    }
    public String hello(User user, int time) {
        if (time == 1){
            return "Hello " + user.toString() + DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss");
        }else {
            return "gun";
        }
    }
}
