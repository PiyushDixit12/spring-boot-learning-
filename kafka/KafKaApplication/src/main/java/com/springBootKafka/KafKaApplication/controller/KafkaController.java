package com.springBootKafka.KafKaApplication.controller;

import com.springBootKafka.KafKaApplication.service.KafkaService;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Queue;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    private KafkaService kafkaService;

    @PostMapping("/send")
    public String send(@RequestBody String message) {
        return  kafkaService.sendMessage(message);
    }



    @GetMapping("/receive")
    public Queue<String> receive() {
         return  kafkaService.getMessage();
    }

}
