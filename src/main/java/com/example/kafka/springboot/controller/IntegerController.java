package com.example.kafka.springboot.controller;

import com.example.kafka.springboot.MyProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.ApplicationScope;


/**
 * Simple Controller which exposes two endpoints referencing last integers produced
 */

@ApplicationScope
@RestController
@RequestMapping("/kafka/integer")
public class IntegerController {

    private final Logger LOG = LoggerFactory.getLogger(IntegerController.class);

    @Autowired
    private final MyProducer myProducer;

    public IntegerController(MyProducer myProducer) {
        this.myProducer = myProducer;
    }


    @GetMapping("/getLastOddInteger")
    public ResponseEntity getLastOddInteger() {
        String message = "Last odd integer produced: " + myProducer.getLastOddInteger() + System.lineSeparator();
        LOG.info(message);
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }

    @GetMapping("/getLastEvenInteger")
    public ResponseEntity getLastEvenInteger() {
        String message = "Last even integer produced: " + myProducer.getLastEvenInteger() + System.lineSeparator();
        LOG.info(message);
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }

}
