package com.Assignment1.Repositories;

import com.Assignment1.Models.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PaginationRepository extends PagingAndSortingRepository<Transaction,String> {

    @Query(value = "select * from Transaction t where t.payer_number = :payerNumber or t.payee_number = :payerNumber",countQuery = "SELECT count(*) FROM Transaction",nativeQuery = true)
    Page<Transaction> findByUserID(Pageable pageable, @Param("payerNumber") String payerNumber);
}
