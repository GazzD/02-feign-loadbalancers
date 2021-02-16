package com.ironhack.worldservice.controller.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorldController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/world")
    public String world() {
        return "World running in port: " + port;
    }

}
