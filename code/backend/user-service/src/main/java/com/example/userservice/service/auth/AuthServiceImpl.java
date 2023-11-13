package com.example.userservice.service.auth;


import com.example.userservice.dto.user.request.RequestSignUpDto;
import com.example.userservice.entity.User;
import com.example.userservice.exception.AuthenticationCodeIncorrectException;
import com.example.userservice.exception.CustomException;
import com.example.userservice.exception.EmailAlreadyExistsException;
import com.example.userservice.exception.PhoneNumberAlreadyExistsException;
import com.example.userservice.file.FileStore;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.security.jwt.JwtTokenProvider;
import com.example.userservice.security.jwt.dto.RegenerateTokenDto;
import com.example.userservice.security.jwt.dto.TokenDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository repository;
    private final Environment env;

    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private RedisTemplate<String , String> redisTemplate;

    private final JavaMailSender javaMailSender;
    private final FileStore fileStore;


    @Transactional
    @Override
    public TokenDto regenerateToken(RegenerateTokenDto refreshTokenDto){
        String refreshToken = refreshTokenDto.getRefreshToken();

        if (!jwtTokenProvider.validateRefreshToken(refreshToken)){
            throw  new CustomException("Invalid refresh token" , HttpStatus.BAD_REQUEST);
        }

        Authentication authentication =jwtTokenProvider.getAuthenticationByRefreshToken(refreshToken);


        String refreshTokenValue = redisTemplate.opsForValue().get("refreshToken:"+ authentication.getName());
        if (!refreshTokenValue.equals(refreshToken)){
            throw  new CustomException("RefreshToken not match" , HttpStatus.BAD_REQUEST);
        }

        String neRefreshToken = jwtTokenProvider.generateRefreshToken(authentication);

        TokenDto tokenDto = new TokenDto(
                jwtTokenProvider.generateAccessToken(authentication),
                neRefreshToken
        );

        redisTemplate.opsForValue().set(
                "refreshToken"+ authentication.getName(),
                neRefreshToken,
                Long.parseLong(env.getProperty("refresh token exporation time")),
                TimeUnit.MICROSECONDS
        );

        return tokenDto;

    }

    @Override
    @Transactional
    public Long signUp(RequestSignUpDto requestSignDto) {
        if (repository.existsByEmail(requestSignDto.getEmail())){
            throw new EmailAlreadyExistsException();
        }else if (repository.existsByPhoneNumber(requestSignDto.getPhoneNumber())){
            throw new PhoneNumberAlreadyExistsException();
        }

        User user = requestSignDto.toEntity(passwordEncoder.encode(requestSignDto.getPassword()));

        return repository.save(user).getId();
    }

    @Override
    public void logout(TokenDto tokenDto) {
        if (!jwtTokenProvider.validateAccessToken(tokenDto.getAccessToken())){
            throw new IllegalArgumentException("Invalid token!");
        }

        Authentication authentication = jwtTokenProvider.getAuthenticationByAccessToken(tokenDto.getAccessToken());

        if (redisTemplate.opsForValue().get(authentication.getName()) != null){
            redisTemplate.delete(authentication.getName());
        }

        Long expiration = jwtTokenProvider.getAccessTokenExpiration(tokenDto.getAccessToken());
        redisTemplate.opsForValue().set(tokenDto.getAccessToken(), "logout" , expiration * 1000 , TimeUnit.MICROSECONDS);
    }

    @Override
    public void sendAuthenticationEmail(String email) {

    }

    @Override
    public void verifyAuthenticationCode(String email, String code) {
        String key = "emailCode" + email;
        String keyCode = redisTemplate.opsForValue().get(key);

        if (keyCode == null || keyCode.isEmpty() || !keyCode.equals(code)) {
            throw  new AuthenticationCodeIncorrectException();
        }

        redisTemplate.delete(key);
    }
}
