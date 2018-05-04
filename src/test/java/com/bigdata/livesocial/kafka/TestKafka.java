package com.bigdata.livesocial.kafka;

import com.bigdata.livesocial.kafka_common.consumer.UserConsumer;
import com.bigdata.livesocial.kafka_common.producer.UserProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
/**
 * @author Dixit Patel
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class TestKafka {

    @Value("${kafka.topic.user}")
    private String topic;

    @Autowired
    private UserConsumer consumer;

    @Autowired
    private UserProducer producer;

    @Test
    public void testReceive() throws Exception {
        consumer.getLatch().await(10, TimeUnit.SECONDS);
        assertThat(consumer.getLatch().getCount()).isEqualTo(0);
    }
}

