package com.example.userservice.controller;

import com.example.userservice.dto.email.AuthenticationEmailRequest;
import com.example.userservice.dto.email.EmailCheckRequest;
import com.example.userservice.dto.user.request.RequestSignUpDto;
import com.example.userservice.security.jwt.dto.RegenerateTokenDto;
import com.example.userservice.security.jwt.dto.TokenDto;
import com.example.userservice.service.auth.AuthService;
import jakarta.persistence.PreUpdate;
import jakarta.validation.Valid;
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


    @PostMapping("/signUp")
    public ResponseEntity<Long> signUp(@Valid @RequestBody RequestSignUpDto requestSignUpDto){
        return  ResponseEntity.status(HttpStatus.CREATED).body(authService.signUp(requestSignUpDto));
    }

    @PostMapping("/user/logout")
    public ResponseEntity<Void> logout(@RequestBody TokenDto tokenDto){
        authService.logout(tokenDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @PostMapping("/mail/send")
    public ResponseEntity<Void> sendAuthenticationEmail(@RequestBody AuthenticationEmailRequest authenticationEmailRequest){
        authService.sendAuthenticationEmail(authenticationEmailRequest.getEmail());
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @PostMapping("/mail/check")
    public ResponseEntity<Void> verifyAuthentication(@RequestBody EmailCheckRequest emailCheckRequest){
        authService.verifyAuthenticationCode(emailCheckRequest.getEmail() , emailCheckRequest.getCode());
        return ResponseEntity.status(HttpStatus.OK).build();

    }
}
