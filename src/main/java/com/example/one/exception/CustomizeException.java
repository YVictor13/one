package com.example.one.exception;

public class CustomizeException  extends RuntimeException{
    private String message;

    public CustomizeException(String message){
        this.message=message;
    }

    public String getMessage(){
        return message;
    }

}
