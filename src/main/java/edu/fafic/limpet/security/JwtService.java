package edu.fafic.limpet.security;

import edu.fafic.limpet.enums.Authority;
import edu.fafic.limpet.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiration}")
    private long expirationMillis;

    private SecretKey secretKey;

    private JwtParser parser;

    @PostConstruct
    public void init() {
        byte[] decodedKey = Base64.getDecoder().decode(secret);
        secretKey = Keys.hmacShaKeyFor(decodedKey);
        parser = Jwts.parser().require("iss", issuer).verifyWith(secretKey).build();
    }

    public String generateToken(User subject, Map<String, Object> claims) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + expirationMillis);

        claims.put("Authorities", subject.getAuthorities());

        return Jwts.builder()
                .subject(subject.getEmail())
                .claims(claims)
                .issuer(issuer)
                .issuedAt(now)
                .expiration(expiry)
                .signWith(secretKey)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            parser.parseSignedClaims(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public String extractUsername(String token) {
        Claims claims = parser.parseSignedClaims(token).getPayload();
        return claims.getSubject();
    }

    public List<? extends GrantedAuthority> extractAuthorities(String token) {
        Claims claims = parser.parseSignedClaims(token).getPayload();
        List<?> authorities = claims.get("Authorities", List.class);

        if (authorities == null) {
            return List.of();
        }

        return authorities.stream()
                .map(String.class::cast)
                .map(Authority::valueOf)
                .map(Authority::toGrantedAuthority)
                .toList();
    }

    public Claims extractAllClaims(String token) {
        return parser.parseSignedClaims(token).getPayload();
    }
}
