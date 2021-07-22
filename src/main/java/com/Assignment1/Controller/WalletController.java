package com.Assignment1.Controller;

import com.Assignment1.Models.UserWallet;
import com.Assignment1.ParamCheck.ParamCheck;
import com.Assignment1.Service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletController {

    @Autowired
    WalletService walletService;


    //API which will create a wallet for a user.
    @PostMapping("/wallet")
    public ResponseEntity<UserWallet> createWallet(@RequestParam String mobileNumber)
    {
        ParamCheck.checkMobileNumber(mobileNumber);
            return new ResponseEntity<UserWallet>(walletService.createWallet(mobileNumber), HttpStatus.OK);


    }
}
