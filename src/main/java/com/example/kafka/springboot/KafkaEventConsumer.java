package com.example.kafka.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


/**
 * KafkaEventConsumer is configured automatically by Spring, and references
 * two integerTopic and dateTopic which are referenced by a property in application.properties
 *
 * Both listeners share the same groupId prefix.
 */
@Component
public class KafkaEventConsumer {

    private final Logger LOG = LoggerFactory.getLogger(KafkaEventConsumer.class);
    private final String integerTopic;
    private final String dateTopic;

    @Autowired
    public KafkaEventConsumer(@Value("${kafka.integerTopic}") String integerTopic,
                              @Value("${kafka.dateTopic}") String dateTopic) {
        this.integerTopic = integerTopic;
        this.dateTopic = dateTopic;
    }

    @KafkaListener(topics = "${kafka.integerTopic}", groupId = "springboot-integer-groupId")
    public void consumeFromIntegerTopic(String message) {
        LOG.info("Consuming from: {}", integerTopic);
        LOG.info("value: {}", message);
    }


    @KafkaListener(topics = "${kafka.dateTopic}", groupId = "springboot-date-groupId")
    public void consumeFromDateTopic(String message) {
        LOG.info("Consuming from {}", dateTopic);
        LOG.info("value: {}", message);
    }

}
