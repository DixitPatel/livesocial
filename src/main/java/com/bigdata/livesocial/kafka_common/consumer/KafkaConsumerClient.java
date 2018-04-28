package com.bigdata.livesocial.kafka_common.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;

/**
 * @author Dixit Patel
 */
public class KafkaConsumerClient {

    private static Logger LOG = LoggerFactory.getLogger(KafkaConsumerClient.class);

    private SimpMessagingTemplate template;
    private  ExecutorService executorService;
    private  List<String> topics;
    private Properties configProps;


    public KafkaConsumerClient(Properties configProps,ExecutorService executorService,List<String> topics,SimpMessagingTemplate template) {
        this.executorService = executorService;
        this.topics = topics;
        this.template=template;
        this.configProps=configProps;
    }

    public void start() {
        LOG.info("Starting consumer");
        executorService.submit(new KafkaConsumerTask(topics,configProps,template));
    }


    static public class KafkaConsumerTask implements Runnable{

        private KafkaConsumer<String, String> consumer;
        private static Logger LOG = LoggerFactory.getLogger(KafkaConsumerClient.class);
        private SimpMessagingTemplate template;


        public KafkaConsumerTask (List<String> topics, Properties configProps,SimpMessagingTemplate template){
            this.template=template;
            this.consumer = new KafkaConsumer<>(configProps);
            this.consumer.subscribe(topics);
        }

        @Override
        public void run() {
            try {
                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(100);
                    for (ConsumerRecord<String, String> record : records)
                    {
                        LOG.debug("topic = %s, partition = %s, offset = %d,key = %s, value = %s\n",
                        record.topic(), record.partition(), record.offset(),
                                record.key(), record.value());
                        //TODO use generic string to send response
                        LOG.info("sendng"+ record);
                        this.template.convertAndSend("/topic/greetings",record);
                    }
                    consumer.commitAsync();
                }
            } finally {
                consumer.close();
            }
        }
    }
}



