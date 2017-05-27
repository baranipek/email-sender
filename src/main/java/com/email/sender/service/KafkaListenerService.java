package com.email.sender.service;


import com.email.sender.model.type.EmailType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.EmailException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Service
@Slf4j
public class KafkaListenerService {
    public final CountDownLatch countDownLatch0 = new CountDownLatch(3);
    public final CountDownLatch countDownLatch1 = new CountDownLatch(3);
    public final CountDownLatch countDownLatch2 = new CountDownLatch(3);

    private final EmailSenderService emailSenderService;
    private static final String EMAIL_TOPIC_NAME = "re";

    @Autowired
    public KafkaListenerService(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    /** * Listen partition 0*/
    @KafkaListener(id = "id0", topicPartitions = {@TopicPartition(topic = EMAIL_TOPIC_NAME, partitions = {"0"})})
    public void listenEmailPartition0(ConsumerRecord<?, ?> record) throws InterruptedException, EmailException {
        Thread.sleep(500);
        emailSenderService.sendEmail((EmailType) record.value());
        log.info("EmailType Send By Thread" + Thread.currentThread().getId());
        countDownLatch0.wait();
    }

    /** * Listen partition 1*/
    @KafkaListener(id = "id1", topicPartitions = {@TopicPartition(topic = EMAIL_TOPIC_NAME, partitions = {"1"})})
    public void listenEmailPartition1(ConsumerRecord<?, ?> record) throws InterruptedException, EmailException {
        Thread.sleep(500);
        emailSenderService.sendEmail((EmailType) record.value());
        log.info("EmailType Send By Thread" + Thread.currentThread().getId());
        countDownLatch1.wait();

    }

    /** * Listen partition 2*/
    @KafkaListener(id = "id2", topicPartitions = {@TopicPartition(topic = EMAIL_TOPIC_NAME, partitions = {"2"})})
    public void listenEmailPartition2(ConsumerRecord<?, ?> record) throws InterruptedException, EmailException {
        Thread.sleep(500);
        emailSenderService.sendEmail((EmailType) record.value());
        log.info("EmailType Send By Thread" + Thread.currentThread().getId());
        countDownLatch2.wait();

    }
}
