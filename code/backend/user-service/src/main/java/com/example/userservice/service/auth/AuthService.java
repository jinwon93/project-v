package com.example.userservice.service.auth;


import com.example.userservice.security.jwt.dto.RegenerateTokenDto;
import com.example.userservice.dto.user.request.RequestSignDto;
import com.example.userservice.security.jwt.dto.TokenDto;

public interface AuthService {

    Long signUp(RequestSignDto requestSignDto);


    TokenDto regenerateToken(RegenerateTokenDto regenerateTokenDto);

    void logout(TokenDto tokenDto);
}
