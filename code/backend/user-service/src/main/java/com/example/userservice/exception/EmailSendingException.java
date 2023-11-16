package com.example.userservice.exception;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class EmailSendingException  extends  RuntimeException{

    private final HttpStatus status;

    public EmailSendingException(String message){
       super(message);
       this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public HttpStatus getHttpStatus(){
        return status;
    }
}
