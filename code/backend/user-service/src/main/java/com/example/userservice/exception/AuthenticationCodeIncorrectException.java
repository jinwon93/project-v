package com.example.userservice.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AuthenticationCodeIncorrectException extends RuntimeException{

    private static final String DEFAULT_MESSAGE = "The authentication code provided is incorrect.";
    private final HttpStatus status;


    public AuthenticationCodeIncorrectException(String message) {
        super(message);
        this.status = HttpStatus.CONFLICT;
    }

    public AuthenticationCodeIncorrectException(){
        super(DEFAULT_MESSAGE);
        this.status = HttpStatus.CONFLICT;
    }
}
