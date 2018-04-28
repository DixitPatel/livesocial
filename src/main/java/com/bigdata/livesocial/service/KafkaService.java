/*
package com.bigdata.livesocial.service;

import java.lang.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.apache.kafka.*;

import org.apache.kafka.common.serialization.*;

*/
/**
 * @author yuvrajarora
 *//*

@Service
public class KafkaService {

    //Settings that will be used for creating producer streams
    protected final ProducerSettings<byte[], String> producerSettings = ProducerSettings
            .create(system, new ByteArraySerializer(), new StringSerializer())
            .withBootstrapServers("localhost:9092");


    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    String kafkaTopic = ""; //Add topic

    public void send(String data) {

        kafkaTemplate.send(kafkaTopic, data);
    }
}
*/
