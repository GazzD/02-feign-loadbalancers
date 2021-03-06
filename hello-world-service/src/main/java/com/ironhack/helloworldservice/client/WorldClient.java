package com.ironhack.helloworldservice.client;

import com.ironhack.helloworldservice.configuration.LoadBalancerConfiguration;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("world-service")
@LoadBalancerClient(name = "world-service", configuration = LoadBalancerConfiguration.class)
public interface WorldClient {
    @GetMapping("/world")
    String world();
}
