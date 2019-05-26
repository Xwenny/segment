package com.jianwen.segment.rpc;

/**
 * Created by lybuestc on 17/3/1.
 */
public class RpcProvider {
    public static void main(String[] args) throws Exception {
        HelloService service = new HelloServiceImpl();
        RpcFramework.export(service, 1234);
    }
}
