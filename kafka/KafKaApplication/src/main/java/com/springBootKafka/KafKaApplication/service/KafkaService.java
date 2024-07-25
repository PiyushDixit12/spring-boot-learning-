package com.springBootKafka.KafKaApplication.service;

import com.springBootKafka.KafKaApplication.config.KafkaConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class KafkaService {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    Queue<String> queue = new LinkedBlockingQueue<>();

//     this method will produce a message
    public String sendMessage(String message) {
        kafkaTemplate.send(KafkaConstant.TOPIC_NAME,"PIYUSH" ,message);

        System.out.println("Successfully send : "+ message);
        return "Successfully send : "+ message;
    }


//    this method will get produce message
    @KafkaListener(topics = KafkaConstant.TOPIC_NAME,groupId = KafkaConstant.TOPIC_GROUP_ID)
    public void ConsumeMessage(String message) {
        System.out.println("receive message : " + message);
        queue.add(message);
    }

    public Queue<String> getMessage() {
        return queue;
    }
}
