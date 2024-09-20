package com.bnb.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bnb.entity.AppUser;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtService {
     @Value("${jwt.algo.key}")
    public  String jwtkey;
     @Value("${jwt.issuer}")
    public String issuer;
     @Value("${jwt.time}")
    public long expiraytime;

     public Algorithm algorithm;


     @PostConstruct
     public  void postConstruct( )throws Exception
     {

         algorithm = Algorithm.HMAC256(jwtkey);

     }


     public String generateToken (AppUser appUser){

         System.out.println(new Date(System.currentTimeMillis()+ expiraytime));

         String token = JWT.create().
                 withClaim("Username", appUser.getUsername())
                 .withExpiresAt(new Date(System.currentTimeMillis()+ expiraytime))
                 .withIssuer(issuer)
                 .sign(algorithm);


             return  token;

     }
   //  ( )
     public String getUserName ( String token ){
         DecodedJWT decodedJWT = JWT.require(algorithm).withIssuer(issuer).build().verify(token);
      return decodedJWT.getClaim("Username").asString();

     }


}
