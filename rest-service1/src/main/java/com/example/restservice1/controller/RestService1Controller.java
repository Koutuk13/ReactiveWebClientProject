package com.example.restservice1.controller;

import com.example.restservice1.OutstandingRID;
import com.example.restservice1.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/service1")
public class RestService1Controller {

    private static final Logger log = LoggerFactory.getLogger(RestService1Controller.class);

    @Autowired
    UserService userService;

    @GetMapping
    public String getUserDetails() throws InterruptedException {
        //return "Hello";
        //userService.fetchUserDetails().subscribe(response-> log.info("Now the service2 returned the data:: {}", response));
        for(int i=0;i<1;i++) { //325339 //11281
            OutstandingRID response = userService.fetchUserDetailsWithBlock(i);
            if (response != null) {
                log.info("Now the service2 returned the data for :: {}, {}, {}",i, response.getOutstadingRID(),response.getParentOutstadingRID());
            } else {
                log.info("Response is null");
            }
        }
        log.info("This is called after the service2");
      //  Thread.sleep(6000);
        return "Service executed success";

    }

}
