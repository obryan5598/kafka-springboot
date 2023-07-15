package com.example.kafka.springboot.config;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;


/**
 * Following Configuration class is used to create a MyProduer instance.
 * MyProducer has specific credentials, which will be used to write on both
 * integers and date topics.
 * Credentials are referenced via spring.kafka.producer.properties.sasl.jaas.config.myProducer property
 */
@Configuration
public class MyProducerFactoryConfig {

    @Value("${spring.kafka.bootstrapServers}")
    String bootstrapServers;

    @Value("springboot-producer")
    String clientId;

    @Bean
    public ProducerFactory<String, String> myProducerFactory() {
        Map<String, Object> producerProps = new HashMap<>();
        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        producerProps.put(CommonClientConfigs.CLIENT_ID_CONFIG, clientId);
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return new DefaultKafkaProducerFactory<>(producerProps);
    }

    @Bean
    public KafkaTemplate<String, String> createKafkaTemplate() {
        return new KafkaTemplate<>(myProducerFactory());
    }
}
