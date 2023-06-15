package com.example.restservice1.exception;

public class OutstandingRIDException extends  RuntimeException{
    public OutstandingRIDException(String message){
        super(message);
    }
}
