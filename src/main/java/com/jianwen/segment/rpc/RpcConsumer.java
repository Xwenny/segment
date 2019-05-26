package com.jianwen.segment.rpc;

/**
 * Created by lybuestc on 17/3/1.
 */
public class RpcConsumer {
    public static void main(String[] args) throws Exception {
        HelloService service = RpcFramework.refer(HelloService.class, "127.0.0.1", 1234);
        for (int i = 0; i < Integer.MAX_VALUE; i ++) {
            User user = new User("user",i);
            String hello = service.hello(user);
            String helloTime = service.hello(user, 1);
            System.out.println(hello);
            System.out.println(helloTime);
            Thread.sleep(5000);
        }
    }
}
