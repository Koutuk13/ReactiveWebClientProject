package com.example.restservice2.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.MediaType;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class HealthcheckController {

    @GetMapping(value = "/healthcheck", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HealthCheckResponse> healthcheck(@RequestParam(value="format") String format) {
        ResponseEntity<HealthCheckResponse> healthCheckResponseResponseEntity = null;
        HealthCheckResponse response;
        if(null != format && "short".equals(format)){
            response = new HealthCheckResponse();
            response.setStatus("OK");
            healthCheckResponseResponseEntity = ResponseEntity.status(HttpStatus.OK).body(response);
        }else if(null != format && "full".equals(format)){
            response = new HealthCheckResponse();
            response.setStatus("OK");
            String dateTime = ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT);
            response.setDateTime(dateTime);
            healthCheckResponseResponseEntity = ResponseEntity.status(HttpStatus.OK).body(response);
        }else{
            healthCheckResponseResponseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return healthCheckResponseResponseEntity;
    }

   /* @PutMapping(value = "/healthcheck")
    public void healthcheckPut() {
        return;
    }

    @PostMapping(value = "/healthcheck")
    public void healthcheckPost() {
        return;
    }


    @DeleteMapping(value = "/healthcheck")
    public void healthcheckDelete() {
        return;
    }*/

}
    class HealthCheckResponse{

        public String status;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public String dateTime;

        public HealthCheckResponse() {
        }

        public HealthCheckResponse(String status, String dateTime) {
            this.status = status;
            this.dateTime = dateTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDateTime() {
            return dateTime;
        }

        public void setDateTime(String dateTime) {
            this.dateTime = dateTime;
        }
    }


