package com.example.userservice.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class EmailAlreadyExistsException extends  RuntimeException {

    private static final  String DEFAULT_MESSAGE = "Email address is already in use";
    private final HttpStatus status;


    public EmailAlreadyExistsException(String message) {
        super(message);
        this.status = HttpStatus.CONFLICT;
    }

    public EmailAlreadyExistsException(){
        super(DEFAULT_MESSAGE);
        this.status = HttpStatus.CONFLICT;
    }
}
