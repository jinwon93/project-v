package com.example.userservice.security.jwt;


import com.example.userservice.entity.User;
import com.example.userservice.exception.CustomException;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserDetailService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.ArrayList;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final Environment env;
    private final UserRepository repository;
    private final UserDetailService userDetailService;

    public String generateAccessToken(Authentication authentication){
        Key securityKey = Keys.hmacShaKeyFor(env.getProperty("access_token_security").getBytes(StandardCharsets.UTF_8));
        User user = repository.findByEmail(authentication.getName());
        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("userId" , user.getId())
                .setExpiration(new Date(System.currentTimeMillis() +
                        Long.parseLong(env.getProperty("access_token_expiration_time"))))
                .signWith(securityKey , SignatureAlgorithm.HS512)
                .compact();
        return accessToken;
    }


    public String generateRefreshToken(Authentication authentication) {
        Key securityKey = Keys.hmacShaKeyFor(env.getProperty("refresh_token.secret").getBytes(StandardCharsets.UTF_8));

        String refreshToken = Jwts.builder()
                .setSubject(authentication.getName())
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(env.getProperty("refresh_token.expiration_time"))))
                .signWith(securityKey , SignatureAlgorithm.HS512)
                .compact();
        return refreshToken;
    }

    public Authentication getAuthenticationByAccessToken(String accessToken){
        Key securityKey = Keys.hmacShaKeyFor(env.getProperty("access_token.secret").getBytes(StandardCharsets.UTF_8));

        JwtParserBuilder jwtParserBuilder = Jwts.parserBuilder().setSigningKey(securityKey);

        Claims claims = jwtParserBuilder
                .build()
                .parseClaimsJws(accessToken)
                .getBody();
        //User ==> security.core // 추후 entity 변경예정
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(claims.getSubject() , "" ,new ArrayList<>());

        return new UsernamePasswordAuthenticationToken(userDetails , "" , userDetails.getAuthorities());
    }

    public Authentication getAuthenticationByRefreshToken(String refreshToken){
        Key securityKey = Keys.hmacShaKeyFor(env.getProperty("refresh_token.secret").getBytes(StandardCharsets.UTF_8));

        JwtParserBuilder jwtParserBuilder = Jwts.parserBuilder().setSigningKey(securityKey);

        Claims claims = jwtParserBuilder
                .build()
                .parseClaimsJws(refreshToken)
                .getBody();

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(claims.getSubject(), "" ,new ArrayList<>());
        return  new UsernamePasswordAuthenticationToken(userDetails , "",userDetails.getAuthorities());
    }


    public boolean validateAccessToken(String accessToken) {
        try {
            Key secretKey = Keys.hmacShaKeyFor(env.getProperty("access_token_secret").getBytes(StandardCharsets.UTF_8));

            JwtParserBuilder jwtParserBuilder = Jwts.parserBuilder().setSigningKey(secretKey);

            jwtParserBuilder.build()
                    .parseClaimsJws(accessToken);
            return  true;
        }catch (JwtException exception) {
            throw new CustomException("Error on Access Token", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public boolean validateRefreshToken(String refreshToken) {
        try {
            Key secretKey = Keys.hmacShaKeyFor(env.getProperty("refresh_token_secret").getBytes(StandardCharsets.UTF_8));

            JwtParserBuilder jwtParserBuilder = Jwts.parserBuilder().setSigningKey(secretKey);

            jwtParserBuilder.build()
                    .parseClaimsJws(refreshToken);
            return  true;
        }catch (JwtException exception) {
            throw new CustomException("Error on Refresh Token", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public Long getAccessTokenExpiration(String accessToken) {
        try {
            Key secretKey = Keys.hmacShaKeyFor(env.getProperty("access_token.secret").getBytes(StandardCharsets.UTF_8));

            // JWT 토큰을 파싱하기 위한 빌더 객체 생성 및 토큰에 사용될 서명 키 설정
            JwtParserBuilder jwtParserBuilder = Jwts.parserBuilder().setSigningKey(secretKey);
            Long expirationTime = jwtParserBuilder
                    .build()
                    // 파싱 대상 JWT 토큰을 Jws(JWT Signature를 포함하는 객체) 객체로 파싱
                    .parseClaimsJws(accessToken)
                    .getBody()
                    .getExpiration()
                    .getTime();
            Long currentTime = System.currentTimeMillis();
            Long remainingTime = expirationTime - currentTime;
            return remainingTime;
        } catch (JwtException e) {
            // MalformedJwtException | ExpiredJwtException | IllegalArgumentException
            throw new CustomException("Error on Refresh Token", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


