package com.jianwen.segment.rocketmq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.TimeUnit;


public class Producer {
    public static void main(String[] args) throws MQClientException, InterruptedException {
        final DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupName");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.setInstanceName("Producer");
        producer.start();
        for (int i = 0; i < 10; i++) {
            try {
                {
                    Message message = new Message("TopicTest1", "TagA", "OrderID001", ("Hello MetaQA").getBytes());
                    SendResult sendResult = producer.send(message);
                    System.out.println(sendResult);
                }
                {
                    Message message = new Message("TopicTest2", "TagB", "OrderID0034", ("Hello MetaQB").getBytes());
                    SendResult sendResult = producer.send(message);
                    System.out.println(sendResult);
                }

                {
                    Message message = new Message("TopicTest3", "TagC", "OrderID061", ("Hello MetaQC").getBytes());
                    SendResult sendResult = producer.send(message);
                    System.out.println(sendResult);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            TimeUnit.MILLISECONDS.sleep(1000);
            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                @Override
                public void run() {
                    producer.shutdown();
                }
            }));
            System.exit(0);

        }
    }
}



