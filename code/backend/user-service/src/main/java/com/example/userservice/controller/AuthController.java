package com.example.userservice.controller;

import com.example.userservice.security.jwt.dto.RegenerateTokenDto;
import com.example.userservice.security.jwt.dto.TokenDto;
import com.example.userservice.service.auth.AuthService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/regenerateToken")
    public ResponseEntity<TokenDto> regenerateToken(@RequestBody RegenerateTokenDto regenerateTokenDto){
        return ResponseEntity.status(HttpStatus.OK).body(authService.regenerateToken(regenerateTokenDto));
    }


}
