package com.cepardov.challenge.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.LocalDateTime;
import java.util.Date;

import static java.time.ZoneOffset.UTC;

public class JwtTokenProvider {

  public String revocateToken(String username) {
    Claims claims = Jwts.claims().setSubject(username);
    Date expiration = Date.from(LocalDateTime.now(UTC).plusMinutes(5).toInstant(UTC));
    return Jwts.builder()
        .setClaims(claims)
        .setExpiration(expiration)
        .signWith(SignatureAlgorithm.HS256, "secretKey")
        .compact();
  }
}
