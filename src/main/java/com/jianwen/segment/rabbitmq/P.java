package com.jianwen.segment.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class P {
    private final static String QUEUE_NAME = "hello";
    public static void main(String[] argv) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("10.0.40.152");
        factory.setUsername("w");
        factory.setPassword("wenny");
        factory.setVirtualHost("testVhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        String message = "Hello World!";
        channel.basicPublish("",QUEUE_NAME,null,message.getBytes("UTF-8"));
        System.out.println("P [x] Sent '"+message+"'");
        channel.close();
        connection.close();
    }

}


