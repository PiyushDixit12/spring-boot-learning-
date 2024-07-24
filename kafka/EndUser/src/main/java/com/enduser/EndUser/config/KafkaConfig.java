package com.enduser.EndUser.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class KafkaConfig {

    //    it is used to fetch message of given topic and related group you provide
    @KafkaListener(topics = AppConstant.LOCATION_UPDATE_TOPIC, groupId = AppConstant.GROUP_ID)
    public void updatedLocation(String value) {
        System.out.println(value);
    }
}
