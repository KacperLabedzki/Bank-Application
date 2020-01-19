package com.bankapplication.bank.response;

import java.util.Date;

public class ErrorMessageResponse {
    private String status;
    private int code;
    private Date timestamp;
    private String message;

    public ErrorMessageResponse(Date timestamp, String message, String status, int code) {
        this.timestamp = timestamp;
        this.message = message;
        this.status = status;
        this.code = code;
    }

    public ErrorMessageResponse(String message) {
        this.message = message;
    }

    public ErrorMessageResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
