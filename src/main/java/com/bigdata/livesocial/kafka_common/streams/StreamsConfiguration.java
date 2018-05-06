/*
package com.bigdata.livesocial.kafka_common.streams;

import com.bigdata.livesocial.cassandra.model.EventDetailsPojo;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.streams.Consumed;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.core.StreamsBuilderFactoryBean;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerde;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

*/
/**
 * @author Dixit Patel
 *//*


@EnableKafka
@Configuration
@EnableKafkaStreams
public class StreamsConfiguration {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.topic.user}")
    private String kafkaTopicUser;

    @Autowired
    private StreamsBuilderFactoryBean myKStreamBuilderFactoryBean;

    @Bean
    public StreamsConfig streamsConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-livesocial");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, new JsonSerde<>(EventDetailsPojo.class));
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, new JsonSerde<>(EventDetailsPojo.class));
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return new StreamsConfig(props);
    }

    @Bean
    public StreamsBuilderFactoryBean myKStreamBuilder(StreamsConfig streamsConfig) {
        return new StreamsBuilderFactoryBean(streamsConfig);
    }

    @Bean
    public KStream<EventDetailsPojo, EventDetailsPojo> eventStream(StreamsBuilder kStreamBuilder) {
        KStream<EventDetailsPojo, EventDetailsPojo> eventStream = kStreamBuilder.stream(kafkaTopicUser);

        */
/*stream
                .mapValues(String::toUpperCase)
                .groupByKey()
                .reduce((String value1, String value2) -> value1 + value2,
                        TimeWindows.of(1000),
                        "windowStore")
                .toStream()
                .map((windowedId, value) -> new KeyValue<>(windowedId.key(), value))
                .filter((i, s) -> s.length() > 40)
                .to("streamingTopic2");

        stream.print();*//*

        KStream<String, EventDetailsPojo> t = eventStream.map((key,value)-> KeyValue.pair(value.getEvent_id(),value))
                    .filter((key,value)->(value.getStatus()!="False"));


        //KTable<String, EventDetailsPojo> eventsTable =
        return eventStream;
    }


}
*/
