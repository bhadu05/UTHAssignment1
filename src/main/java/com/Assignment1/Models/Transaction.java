package com.Assignment1.Models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table
public class Transaction {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
   private String transactionID;
   private String payerNumber;
   private String payeeNumber;
   private Double amount;
   private String status;
   private String date;
   private Double payerRemainingBalance;
   private Double payeeRemainingBalance;
    @CreationTimestamp
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;


    public Transaction( String payerNumber, String payeeNumber, Double amount, String status) {
        this.payerNumber = payerNumber;
        this.payeeNumber = payeeNumber;
        this.amount = amount;
        this.status = status;
        Date date = new Date();
        String strDateFormat = "dd-MM-yyyy HH:mm:ss";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        this.date= dateFormat.format(date);
    }
    public Transaction()
    {

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getPayerNumber() {
        return payerNumber;
    }

    public void setPayerNumber(String payerNumber) {
        this.payerNumber = payerNumber;
    }

    public String getPayeeNumber() {
        return payeeNumber;
    }

    public void setPayeeNumber(String payeeNumber) {
        this.payeeNumber = payeeNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public Double getPayerRemainingBalance() {
        return payerRemainingBalance;
    }

    public void setPayerRemainingBalance(Double payeeRemainingBalance) {
        this.payerRemainingBalance = payeeRemainingBalance;
    }

    public Double getPayeeRemainingBalance() {
        return payeeRemainingBalance;
    }

    public void setPayeeRemainingBalance(Double payeeRemainingBalance) {
        this.payeeRemainingBalance = payeeRemainingBalance;
    }

    //In overriding transactionID and Date does not compared.
    @Override
    public boolean equals(Object t) {
        if(this==t)
            return true;
        if(t==null || this.getClass()!=t.getClass())
            return false;
        if(amount.equals(((Transaction)t).amount) && payeeNumber.equals(((Transaction)t).payeeNumber) && payerNumber.equals(((Transaction)t).payerNumber) && status.equals(((Transaction)t).status) && date.equals(((Transaction) t).date))
            return true;
        return false;
    }

}
