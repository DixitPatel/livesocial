package com.bigdata.livesocial;

import com.bigdata.livesocial.kafka_common.consumer.KafkaConsumerClient;
import com.bigdata.livesocial.kafka_common.consumer.KafkaConsumerFactory;
import com.bigdata.livesocial.kafka_common.model.Coordinate;
import com.bigdata.livesocial.kafka_common.producer.UserProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * @author Dixit Patel
 */
@Controller
public class KafkaWebSocketController {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaWebSocketController.class);


    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private UserProducer producer;

    @MessageMapping("/consumeEvents")
    public void consumeEvents(String message) throws Exception {
        //TODO Session required ?
        LOG.info("Creating consumer session ");
        LOG.info("Message :  "+message);
        //TODO think about user group id
        LOG.info("Consumer has been started");

        //TODO
        //return "User Listener";
    }

    @KafkaListener(topics = "${kafka.topic.user}")
    public void listen(@Payload String coordinate) {
        //Send to web client
        //TODO
        LOG.info("received message from kafka ='{}'", coordinate);
        //this.template.convertAndSend("/topic/user","return from consumer");
        this.template.convertAndSend("/topic/user","asdasd");
    }


    @MessageMapping("/publishEvent")
    public void publishEvent(String message) throws Exception {
        LOG.info("Sending event to Kafka ");
        LOG.info("Message :  "+message);
        //TODO think about user group id
        LOG.info("Consumer has been started");
        producer.sendToKafka(message);
        //TODO
        //return "User Listener";
    }


    @MessageMapping("/addEventDetails")
    public void addEventDetails(String message) throws Exception {
        LOG.info("Sending event to Kafka ");
        LOG.info("Message :  "+message);
        //TODO think about user group id
        LOG.info("Consumer has been started");
        producer.sendToKafka(message);
        //TODO
        //return "User Listener";
    }
}
