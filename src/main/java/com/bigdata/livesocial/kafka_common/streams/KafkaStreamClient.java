/*
package com.bigdata.livesocial.kafka_common.streams;

import com.bigdata.livesocial.cassandra.model.EventDetailsPojo;
import org.apache.kafka.streams.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.StreamsBuilderFactoryBean;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Service;

*/
/**
 * @author Dixit Patel
 *//*



@Service
public class KafkaStreamClient {

    @Value("${spring.kafka.topic.user}")
    private String kafkaTopicUser;

   // @Autowired
    private StreamsBuilderFactoryBean myKStreamBuilderFactoryBean;

    public void processStream(EventDetailsPojo message) throws Exception {
        KStream<String, JsonDeserializer> kstream = myKStreamBuilderFactoryBean.getObject().stream(kafkaTopicUser, Consumed.with(myKStreamBuilderFactoryBean.getStreamsConfig().defaultKeySerde(),myKStreamBuilderFactoryBean.getStreamsConfig().defaultValueSerde()));

    }
}
*/
