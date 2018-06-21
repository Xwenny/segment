package com.jianwen.segment.rocketmq.test;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.Charset;

public class Produce2 {

    public static void main(String[] args) {
        DefaultMQProducer producer = new DefaultMQProducer("Producer");
        producer.setNamesrvAddr("localhost:9876");

        try {

            producer.start();

            for(int i=0; i<20; i++) {

                Message msg = new Message("TestTopicA", "Push", "test1",
                        "Test Msg 1".getBytes(Charset.forName("utf-8")));

                SendResult result = producer.send(msg);
                System.out.println("id:" + result.getMsgId() +
                        " result:" + result.getSendStatus());

                msg = new Message("TestTopicA", "Pull", "test2",
                        "Test Msg 2".getBytes(Charset.forName("utf-8")));



                result = producer.send(msg);

                System.out.println("id:" + result.getMsgId() +
                        " result:" + result.getSendStatus());
                Thread.sleep(3000);
            }
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            producer.shutdown();
        }
        try {
            Thread.sleep(2000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}