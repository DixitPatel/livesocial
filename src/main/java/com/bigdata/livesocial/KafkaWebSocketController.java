package com.bigdata.livesocial;

import com.bigdata.livesocial.kafka_common.consumer.KafkaConsumerClient;
import com.bigdata.livesocial.kafka_common.consumer.KafkaConsumerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
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

    @MessageMapping("/consumeEvents")
    @SendTo("/topic/user")
    public String greeting(String message) throws Exception {
        //TODO Session required ?
        LOG.info("Creating consumer session ");
        KafkaConsumerFactory consumerFactory = new KafkaConsumerFactory();
        //TODO think about user group id
        KafkaConsumerClient consumer = consumerFactory.getConsumer("user","user",template);
        LOG.info("Consumer has been started");

        //TODO
        this.template.convertAndSend("/topic/user","return from consumer");
        return "User Listener";
    }
}
