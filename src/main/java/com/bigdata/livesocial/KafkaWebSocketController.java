package com.bigdata.livesocial;

import com.bigdata.livesocial.cassandra.CassandraService;
import com.bigdata.livesocial.cassandra.model.Event;
import com.bigdata.livesocial.kafka_common.producer.CommunityProducer;
import com.bigdata.livesocial.kafka_common.producer.Friends1Producer;
import com.bigdata.livesocial.kafka_common.producer.Friends2Producer;
import com.bigdata.livesocial.kafka_common.producer.UserProducer;
import com.bigdata.livesocial.cassandra.model.EventDetailsPojo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.UUID;


/**
 * @author Dixit Patel
 */
@Controller
@Configuration
public class KafkaWebSocketController {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaWebSocketController.class);

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private CassandraService cassandraService;

    @Autowired
    private UserProducer userProducer;


    @Autowired
    private CommunityProducer communityProducer;


    //Better name please ?

    @Autowired
    private Friends1Producer friends1Producer;


    @Autowired
    private Friends2Producer friends2Producer;


    @Value("${kafka.topic.community}")
    private String kafkaTopicCommunity;

    @Value("${kafka.topic.user}")
    private String kafkaTopicUser;

    @Value("${kafka.topic.friends1}")
    private String kafkaTopicFriends1;

    @Value("${kafka.topic.friends2}")
    private String kafkaTopicFriends2;

    @MessageMapping("/publishCommonEvent")
    public void publishEvent(EventDetailsPojo message) throws Exception {
        LOG.info("Message To publish :  " + message);
        LOG.info("Consumer has been started");

        //Create a stream with key as event id  and value is message
        // publish --> add to a topic named new events --> kafka stream to process --> ktable --> consumer

        Event cassandraEvent = new Event();
        cassandraEvent.setName(message.getEvent_name());
        cassandraEvent.setUserName(message.getUser_name());
        cassandraEvent.setDescription(message.getEvent_description());
        cassandraEvent.setCoordinates(message.getGeoJson().toString());
        cassandraEvent.setStartTime(message.getStart_time());
        cassandraEvent.setEndTime(message.getEnd_time());
        //UUID eventId = cassandraService.addEventDetails(cassandraEvent);
        LOG.info("Adding to Cassandra"+cassandraEvent.toString());
        userProducer.sendToKafka(message);

    }

    @KafkaListener(topics = "${kafka.topic.user}")
    public void listenEvents(@Payload EventDetailsPojo event) {
        LOG.info("received message from kafka ='{}'", event.toString());
        this.template.convertAndSend("/topic/"+kafkaTopicUser, event);
    }

    //TODO How to create topics dynamically ?
    // Create  a new topic --> publish --> kafka stream to detect and send to a
    // new listener that listens to new topics

    @MessageMapping("/publishCommunityEvent")
    public void publishCommunityEvent(EventDetailsPojo message) throws Exception {
        LOG.info("Message To publish :  " + message);
        LOG.info("Consumer has been started");

        //Create a stream with key as event id  and value is message
        // publish --> add to a topic named new events --> kafka stream to process --> ktable --> consumer

        Event cassandraEvent = new Event();
        cassandraEvent.setName(message.getEvent_name());
        cassandraEvent.setUserName(message.getUser_name());
        cassandraEvent.setDescription(message.getEvent_description());
        cassandraEvent.setCoordinates(message.getGeoJson().toString());
        cassandraEvent.setStartTime(message.getStart_time());
        cassandraEvent.setEndTime(message.getEnd_time());
        //UUID eventId = cassandraService.addEventDetails(cassandraEvent);
        LOG.info("Adding to Cassandra"+cassandraEvent.toString());
        communityProducer.sendToKafka(message);

    }
    @KafkaListener(topics = "${kafka.topic.community}")
    public void listenCommunity(@Payload EventDetailsPojo event) {
        LOG.info("received message from kafka ='{}'", event.toString());
        this.template.convertAndSend("/topic/"+kafkaTopicCommunity, event);
    }


    @MessageMapping("/publishFriends1Event")
    public void publishFriends1Event(EventDetailsPojo message) throws Exception {
        LOG.info("Message To publish :  " + message);
        LOG.info("Consumer has been started");

        //Create a stream with key as event id  and value is message
        // publish --> add to a topic named new events --> kafka stream to process --> ktable --> consumer

        Event cassandraEvent = new Event();
        cassandraEvent.setName(message.getEvent_name());
        cassandraEvent.setUserName(message.getUser_name());
        cassandraEvent.setDescription(message.getEvent_description());
        cassandraEvent.setCoordinates(message.getGeoJson().toString());
        cassandraEvent.setStartTime(message.getStart_time());
        cassandraEvent.setEndTime(message.getEnd_time());
        //UUID eventId = cassandraService.addEventDetails(cassandraEvent);
        LOG.info("Adding to Cassandra"+cassandraEvent.toString());
        friends1Producer.sendToKafka(message);

    }
    @KafkaListener(topics = "${kafka.topic.friends1}")
    public void listenFriends1(@Payload EventDetailsPojo event) {
        LOG.info("received message from kafka ='{}'", event.toString());
        this.template.convertAndSend("/topic/"+kafkaTopicFriends1, event);
    }


    @MessageMapping("/publishFriends2Event")
    public void publishFriends2Event(EventDetailsPojo message) throws Exception {
        LOG.info("Message To publish :  " + message);
        LOG.info("Consumer has been started");

        //Create a stream with key as event id  and value is message
        // publish --> add to a topic named new events --> kafka stream to process --> ktable --> consumer

        Event cassandraEvent = new Event();
        cassandraEvent.setName(message.getEvent_name());
        cassandraEvent.setUserName(message.getUser_name());
        cassandraEvent.setDescription(message.getEvent_description());
        cassandraEvent.setCoordinates(message.getGeoJson().toString());
        cassandraEvent.setStartTime(message.getStart_time());
        cassandraEvent.setEndTime(message.getEnd_time());
       // UUID eventId = cassandraService.addEventDetails(cassandraEvent);
        LOG.info("Adding to Cassandra"+cassandraEvent.toString());

        friends2Producer.sendToKafka(message);

    }

    @KafkaListener(topics = "${kafka.topic.friends2}")
    public void listenFriends2(@Payload EventDetailsPojo event) {
        LOG.info("received message from kafka ='{}'", event.toString());
        this.template.convertAndSend("/topic/"+kafkaTopicFriends2, event);
    }



    @MessageMapping("/addEventDetails")
    public void addEventDetails(EventDetailsPojo message) throws Exception {
        LOG.info("Sending event to Kafka ");
        LOG.info("Message :  " + message);
        //TODO think about user group id
        LOG.info("Consumer has been started");
        //TODO
    }
}
