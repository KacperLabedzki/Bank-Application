package com.bankapplication.bank.controller;

import com.bankapplication.bank.model.Transfer;
import com.bankapplication.bank.response.TransferStatusResponse;
import com.bankapplication.bank.service.TransferService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/bankapi/")
public class TransferController {
    private TransferService transferService;
    private final String ENDPOINT = "/transfer";

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping(ENDPOINT)
    public ResponseEntity<TransferStatusResponse> sendTransfer(@RequestBody Transfer transfer) {
        try {
            return new ResponseEntity<TransferStatusResponse>(transferService.sendTransfer(transfer), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(ENDPOINT)
    public ResponseEntity getAllTransfers(){
        return new ResponseEntity(transferService.getAllTransfers(),HttpStatus.OK);
    }
}
