package com.Assignment1.Controller;

import com.Assignment1.Models.Transaction;
import com.Assignment1.ParamCheck.ParamCheck;
import com.Assignment1.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;


    //API to transfer money from one wallet to another wallet.
    @PostMapping("/transaction")
    public ResponseEntity<Transaction> doTransaction(String payerNumber, String payeeNumber, Double amount)
    {
        ParamCheck.checkMobileNumber(payerNumber);
        ParamCheck.checkMobileNumber(payeeNumber);
        ParamCheck.checkBalance(amount);
        return new ResponseEntity<Transaction>(transactionService.doTransaction(payerNumber, payeeNumber, amount), HttpStatus.OK);


    }

    //Transaction summery API.
    @GetMapping(value = "/transaction",params = "userID")
    public ResponseEntity<Page<Transaction>> getTransactionSummary(@RequestParam Long userID)
    {
        ParamCheck.checkUserID(userID);
            return new ResponseEntity<Page<Transaction>>(transactionService.getTransactionSummary(userID),HttpStatus.OK);

    }

    //Transaction status.
    @GetMapping(value = "/transaction",params = "transactionID")
    public ResponseEntity<Transaction> getTransactionStatus(@RequestParam String transactionID)
    {
        ParamCheck.checkTransactionID(transactionID);
            return new ResponseEntity<Transaction>(transactionService.getTransactionStatus(transactionID),HttpStatus.OK);

    }
}
