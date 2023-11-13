package com.example.userservice.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class PhoneNumberAlreadyExistsException  extends  RuntimeException{

    private static final  String DEFAULT_MESSAGE = "Phone Number is already in use";
    private final HttpStatus status;


    public PhoneNumberAlreadyExistsException(String message) {
        super(message);
        this.status = HttpStatus.CONFLICT;
    }

    public PhoneNumberAlreadyExistsException(){
        super(DEFAULT_MESSAGE);
        this.status = HttpStatus.CONFLICT;
    }
}
