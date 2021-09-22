package com.example.demo.exception;

public class statusException extends RuntimeException {
    private String message;

    public statusException(String message){
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }


}
