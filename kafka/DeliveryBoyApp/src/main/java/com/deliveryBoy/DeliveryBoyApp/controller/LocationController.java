package com.deliveryBoy.DeliveryBoyApp.controller;

import com.deliveryBoy.DeliveryBoyApp.service.KafkaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/location")
public class LocationController {
    @Autowired
    private KafkaService kafkaService;

    private Logger logger = LoggerFactory.getLogger(LocationController.class);
//    this endpoint will used to create a location update
    @PostMapping
    public ResponseEntity<?> updateLocation() {
      try {
          for (int i = 0; i <1000 ; i++) {


              this.kafkaService.updateLocation("( " + Math.round(Math.random() * 100) + " , " + Math.round(Math.random() * 100) + ")");
              logger.info("Location successfully updated");
          }
        } catch (Exception e){
          logger.error("Error while updating location", e);
          e.printStackTrace();
      }
        return new ResponseEntity<>(Map.of("message", "Location Updated"), HttpStatus.OK);
    }
}
