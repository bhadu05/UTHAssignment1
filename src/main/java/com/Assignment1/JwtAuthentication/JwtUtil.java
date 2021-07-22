package com.Assignment1.JwtAuthentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


//This class will have logic to generate and validate token.
@Service
public class JwtUtil {

    private String secretKey = "UTHAssignment";

    //We can extract any field that we have set during token creation in same way.

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    //These two method are used to extract any field from token.
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    //Check if token is expired or not.
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    //Generate the token from userName.
    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        return generateTokenHelper(claims, userName);
    }

    //Helper method to generate the token.
    private String generateTokenHelper(Map<String, Object> claims, String userName) {

        //Build token using Jwts library
        //Set Subject as userName.
        //Set IssuedAt as current date and time.
        //Set Expiration 100*60*60*10 is one hour.
        //Sign token with signWith.

        return Jwts.builder().setClaims(claims).setSubject(userName).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 100 * 60 * 60 * 10))//1 hour.
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    //Method to validate token.
    //To validate token this method takes two arguments first is token and second is userDetails.
    //It will put two checks userName is same and token is not expired.
    //First extractUsername from token.
    //Then extract ExpiryDate from token.
    //Then check is userName extracted from toke is same as userDetail.getUsername()
    //And token is not expired.
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
