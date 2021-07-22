package com.Assignment1.Controller;

import com.Assignment1.JwtAuthentication.JwtUtil;
import com.Assignment1.Models.AuthorizationBody;
import com.Assignment1.ParamCheck.ParamCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtTokenGenerateController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/token")
    public ResponseEntity<String> generateToken(@RequestBody AuthorizationBody authorizationBody) throws Exception {
       
        ParamCheck.checkAuthorizationBody(authorizationBody);


            //Authenticate user.
        if(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authorizationBody.getUserName(), authorizationBody.getPassword())).isAuthenticated())
        //If user is authenticated.
        //Generate token from username.
        return new ResponseEntity<String>(jwtUtil.generateToken(authorizationBody.getUserName()), HttpStatus.OK);

        else
            return new ResponseEntity<String>("User is unauthorized",HttpStatus.UNAUTHORIZED);


    }
}
