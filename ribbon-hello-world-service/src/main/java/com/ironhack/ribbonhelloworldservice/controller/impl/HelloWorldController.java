package com.ironhack.ribbonhelloworldservice.controller.impl;

import com.ironhack.ribbonhelloworldservice.client.HelloClient;
import com.ironhack.ribbonhelloworldservice.client.WorldClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloWorldController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private HelloClient helloClient;

    @Autowired
    private WorldClient worldClient;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/hello-world/rest")
    @ResponseStatus(HttpStatus.OK)
    public String helloWorldRest() {
        logger.info("Init helloWorld method using rest template");
        RestTemplate restTemplate = new RestTemplate();
        String helloUrl = discoveryClient.getInstances("hello-service").get(0).getUri().toString() + "/hello";
        logger.info(helloUrl);
        String worldUrl = discoveryClient.getInstances("world-service").get(0).getUri().toString() + "/world";
        logger.info(worldUrl);
        String hello = restTemplate.getForObject(helloUrl, String.class);
        String world = restTemplate.getForObject(worldUrl, String.class);

        return hello + world;

    }

    @GetMapping("/hello-world/feign")
    @ResponseStatus(HttpStatus.OK)
    public String helloWorldFeign() {
        logger.info("Init helloWorld method using feign");
        String hello = helloClient.hello();
        String world = worldClient.world();

        return hello + world;
    }
}
