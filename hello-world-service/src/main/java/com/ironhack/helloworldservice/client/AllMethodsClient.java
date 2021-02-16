package com.ironhack.helloworldservice.client;

import com.ironhack.helloworldservice.controller.dto.User;
import com.ironhack.helloworldservice.controller.dto.UserNameDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "all-method-rest-service")
public interface AllMethodsClient {
    @GetMapping("/user/{id}")
    User getUser(@PathVariable Long id);

    @PostMapping("/user")
    User postUser(@RequestBody User user);

    @PutMapping("/user/{id}")
    void putUser(@RequestBody User user, @PathVariable Long id);

    @PatchMapping("/user/{id}")
    void patchUser(@RequestBody UserNameDTO userNameDTO, @PathVariable Long id);

    @DeleteMapping("/user/{id}")
    void deleteUser(@PathVariable Long id);
}
