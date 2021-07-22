package com.Assignment1.Service;

import com.Assignment1.ExceptionHandler.CustomException;
import com.Assignment1.Models.Transaction;
import com.Assignment1.Models.User;
import com.Assignment1.Models.UserWallet;
import com.Assignment1.Repositories.PaginationRepository;
import com.Assignment1.Repositories.TransactionRepository;
import com.Assignment1.Repositories.UserRepository;
import com.Assignment1.Repositories.UserWalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TransactionService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserWalletRepository userWalletRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    PaginationRepository paginationRepository;

    @Transactional
    public Transaction doTransaction(String payerNumber, String payeeNumber, Double amount) {

        //Check for validations.

        //Add locks for sql.

        if(userWalletRepository.findByID(payerNumber)!=null)
        {
            if(userWalletRepository.findByID(payeeNumber)!=null)
            {
                if(userWalletRepository.findByID(payerNumber).getBalance()>=amount)
                {
                    //Create a transaction.
                    Transaction transaction = transactionRepository.save(new Transaction(payerNumber, payeeNumber, amount, "Success"));

                    //Find payerWallet and payeeWallet.
                    UserWallet payerWallet = userWalletRepository.findByID(payerNumber);
                    UserWallet payeeWallet = userWalletRepository.findByID(payeeNumber);

                    //Adjust the amount after transaction for payer and payee.
                    payerWallet.setBalance(payerWallet.getBalance()-amount);
                    payeeWallet.setBalance(payeeWallet.getBalance()+amount);

                    //Save remaining balance of payer in transaction.
                    transaction.setPayerRemainingBalance(payerWallet.getBalance());

                    //Save remaining balance of payee in transaction.
                    transaction.setPayeeRemainingBalance(payeeWallet.getBalance());


                    //Save new details in payerWallet and payeeWallet.
                    userWalletRepository.save(payerWallet);
                    userWalletRepository.save(payeeWallet);
                    return transaction;
                }
                else
                    throw new CustomException("601","Payer balance is insufficient.");
            }
            else
                throw new CustomException("601","Payee doesn't have a wallet.");
        }
        else
            throw new CustomException("601","Payer doesn't have a wallet.");

    }


    public Page<Transaction> getTransactionSummary(Long userID)
    {

        User user = userRepository.findByUserID(userID);

        //Check for validations.
        if(user!=null && userWalletRepository.findByID(user.getMobileNumber())!=null)
        {
            Pageable firstPageWithOneElement = PageRequest.of(0, 15, Sort.by("Date").descending());
            Page<Transaction> transactions = paginationRepository.findByUserID(firstPageWithOneElement, user.getMobileNumber());
            return transactions;
        }
        else
            throw new CustomException("601","Either user not exist or user does not have a wallet");
    }

    public Transaction getTransactionStatus(String transactionID) {
        if(transactionRepository.findByID(transactionID)!=null)
            return transactionRepository.findByID(transactionID);
        else
            throw new CustomException("601","TransactionID does not exist");
    }
}
