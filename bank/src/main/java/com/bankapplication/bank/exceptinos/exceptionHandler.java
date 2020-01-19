package com.bankapplication.bank.exceptinos;

import com.bankapplication.bank.model.StatusCode;
import com.bankapplication.bank.model.TransferStatus;
import com.bankapplication.bank.response.ErrorMessageResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class exceptionHandler extends ResponseEntityExceptionHandler {
    private final static String ERROR = "Error";

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request){
        String error = ex.getLocalizedMessage();
        if(error==null)error = ex.toString();
        ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse(error);
        return  new ResponseEntity<>(errorMessageResponse,new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<Object> handleBadRequestException(BadRequestException ex, WebRequest request){
        StatusCode statusCode = StatusCode.BAD_REQUEST;
        String error = ex.getLocalizedMessage();
        if(error == null)error = ex.toString();
        ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse(StatusCode.BAD_REQUEST,statusCode.getCode(), new Date(),error);
        return  new ResponseEntity<>(errorMessageResponse,new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {InsufficientAccountBalanceException.class})
    public ResponseEntity<Object> handleInsufficientAccountBalanceException(InsufficientAccountBalanceException ex, WebRequest request){
        StatusCode statusCode = StatusCode.BAD_REQUEST;
        String error = ex.getLocalizedMessage();
        if(error == null)error = ex.toString();
        ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse(StatusCode.BAD_REQUEST,statusCode.getCode(), new Date(),error);
        return  new ResponseEntity<>(errorMessageResponse,new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
