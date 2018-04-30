package com.bigdata.livesocial;

import com.bigdata.livesocial.cassandra.CassandraService;
import com.bigdata.livesocial.cassandra.model.Event;
import com.bigdata.livesocial.model.Coordinate;
import com.bigdata.livesocial.kafka_common.producer.UserProducer;
import com.bigdata.livesocial.model.EventDetailsPojo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.geojson.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class KafkaWebSocketController {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaWebSocketController.class);

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private CassandraService cassandraService;

    @Autowired
    private UserProducer producer;

    @MessageMapping("/publishEvent")
    public void publishEvent(EventDetailsPojo message) throws Exception {
        LOG.info("Message To publish :  " + message);
        LOG.info("Consumer has been started");
        Event cassandraEvent = new Event();
        CassandraService cassandraService = new CassandraService();
        cassandraEvent.setName(message.getEvent_name());
        cassandraEvent.setUserName(message.getUser_name());
        cassandraEvent.setDescription(message.getEvent_description());
        cassandraEvent.setCoordinates(message.getGeoJson().toString());
        cassandraEvent.setStartTime(message.getStart_time());
        cassandraEvent.setEndTime(message.getEnd_time());
        UUID eventId = cassandraService.addEventDetails(cassandraEvent);
        LOG.info("Adding to Cassandra"+cassandraEvent.toString());
        message.setEventId(eventId);
        producer.sendToKafka(message);
    }

    @KafkaListener(topics = "${kafka.topic.user}")
    public void listen(@Payload EventDetailsPojo event) {
        LOG.info("received message from kafka ='{}'", event.toString());
        this.template.convertAndSend("/topic/user", event);
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
