package com.ironhack.helloworldservice.controller.impl;

import com.ironhack.helloworldservice.client.AllMethodsClient;
import com.ironhack.helloworldservice.client.HelloClient;
import com.ironhack.helloworldservice.client.WorldClient;
import com.ironhack.helloworldservice.controller.dto.User;
import com.ironhack.helloworldservice.controller.dto.UserNameDTO;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@RestController
public class HelloWorldController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private HelloClient helloClient;

    @Autowired
    private AllMethodsClient allMethodsClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WorldClient worldClient;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }


    @GetMapping("/hello-world/rest")
    @ResponseStatus(HttpStatus.OK)
    public String helloWorldRest() {
        logger.info("Init helloWorld method using rest template");

        String helloUrl = "http://hello-service" + "/hello";
        logger.info(helloUrl);
        String worldUrl = "http://world-service" + "/world";
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

    @GetMapping("/test-rest")
    private String testAllMethods() {

        HttpClient client = HttpClients.createDefault();
        RestTemplate restTemplate = new RestTemplate();
        // Updates request factory configuration to allow PATCH requests
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(client));

        logger.info("Init testAllMethods method using rest template");
        String baseUrl = discoveryClient.getInstances("all-method-rest-service").get(0).getUri().toString();
        User user = restTemplate.getForObject(baseUrl + "/user/1", User.class);
        logger.debug("GET User");
        logger.debug(user.toString());
        user = restTemplate.postForObject(baseUrl + "/user", user, User.class);
        logger.debug("POST User");
        logger.debug(user.toString());
        user.setName("Juanito");
        logger.debug("PUT User");
        restTemplate.put(baseUrl + "/user/1", user, new HashMap<>());
        UserNameDTO userNameDTO = new UserNameDTO("Pepe");
        logger.debug("PATCH User");
        restTemplate.exchange(baseUrl + "/user/1", HttpMethod.PATCH,  new HttpEntity<>(userNameDTO), Void.class);
        logger.debug("DELETE User");
        restTemplate.delete(baseUrl + "/user/1");

        return "Everything is fine :D";
    }

    @GetMapping("/test-feign")
    private String testAllMethodsFeign() {
        logger.info("Init testAllMethods method using feign");

        User user = allMethodsClient.getUser(1L);
        logger.debug("GET User");
        logger.debug(user.toString());


        user = allMethodsClient.postUser(user);
        logger.debug("POST User");
        logger.debug(user.toString());


        user.setName("Juanito");
        logger.debug("PUT User");
        allMethodsClient.putUser(user, 1L);

        UserNameDTO userNameDTO = new UserNameDTO("Pepe");
        logger.debug("PATCH User");
        allMethodsClient.patchUser(userNameDTO, 1L);

        logger.debug("DELETE User");
        allMethodsClient.deleteUser(1L);

        return "Everything is fine :D";
    }

}
