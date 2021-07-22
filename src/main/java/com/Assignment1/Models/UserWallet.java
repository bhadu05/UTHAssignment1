package com.Assignment1.Models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "wallet")
public class UserWallet {

    @Id
    @Column(unique = true)
    String mobileNumber;
    @Column
    Double balance;

    @CreationTimestamp
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    public UserWallet() {
        this.balance = 0.0;
    }
    public UserWallet(String mobileNumber)
    {
        this.mobileNumber=mobileNumber;
        this.balance=1000.0;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double amount) {
        this.balance = amount;
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

    @Override
    public boolean equals(Object obj) {
        if(this==obj)
            return true;
        if(obj==null || this.getClass()!=obj.getClass())
            return false;
        if(mobileNumber.equals(((UserWallet)obj).mobileNumber) && balance.equals(((UserWallet)obj).balance))
            return true;
        return false;
    }
}
