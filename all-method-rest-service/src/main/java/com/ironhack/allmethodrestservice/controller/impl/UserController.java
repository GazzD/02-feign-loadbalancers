package com.ironhack.allmethodrestservice.controller.impl;

import com.ironhack.allmethodrestservice.controller.dto.UserNameDTO;
import com.ironhack.allmethodrestservice.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getUser(@PathVariable Long id) {
        logger.info("INIT getUser");
        return new User(id, "Víctor");
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public User postUser(@RequestBody User user) {
        logger.info("INIT postUser");
        user.setId(2L);
        return user;
    }

    @PutMapping("/user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putUser(@RequestBody User user, @PathVariable Long id) {
        logger.info("INIT putUser");
        user.setId(id);
    }

    @PatchMapping("/user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patchUser(@RequestBody UserNameDTO userNameDTO, @PathVariable Long id) {
        logger.info("INIT patchUser");
    }

    @DeleteMapping("/user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        logger.info("INIT deleteUser");
    }

}
