package com.springBootKafka.KafKaApplication.config;

public interface KafkaConstant {
   String TOPIC_GROUP_ID = "${spring.kafka.consumer.group-id}";
    String  TOPIC_NAME = "chat_topics";
}

