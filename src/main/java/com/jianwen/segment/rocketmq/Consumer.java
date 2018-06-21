package com.jianwen.segment.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class Consumer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("ConsumerGroupName");
        consumer.setNamesrvAddr("10.0.200.51:9876");
        consumer.setInstanceName("Consumer");
        consumer.subscribe("TopicTest1","TagA || TagC || TagD");
        consumer.subscribe("TopicTest2","*");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                System.out.println(Thread.currentThread().getName()+"Receive New Message:"+list.size());
                MessageExt messageExt = list.get(0);
                if (messageExt.getTopic().equals("TopicTest1")){
                    if (messageExt.getTags()!=null&&messageExt.getTags().equals("TagA")){
                        System.out.println(new String(messageExt.getBody()));
                    }else if (messageExt.getTags()!=null&&messageExt.getTags().equals("TagC")){
                        System.out.println(new String(messageExt.getBody()));
                    }else if (messageExt.getTags()!=null&&messageExt.getTags().equals("TagD")){
                        System.out.println(new String(messageExt.getBody()));
                }else if (messageExt.getTopic().equals("TopicTest2")){
                        System.out.println(new String(messageExt.getBody()));
                    }}
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }

        });
        consumer.start();
        System.out.println("ConsumerStarted.");

    }
}
