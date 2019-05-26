package com.jianwen.segment.kafka;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStreamBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * @author jianwen
 * @since 2019/02/24
 */
public class KafkaMainTest {

    private static Producer<String, String> stringproducer;
    private static Producer<byte[],byte[]> byteproducer;
    private static KafkaConsumer<String, String> consumer;

    @BeforeClass
    public static void before(){
        Properties stringProperties = new Properties();
        stringProperties.put("bootstrap.servers", "localhost:9092");
        stringProperties.put("acks", "all");
        stringProperties.put("retries", 0);
        stringProperties.put("batch.size", 16384);
        stringProperties.put("linger.ms", 1);
        stringProperties.put("buffer.memory", 33554432);
        stringProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        stringProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Properties byteProperties = new Properties();
        byteProperties.put("bootstrap.servers", "localhost:9092");
        byteProperties.put("acks", "all");
        byteProperties.put("retries", 0);
        byteProperties.put("batch.size", 16384);
        byteProperties.put("linger.ms", 1);
        byteProperties.put("buffer.memory", 33554432);
        byteProperties.put("key.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
        byteProperties.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
        stringproducer = new KafkaProducer<>(stringProperties);
        byteproducer = new KafkaProducer<>(byteProperties);

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "false");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<>(props);

    }

    // 快速启动
    @Test
    public void testQuickStart() throws Exception{
        for(int i = 0; i < 100; i++) {
            Future<RecordMetadata> result = stringproducer.send(new ProducerRecord("my-topic", Integer.toString(i), Integer.toString(i)));
            System.out.println(i + "--result = " + result.isDone());//由于是异步发送，所以输出这行时消息可能还没发送成功，结果会为 false
            Thread.sleep(1000);
            System.out.println(i + "--result behind sleep: " + result.isDone());//等到 sleep 一秒，消息基本已经发送给 kafka 了，这时候结果为 true
        }
    }

    // 同步发送
    @Test
    public void syncSend() throws Exception {
        byte[] key = "key".getBytes();
        byte[] value = "value".getBytes();
        ProducerRecord<byte[],byte[]> record = new ProducerRecord("my-topic", key, value);
        for (int i = 0;i<10;i++) {
            RecordMetadata result = byteproducer.send(record).get();
            System.out.println("当前已经发送到: " + result.offset());
            Thread.sleep(1000);
        }
    }

    // 异步发送
    @Test
    public void asyncSend() throws InterruptedException {
        byte[] key = "key".getBytes();
        byte[] value = "value".getBytes();
        ProducerRecord<byte[],byte[]> record = new ProducerRecord<>("the-topic", key, value);
        byteproducer.send(record,
                new Callback() {
                    public void onCompletion(RecordMetadata metadata, Exception e) {
                        if(e != null){
                            e.printStackTrace();}
                        System.out.println("The offset of the record we just sent is: " + metadata.offset());
                    }
                });
        Thread.sleep(1000);
    }

    // 自动提交偏移量
    @Test
    public void antoConsume(){
        consumer.subscribe(Arrays.asList("test", "my-topic"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records)
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
        }
    }

    // 手动控制偏移量
    @Test
    public void handConsume(){
        consumer.subscribe(Arrays.asList("test", "my-topic"));
        final int minBatchSize = 10;
        List<ConsumerRecord<String, String>> buffer = new ArrayList<>();
        // 插入数据库后提交偏移量
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                buffer.add(record);
            }
            if (buffer.size() >= minBatchSize) {
                insertIntoDb(buffer);
                consumer.commitSync();
                buffer.clear();
            }
        }

        // 处理完每个分区中的消息后提交偏移量
//        try {
//            while(true) {
//                ConsumerRecords<String, String> records = consumer.poll(Long.MAX_VALUE);
//                for (TopicPartition partition : records.partitions()) {
//                    List<ConsumerRecord<String, String>> partitionRecords = records.records(partition);
//                    for (ConsumerRecord<String, String> record : partitionRecords) {
//                        System.out.println(record.offset() + ": " + record.value());
//                    }
//                    long lastOffset = partitionRecords.get(partitionRecords.size() - 1).offset();
//                    consumer.commitSync(Collections.singletonMap(partition, new OffsetAndMetadata(lastOffset + 1)));
//                }
//            }
//        } finally {
//            consumer.close();
//        }
    }

    // 订阅指定分区
    @Test
    public void assign(){
        String topic = "foo";
        TopicPartition partition0 = new TopicPartition(topic, 0);
        TopicPartition partition1 = new TopicPartition(topic, 1);
        consumer.assign(Arrays.asList(partition0, partition1));
    }

    private void insertIntoDb(List<ConsumerRecord<String, String>> buffer) {
        System.out.println("start ..." + buffer.size());
    }

    // kafka streams
    @Test
    public void stream(){
        Map<String, Object> props = new HashMap<>();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "my-stream-processing-application");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        StreamsConfig config = new StreamsConfig(props);

        KStreamBuilder builder = new KStreamBuilder();
        builder.stream("my-input-topic").mapValues(value -> value.toString() + "hehe").to("my-out");

        KafkaStreams streams = new KafkaStreams(builder, config);
        streams.start();
    }

    @AfterClass
    public static void after() {
        stringproducer.close();
        byteproducer.close();
    }

}
