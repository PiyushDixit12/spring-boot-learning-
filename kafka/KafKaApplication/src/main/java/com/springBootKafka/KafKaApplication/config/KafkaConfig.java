package com.springBootKafka.KafKaApplication.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

//    creating a new topic where our messages will store
    @Bean
    public NewTopic KafkaConfig() {
        return TopicBuilder.name(KafkaConstant.TOPIC_NAME)
                .partitions(1)
                .replicas(1)
                .build();
    }
}
