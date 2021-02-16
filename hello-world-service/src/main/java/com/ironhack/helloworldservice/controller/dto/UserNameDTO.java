package com.ironhack.helloworldservice.controller.dto;

public class UserNameDTO {
    private String name;

    public UserNameDTO() {
    }

    public UserNameDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
