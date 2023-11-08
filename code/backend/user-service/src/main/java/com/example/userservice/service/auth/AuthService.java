package com.example.userservice.service.auth;


import com.example.userservice.dto.user.request.RequestSignUpDto;
import com.example.userservice.security.jwt.dto.RegenerateTokenDto;
import com.example.userservice.dto.user.request.RequestSignDto;
import com.example.userservice.security.jwt.dto.TokenDto;

public interface AuthService {

    Long signUp(RequestSignUpDto requestSignDto);


    TokenDto regenerateToken(RegenerateTokenDto regenerateTokenDto);

    void logout(TokenDto tokenDto);

    void sendAuthenticationEmail(String email);
    void verifyAuthenticationCode(String email, String code);
}
