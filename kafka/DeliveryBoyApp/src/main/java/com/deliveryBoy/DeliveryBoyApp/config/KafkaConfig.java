package com.deliveryBoy.DeliveryBoyApp.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

//    It is Used to create a Topic
    @Bean
    public NewTopic newTopic() {
        return TopicBuilder
                .name(AppConstant.LOCATION_TOPIC_NAME)
//                .partitions()
//                .replicas()
                .build();
    }
}

