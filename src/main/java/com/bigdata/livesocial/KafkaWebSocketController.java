package com.bigdata.livesocial;

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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    @MessageMapping("/publishEvent")
    public void publishEvent(EventDetailsPojo message) throws Exception {
        LOG.info("Message :  " + message);

        JSONParser parser = new JSONParser();
        JSONObject eventObject = (JSONObject) parser.parse(message.toString());
        Event cassandraEvent = new Event();
        com.bigdata.livesocial.model.Event kafkaEvent = new com.bigdata.livesocial.model.Event();

        cassandraEvent.setName((String) eventObject.get("event_name"));
        kafkaEvent.setName((String) eventObject.get("event_name"));

        cassandraEvent.setDescription((String) eventObject.get("event_description"));
        kafkaEvent.setDescription((String) eventObject.get("event_description"));

        JSONObject geoString = (JSONObject) eventObject.get("geoJson");

        JSONArray features = (JSONArray)geoString.get("features");
        ((JSONObject)features.get(0)).get("geometry");
        ObjectMapper mapper = new ObjectMapper();

        GeoJsonObject featureCollection = new ObjectMapper().readValue(geoString.toString(), GeoJsonObject.class);

        if (featureCollection instanceof List) {
            List<Feature> featuresList = (List<Feature>)featureCollection;
            if(!features.isEmpty()) {
                GeoJsonObject geometry = featuresList.get(0).getGeometry();
                if (geometry instanceof Point) {
                    Point point = (Point) geometry;
                    List<LngLatAlt> coordinates = new ArrayList<>();
                    coordinates.add(point.getCoordinates());
                    cassandraEvent.setCoordinates(coordinates.toString());
                } else if (geometry instanceof Polygon) {
                    Polygon polygon = (Polygon) geometry;
                    List<List<LngLatAlt>> coordinates = new ArrayList<>();
                    coordinates.addAll(polygon.getCoordinates());
                    cassandraEvent.setCoordinates(coordinates.toString());
                }
            }

        }
        LOG.info("Consumer has been started");
        LOG.info("Adding to Cassandra"+cassandraEvent.toString());
        kafkaEvent.setCoordinates(geoString.toJSONString());
        //
        // kafkaEvent.setCurrent_time();
        producer.sendToKafka(kafkaEvent);

        //TODO
        //return "User Listener";
    }

    @KafkaListener(topics = "${kafka.topic.user}")
    public void listen(@Payload Event event) {
        //Send to web client
        //TODO
        LOG.info("received message from kafka ='{}'", event.toString());
        //convert to json format for ws

        JSONObject eventResponse = new JSONObject();
        eventResponse.put("event_name",event.getName());
        eventResponse.put("event_description",event.getDescription());
        eventResponse.put("geoJson",event.getCoordinates());
        this.template.convertAndSend("/topic/user", eventResponse.toJSONString());
    }





    @MessageMapping("/addEventDetails")
    public void addEventDetails(String message) throws Exception {
        LOG.info("Sending event to Kafka ");
        LOG.info("Message :  " + message);
        //TODO think about user group id
        LOG.info("Consumer has been started");
        //TODO
        //return "User Listener";
    }
}
