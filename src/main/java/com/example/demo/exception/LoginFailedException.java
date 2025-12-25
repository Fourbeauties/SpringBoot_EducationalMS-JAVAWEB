package com.example.demo.exception;

import com.example.demo.handler.BaseException;

/**
 * 登录失败
 */
public class LoginFailedException extends BaseException {
    public LoginFailedException(String msg){
        super(msg);
    }
}
