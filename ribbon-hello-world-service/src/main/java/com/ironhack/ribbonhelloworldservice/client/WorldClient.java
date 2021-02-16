package com.ironhack.ribbonhelloworldservice.client;

import com.ironhack.ribbonhelloworldservice.configuration.RibbonConfiguration;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("world-service")
@RibbonClient(name = "world-service", configuration = RibbonConfiguration.class)
public interface WorldClient {
    @GetMapping("/world")
    String world();
}
