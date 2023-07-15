package com.example.kafka.springboot;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * MyProducer has been created by MyProducerFactoryConfig class.
 * It sends both integers and date events in a scheduled fashion, where fixed
 * rates have been referenced in integer.scheduled.rate and date.scheduled.rate
 * values in application.properties.
 */

@Component
@EnableScheduling
public class MyProducer {

    private final Logger LOG = LoggerFactory.getLogger(MyProducer.class);
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String integerTopic;
    private final String dateTopic;
    private final Random random = new Random();
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS-Z");
    private Integer lastOddInteger;
    private Integer lastEvenInteger;


    @Autowired
    public MyProducer(KafkaTemplate<String, String> kafkaTemplate,
                      // Topic name referenced inside application.properties
                      @Value("${kafka.integerTopic}") String integerTopic,

                      // Topic name directly referenced by EAP system property
                      @Value("${kafka.dateTopic}") String dateTopic) {

        this.kafkaTemplate = kafkaTemplate;
        this.integerTopic = integerTopic;
        this.dateTopic = dateTopic;
    }

    @Scheduled(fixedRateString = "${integer.scheduled.rate}")
    public void sendToIntegerTopic() {
        Integer randomValue = random.nextInt(100);
        if (randomValue % 2 != 0) {
            lastOddInteger = randomValue;
        } else {
            lastEvenInteger = randomValue;
        }
        LOG.info("Sending to integer topic value: " + randomValue);
        kafkaTemplate.send(integerTopic, Integer.toString(randomValue));
    }


    @Scheduled(fixedRateString = "${date.scheduled.rate}")
    public void sendToDateTopic() {
        String currentDate = formatter.format(new Date());
        LOG.info("Sending to date topic message: " + currentDate);
        kafkaTemplate.send(dateTopic, currentDate);
    }

    public Integer getLastOddInteger() {
        return this.lastOddInteger;
    }

    public Integer getLastEvenInteger() {
        return this.lastEvenInteger;
    }
}
