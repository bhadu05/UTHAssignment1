package com.Assignment1.Repositories;

import com.Assignment1.Models.UserWallet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


public interface UserWalletRepository extends CrudRepository<UserWallet,String > {

    @Query(value = "select * from wallet  where mobile_number=:mobileNumber",nativeQuery = true)
    public UserWallet findByID(@Param("mobileNumber")String mobileNumber);

}
