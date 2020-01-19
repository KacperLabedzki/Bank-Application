package com.bankapplication.bank.response;


import com.bankapplication.bank.model.TransferStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferStatusResponse {
    private TransferStatus transferStatus;
    private int statusCode;
    private Date timestamp;
}
