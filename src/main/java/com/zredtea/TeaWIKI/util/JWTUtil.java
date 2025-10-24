package com.zredtea.TeaWIKI.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JWTUtil {
    @Value("${jwt.secret:defaultSecretKey}")
    private String secretKey;

    @Getter
    @Value("${jwt.expiration:86400}")
    private Long expiration;

    private final RedisTemplate<String, Object> redisTemplate;

    public JWTUtil(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public String generateToken(Integer userId, String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration * 1000);

        String token = Jwts.builder()
                .setSubject(userId.toString())
                .claim("username",username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        String redisKey = "USER_TOKEN" + userId;
        redisTemplate.opsForValue().set(redisKey, token, expiration, TimeUnit.SECONDS);

        return token;
    }

    public Integer getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        return Integer.parseInt(claims.getSubject());
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        return claims.get("username", String.class);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);

            String redisKey = "USER_TOKEN" + getUserIdFromToken(token);
            String redisToken =  (String) redisTemplate.opsForValue().get(redisKey);

            return token.equals(redisToken);
        } catch (Exception e){
            return false;
        }
    }

    public void invalidateToken(String token) {
        try {
            String redisKey = "USER_TOKEN" + getUserIdFromToken(token);
            redisTemplate.delete(redisKey);
        } catch (Exception e) {

        }
    }
}
