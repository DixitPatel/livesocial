package com.bigdata.livesocial.kafka_common.producer;

import com.bigdata.livesocial.cassandra.model.EventDetailsPojo;
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
public class Friends2Producer {

    private static final Logger LOG = LoggerFactory.getLogger(Friends2Producer.class);

    @Autowired
    private KafkaTemplate<String, EventDetailsPojo> kafkaTemplate;

    @Value("${kafka.topic.friends2}")
    private String topic;

    public void sendToKafka(EventDetailsPojo event){
        LOG.info("sending message='{}' to topic='{}'", event.toString(), topic);
        kafkaTemplate.send(topic,event);
    }
}

