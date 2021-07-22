package com.Assignment1.Controller;

import com.Assignment1.ParamCheck.ParamCheck;
import com.Assignment1.Service.UserService;
import com.Assignment1.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService UserService;

    //API which will create a user in the table.
    @PostMapping("/user")
    public User createUser(@RequestBody User user)
    {


            ParamCheck.checkUser(user);
            return UserService.createUser(user);

/*            System.out.println(user.getAddress1());


        else
         throw new InvalidParameterException("Invalid Parameters in user");*/
    }


    //API which will read data from database.
    @GetMapping("/user")
    public ResponseEntity<User> findUser(@RequestParam Long userID)
    {
        ParamCheck.checkUserID(userID);
        return new ResponseEntity<User>(UserService.findUser(userID),HttpStatus.OK);

    }


    //Update API
    @PutMapping("/user")
    public ResponseEntity<User> updateUser(@RequestParam Long userID,@RequestBody User user)
    {
        ParamCheck.checkUserID(userID);
        ParamCheck.checkUser(user);
        return new ResponseEntity<User>(UserService.updateUser(userID,user),HttpStatus.OK);

    }


    //API which will delete data in the user table.
    @DeleteMapping("/user")
    public ResponseEntity<String> deleteUser(@RequestParam Long userID)
    {

        ParamCheck.checkUserID(userID);
        return new ResponseEntity<String >(UserService.deleteUser(userID),HttpStatus.OK);

    }


}
