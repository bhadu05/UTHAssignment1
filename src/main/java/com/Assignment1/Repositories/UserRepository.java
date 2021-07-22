package com.Assignment1.Repositories;

import com.Assignment1.Models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends CrudRepository<User,Long > {

    public User findByUserName(String userName);

    public User findByEmailID(String emailID);

    public User findByMobileNumber(String mobileNumber);

    public User findByUserID(Long userID);

}
