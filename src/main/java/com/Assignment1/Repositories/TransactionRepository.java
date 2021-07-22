package com.Assignment1.Repositories;

import com.Assignment1.Models.Transaction;
import com.Assignment1.Models.UserWallet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


public interface TransactionRepository extends CrudRepository<Transaction,String >  {

    @Query(value = "select * from Transaction t where t.transactionid=?",nativeQuery = true)
    public Transaction findByID(String transactionID);


}
