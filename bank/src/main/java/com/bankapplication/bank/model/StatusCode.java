package com.bankapplication.bank.model;

public enum StatusCode {
    BAD_REQUEST(400),OK(200),NOT_FOUND(404),INTERNAL_SERVER_ERROR(500);
    private int code;

    StatusCode(int code) {
        this.code = code;
    }

    public int getCode(){
        return code;
    }
}
