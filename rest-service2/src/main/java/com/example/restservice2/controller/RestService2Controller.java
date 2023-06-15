package com.example.restservice2.controller;

import com.example.restservice2.model.OutstandingRID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/service2")
public class RestService2Controller {

    private static final Logger log = LoggerFactory.getLogger(RestService2Controller.class);

    @GetMapping(value="/{i}")
    public ResponseEntity<String> getUserDetails(@PathVariable String i) throws InterruptedException {

        Thread.sleep(50);
        log.info("Rest Service 2 invoked for variable : {}", i);
        return ResponseEntity.status(HttpStatus.OK).body("user details from rest service 2");
       // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No user details found");
    }

    @GetMapping(value = "/mono/{i}")
    public Mono<ResponseEntity<OutstandingRID>> getUserDetailsWithMono(@PathVariable String i) throws InterruptedException {
        log.info("Rest Service 2 invoked for variable : {}", i);
        //return Mono.just(ResponseEntity.status(HttpStatus.OK).body("user details from rest service 2")).delayElement(Duration.ofMillis(50));
        return Mono.just(ResponseEntity.status(HttpStatus.OK).body(OutstandingRID.builder().outstadingRID("abc").parentOutstadingRID("xyz").build())).delayElement(Duration.ofMillis(50));
         //return Mono.just(ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body("No user details found"));
    }

    @GetMapping(value = "/mono/error/{i}")
    public Mono<ResponseEntity<OutstandingRID>> getErrorDetailsWithMono(@PathVariable String i) throws InterruptedException {
        log.info("Rest Service 2 invoked for variable : {}", i);
        return Mono.just(ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(OutstandingRID.builder().error("No user details found").build()));
    }
}
