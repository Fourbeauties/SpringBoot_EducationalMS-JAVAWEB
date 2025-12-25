package com.example.demo.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ScoreUpdateDTO implements Serializable {

    private int id;

    private String score;

    private String result;

}
