package com.example.demo.result;

public class LayuiResult<T> {
    private int code=0;
    private String msg="ok";
    private int count;
    private T data;
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    private LayuiResult() {
        super();
    }
    public LayuiResult(T data, int count) {
        super();
        this.data = data;
        this.count = count;
    }
    public static <T> LayuiResult ok(T data, int count){
        return new LayuiResult<>(data,count);
    }

}


