package com.bigdata.livesocial.kafka_common.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.streams.StreamsConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Dixit Patel
 */

public class KafkaConsumerFactory {
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    private Properties configProps;
    private SimpMessagingTemplate template;

    private String bootstrapServers="localhost:9092";

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumerFactory.class);

    public void initStreamConsumerConfig() {
        configProps=new Properties();
        configProps.put(StreamsConfig.APPLICATION_ID_CONFIG, "livesocial-streams-pipe");
        configProps.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        configProps.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        //configProps.put(StreamsConfig.GROUP_ID_CONFIG, "user");
        //configProps.put(StreamsConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        //dynamic topic listener
        configProps.put(StreamsConfig.METADATA_MAX_AGE_CONFIG,10000);
    }

    public void initConsumerConfig(){
        configProps=new Properties();
        log.debug("bootstrapServers"+bootstrapServers);
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "user");
        configProps.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,true);
        configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        //dynamic topic listener
        configProps.put(ConsumerConfig.METADATA_MAX_AGE_CONFIG,10000);
    }

    static public KafkaConsumerFactory create(Properties configProps) throws IllegalAccessException, InstantiationException {
        return new KafkaConsumerFactory(configProps);
    }

    public KafkaConsumerFactory() {
        initConsumerConfig();
    }


    private KafkaConsumerFactory(Properties configProps) {
        this.configProps = configProps;
    }

    public KafkaConsumerClient getConsumer(String groupId, String topics,SimpMessagingTemplate template) {
        return getConsumer(groupId, Arrays.asList(topics.split(",")),template);
    }

    public KafkaConsumerClient getConsumer(String groupId, List<String> topics,SimpMessagingTemplate template) {
        if (groupId.isEmpty()) {
            //TODO
            if (configProps.containsKey("group.id")) {
                groupId = String.format("%s-%s", configProps.getProperty("group.id"), groupId);
            }
        }
        Properties sessionProps = (Properties)configProps.clone();
        sessionProps.setProperty("group.id", groupId);

        KafkaConsumerClient consumer = new KafkaConsumerClient(configProps,executorService, topics,template);
        consumer.start();
        return consumer;
    }
}