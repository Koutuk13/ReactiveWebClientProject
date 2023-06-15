package com.example.restservice1.service;

import com.example.restservice1.OutstandingRID;
import com.example.restservice1.exception.ClientDataException;
import com.example.restservice1.exception.OutstandingRIDException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    WebClient.Builder builder;

    public Mono<String> fetchUserDetails(int i){

        return builder.build()
                .get()
                .uri("http://localhost:8085/service2/i")
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> handle4xxClientError(clientResponse))
                .bodyToMono(String.class)
                .onErrorContinue(ClientDataException.class,(e,item)->log.info("There was exception in the service2 call"));
    }

    public OutstandingRID fetchUserDetailsWithBlock(int i){

        return builder.build()
                .get()
                .uri("http://localhost:8085/service2/mono/error/"+i)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> handle4xxClientError(clientResponse))
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> handle5xxServerError(clientResponse))
                .bodyToMono(OutstandingRID.class)
                //.onErrorContinue(ClientDataException.class,(e,item)->log.info("There was exception in the service2 call"))
                .onErrorContinue((e,item)->{
                    log.info("There was exception in the service2 call");
                    log.info("item is :: {}", item);

                })
                .block();
    }

    private Mono<? extends Throwable> handle4xxClientError(ClientResponse clientResponse) {
        Mono<String> errorMessage = clientResponse.bodyToMono(String.class);
        return errorMessage.map((message)->{
            log.error("Handle4xxError::Error code and error message from API is :: {} {}", clientResponse.rawStatusCode(), message);
            throw new ClientDataException(message);
        });
    }

    private Mono<? extends Throwable> handle5xxServerError(ClientResponse clientResponse) {
        Mono<String> errorMessage = clientResponse.bodyToMono(String.class);
        return errorMessage.map((message)->{
            log.error("Handle5xxError::Error code and error message from API is :: {} {}", clientResponse.rawStatusCode(), message);
            throw new OutstandingRIDException(message);
        });
    }
}
