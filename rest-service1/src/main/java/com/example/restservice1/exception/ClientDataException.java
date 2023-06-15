package com.example.restservice1.exception;

public class ClientDataException extends  RuntimeException{
    public ClientDataException(String message){
        super(message);
    }
}
