package com.payments.Service;

import com.payments.Entity.Customer;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {


    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor("kckjijwnconkxowlmwlqjqwjqwjdklqwdqkw lqww dlqw qw kqw kqw lqmwqwlkklnalkxankln".getBytes());

    public String generateToken(Customer customer) {
        return Jwts.builder()
                .setSubject(String.valueOf(customer.getId()))
                .claim("accountId", customer.getAccount().getAccountId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

}

