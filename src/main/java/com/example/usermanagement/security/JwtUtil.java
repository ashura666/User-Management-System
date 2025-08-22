package com.example.usermanagement.security;

import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;

@Component
public class JwtUtil {
	 @Value("${app.jwt.secret}")
	    private String secret;

	    @Value("${app.jwt.expiration-ms}")
	    private long expirationMs;

	    private SecretKey getKey() {
	        byte[] keyBytes = Decoders.BASE64.decode(secret);
	        return Keys.hmacShaKeyFor(keyBytes);
	    }

	    public String extractUsername(String token) {
	        return extractClaim(token, Claims::getSubject);
	    }

	    public Date extractExpiration(String token) {
	        return extractClaim(token, Claims::getExpiration);
	    }

	    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
	        Claims claims = Jwts.parserBuilder()
	                .setSigningKey(getKey())
	                .build()
	                .parseClaimsJws(token)
	                .getBody();
	        return resolver.apply(claims);
	    }

	    public boolean isTokenValid(String token, UserDetails userDetails) {
	        final String username = extractUsername(token);
	        return username.equals(userDetails.getUsername()) && extractExpiration(token).after(new Date());
	    }

	    public String generateToken(UserDetails userDetails) {
	        Date now = new Date();
	        Date expiry = new Date(now.getTime() + expirationMs);
	        return Jwts.builder()
	                .setSubject(userDetails.getUsername())
	                .setIssuedAt(now)
	                .setExpiration(expiry)
	                .signWith(getKey())
	                .compact();
	    }
}
