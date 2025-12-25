package com.example.demo.pojo.dto;

import lombok.Data;

@Data

public class ChangePwdDTO {

    private String oldPassword;

    private String newPassword1;

    private String newPassword2;

}
