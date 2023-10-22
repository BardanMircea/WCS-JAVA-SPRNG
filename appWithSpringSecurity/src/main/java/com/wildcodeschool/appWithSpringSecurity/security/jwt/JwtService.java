package com.wildcodeschool.appWithSpringSecurity.security.jwt;

import com.wildcodeschool.appWithSpringSecurity.entity.Role;
import com.wildcodeschool.appWithSpringSecurity.enums.RoleEnum;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;

@Component
public class JwtService {

    @Value("${app.jwt.secret}")
    private String myJwtSecret;

    @Value("${app.jwt.expiration}")
    private int jwtExpirationInMs;

    public String generateToken(String username, Set<Role> roles) {
        return Jwts.builder()
                .subject(username)
                .claim("user", roles.contains(new Role(RoleEnum.USER)))
                .claim("admin", roles.contains(new Role(RoleEnum.ADMIN)))
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + jwtExpirationInMs))
                .signWith(Keys.hmacShaKeyFor(myJwtSecret.getBytes()))
                .compact();
    }

    public String getSub(String token){
        return Jwts
                .parser()
                .verifyWith(Keys.hmacShaKeyFor(myJwtSecret.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public Date getExpiration(String token){
        return Jwts
                .parser()
                .verifyWith(Keys.hmacShaKeyFor(myJwtSecret.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
    }
}
