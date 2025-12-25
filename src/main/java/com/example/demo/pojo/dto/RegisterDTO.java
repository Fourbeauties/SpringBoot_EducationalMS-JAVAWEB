package com.example.demo.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data

public class RegisterDTO implements Serializable {

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;


    private String password2;

}
