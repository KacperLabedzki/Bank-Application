package com.bankapplication.bank.response;

import com.bankapplication.bank.model.StatusCode;
import com.bankapplication.bank.model.TransferStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessageResponse {
    private StatusCode statusName;
    private int statusCode;
    private Date timestamp;
    private String message;

    public ErrorMessageResponse(String message) {
        this.message = message;
    }
}
