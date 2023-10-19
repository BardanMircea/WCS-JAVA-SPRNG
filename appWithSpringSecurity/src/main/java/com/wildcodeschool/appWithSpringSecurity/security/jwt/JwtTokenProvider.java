package com.wildcodeschool.appWithSpringSecurity.jwt;

import com.wildcodeschool.appWithSpringSecurity.entity.Utilisateur;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.expiration}")
    private int jwtExpirationInMs;

    private String generateSecret(){
        // TODO : generate Secret

        return "mySecret";
    }

    public String generateToken(Authentication authentication) {
        // Generate a JWT token with claims, expiration, and secret key
        return Jwts.builder()
                .setSubject(((Utilisateur) authentication.getPrincipal()).getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationInMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Token is invalid");
        }
    }

}
