package com.bigdata.livesocial.kafka_common.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

/**
 * @author Dixit Patel
 */

@Service
public class UserConsumer {
    private static final Logger LOG = LoggerFactory.getLogger(UserConsumer.class);

    private CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch() {
        return latch;
    }
    @KafkaListener(topics = "${kafka.topic.user}")
    public void listen(@Payload String message) {
        //Send to web client
        //TODO
        LOG.info("received message='{}'", message);
        latch.countDown();
        LOG.info("latch count "+latch.getCount());

    }
}
