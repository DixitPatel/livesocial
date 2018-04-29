package com.bigdata.livesocial.kafka_common.producer;

import com.bigdata.livesocial.kafka_common.model.Coordinate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Dixit Patel
 */

@Service
public class UserProducer {

    private static final Logger LOG = LoggerFactory.getLogger(UserProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic.user}")
    private String topic;

    public void sendToKafka(String message){
        LOG.info("sending message='{}' to topic='{}'", message, topic);
        kafkaTemplate.send(topic, "Val");
    }
}
