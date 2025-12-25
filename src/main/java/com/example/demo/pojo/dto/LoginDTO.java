package com.example.demo.pojo.dto;

import lombok.Data;

@Data
public class LoginDTO {

    private String username;

    private String password;

    private String randomCode;

    private boolean rememberMe;

}
