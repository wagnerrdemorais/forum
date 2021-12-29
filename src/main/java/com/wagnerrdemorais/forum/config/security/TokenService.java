package com.wagnerrdemorais.forum.config.security;

import com.wagnerrdemorais.forum.modelo.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${forum.jwt.expiration}")
    private String expiration;

    @Value("${forum.jwt.secret}")
    private String secret;

    public String generateToken(Authentication authentication) {
        Usuario principal = (Usuario) authentication.getPrincipal();
        Date date = new Date();
        Date expiracao = new Date(date.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
            .setIssuer("FORUM API")
            .setSubject(principal.getId().toString())
            .setIssuedAt(date)
            .setExpiration(expiracao)
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact();
    }
}
