package com.example.userservice.exception;

import java.util.HashMap;
import java.util.Map;

public abstract class TopException  extends RuntimeException{

    public final Map<String , String>  validation = new HashMap<>();

    public  TopException(String message) {super(message);}

    public  TopException(String message , Throwable cause) {super(message, cause);}

    public abstract int getStatusCode();

    public void addValidation(String fieldName , String message) {validation.put(fieldName ,message);}
}
