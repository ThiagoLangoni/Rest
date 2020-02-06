package br.com.fiap.livros.security;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expire}")
    private int expire;

    public String generateToken(String userName) {
        // @formatter: off
        Date dataCriacao = Date.from(LocalDateTime.now().toInstant(OffsetDateTime.now().getOffset()));
        Date dataExpiracao = Date.from(LocalDateTime.now().plusMinutes(expire).toInstant(OffsetDateTime.now().getOffset()));

        return Jwts.builder()
                    .addClaims(new HashMap<>())
                    .setSubject(userName)
                    .setIssuedAt(dataCriacao)
                    .setExpiration(dataExpiracao)
                    .signWith(SignatureAlgorithm.ES512, secret)
                    .compact();
        // @formatter: on
    }

    public String getUserName(String token) {
        //@formatter: off
        Claims claims = Jwts.parser()
                           .setSigningKey(secret)
                           .parseClaimsJws(token.replace("Bearer ", ""))
                           .getBody();

        String subject = claims.getSubject();

        return subject;

        //@formatter: on
    }






}